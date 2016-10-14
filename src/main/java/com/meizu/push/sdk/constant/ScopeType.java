package com.meizu.push.sdk.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 标签推送集合类型
 */
public enum ScopeType {

    UNION(0, "并集"), INTERSECTION(1, "交集");

    private Integer desc;
    private String value;

    ScopeType(Integer desc, String value) {
        this.desc = desc;
        this.value = value;
    }


    public Integer getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }


    private static final Map<Integer, ScopeType> ENUMMAP = new HashMap<Integer, ScopeType>();

    static {
        for (ScopeType userType : ScopeType.values()) {
            ENUMMAP.put(userType.getDesc(), userType);
        }
    }

    public static ScopeType fromValue(Integer desc) {
        return ENUMMAP.get(desc);
    }
}
