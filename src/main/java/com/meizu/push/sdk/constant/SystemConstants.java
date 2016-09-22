package com.meizu.push.sdk.constant;

/**
 * Created by wangxinguo on 2016-8-21.
 */
public class SystemConstants {

    public static final String CHAR_SET = "UTF-8";

    public static final String SDK_VERSION = "2016.09.05";

    /**
     * pushId推送接口（透传消息）
     */
    public static final String PUSH_APPID_UNVARNISHED_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/unvarnished/pushByPushId";

    /**
     * pushId推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/varnished/pushByPushId";

    /**
     * 获取推送任务taskId
     */
    public static final String PUSH_APPID_GET_TASKID = "http://api-push.meizu.com/garcia/api/server/push/pushTask/getTaskId";

    /**
     * pushId task推送接口（透传消息）
     */
    public static final String PUSH_APPID_UNVARNISHED_TASKID_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/task/unvarnished/pushByPushId";

    /**
     * pushId task推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_TASKID_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/task/varnished/pushByPushId";

    /**
     * 全部用户推送
     */
    public static final String PUSH_APPID_PUSH_TO_APP = "http://api-push.meizu.com/garcia/api/server/push/pushTask/pushToApp";

    /**
     * 取消推送任务
     */
    public static final String PUSH_CANCEL_PUSH_TO_APP = "http://api-push.meizu.com/garcia/api/server/push/pushTask/cancel";
}
