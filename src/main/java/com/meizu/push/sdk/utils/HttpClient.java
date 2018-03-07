package com.meizu.push.sdk.utils;


import com.alibaba.fastjson.JSONObject;
import com.meizu.push.sdk.constant.SystemConstants;
import com.meizu.push.sdk.exception.InvalidRequestException;
import com.meizu.push.sdk.server.model.HttpResult;

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

import static com.alibaba.fastjson.JSON.parseObject;

public class HttpClient {
    protected static final Logger logger = Logging.getLogger(HttpClient.class.getName());

    private static final String JDK_VERSION = System.getProperty("java.version", "UNKNOWN");
    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static final String SIGN = "sign";

    protected final Random random = new Random();

    private static String HOST_NAME;
    private static String LOCAL_IP;

    protected final String appSecret;
    protected final long appId;
    /**
     * 是否使用https接口调用：true 使用https连接，false使用http连接；默认使用http
     */
    protected final boolean useSSL;

    public HttpClient(String appSecret) {
        this(0, appSecret);
    }

    public HttpClient(String appSecret, boolean useSSL) {
        this(0, appSecret, useSSL);
    }

    public HttpClient(long appId, String appSecret) {
        this(appId, appSecret, Boolean.FALSE);
    }

    public HttpClient(long appId, String appSecret, boolean useSSL) {
        nonNull(appSecret);
        this.appId = appId;
        this.appSecret = appSecret;
        this.useSSL = useSSL;
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
            logger.log(Level.FINEST, "ignoreSsl error", e);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.FINEST, "ignoreSsl error", e);
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

    private static void close(Closeable closeable) throws IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            logger.log(Level.FINEST, "IOException closing stream", e);
        }
    }

    protected static StringBuilder newBodyWithArrayParameters(String name, List<String> parameters) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.size(); ++i) {
            if (i == 0) {
                sb.append(nonNull(name)).append("=").append(URLEncoder.encode(nonNull(parameters.get(i)), SystemConstants.CHAR_SET));
            } else {
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
            if (newLine != null) {
                content.append(newLine).append('\n');
            }
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
            if (stream != null) {
                close(stream);
            }
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
        return MD5Util.MD5Encode(basestring.toString(), SystemConstants.CHAR_SET);
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
     * 超时以及服务不可用异常返回null
     *
     * @param useSSL 是否启用https
     * @param url
     * @param body
     * @return
     * @throws IOException
     */
    protected HttpResult post(boolean useSSL, String url, String body) throws IOException {
        if (useSSL) {
            if (url.startsWith("http://")) {
                url = url.replace("http://", "https://");
            }
        }

        String bodyParam = body;
        if (StringUtils.isNotBlank(bodyParam) && bodyParam.charAt(0) == 38) {
            bodyParam = body.toString().substring(1);
        }

        HttpURLConnection conn;
        int status;
        try {
            logger.fine("post to: " + url);
            conn = this.doPost(url, bodyParam);
            status = conn.getResponseCode();
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException posting to push", e);
            return null;
        }
       /*
        5xx（服务器错误）
        这些状态代码表示，服务器在尝试处理请求时发生内部错误。这些错误可能是服务器本身的错误，而不是请求出错。
        代码 说明
        500（服务器内部错误） 服务器遇到错误，无法完成请求。
        501（尚未实施） 服务器不具备完成请求的功能。例如，当服务器无法识别请求方法时，服务器可能会返回此代码。
        502（错误网关） 服务器作为网关或代理，从上游服务器收到了无效的响应。
        503（服务不可用） 目前无法使用服务器（由于超载或进行停机维护）。通常，这只是一种暂时的状态。
        504（网关超时） 服务器作为网关或代理，未及时从上游服务器接收请求。
        505（HTTP 版本不受支持） 服务器不支持请求中所使用的 HTTP 协议版本。
       */
        if (status / 100 == 5) {
            logger.fine("push service is unavailable (status " + status + ")");
            return null;
        } else {
            String responseBody;
            if (status != 200) {
                try {
                    responseBody = getAndClose(conn.getErrorStream());
                    logger.finest("Plain post error response: " + responseBody);
                } catch (IOException e) {
                    responseBody = "N/A";
                    logger.log(Level.FINE, "Exception reading response: ", e);
                }
                throw new InvalidRequestException(status, responseBody);
            } else {
                try {
                    responseBody = getAndClose(conn.getInputStream());
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Exception reading response: ", e);
                    return null;
                }
                try {
                    JSONObject json = parseObject(responseBody);
                    return (new HttpResult.Builder()).fromJson(json);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Exception parsing response: ", e);
                    throw new IOException("Invalid response from push: " + responseBody);
                }
            }
        }
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