package com.meizu.push.sdk.vo;

import java.io.Serializable;


/**
 * vip特性
 * @date 2025年4月1日
 * @time 下午3:25:10
 */
public class VipInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 通知栏推送子标题【非必填，字数限制0~16】
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
     * 通知栏主标题变色 (默认不变色，可变蓝色#206CFF、红色#E42D22) 适用Flyme系统样式、大图样式、小图样式，与 【非必填】
     */
    private String titleColor = "";
    /**
     * 通知栏底图 (328px*120px jpg、png、jpeg 200kb以内) 与标题颜色互斥，与展开大图互斥，独立成组 【非必填】
     */
    private String backgroundImgUrl = "";
    /**
     * 通知栏自定义小图标 (18px*18px jpg、png、jpeg 100kb以内) 与标题颜色互斥，与底图互斥，独立成组 【非必填】
     */
    private String smallIconUrl = "";
    /**
     * 通知栏自定义大图标 (42px*42px jpg、png、jpeg 100kb以内) 与底图互斥，独立成组 【非必填】
     */
    private String bigIconUrl = "";

    public VipInfo() {
    }

    public VipInfo(String subtitle, boolean pullDownTop, int timeTop, boolean notGroup, String titleColor, String backgroundImgUrl, String smallIconUrl, String bigIconUrl) {
        this.subtitle = subtitle;
        this.pullDownTop = pullDownTop;
        this.timeTop = timeTop;
        this.notGroup = notGroup;
        this.titleColor = titleColor;
        this.backgroundImgUrl = backgroundImgUrl;
        this.smallIconUrl = smallIconUrl;
        this.bigIconUrl = bigIconUrl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean getPullDownTop() {
        return pullDownTop;
    }

    public void setPullDownTop(boolean pullDownTop) {
        this.pullDownTop = pullDownTop;
    }

    public int getTimeTop() {
        return timeTop;
    }

    public void setTimeTop(int timeTop) {
        this.timeTop = timeTop;
    }

    public boolean getNotGroup() {
        return notGroup;
    }

    public void setNotGroup(boolean notGroup) {
        this.notGroup = notGroup;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getBackgroundImgUrl() {
        return backgroundImgUrl;
    }

    public void setBackgroundImgUrl(String backgroundImgUrl) {
        this.backgroundImgUrl = backgroundImgUrl;
    }

    public String getSmallIconUrl() {
        return smallIconUrl;
    }

    public void setSmallIconUrl(String smallIconUrl) {
        this.smallIconUrl = smallIconUrl;
    }

    public String getBigIconUrl() {
        return bigIconUrl;
    }

    public void setBigIconUrl(String bigIconUrl) {
        this.bigIconUrl = bigIconUrl;
    }

    @Override
    public String toString() {
        return "VipInfo{" +
                "subtitle='" + subtitle + '\'' +
                ", pullDownTop=" + pullDownTop +
                ", timeTop=" + timeTop +
                ", notGroup=" + notGroup +
                ", titleColor='" + titleColor + '\'' +
                ", backgroundImgUrl='" + backgroundImgUrl + '\'' +
                ", smallIconUrl='" + smallIconUrl + '\'' +
                ", bigIconUrl='" + bigIconUrl + '\'' +
                '}';
    }
}
