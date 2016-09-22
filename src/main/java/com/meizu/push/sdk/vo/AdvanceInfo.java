package com.meizu.push.sdk.vo;

import java.io.Serializable;


/**
 * 高级设置
 * 
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午6:28:14
 */
public class AdvanceInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 是否定速推送 (fixSpeedRate 定速速率) 【非必填，默认false】 只对全部用户推送和标签推送生效
	 */
	private boolean isFixSpeed = Boolean.FALSE;
	/**
	 * 定速速率 【fixSpeed为true时，必填】 只对全部用户推送和标签推送生效
	 */
	private long fixSpeedRate;
//	/**
//	 * 是否定时展示 (fixDisplayTime 定时展示时间) 【非必填,默认false】
//	 */
//	private boolean isFixDisplay = Boolean.FALSE;
//	/**
//	 * 定时展示开始时间(yyyy-MM-dd HH:mm:ss) 【fixDisplay为true时，必填】
//	 */
//	private String fixStartDisplayTime = "";
//	/**
//	 * 定时展示结束时间(yyyy-MM-dd HH:mm:ss) 【fixDisplay为true时，必填，并且开始时间要晚于结束时间】
//	 */
//	private String fixEndDisplayTime = "";
//	/**
//	 * 联网方式 (0, "不限制"),(1, "WIFI") 【非必填，默认0】
//	 */
//	private int netType = 0;
//	/**
//	 * 通知图片,默认取应用图片 【非必填，默认为空 取终端应用图标】
//	 */
//	private String noticeImgUrl = "";
	/**
	 * 是否通知栏悬浮窗显示 true 显示 false 不显示 【非必填，默认false】
	 */
	private boolean isSuspend = Boolean.TRUE;
	/**
	 * 是否可清除通知栏 true 可以 false 不可以 【非必填，默认true】
	 */
	private boolean isClearNoticeBar = Boolean.TRUE;

	/**
	 * 通知方式
	 */
	private NotificationType notificationType = new NotificationType();

    public AdvanceInfo() {
    }

    public AdvanceInfo(boolean isFixSpeed, long fixSpeedRate, boolean isSuspend, boolean isClearNoticeBar, NotificationType notificationType) {
        this.isFixSpeed = isFixSpeed;
        this.fixSpeedRate = fixSpeedRate;
        this.isSuspend = isSuspend;
        this.isClearNoticeBar = isClearNoticeBar;
        this.notificationType = notificationType;
    }

    public AdvanceInfo(boolean isFixSpeed, long fixSpeedRate) {
        this.isFixSpeed = isFixSpeed;
        this.fixSpeedRate = fixSpeedRate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isFixSpeed() {
        return isFixSpeed;
    }

    public void setFixSpeed(boolean fixSpeed) {
        isFixSpeed = fixSpeed;
    }

    public long getFixSpeedRate() {
        return fixSpeedRate;
    }

    public void setFixSpeedRate(long fixSpeedRate) {
        this.fixSpeedRate = fixSpeedRate;
    }

    public boolean isSuspend() {
        return isSuspend;
    }

    public void setSuspend(boolean suspend) {
        isSuspend = suspend;
    }

    public boolean isClearNoticeBar() {
        return isClearNoticeBar;
    }

    public void setClearNoticeBar(boolean clearNoticeBar) {
        isClearNoticeBar = clearNoticeBar;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public String toString() {
        return "AdvanceInfo{" +
                "isFixSpeed=" + isFixSpeed +
                ", fixSpeedRate=" + fixSpeedRate +
                ", isSuspend=" + isSuspend +
                ", isClearNoticeBar=" + isClearNoticeBar +
                ", notificationType=" + notificationType +
                '}';
    }
}
