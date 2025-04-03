package com.meizu.push.sdk.server.model.push;

import com.meizu.push.sdk.constant.TitleColorType;

import java.io.Serializable;

/**
 * vip特性
 * @date 2025年4月1日
 * @time 下午3:25:10
 */
public class VipFeatures implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /* =============通知栏样式 begin=============  */

    /**
     * 通知栏推送子标题【非必填，限制字数0~16】
     */
    private String subtitle = "";
    /**
     * 通知栏是否允许即时置顶 (与定时置顶不能同时设置) 【非必填，默认false】
     */
    private boolean pullDownTop = Boolean.FALSE;
    /**
     * 通知栏定时置顶时长 (与即时置顶不能同时设置) 【非必填，限制为1800~7200秒内的正整数】
     */
    private int timeTop;
    /**
     * 通知栏消息独立成组 【非必填，默认false】
     */
    private boolean notGroup = Boolean.FALSE;
    /**
     * 通知栏主标题变色 (默认不变色，可变蓝色#206CFF、红色#E42D22) 适用Flyme系统样式、大图样式、小图样式，与 【非必填】
     */
    private String titleColor;
    /**
     * 通知栏底图 (328px*120px jpg、png、jpeg 200kb以内) 与标题颜色及展开大图不能同时设置，独立成组 【非必填】
     */
    private String backgroundImgUrl = "";
    /**
     * 通知栏自定义小图标 (18px*18px jpg、png、jpeg 100kb以内) 与标题颜色及底图不能同时设置，独立成组 【非必填】
     */
    private String smallIconUrl = "";
    /**
     * 通知栏自定义大图标 (42px*42px jpg、png、jpeg 100kb以内) 与底图不能同时设置，独立成组 【非必填】
     */
    private String bigIconUrl = "";
    /**
     * 展开大图 (内容不为空时，noticeExpandType必须设置为大图)【非必填】
     */
    private String noticeExpandImgUrl = "";

    /* =============通知栏样式 end=============  */

    public VipFeatures() {
    }

    public VipFeatures(Builder builder) {
        this.subtitle = builder.subtitle;
        this.pullDownTop = builder.pullDownTop;
        this.timeTop = builder.timeTop;
        this.notGroup = builder.notGroup;
        this.titleColor = builder.titleColor.getColor();
        this.backgroundImgUrl = builder.backgroundImgUrl;
        this.smallIconUrl = builder.smallIconUrl;
        this.bigIconUrl = builder.bigIconUrl;
        this.noticeExpandImgUrl = builder.noticeExpandImgUrl;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean getPullDownTop() {
        return pullDownTop;
    }

    public int getTimeTop() {
        return timeTop;
    }

    public boolean getNotGroup() {
        return notGroup;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public String getBackgroundImgUrl() {
        return backgroundImgUrl;
    }

    public String getSmallIconUrl() {
        return smallIconUrl;
    }

    public String getBigIconUrl() {
        return bigIconUrl;
    }

    public String getNoticeExpandImgUrl() {
        return noticeExpandImgUrl;
    }

    @Override
    public String toString() {
        return "VipFeatures{" +
                "subtitle='" + subtitle + '\'' +
                ", pullDownTop=" + pullDownTop +
                ", timeTop=" + timeTop +
                ", notGroup=" + notGroup +
                ", titleColor='" + titleColor + '\'' +
                ", backgroundImgUrl='" + backgroundImgUrl + '\'' +
                ", smallIconUrl='" + smallIconUrl + '\'' +
                ", bigIconUrl='" + bigIconUrl + '\'' +
                ", noticeExpandImgUrl='" + noticeExpandImgUrl + '\'' +
                '}';
    }

    public static final class Builder {

        /* =============通知栏样式 begin=============  */
        /**
         * 通知栏推送子标题【非必填，限制字数0~16】
         */
        private String subtitle = "";
        /**
         * 通知栏是否允许即时置顶 (与定时置顶互斥) 【非必填，默认false】
         */
        private boolean pullDownTop = Boolean.FALSE;
        /**
         * 通知栏定时置顶时长 (与即时置顶互斥) 【非必填，限制为1800~7200秒内的正整数】
         */
        private int timeTop;
        /**
         * 通知栏消息独立成组 【非必填，默认false】
         */
        private boolean notGroup = Boolean.FALSE;
        /**
         * 通知栏主标题变色 (默认不变色，可变蓝色#206CFF、红色#E42D22) 适用Flyme系统样式、大图样式、小图样式【非必填】
         */
        private TitleColorType titleColor;
        /**
         * 通知栏底图 (328px*120px jpg、png、jpeg 200kb以内) 与标题颜色及展开大图互斥，独立成组 【非必填】
         */
        private String backgroundImgUrl = "";
        /**
         * 通知栏自定义小图标 (18px*18px jpg、png、jpeg 100kb以内) 与标题颜色及底图互斥，独立成组 【非必填】
         */
        private String smallIconUrl = "";
        /**
         * 通知栏自定义大图标 (42px*42px jpg、png、jpeg 100kb以内) 与底图互斥，独立成组 【非必填】
         */
        private String bigIconUrl = "";
        /**
         * 展开大图, 【noticeExpandType为大图时，必填】
         */
        private String noticeExpandImgUrl = "";

        /* =============通知栏样式 end=============  */
        public Builder() {
        }

        public Builder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder pullDownTop(boolean pullDownTop) {
            this.pullDownTop = pullDownTop;
            return this;
        }

        public Builder timeTop(int timeTop) {
            this.timeTop = timeTop;
            return this;
        }

        public Builder notGroup(boolean notGroup) {
            this.notGroup = notGroup;
            return this;
        }

        public Builder titleColor(TitleColorType titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder backgroundImgUrl(String backgroundImgUrl) {
            this.backgroundImgUrl = backgroundImgUrl;
            return this;
        }

        public Builder smallIconUrl(String smallIconUrl) {
            this.smallIconUrl = smallIconUrl;
            return this;
        }

        public Builder bigIconUrl(String bigIconUrl) {
            this.bigIconUrl = bigIconUrl;
            return this;
        }

        public Builder noticeExpandImgUrl(String noticeExpandImgUrl) {
            this.noticeExpandImgUrl = noticeExpandImgUrl;
            return this;
        }

        public VipFeatures build() {
            return new VipFeatures(this);
        }

    }


}
