package com.meizu.push.sdk.constant;

/**
 * Created by wangxinguo on 2016-8-21.
 */
public class SystemConstants {

    public static final String CHAR_SET = "UTF-8";

    public static final String SDK_VERSION = "1.2.10.20230811_release";

    private static String PUSH_HOST_NAME = "server-api-push.meizu.com";
    private static String SUB_HOST_NAME = "api-push.meizu.com";
    private static final String HTTPS = "https://";

    public static void initOverSea(){
        PUSH_HOST_NAME = "server-api-push.in.meizu.com";
        SUB_HOST_NAME = "api-push.in.meizu.com";
        PUSH_UPLOAD_IMAGE = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/uploadImg";
        PUSH_APPID_UNVARNISHED_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/unvarnished/pushByPushId";
        PUSH_APPID_UNVARNISHED_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/unvarnished/pushByAlias";
        PUSH_APPID_VARNISHED_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/varnished/pushByPushId";
        PUSH_APPID_VARNISHED_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/varnished/pushByAlias";
        PUSH_APPID_GET_TASKID = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/getTaskId";
        PUSH_APPID_UNVARNISHED_TASKID_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/unvarnished/pushByPushId";
        PUSH_APPID_UNVARNISHED_TASKID_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/unvarnished/pushByAlias";
        PUSH_APPID_VARNISHED_TASKID_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/varnished/pushByPushId";
        PUSH_APPID_VARNISHED_TASKID_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/varnished/pushByAlias";
        PUSH_APPID_PUSH_TO_APP = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/pushToApp";
        PUSH_APPID_PUSH_TO_TAG = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/pushToTag";
        PUSH_CANCEL_PUSH_TO_APP = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/cancel";
        GET_PUSH_STATISTICS_BY_TASKID = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/statistics/getTaskStatistics";
        GET_PUSH_DAILY_STATICS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/statistics/dailyPushStatics";
        CHANGE_REGISTER_SWITCH = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/changeRegisterSwitch";
        CHANGE_ALL_SWITCH = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/changeAllSwitch";
        GET_REGISTER_SWITCH = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/getRegisterSwitch";
        SUBSCRIBE_ALIAS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/subscribeAlias";
        UN_SUBSCRIBE_ALIAS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/unSubscribeAlias";
        GET_SUB_ALIAS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/getSubAlias";
        SUBSCRIBE_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/subscribeTags";
        UN_SUBSCRIBE_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/unSubscribeTags";
        GET_SUBSCRIBE_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/getSubTags";
        UN_SUB_ALL_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/unSubAllTags";
    }

    public static void initMainLand(){
        PUSH_HOST_NAME = "server-api-push.meizu.com";
        SUB_HOST_NAME = "api-push.meizu.com";
        PUSH_UPLOAD_IMAGE = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/uploadImg";
        PUSH_APPID_UNVARNISHED_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/unvarnished/pushByPushId";
        PUSH_APPID_UNVARNISHED_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/unvarnished/pushByAlias";
        PUSH_APPID_VARNISHED_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/varnished/pushByPushId";
        PUSH_APPID_VARNISHED_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/varnished/pushByAlias";
        PUSH_APPID_GET_TASKID = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/getTaskId";
        PUSH_APPID_UNVARNISHED_TASKID_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/unvarnished/pushByPushId";
        PUSH_APPID_UNVARNISHED_TASKID_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/unvarnished/pushByAlias";
        PUSH_APPID_VARNISHED_TASKID_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/varnished/pushByPushId";
        PUSH_APPID_VARNISHED_TASKID_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/varnished/pushByAlias";
        PUSH_APPID_PUSH_TO_APP = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/pushToApp";
        PUSH_APPID_PUSH_TO_TAG = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/pushToTag";
        PUSH_CANCEL_PUSH_TO_APP = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/cancel";
        GET_PUSH_STATISTICS_BY_TASKID = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/statistics/getTaskStatistics";
        GET_PUSH_DAILY_STATICS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/statistics/dailyPushStatics";
        CHANGE_REGISTER_SWITCH = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/changeRegisterSwitch";
        CHANGE_ALL_SWITCH = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/changeAllSwitch";
        GET_REGISTER_SWITCH = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/getRegisterSwitch";
        SUBSCRIBE_ALIAS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/subscribeAlias";
        UN_SUBSCRIBE_ALIAS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/unSubscribeAlias";
        GET_SUB_ALIAS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/getSubAlias";
        SUBSCRIBE_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/subscribeTags";
        UN_SUBSCRIBE_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/unSubscribeTags";
        GET_SUBSCRIBE_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/getSubTags";
        UN_SUB_ALL_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/unSubAllTags";
    }
    /**
     * 推送服务：图片上传接口
     */
    public static String PUSH_UPLOAD_IMAGE = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/uploadImg";
    /**
     * 推送服务:pushId推送接口（透传消息）
     */
    public static String PUSH_APPID_UNVARNISHED_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/unvarnished/pushByPushId";
    /**
     * 推送服务:alias推送接口(透传消息)
     */
    public static String PUSH_APPID_UNVARNISHED_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/unvarnished/pushByAlias";

