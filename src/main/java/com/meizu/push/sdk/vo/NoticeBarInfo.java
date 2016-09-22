package com.meizu.push.sdk.vo;

import java.io.Serializable;

/**
 * 通知栏设置
 * 
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午6:23:18
 */
public class NoticeBarInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
//	/**
//	 * 通知栏图片URL 【通知栏类型为0时，非必填；通知栏类型为1时，必填，图片格式为1008*192,200KB以内】
//	 */
//	private String noticeBarImgUrl = "";

    public NoticeBarInfo() {
    }

    public NoticeBarInfo(int noticeBarType, String title, String content) {
        this.noticeBarType = noticeBarType;
        this.title = title;
        this.content = content;
    }

    public int getNoticeBarType() {
		return noticeBarType;
	}

	public void setNoticeBarType(int noticeBarType) {
		this.noticeBarType = noticeBarType;
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

    @Override
    public String toString() {
        return "NoticeBarInfo{" +
                "noticeBarType=" + noticeBarType +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
