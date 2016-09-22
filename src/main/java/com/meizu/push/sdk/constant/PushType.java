package com.meizu.push.sdk.constant;

import java.util.HashMap;
import java.util.Map;

public enum PushType {
	STATUSBAR(0,"通知"),DIRECT(1,"透传消息");


	
	private Integer desc;
	private String value;
	
    PushType(Integer desc, String value) {
		this.desc = desc;
		this.value = value;
	}

	
	public int getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}


	private static final Map<Integer, PushType> ENUMMAP = new HashMap<Integer, PushType>();
	
	static {
		for (PushType pushType : PushType.values()) {
			ENUMMAP.put(pushType.getDesc(), pushType);
		}
	}

	public static PushType fromValue(Integer desc) {
		return ENUMMAP.get(desc);
	}
}
