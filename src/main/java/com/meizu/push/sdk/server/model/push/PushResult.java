package com.meizu.push.sdk.server.model.push;

import java.util.List;
import java.util.Map;

/**
 * 消息推送结果
 *
 * @author wangxinguo <wangxinguo@meizu.com>
 * @date 2016-9-3
 */
public class PushResult {

    private String msgId;//推送消息ID，用于推送流程明细排查
    private Map<String, List<String>> respTarget;//推送目标结果状态

    public static PushResult build(String msgId, Map<String, List<String>> respTarget) {
        return new PushResult(msgId, respTarget);
    }

    private PushResult(String msgId, Map<String, List<String>> respTarget) {
        this.msgId = msgId;
        this.respTarget = respTarget;
    }

    public String getMsgId() {
        return msgId;
    }

    public Map<String, List<String>> getRespTarget() {
        return respTarget;
    }

    @Override
    public String toString() {
        return "PushResult{" +
                "msgId='" + msgId + '\'' +
                ", respTarget=" + respTarget +
                '}';
    }
}
