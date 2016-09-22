package com.meizu.push.sdk.server;


import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * 通知栏消息
 *
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午3:00:15
 */
public class VarnishedMessage extends Message {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /* =============通知栏样式 begin=============  */
    /**
     * 通知栏类型(0, "默认") 【必填，值为0】
     */
    private int noticeBarType = 0;
    /**
     * 推送标题, 【必填，字数限制1~32】
     */
    private String title = "";
    /**
     * 推送内容, 【必填，字数限制1~100】
     */
    private String content = "";
    /* =============通知栏样式 end=============  */


    /* =============展开方式 begin=============  */
    /**
     * 展开方式 (0, "禁用"),(1, "文本")
     */
    private int noticeExpandType = 0;
    /**
     * 展开内容, 【noticeExpandType为文本时，必填】
     */
    private String noticeExpandContent = "";
    /* =============展开方式 end=============  */


    /* =============点击动作 begin=============  */
    /**
     * 点击动作 (0,"打开应用"),(1,"打开应用页面"),(2,"打开H5地址")
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

    /* =============点击动作 end=============  */


    /* =============推送时间 begin=============  */
    /**
     * 是否进离线消息 【非必填，默认值为true】
     */
    private boolean isOffLine = Boolean.TRUE;
    /**
     * 有效时长 (1- 72 小时内的正整数) 【offLine值为true时，必填，范围1~72小时】
     */
    private int validTime = 24;
    /**
     * 定时推送 (0, "即时"),(1, "定时") 【必填，默认0】 只对全部用户推送生效
     */
    private int pushTimeType = 0;
    /**
     * 任务定时开始时间【非必填pushTimeType为1必填】只对全部用户推送生效
     */
    private Date startTime;
    /* =============推送时间 end=============  */


    /* =============高级设置 begin=============  */
    /**
     * 是否定速推送 (fixSpeedRate 定速速率) 【非必填，默认false】 只对全部用户推送和标签推送生效
     */
    private boolean isFixSpeed = Boolean.FALSE;
    /**
     * 定速速率 【fixSpeed为true时，必填】 只对全部用户推送和标签推送生效
     */
    private long fixSpeedRate;
    /**
     * 是否通知栏悬浮窗显示 true 显示 false 不显示 【非必填，默认true】
     */
    private boolean isSuspend = Boolean.TRUE;
    /**
     * 是否可清除通知栏 true 可以 false 不可以 【非必填，默认true】
     */
    private boolean isClearNoticeBar = Boolean.TRUE;
    /**
     * 通知方式 震动 false关闭 true 开启 , 【非必填，默认true】
     */
    private boolean vibrate = Boolean.TRUE;
    /**
     * 通知方式 闪光 false关闭 true 开启 , 【非必填，默认true】
     */
    private boolean lights = Boolean.TRUE;
    /**
     * 通知方式 声音 false关闭 true 开启 , 【非必填，默认true】
     */
    private boolean sound = Boolean.TRUE;
    /* =============高级设置 end=============  */

    public VarnishedMessage() {
    }