    /**
     * 推送服务:pushId推送接口（通知栏消息）
     */
    public static String PUSH_APPID_VARNISHED_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/varnished/pushByPushId";

    /**
     * 推送服务:alias推送接口（通知栏消息）
     */
    public static String PUSH_APPID_VARNISHED_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/varnished/pushByAlias";

    /**
     * 推送服务:获取推送任务taskId
     */
    public static String PUSH_APPID_GET_TASKID = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/getTaskId";

    /**
     * 推送服务:pushId task推送接口（透传消息）
     */
    public static String PUSH_APPID_UNVARNISHED_TASKID_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/unvarnished/pushByPushId";
    /**
     * 推送服务:alias task推送接口（透传消息）
     */
    public static String PUSH_APPID_UNVARNISHED_TASKID_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/unvarnished/pushByAlias";

    /**
     * 推送服务:pushId task推送接口（通知栏消息）
     */
    public static String PUSH_APPID_VARNISHED_TASKID_PUSHIDS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/varnished/pushByPushId";

    /**
     * 推送服务:alias task推送接口（通知栏消息）
     */
    public static String PUSH_APPID_VARNISHED_TASKID_ALIAS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/task/varnished/pushByAlias";

    /**
     * 推送服务:全部用户推送
     */
    public static String PUSH_APPID_PUSH_TO_APP = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/pushToApp";

    /**
     * 推送服务:标签推送
     */
    public static String PUSH_APPID_PUSH_TO_TAG = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/pushToTag";

    /**
     * 推送服务:取消推送任务
     */
    public static String PUSH_CANCEL_PUSH_TO_APP = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/pushTask/cancel";


    /**
     * 统计服务: 通过taskId获取推送统计信息
     */
    public static String GET_PUSH_STATISTICS_BY_TASKID = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/statistics/getTaskStatistics";

    /**
     * 统计服务: 获取应用推送统计（最长跨度30天）
     */
    public static String GET_PUSH_DAILY_STATICS = HTTPS + PUSH_HOST_NAME + "/garcia/api/server/push/statistics/dailyPushStatics";


    /**
     * 订阅服务:修改订阅开关状态
     */
    public static String CHANGE_REGISTER_SWITCH = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/changeRegisterSwitch";
    /**
     * 订阅服务:修改所有开关状态
     */
    public static String CHANGE_ALL_SWITCH = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/changeAllSwitch";
    /**
     * 订阅服务:获取订阅开关状态
     */
    public static String GET_REGISTER_SWITCH = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/getRegisterSwitch";
    /**
     * 订阅服务:别名订阅
     */
    public static String SUBSCRIBE_ALIAS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/subscribeAlias";
    /**
     * 订阅服务:取消别名订阅
     */
    public static String UN_SUBSCRIBE_ALIAS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/unSubscribeAlias";
    /**
     * 订阅服务:获取订阅别名
     */
    public static String GET_SUB_ALIAS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/getSubAlias";
    /**
     * 订阅服务:标签订阅
     */
    public static String SUBSCRIBE_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/subscribeTags";
    /**
     * 订阅服务:取消标签订阅
     */
    public static String UN_SUBSCRIBE_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/unSubscribeTags";
    /**
     * 订阅服务:获取订阅标签
     */
    public static String GET_SUBSCRIBE_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/getSubTags";
    /**
     * 订阅服务:取消订阅所有标签
     */
    public static String UN_SUB_ALL_TAGS = HTTPS + SUB_HOST_NAME + "/garcia/api/server/message/unSubAllTags";

}
