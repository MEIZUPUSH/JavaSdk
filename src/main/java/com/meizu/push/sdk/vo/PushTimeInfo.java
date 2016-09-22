package com.meizu.push.sdk.vo;

import java.io.Serializable;


/**
 * 推送时间设置
 *
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午2:35:49
 */
public class PushTimeInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 是否进离线消息 【非必填，默认值为false】
     */
    private boolean isOffLine = Boolean.TRUE;
    /**
     * 有效时长 (1- 72 小时内的正整数) 【offLine值为true时，必填，范围0~72小时】
     */
    private int validTime = 24;

    /**
     * 定时推送 (0, "即时"),(1, "定时") 【必填，默认0】 只对全部用户推送和标签推送生效
     */
    private int pushTimeType = 0;
    /**
     * 任务定时开始时间 (yyyy-MM-dd HH:mm:ss) 【非必填pushTimeType为1必填】只对全部用户推送和标签推送生效
     */
    private String startTime = "";

    public PushTimeInfo() {
    }

    public PushTimeInfo(boolean isOffLine, int validTime) {
        this.isOffLine = isOffLine;
        this.validTime = validTime;
    }

    public PushTimeInfo(boolean isOffLine, int validTime, int pushTimeType, String startTime) {
        this.isOffLine = isOffLine;
        this.validTime = validTime;
        this.pushTimeType = pushTimeType;
        this.startTime = startTime;
    }

    public boolean isOffLine() {
        return isOffLine;
    }

    public int getPushTimeType() {
        return pushTimeType;
    }

    public void setPushTimeType(int pushTimeType) {
        this.pushTimeType = pushTimeType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setOffLine(boolean isOffLine) {
        this.isOffLine = isOffLine;
    }

    public int getValidTime() {
        return validTime;
    }

    public void setValidTime(int validTime) {
        this.validTime = validTime;
    }

    @Override
    public String toString() {
        return "PushTimeInfo [isOffLine=" + isOffLine + ", validTime=" + validTime + ", pushTimeType=" + pushTimeType + ", startTime=" + startTime + "]";
    }

}
