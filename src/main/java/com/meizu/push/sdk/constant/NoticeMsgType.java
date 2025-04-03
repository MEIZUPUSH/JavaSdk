package com.meizu.push.sdk.constant;


/**
 * 通知消息分类：公信OR私信
 */

public enum NoticeMsgType {
    /**
     *
     */
    PUB_MSG(0, "公共消息"),
    PERSONAL_MSG(1, "个人消息") ;

    private Integer type;
    private String desc;

    NoticeMsgType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static NoticeMsgType getBy(Integer type) {
        if(type!=null && 1 == type) {
            return PERSONAL_MSG;
        }

        return PUB_MSG;
    }

    public Integer getType() {
        return type;
    }
}
