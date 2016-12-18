package com.meizu.push.sdk.constant;

/**
 * Created by wangxinguo on 2016-8-21.
 */
public class SystemConstants {

    public static final String CHAR_SET = "UTF-8";

    public static final String SDK_VERSION = "1.0.0.20171218_release";

    /**
     * pushId推送接口（透传消息）
     */
    public static final String PUSH_APPID_UNVARNISHED_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/unvarnished/pushByPushId";
    /**
     * alias推送接口(透传消息)
     */
    public static final String PUSH_APPID_UNVARNISHED_ALIAS = "http://api-push.meizu.com/garcia/api/server/push/unvarnished/pushByAlias";

    /**
     * pushId推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/varnished/pushByPushId";

    /**
     * alias推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_ALIAS = "http://api-push.meizu.com/garcia/api/server/push/varnished/pushByAlias";

    /**
     * 获取推送任务taskId
     */
    public static final String PUSH_APPID_GET_TASKID = "http://api-push.meizu.com/garcia/api/server/push/pushTask/getTaskId";

    /**
     * pushId task推送接口（透传消息）
     */
    public static final String PUSH_APPID_UNVARNISHED_TASKID_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/task/unvarnished/pushByPushId";
    /**
     * alias task推送接口（透传消息）
     */
    public static final String PUSH_APPID_UNVARNISHED_TASKID_ALIAS = "http://api-push.meizu.com/garcia/api/server/push/task/unvarnished/pushByAlias";

    /**
     * pushId task推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_TASKID_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/task/varnished/pushByPushId";

    /**
     * alias task推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_TASKID_ALIAS = "http://api-push.meizu.com/garcia/api/server/push/task/varnished/pushByAlias";

    /**
     * 全部用户推送
     */
    public static final String PUSH_APPID_PUSH_TO_APP = "http://api-push.meizu.com/garcia/api/server/push/pushTask/pushToApp";

    /**
     * 标签推送
     */
    public static final String PUSH_APPID_PUSH_TO_TAG = "http://api-push.meizu.com/garcia/api/server/push/pushTask/pushToTag";

    /**
     * 取消推送任务
     */
    public static final String PUSH_CANCEL_PUSH_TO_APP = "http://api-push.meizu.com/garcia/api/server/push/pushTask/cancel";


    /**
     * 通过taskId获取推送统计信息
     */
    public static final String GET_PUSH_STATISTICS_BY_TASKID = "http://api-push.meizu.com/garcia/api/server/push/statistics/getTaskStatistics";
}
