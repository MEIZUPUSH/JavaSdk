package com.meizu.push.sdk.vo;

import java.io.Serializable;

/**
 * 展开方式设置
 * 
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午6:23:31
 */
public class NoticeExpandInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 展开方式 (0, "禁用"),(1, "文本"),(2, "大图")
	 * 【必填，值为0或者1或者2】【如果noticeBarType通知栏类型为默认，
	 * 则有禁用、文本、大图三种模式；如果noticeBarType通知栏类型为图片，则只有禁用、大图两种模式】
	 */
	private int noticeExpandType = 0;
	/**
	 * 展开内容, 【noticeExpandType为文本时，必填】
	 */
	private String noticeExpandContent = "";
//	/**
//	 * 展开大图URL 【noticeExpandType为大图时，必填】
//	 */
//	private String noticeExpandImgUrl = "";

    public NoticeExpandInfo() {
    }

    public NoticeExpandInfo(int noticeExpandType, String noticeExpandContent) {
        this.noticeExpandType = noticeExpandType;
        this.noticeExpandContent = noticeExpandContent;
    }

    public int getNoticeExpandType() {
		return noticeExpandType;
	}

	public void setNoticeExpandType(int noticeExpandType) {
		this.noticeExpandType = noticeExpandType;
	}

	public String getNoticeExpandContent() {
		return noticeExpandContent;
	}

	public void setNoticeExpandContent(String noticeExpandContent) {
		this.noticeExpandContent = noticeExpandContent;
	}

    @Override
    public String toString() {
        return "NoticeExpandInfo{" +
                "noticeExpandType=" + noticeExpandType +
                ", noticeExpandContent='" + noticeExpandContent + '\'' +
                '}';
    }
}
