package com.meizu.push.sdk.server;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by wangxinguo on 2016-8-21.
 */
public final class HttpResult implements Serializable {

    private static final long serialVersionUID = 8030699726843716781L;
    private final String code;
    private final String message;
    private final String value;
    private final String msgId;


    private HttpResult(HttpResult.Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.value = builder.value;
        this.msgId = builder.msgId;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getValue() {
        return value;
    }

    public String getMsgId() {
        return msgId;
    }

    public static final class Builder {
        private String code;
        private String message;
        private String value;
        private String msgId;

        public Builder() {
        }

        public HttpResult fromJson(JSONObject json) {
            this.code = json.getString("code");
            this.message = json.getString("message");
            this.value = json.getString("value");
            if (json.containsKey("msgId")) {
                this.msgId = json.getString("msgId");
            }
            return this.build();
        }

        public HttpResult build() {
            return new HttpResult(this);
        }
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
