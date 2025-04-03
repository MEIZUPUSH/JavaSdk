package com.meizu.push.sdk.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 子标题颜色
 */

public enum TitleColorType {
	BLUE("#206CFF","蓝色"),RED("#E42D22","红色");
	
	private String color;
	private String desc;

    TitleColorType(String color, String value) {
		this.color = color;
		this.desc = value;
	}


	public String getColor() {
		return color;
	}

	public String getDesc() {
		return desc;
	}


	private static final Map<String, TitleColorType> ENUMMAP = new HashMap<String, TitleColorType>();

	static {
		for (TitleColorType titleColorType : TitleColorType.values()) {
			ENUMMAP.put(titleColorType.getColor(), titleColorType);
		}
	}

	public static TitleColorType fromValue(String color) {
		return ENUMMAP.get(color);
	}
}
