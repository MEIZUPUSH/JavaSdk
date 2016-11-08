package com.meizu.push.sdk.server;


/**
 * 统计结果
 */
public class TaskStatistics {

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

    @Override
    public String toString() {
        return "TaskStatistics{" +
                "targetNo=" + targetNo +
                ", validNo=" + validNo +
                ", pushedNo=" + pushedNo +
                ", acceptNo=" + acceptNo +
                ", displayNo=" + displayNo +
                ", clickNo=" + clickNo +
                '}';
    }
}
