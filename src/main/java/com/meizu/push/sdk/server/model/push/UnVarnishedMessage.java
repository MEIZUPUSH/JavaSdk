package com.meizu.push.sdk.server.model.push;


import java.util.Date;

/**
 * 透传消息
 *
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午2:20:17
 */
public class UnVarnishedMessage extends Message {


    /**
     * 推送标题【非必填，字数显示1~100个】
     */
    private String title;
    /**
     * 透传内容 【必填，字数限制2000以内】
     */
    private String content;
    /**
     * 是否进离线消息 【非必填，默认值为true】
     */
    private boolean isOffLine = Boolean.TRUE;
    /**
     * 有效时长 (1- 72 小时内的正整数) 【offLine值为true时，必填，范围1~72小时】
     */
    private int validTime = 24;

    /**
     * 是否定速推送 (fixSpeedRate 定速速率) 【非必填，默认false】 只对任务推送生效
     */
    private boolean isFixSpeed = Boolean.FALSE;
    /**
     * 定速速率 【fixSpeed为true时，必填】 只对任务推送生效
     */
    private long fixSpeedRate;

    /**
     * 定时推送 (0, "即时"),(1, "定时") 【必填，默认0】 只对全部用户推送生效
     */
    private int pushTimeType = 0;
    /**
     * 任务定时开始时间【非必填pushTimeType为1必填】只对全部用户推送生效
     */
    private Date startTime;

    public int getPushTimeType() {
        return pushTimeType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isOffLine() {
        return isOffLine;
    }

    public int getValidTime() {
        return validTime;
    }

    public boolean isFixSpeed() {
        return isFixSpeed;
    }

    public long getFixSpeedRate() {
        return fixSpeedRate;
    }

    @Override
    public String toString() {
        return "UnVarnishedMessage{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isOffLine=" + isOffLine +
                ", validTime=" + validTime +
                ", isFixSpeed=" + isFixSpeed +
                ", fixSpeedRate=" + fixSpeedRate +
                ", pushTimeType=" + pushTimeType +
                ", startTime=" + startTime +
                '}';
    }

    protected UnVarnishedMessage(UnVarnishedMessage.Builder builder) {
        super.setAppId(builder.appId);
        super.setRestrictedPackageNames(builder.restrictedPackageNames);
        this.title = builder.title;
        this.content = builder.content;
        this.isOffLine = builder.isOffLine;
        this.validTime = builder.validTime;
        this.fixSpeedRate = builder.fixSpeedRate;
        this.isFixSpeed = builder.isFixSpeed;
        this.pushTimeType = builder.pushTimeType;
        this.startTime = builder.startTime;
    }


    public static final class Builder {
        private Long appId;
        private String[] restrictedPackageNames;
        private String title;
        private String content;
        private boolean isOffLine = Boolean.TRUE;
        private int validTime = 24;
        private boolean isFixSpeed = Boolean.FALSE;
        private long fixSpeedRate;
        private int pushTimeType = 0;
        private Date startTime;

        public UnVarnishedMessage.Builder title(String title) {
            this.title = title;
            return this;
        }

        public UnVarnishedMessage.Builder appId(Long appId) {
            this.appId = appId;
            return this;
        }

        public UnVarnishedMessage.Builder content(String content) {
            this.content = content;
            return this;
        }

        public UnVarnishedMessage.Builder isOffLine(boolean isOffLine) {
            this.isOffLine = isOffLine;
            return this;
        }

        public UnVarnishedMessage.Builder validTime(int validTime) {
            this.validTime = validTime;
            return this;
        }

        public UnVarnishedMessage.Builder isFixSpeed(boolean isFixSpeed) {
            this.isFixSpeed = isFixSpeed;
            return this;
        }

        public UnVarnishedMessage.Builder fixSpeedRate(long fixSpeedRate) {
            this.fixSpeedRate = fixSpeedRate;
            return this;
        }

        public UnVarnishedMessage.Builder pushTimeType(int pushTimeType) {
            this.pushTimeType = pushTimeType;
            return this;
        }

        public UnVarnishedMessage.Builder startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }
        
        public UnVarnishedMessage.Builder restrictedPackageNames(String[] restrictedPackageNames) {
            this.restrictedPackageNames = restrictedPackageNames;
            return this;
        }

        public UnVarnishedMessage build() {
            return new UnVarnishedMessage(this);
        }
    }

}
