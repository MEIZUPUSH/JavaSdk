package com.meizu.push.sdk.server.model.push;

import java.io.Serializable;

/**
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午7:23:15
 */
public abstract class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 平台注册应用ID
     */
    private Long appId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
