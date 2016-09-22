package com.meizu.push.sdk.vo;

import java.io.Serializable;

/**
 * Created by wangxinguo on 2016-8-21.
 */
public class UnVarnishedMessageJson implements Serializable{

    /**
     * 推送标题【非必填，字数显示1~100个】
     */
    private String title;
    /**
     * 透传内容 【必填，字数限制2000以内】
     */
    private String content;

    /**
     * 离线消息设置 非必填
     */
    private PushTimeInfo pushTimeInfo = new PushTimeInfo();

    /**
     * 高级设置
     */
    private AdvanceInfo advanceInfo = new AdvanceInfo();

    public UnVarnishedMessageJson() {

    }

    public UnVarnishedMessageJson(String title, String content, PushTimeInfo pushTimeInfo) {
        this.title = title;
        this.content = content;
        this.pushTimeInfo = pushTimeInfo;
    }

    public UnVarnishedMessageJson(String title, String content, PushTimeInfo pushTimeInfo, AdvanceInfo advanceInfo) {
        this.title = title;
        this.content = content;
        this.pushTimeInfo = pushTimeInfo;
        this.advanceInfo = advanceInfo;
    }

    public AdvanceInfo getAdvanceInfo() {
        return advanceInfo;
    }

    public void setAdvanceInfo(AdvanceInfo advanceInfo) {
        this.advanceInfo = advanceInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PushTimeInfo getPushTimeInfo() {
        return pushTimeInfo;
    }

    public void setPushTimeInfo(PushTimeInfo pushTimeInfo) {
        this.pushTimeInfo = pushTimeInfo;
    }
}
