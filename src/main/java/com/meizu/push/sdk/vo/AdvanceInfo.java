package com.meizu.push.sdk.vo;

import com.meizu.push.sdk.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;


/**
 * 高级设置
 *
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午6:28:14
 */
public class AdvanceInfo implements Serializable {

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
    /**
     * 是否定时展示 (fixDisplayTime 定时展示时间) 【非必填,默认false】
     */
    private boolean isFixDisplay = Boolean.FALSE;
    /**
     * 定时展示开始时间(yyyy-MM-dd HH:mm:ss) 【fixDisplay为true时，必填】
     */
    private String fixStartDisplayTime = "";
    /**
     * 定时展示结束时间(yyyy-MM-dd HH:mm:ss) 【fixDisplay为true时，必填，并且开始时间要晚于结束时间】
     */
    private String fixEndDisplayTime = "";
    /**
     * 是否通知栏悬浮窗显示 true 显示 false 不显示 【非必填，默认false】
     */
    private boolean isSuspend = Boolean.TRUE;
    /**
     * 是否可清除通知栏 true 可以 false 不可以 【非必填，默认true】
     */
    private boolean isClearNoticeBar = Boolean.TRUE;
    /**
     * 分组合并推送的key，凡是带有此key的通知栏消息只会显示最后到达的一条
     * 字段规则合法的消息组ID由数字([0-9]), 大小写字母([a-zA-Z]), 下划线(_)和中划线(-)组成, 长度不大于8个字符
     */
    private String notifyKey = "";

    /**
     * 通知方式
     */
    private NotificationType notificationType = new NotificationType();

    public AdvanceInfo() {
    }

    /**
     * 透传任务调度类型
     *
     * @param isFixSpeed
     * @param fixSpeedRate
     * @param isSuspend
     * @param isClearNoticeBar
     * @param notificationType
     */
    public AdvanceInfo(boolean isFixSpeed, long fixSpeedRate, boolean isSuspend,
                       boolean isClearNoticeBar, NotificationType notificationType) {
        this.isFixSpeed = isFixSpeed;
        this.fixSpeedRate = fixSpeedRate;
        this.isSuspend = isSuspend;
        this.isClearNoticeBar = isClearNoticeBar;
        this.notificationType = notificationType;
    }

    /**
     * 通知栏任务调度类型
     *
     * @param isFixSpeed
     * @param fixSpeedRate
     * @param isSuspend
     * @param isClearNoticeBar
     * @param notificationType
     * @param isFixDisplay
     * @param fixStartDisplayDate
     * @param fixEndDisplayDate
     */
    public AdvanceInfo(boolean isFixSpeed, long fixSpeedRate, boolean isSuspend,
                       boolean isClearNoticeBar, NotificationType notificationType,
                       boolean isFixDisplay, Date fixStartDisplayDate, Date fixEndDisplayDate,
                       String notifyKey) {
        this.isFixSpeed = isFixSpeed;
        this.fixSpeedRate = fixSpeedRate;
        this.isSuspend = isSuspend;
        this.isClearNoticeBar = isClearNoticeBar;
        this.notificationType = notificationType;
        this.isFixDisplay = isFixDisplay;
        if (fixStartDisplayDate != null) {
            this.fixStartDisplayTime = DateUtils.date2String(fixStartDisplayDate);
        }
        if (fixEndDisplayDate != null) {
            this.fixEndDisplayTime = DateUtils.date2String(fixEndDisplayDate);
        }
        this.notifyKey = notifyKey;
    }


    /**
     * 透传非调度类型
     *
     * @param isFixSpeed
     * @param fixSpeedRate
     */
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

    public boolean isFixDisplay() {
        return isFixDisplay;
    }

    public void setFixDisplay(boolean fixDisplay) {
        isFixDisplay = fixDisplay;
    }

    public String getFixStartDisplayTime() {
        return fixStartDisplayTime;
    }

    public void setFixStartDisplayTime(String fixStartDisplayTime) {
        this.fixStartDisplayTime = fixStartDisplayTime;
    }

    public String getFixEndDisplayTime() {
        return fixEndDisplayTime;
    }

    public void setFixEndDisplayTime(String fixEndDisplayTime) {
        this.fixEndDisplayTime = fixEndDisplayTime;
    }
    
    public String getNotifyKey() {
		return notifyKey;
	}

	public void setNotifyKey(String notifyKey) {
		this.notifyKey = notifyKey;
	}

    @Override
    public String toString() {
        return "AdvanceInfo{" +
                "isFixSpeed=" + isFixSpeed +
                ", fixSpeedRate=" + fixSpeedRate +
                ", isFixDisplay=" + isFixDisplay +
                ", fixStartDisplayTime='" + fixStartDisplayTime + '\'' +
                ", fixEndDisplayTime='" + fixEndDisplayTime + '\'' +
                ", isSuspend=" + isSuspend +
                ", isClearNoticeBar=" + isClearNoticeBar +
                ", notificationType=" + notificationType +
                ", notifyKey=" + notifyKey +
                '}';
    }
}
