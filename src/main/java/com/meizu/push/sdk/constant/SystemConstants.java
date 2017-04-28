package com.meizu.push.sdk.constant;

/**
 * Created by wangxinguo on 2016-8-21.
 */
public class SystemConstants {

    public static final String CHAR_SET = "UTF-8";

    public static final String SDK_VERSION = "1.2.1.20170428_release";

    /**
     * 推送服务:pushId推送接口（透传消息）
     */
    public static final String PUSH_APPID_UNVARNISHED_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/unvarnished/pushByPushId";
    /**
     * 推送服务:alias推送接口(透传消息)
     */
    public static final String PUSH_APPID_UNVARNISHED_ALIAS = "http://api-push.meizu.com/garcia/api/server/push/unvarnished/pushByAlias";

    /**
     * 推送服务:pushId推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/varnished/pushByPushId";

    /**
     * 推送服务:alias推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_ALIAS = "http://api-push.meizu.com/garcia/api/server/push/varnished/pushByAlias";

    /**
     * 推送服务:获取推送任务taskId
     */
    public static final String PUSH_APPID_GET_TASKID = "http://api-push.meizu.com/garcia/api/server/push/pushTask/getTaskId";

    /**
     * 推送服务:pushId task推送接口（透传消息）
     */
    public static final String PUSH_APPID_UNVARNISHED_TASKID_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/task/unvarnished/pushByPushId";
    /**
     * 推送服务:alias task推送接口（透传消息）
     */
    public static final String PUSH_APPID_UNVARNISHED_TASKID_ALIAS = "http://api-push.meizu.com/garcia/api/server/push/task/unvarnished/pushByAlias";

    /**
     * 推送服务:pushId task推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_TASKID_PUSHIDS = "http://api-push.meizu.com/garcia/api/server/push/task/varnished/pushByPushId";

    /**
     * 推送服务:alias task推送接口（通知栏消息）
     */
    public static final String PUSH_APPID_VARNISHED_TASKID_ALIAS = "http://api-push.meizu.com/garcia/api/server/push/task/varnished/pushByAlias";

    /**
     * 推送服务:全部用户推送
     */
    public static final String PUSH_APPID_PUSH_TO_APP = "http://api-push.meizu.com/garcia/api/server/push/pushTask/pushToApp";

    /**
     * 推送服务:标签推送
     */
    public static final String PUSH_APPID_PUSH_TO_TAG = "http://api-push.meizu.com/garcia/api/server/push/pushTask/pushToTag";

    /**
     * 推送服务:取消推送任务
     */
    public static final String PUSH_CANCEL_PUSH_TO_APP = "http://api-push.meizu.com/garcia/api/server/push/pushTask/cancel";


    /**
     * 统计服务:通过taskId获取推送统计信息
     */
    public static final String GET_PUSH_STATISTICS_BY_TASKID = "http://api-push.meizu.com/garcia/api/server/push/statistics/getTaskStatistics";


    /**
     * 订阅服务:修改订阅开关状态
     */
    public static final String CHANGE_REGISTER_SWITCH = "http://api-push.meizu.com/garcia/api/server/message/changeRegisterSwitch";
    /**
     * 订阅服务:修改所有开关状态
     */
    public static final String CHANGE_ALL_SWITCH = "http://api-push.meizu.com/garcia/api/server/message/changeAllSwitch";
    /**
     * 订阅服务:获取订阅开关状态
     */
    public static final String GET_REGISTER_SWITCH = "http://api-push.meizu.com/garcia/api/server/message/getRegisterSwitch";
    /**
     * 订阅服务:别名订阅
     */
    public static final String SUBSCRIBE_ALIAS = "http://api-push.meizu.com/garcia/api/server/message/subscribeAlias";
    /**
     * 订阅服务:取消别名订阅
     */
    public static final String UN_SUBSCRIBE_ALIAS = "http://api-push.meizu.com/garcia/api/server/message/unSubscribeAlias";
    /**
     * 订阅服务:获取订阅别名
     */
    public static final String GET_SUB_ALIAS = "http://api-push.meizu.com/garcia/api/server/message/getSubAlias";
    /**
     * 订阅服务:标签订阅
     */
    public static final String SUBSCRIBE_TAGS = "http://api-push.meizu.com/garcia/api/server/message/subscribeTags";
    /**
     * 订阅服务:取消标签订阅
     */
    public static final String UN_SUBSCRIBE_TAGS = "http://api-push.meizu.com/garcia/api/server/message/unSubscribeTags";
    /**
     * 订阅服务:获取订阅标签
     */
    public static final String GET_SUBSCRIBE_TAGS = "http://api-push.meizu.com/garcia/api/server/message/getSubTags";
    /**
     * 订阅服务:取消订阅所有标签
     */
    public static final String UN_SUB_ALL_TAGS = "http://api-push.meizu.com/garcia/api/server/message/unSubAllTags";

}
