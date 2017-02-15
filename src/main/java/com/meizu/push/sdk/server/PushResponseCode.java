package com.meizu.push.sdk.server;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by wangxinguo on 2016-8-24.
 */
public class PushResponseCode {
    private int value;
    private String description;
    private static HashMap<Integer, PushResponseCode> intPushResponseCodeMap = createIntegerPushResponseCodeMapping();
    public static PushResponseCode RSP_OK = valueOf(200, "成功(rsp_ok)");
    public static PushResponseCode RSP_NO_AUT = valueOf(201, "没有权限,服务器主动拒绝(rsp_no_aut)");
    public static PushResponseCode RSP_DB_ERROR = valueOf(501, "推送消息报错失败(rsp_db_error)");
    public static PushResponseCode RSP_INTERNAL_ERROR = valueOf(513, "推送消息报错失败(rsp_internal_error)");
    public static PushResponseCode RSP_SPEED_LIMIT = valueOf(518, "推送超过配置的速率(rsp_speed_limit)");
    public static PushResponseCode RSP_OVERFLOW = valueOf(519, "服务过载(rsp_overflow)");
    public static PushResponseCode RSP_REPEATED = valueOf(520, "消息折叠,短时间内同一设备同一消息收到多次(rsp_repeated)");
    public static PushResponseCode RSP_UNSUBSCRIBE_PUSHID = valueOf(110002, "pushId未订阅(un subscribe pushId)");
    public static PushResponseCode RSP_INVALID_PUSHID = valueOf(110003, "pushId非法(invalid pushId)");
    public static PushResponseCode RSP_UNSUBSCRIBE_ALIAS = valueOf(110005, "别名未订阅(un subscribe alias)");

    private static HashMap<Integer, PushResponseCode> createIntegerPushResponseCodeMapping() {
        HashMap result = new HashMap();
        return result;
    }

    public static Collection<PushResponseCode> getAllPushResponseCodes() {
        return intPushResponseCodeMap.values();
    }

    private PushResponseCode(int value) {
        this.value = value;
    }

    private PushResponseCode(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return "PushResponseCode{" +
                "value=" + value +
                ", description='" + description + '\'' +
                '}';
    }

    public String getFullDescription() {
        return this.getName() + "," + this.value + "," + this.description;
    }

    public String getName() {
        return this.description;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static PushResponseCode valueOf(int value) {
        return (PushResponseCode) intPushResponseCodeMap.get(Integer.valueOf(value));
    }

    public static PushResponseCode valueOf(int value, PushResponseCode defaultIfMissing) {
        PushResponseCode code = (PushResponseCode) intPushResponseCodeMap.get(Integer.valueOf(value));
        return code == null ? defaultIfMissing : code;
    }

    public static PushResponseCode valueOf(Integer code, String reason) {
        PushResponseCode result = (PushResponseCode) intPushResponseCodeMap.get(code);
        if (result == null) {
            result = new PushResponseCode(code.intValue(), reason);
            intPushResponseCodeMap.put(code, result);
        }

        return result;
    }
}
