package com.meizu.push.sdk.server.model.sub;

/**
 * 设备开关状态
 *
 * @author wangxinguo <wangxinguo@meizu.com>
 * @date 2016-9-3
 */
public class SwitchStatusInfo {

    /**
     * 订阅pushId
     */
    private String pushId;
    /**
     * 通知栏开关状态
     */
    private boolean statusbarSwitch;
    /**
     * 透传开关状态
     */
    private boolean directSwitch;

    public SwitchStatusInfo(String pushId, boolean statusbarSwitch, boolean directSwitch) {
        this.pushId = pushId;
        this.statusbarSwitch = statusbarSwitch;
        this.directSwitch = directSwitch;
    }

    public String getPushId() {
        return pushId;
    }

    public boolean isStatusbarSwitch() {
        return statusbarSwitch;
    }

    public boolean isDirectSwitch() {
        return directSwitch;
    }

    @Override
    public String toString() {
        return "SwitchStatusInfo{" +
                "pushId='" + pushId + '\'' +
                ", statusbarSwitch=" + statusbarSwitch +
                ", directSwitch=" + directSwitch +
                '}';
    }
}