    public VarnishedMessage(VarnishedMessage.Builder builder) {
        super.setAppId(builder.appId);
        this.noticeBarType = builder.noticeBarType;
        this.title = builder.title;
        this.content = builder.content;
        this.noticeExpandType = builder.noticeExpandType;
        this.noticeExpandContent = builder.noticeExpandContent;
        this.clickType = builder.clickType;
        this.url = builder.url;
        this.parameters = builder.parameters;
        this.activity = builder.activity;
        this.isOffLine = builder.isOffLine;
        this.validTime = builder.validTime;
        this.pushTimeType = builder.pushTimeType;
        this.startTime = builder.startTime;
        this.isFixSpeed = builder.isFixSpeed;
        this.fixSpeedRate = builder.fixSpeedRate;
        this.isSuspend = builder.isSuspend;
        this.isClearNoticeBar = builder.isClearNoticeBar;
        this.vibrate = builder.vibrate;
        this.lights = builder.lights;
        this.sound = builder.sound;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPushTimeType() {
        return pushTimeType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getNoticeBarType() {
        return noticeBarType;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getNoticeExpandType() {
        return noticeExpandType;
    }

    public String getNoticeExpandContent() {
        return noticeExpandContent;
    }

    public int getClickType() {
        return clickType;
    }

    public String getUrl() {
        return url;
    }

    public JSONObject getParameters() {
        return parameters;
    }

    public String getActivity() {
        return activity;
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

    public boolean isSuspend() {
        return isSuspend;
    }

    public boolean isClearNoticeBar() {
        return isClearNoticeBar;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public boolean isLights() {
        return lights;
    }

    public boolean isSound() {
        return sound;
    }


    @Override
    public String toString() {
        return "VarnishedMessage{" +
                "noticeBarType=" + noticeBarType +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", noticeExpandType=" + noticeExpandType +
                ", noticeExpandContent='" + noticeExpandContent + '\'' +
                ", clickType=" + clickType +
                ", url='" + url + '\'' +
                ", parameters=" + parameters +
                ", activity='" + activity + '\'' +
                ", isOffLine=" + isOffLine +
                ", validTime=" + validTime +
                ", pushTimeType=" + pushTimeType +
                ", startTime=" + startTime +
                ", isFixSpeed=" + isFixSpeed +
                ", fixSpeedRate=" + fixSpeedRate +
                ", isSuspend=" + isSuspend +
                ", isClearNoticeBar=" + isClearNoticeBar +
                ", vibrate=" + vibrate +
                ", lights=" + lights +
                ", sound=" + sound +
                '}';
    }

    public static final class Builder {
        /**
         * 平台注册应用ID
         */
        private Long appId;

    /* =============通知栏样式 begin=============  */
        /**
         * 通知栏类型(0, "默认"),(1, "图片") , 【必填，值为0或者1】
         */
        private int noticeBarType = 0;
        /**
         * 推送标题, 【必填，字数限制1~32】
         */
        private String title = "";
        /**
         * 推送内容, 【必填，字数限制1~100】
         */
        private String content = "";
    /* =============通知栏样式 end=============  */


    /* =============展开方式 begin=============  */
        /**
         * 展开方式 (0, "禁用"),(1, "文本")
         */
        private int noticeExpandType = 0;
        /**
         * 展开内容, 【noticeExpandType为文本时，必填】
         */
        private String noticeExpandContent = "";
    /* =============展开方式 end=============  */


    /* =============点击动作 begin=============  */
        /**
         * 点击动作 (0,"打开应用"),(1,"打开应用页面"),(2,"打开H5地址")
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
    /* =============点击动作 end=============  */


    /* =============推送时间 begin=============  */
        /**
         * 是否进离线消息 【非必填，默认值为true】
         */
        private boolean isOffLine = Boolean.TRUE;
        /**
         * 有效时长 (1- 72 小时内的正整数) 【offLine值为true时，必填，范围0~72小时】
         */
        private int validTime = 24;
        /**
         * 定时推送 (0, "即时"),(1, "定时") 【必填，默认0】 只对全部用户推送生效
         */
        private int pushTimeType = 0;
        /**
         * 任务定时开始时间【非必填pushTimeType为1必填】只对全部用户推送生效
         */
        private Date startTime;
    /* =============推送时间 end=============  */


    /* =============高级设置 begin=============  */
        /**
         * 是否定速推送 (fixSpeedRate 定速速率) 【非必填，默认false】 只对全部用户推送和标签推送生效
         */
        private boolean isFixSpeed = Boolean.FALSE;
        /**
         * 定速速率 【fixSpeed为true时，必填】 只对全部用户推送和标签推送生效
         */
        private long fixSpeedRate;
        /**
         * 是否通知栏悬浮窗显示 true 显示 false 不显示 【非必填，默认true】
         */
        private boolean isSuspend = Boolean.TRUE;
        /**
         * 是否可清除通知栏 true 可以 false 不可以 【非必填，默认true】
         */
        private boolean isClearNoticeBar = Boolean.TRUE;
        /**
         * 通知方式 震动 false关闭 true 开启 , 【非必填，默认true】
         */
        private boolean vibrate = Boolean.TRUE;
        /**
         * 通知方式 闪光 false关闭 true 开启 , 【非必填，默认true】
         */
        private boolean lights = Boolean.TRUE;

        /**
         * 通知方式 声音 false关闭 true 开启 , 【非必填，默认true】
         */
        private boolean sound = Boolean.TRUE;
    /* =============高级设置 end=============  */

        public Builder() {
        }

        public VarnishedMessage.Builder appId(Long appId) {
            this.appId = appId;
            return this;
        }

        public VarnishedMessage.Builder noticeBarType(int noticeBarType) {
            this.noticeBarType = noticeBarType;
            return this;
        }

        public VarnishedMessage.Builder title(String title) {
            this.title = title;
            return this;
        }

        public VarnishedMessage.Builder content(String content) {
            this.content = content;
            return this;
        }


        public VarnishedMessage.Builder noticeExpandType(int noticeExpandType) {
            this.noticeExpandType = noticeExpandType;
            return this;
        }

        public VarnishedMessage.Builder noticeExpandContent(String noticeExpandContent) {
            this.noticeExpandContent = noticeExpandContent;
            return this;
        }


        public VarnishedMessage.Builder clickType(int clickType) {
            this.clickType = clickType;
            return this;
        }

        public VarnishedMessage.Builder url(String url) {
            this.url = url;
            return this;
        }

        public VarnishedMessage.Builder parameters(JSONObject parameters) {
            this.parameters = parameters;
            return this;
        }

        public VarnishedMessage.Builder activity(String activity) {
            this.activity = activity;
            return this;
        }

        public VarnishedMessage.Builder offLine(boolean isOffLine) {
            this.isOffLine = isOffLine;
            return this;
        }

        public VarnishedMessage.Builder validTime(int validTime) {
            this.validTime = validTime;
            return this;
        }


        public VarnishedMessage.Builder fixSpeed(boolean isFixSpeed) {
            this.isFixSpeed = isFixSpeed;
            return this;
        }

        public VarnishedMessage.Builder fixSpeedRate(int fixSpeedRate) {
            this.fixSpeedRate = fixSpeedRate;
            return this;
        }

        public VarnishedMessage.Builder suspend(boolean isSuspend) {
            this.isSuspend = isSuspend;
            return this;
        }

        public VarnishedMessage.Builder clearNoticeBar(boolean isClearNoticeBar) {
            this.isClearNoticeBar = isClearNoticeBar;
            return this;
        }

        public VarnishedMessage.Builder vibrate(boolean vibrate) {
            this.vibrate = vibrate;
            return this;
        }

        public VarnishedMessage.Builder lights(boolean lights) {
            this.lights = lights;
            return this;
        }

        public VarnishedMessage.Builder sound(boolean sound) {
            this.sound = sound;
            return this;
        }

        public VarnishedMessage.Builder pushTimeType(int pushTimeType) {
            this.pushTimeType = pushTimeType;
            return this;
        }

        public VarnishedMessage.Builder startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public VarnishedMessage build() {
            return new VarnishedMessage(this);
        }

    }


}
