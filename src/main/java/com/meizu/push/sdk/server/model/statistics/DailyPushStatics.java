package com.meizu.push.sdk.server.model.statistics;


import com.meizu.push.sdk.utils.DateUtils;

import java.util.Date;

/**
 * 应用日统计结果
 */
public class DailyPushStatics {

    /**
     * 日期
     */
    private Date date;

    /**
     * 目标数
     */
    private Long targetNo;

    /**
     * 有效数
     */
    private Long validNo;

    /**
     * 推送数
     */
    private Long pushedNo;

    /**
     * 接受数
     */
    private Long acceptNo;

    /**
     * 展示数
     */
    private Long displayNo;

    /**
     * 点击数
     */
    private Long clickNo;


    public Long getTargetNo() {
        return targetNo;
    }

    public void setTargetNo(Long targetNo) {
        this.targetNo = targetNo;
    }

    public Long getValidNo() {
        return validNo;
    }

    public void setValidNo(Long validNo) {
        this.validNo = validNo;
    }

    public Long getPushedNo() {
        return pushedNo;
    }

    public void setPushedNo(Long pushedNo) {
        this.pushedNo = pushedNo;
    }

    public Long getAcceptNo() {
        return acceptNo;
    }

    public void setAcceptNo(Long acceptNo) {
        this.acceptNo = acceptNo;
    }

    public Long getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(Long displayNo) {
        this.displayNo = displayNo;
    }

    public Long getClickNo() {
        return clickNo;
    }

    public void setClickNo(Long clickNo) {
        this.clickNo = clickNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String dateStr) {
        date = DateUtils.str2Date(dateStr, "yyyy-MM-dd");
    }

    @Override
    public String toString() {
        return "TaskStatisticsDaily{" +
                "date=" + date +
                ", targetNo=" + targetNo +
                ", validNo=" + validNo +
                ", pushedNo=" + pushedNo +
                ", acceptNo=" + acceptNo +
                ", displayNo=" + displayNo +
                ", clickNo=" + clickNo +
                '}';
    }
}
