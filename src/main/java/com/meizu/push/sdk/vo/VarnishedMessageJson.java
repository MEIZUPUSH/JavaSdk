package com.meizu.push.sdk.vo;

import java.io.Serializable;

/**
 * Created by wangxinguo on 2016-8-22.
 */
public class VarnishedMessageJson implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 通知栏样式
     */
    private NoticeBarInfo noticeBarInfo = new NoticeBarInfo();
    /**
     * 展开方式
     */
    private NoticeExpandInfo noticeExpandInfo = new NoticeExpandInfo();
    /**
     * 点击动作
     */
    private ClickTypeInfo clickTypeInfo = new ClickTypeInfo();
    /**
     * 推送时间
     */
    private PushTimeInfo pushTimeInfo = new PushTimeInfo();
    /**
     * 高级设置
     */
    private AdvanceInfo advanceInfo = new AdvanceInfo();

    public VarnishedMessageJson() {
    }

    public VarnishedMessageJson(NoticeBarInfo noticeBarInfo, NoticeExpandInfo noticeExpandInfo, ClickTypeInfo clickTypeInfo, PushTimeInfo pushTimeInfo, AdvanceInfo advanceInfo) {
        this.noticeBarInfo = noticeBarInfo;
        this.noticeExpandInfo = noticeExpandInfo;
        this.clickTypeInfo = clickTypeInfo;
        this.pushTimeInfo = pushTimeInfo;
        this.advanceInfo = advanceInfo;
    }

    public NoticeBarInfo getNoticeBarInfo() {
        return noticeBarInfo;
    }

    public void setNoticeBarInfo(NoticeBarInfo noticeBarInfo) {
        this.noticeBarInfo = noticeBarInfo;
    }

    public NoticeExpandInfo getNoticeExpandInfo() {
        return noticeExpandInfo;
    }

    public void setNoticeExpandInfo(NoticeExpandInfo noticeExpandInfo) {
        this.noticeExpandInfo = noticeExpandInfo;
    }

    public ClickTypeInfo getClickTypeInfo() {
        return clickTypeInfo;
    }

    public void setClickTypeInfo(ClickTypeInfo clickTypeInfo) {
        this.clickTypeInfo = clickTypeInfo;
    }

    public PushTimeInfo getPushTimeInfo() {
        return pushTimeInfo;
    }

    public void setPushTimeInfo(PushTimeInfo pushTimeInfo) {
        this.pushTimeInfo = pushTimeInfo;
    }

    public AdvanceInfo getAdvanceInfo() {
        return advanceInfo;
    }

    public void setAdvanceInfo(AdvanceInfo advanceInfo) {
        this.advanceInfo = advanceInfo;
    }

}
