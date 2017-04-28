package com.meizu.push.sdk.server.model.sub;

/**
 * 订阅别名信息
 *
 * @author wangxinguo <wangxinguo@meizu.com>
 * @date 2016-9-3
 */
public class AliasInfo {

    /**
     * 订阅pushId
     */
    private String pushId;
    /**
     * 订阅别名
     */
    private String alias;

    public String getPushId() {
        return pushId;
    }

    public String getAlias() {
        return alias;
    }

    public AliasInfo(String pushId, String alias) {
        this.pushId = pushId;
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "AliasInfo{" +
                "pushId='" + pushId + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
