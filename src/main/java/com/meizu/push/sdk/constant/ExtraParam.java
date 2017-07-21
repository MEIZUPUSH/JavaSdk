package com.meizu.push.sdk.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * extra 扩展参数
 */
public enum ExtraParam {

    CALLBACK("callback", "回执接口"),
    CALLBACK_PARAM("callback.param", "回执参数"),
    CALLBACK_TYPE("callback.type", "回执类型"); //参考枚举 CallBackType

    private String key;
    private String value;

    ExtraParam(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }


    private static final Map<String, ExtraParam> ENUMMAP = new HashMap<String, ExtraParam>();

    static {
        for (ExtraParam extraParam : ExtraParam.values()) {
            ENUMMAP.put(extraParam.getKey(), extraParam);
        }
    }

    public static ExtraParam fromValue(String key) {
        return ENUMMAP.get(key);
    }

}
