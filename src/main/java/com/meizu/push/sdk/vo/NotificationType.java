package com.meizu.push.sdk.vo;

import java.io.Serializable;

/**
 * 通知方式
 * 
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午6:34:56
 */
public class NotificationType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 震动 false关闭 true 开启 , 【非必填，默认true】
	 */
	private boolean vibrate = Boolean.TRUE;
	/**
	 * 闪光 false关闭 true 开启 , 【非必填，默认true】
	 */
	private boolean lights = Boolean.TRUE;
	/**
	 * 声音 false关闭 true 开启 , 【非必填，默认true】
	 */
	private boolean sound = Boolean.TRUE;

    public NotificationType() {
    }

    public NotificationType(boolean vibrate, boolean lights, boolean sound) {
        this.vibrate = vibrate;
        this.lights = lights;
        this.sound = sound;
    }

    public boolean isVibrate() {
		return vibrate;
	}

	public void setVibrate(boolean vibrate) {
		this.vibrate = vibrate;
	}

	public boolean isLights() {
		return lights;
	}

	public void setLights(boolean lights) {
		this.lights = lights;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	@Override
	public String toString() {
		return "NotificationType [vibrate=" + vibrate + ", lights=" + lights + ", sound=" + sound + "]";
	}

}
