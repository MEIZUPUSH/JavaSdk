package com.meizu.push.sdk.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 回执类型
 */
public enum CallBackType {
    RECEIVE("1", "送达回执"),
    CLICK("2", "点击回执"),
    RECEIVE_CLICK("3", "送达与点击回执");

    private String key;
    private String value;

    CallBackType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, CallBackType> ENUMMAP = new HashMap<String, CallBackType>();

    static {
        for (CallBackType callBackType : CallBackType.values()) {
            ENUMMAP.put(callBackType.getKey(), callBackType);
        }
    }

    public static CallBackType fromValue(int key) {
        return ENUMMAP.get(key);
    }
}
