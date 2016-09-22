package com.meizu.push.sdk.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 点击动作设置
 * 
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午6:26:12
 */
public class ClickTypeInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 点击动作 (0,"打开应用"),(1,"打开应用页面"),(2,"打开H5地址"),(3, "应用客户端自定义")
	 */
	private int clickType;
	/**
	 * H5页面地址, 【clickType为打开H5地址时，必填 长度限制1000】
	 */
	private String url = "";
	/**
	 * 参数 【JSON格式】【非必填】
	 */
	private JSONObject parameters;
	/**
	 * 应用页面地址 【clickType为打开应用页面时，必填 长度限制1000】
	 */
	private String activity = "";


    public ClickTypeInfo() {
    }

    public ClickTypeInfo(int clickType, String url, JSONObject parameters, String activity) {
        this.clickType = clickType;
        this.url = url;
        this.parameters = parameters;
        this.activity = activity;
    }

    public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public int getClickType() {
		return clickType;
	}

	public void setClickType(int clickType) {
		this.clickType = clickType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public JSONObject getParameters() {
		return parameters;
	}

	public void setParameters(JSONObject parameters) {
		this.parameters = parameters;
	}

    @Override
	public String toString() {
		return "ClickTypeInfo [clickType=" + clickType + ", url=" + url + ", parameters=" + parameters + ", activity=" + activity + "]";
	}

}
