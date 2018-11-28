package com.meizu.push.sdk.server.model.push;

import java.io.Serializable;

/**
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午7:23:15
 */
public abstract class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台注册应用ID
     */
    private Long appId;

    /**
     * 多包名列表
     */
    private String[] restrictedPackageNames;


    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String[] getRestrictedPackageNames() {
        return restrictedPackageNames;
    }

    public void setRestrictedPackageNames(String[] restrictedPackageNames) {
        this.restrictedPackageNames = restrictedPackageNames;
    }
}
