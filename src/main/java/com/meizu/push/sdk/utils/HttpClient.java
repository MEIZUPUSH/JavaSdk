package com.meizu.push.sdk.utils;


import com.meizu.push.sdk.constant.SystemConstants;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpClient {
    protected static final Logger logger = Logger.getLogger(HttpClient.class.getName());

    private static final String JDK_VERSION = System.getProperty("java.version", "UNKNOWN");
    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static final String SIGN = "sign";

    protected final Random random = new Random();

    private static String HOST_NAME;
    private static String LOCAL_IP;

    protected final String appSecret;

    public HttpClient(String appSecret) {
        this.appSecret = appSecret;
        getLocalHostNameAndIp();
    }

    private static void getLocalHostNameAndIp() {
        try {
            HOST_NAME = InetAddress.getLocalHost().getHostName();
            LOCAL_IP = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
        }
    }

    protected HttpURLConnection doPost(String url, String body) throws IOException {
        return doPost(url, "application/x-www-form-urlencoded;charset=UTF-8", body);
    }

    public HttpURLConnection doGet(String url, String parameter) throws IOException {
        return doGet(url, "application/x-www-form-urlencoded;charset=UTF-8", parameter);
    }

    protected HttpURLConnection doPost(String url, String contentType, String body) throws IOException {
        if ((url == null) || (body == null)) {
            throw new IllegalArgumentException("arguments cannot be null");
        }

        //忽略ssl证书认证
        try {
            ignoreSsl();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        StringBuilder signBody = new StringBuilder(body);
        addParameter(signBody, SIGN, getSignature(str2Param(body), this.appSecret));

        logger.fine(new StringBuilder().append("Sending post to ").append(url).toString());
        logger.finest(new StringBuilder().append("post body: ").append(URLDecoder.decode(signBody.toString(), SystemConstants.CHAR_SET)).toString());
        HttpURLConnection conn = getConnection(url);
        prepareConnection(conn);

        byte[] bytes = signBody.toString().getBytes();

        conn.setConnectTimeout(20000);
        conn.setReadTimeout(20000);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setFixedLengthStreamingMode(bytes.length);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", contentType);
        OutputStream out = conn.getOutputStream();
        try {
            out.write(bytes);
        } finally {
            close(out);
        }
        return conn;
    }

    private Map<String, String> str2Param(String body) throws UnsupportedEncodingException {
        Map<String, String> paramMap = new HashMap<String, String>();
        String[] paramArray = body.split("&");
        for (String param : paramArray) {
            String[] argArray = param.split("=");
            if (argArray.length == 2) {
                paramMap.put(argArray[0], URLDecoder.decode(argArray[1], SystemConstants.CHAR_SET));
            } else if (argArray.length == 1) {
                paramMap.put(argArray[0], "");
            }
        }
        return paramMap;
    }

    protected HttpURLConnection doGet(String url, String contentType, String parameter) throws IOException {
        if ((url == null) || (parameter == null)) {
            throw new IllegalArgumentException("arguments cannot be null");
        }

        StringBuilder signParameter = new StringBuilder(parameter);
        addParameter(signParameter, SIGN, getSignature(str2Param(parameter), this.appSecret));

        logger.fine(new StringBuilder().append("Sending get to ").append(url).toString());
        logger.finest(new StringBuilder().append("get parameter: ").append(URLDecoder.decode(signParameter.toString(), SystemConstants.CHAR_SET)).toString());

        String fullUrl = new StringBuilder().append(url).append("?").append(signParameter).toString();

        HttpURLConnection conn = getConnection(fullUrl);
        prepareConnection(conn);
        conn.setConnectTimeout(20000);
        conn.setReadTimeout(20000);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", contentType);
        conn.getInputStream();
        return conn;
    }

    protected void prepareConnection(HttpURLConnection conn) {
        conn.setRequestProperty("F-PUSH-SDK-VERSION", SystemConstants.SDK_VERSION);
        conn.setRequestProperty("F-PUSH-JDK-VERSION", JDK_VERSION);
        conn.setRequestProperty("F-PUSH-OS", OS);
        if (HOST_NAME != null) {
            conn.setRequestProperty("F-PUSH-CLIENT-HOST", HOST_NAME);
        }
        if (LOCAL_IP != null) {
            conn.setRequestProperty("F-PUSH-CLIENT-IP", LOCAL_IP);
        }
    }

    protected static StringBuilder newBody(String name, String value) throws UnsupportedEncodingException {
        return new StringBuilder(nonNull(name)).append('=').append(URLEncoder.encode(nonNull(value), SystemConstants.CHAR_SET));
    }

    private static void close(Closeable closeable) {
        if (closeable == null)
            return;
        try {
            closeable.close();
        } catch (IOException e) {
            logger.log(Level.FINEST, "IOException closing stream", e);
        }
    }

    protected static StringBuilder newBodyWithArrayParameters(String name, List<String> parameters) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.size(); ++i) {
            if (i == 0)
                sb.append(nonNull(name)).append("=").append(URLEncoder.encode(nonNull(parameters.get(i)), SystemConstants.CHAR_SET));
            else {
                (nonNull(sb)).append('&').append(nonNull(name)).append('=').append(URLEncoder.encode(nonNull(parameters.get(i)), SystemConstants.CHAR_SET));
            }
        }
        if (parameters.size() == 0) {
            sb.append(name).append("=").append("");
        }
        return sb;
    }

    protected static void addParameter(StringBuilder body, String name, String value) throws UnsupportedEncodingException {
        nonNull(body).append('&').append(nonNull(name)).append('=').append(URLEncoder.encode(nonNull(value), SystemConstants.CHAR_SET));
    }

    protected HttpURLConnection getConnection(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        return conn;
    }

    protected static String getString(InputStream stream) throws IOException {
        String newLine;
        if (stream == null) {
            return "";
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder content = new StringBuilder();
        do {
            newLine = reader.readLine();
            if (newLine != null)
                content.append(newLine).append('\n');
        } while (newLine != null);
        if (content.length() > 0) {
            content.setLength(content.length() - 1);
        }
        return content.toString();
    }

    public static String getAndClose(InputStream stream) throws IOException {
        try {
            String str = getString(stream);
            return str;
        } finally {
            if (stream != null)
                close(stream);
        }
    }

    protected static <T> T nonNull(T argument) {
        if (argument == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        return argument;
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static String getSignature(Map<String, String> paramMap, String secret) {
        Map<String, String> sortedParams = new TreeMap<String, String>(paramMap);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            basestring.append(param.getKey()).append("=").append(param.getValue());
        }
        basestring.append(secret);
        return MD5Util.MD5Encode(basestring.toString());
    }

    private static void ignoreSsl() throws KeyManagementException, NoSuchAlgorithmException {
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    /**
     * @param
     * @throws
     * @method 信任所有的ssl调用
     * @author wangxinguo <wangxinguo@meizu.com>
     * @date 2016-9-1 15:50
     * @reurn
     */
    private static void trustAllHttpsCertificates() throws NoSuchAlgorithmException, KeyManagementException {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    /**
     * @param
     * @author wangxinguo <wangxinguo@meizu.com>
     * @method ssl管理类
     * @date 2016-9-1 15:49
     * @reurn
     * @throws
     */
    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }

}