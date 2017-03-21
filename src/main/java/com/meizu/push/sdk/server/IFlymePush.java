package com.meizu.push.sdk.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meizu.push.sdk.constant.PushType;
import com.meizu.push.sdk.constant.ScopeType;
import com.meizu.push.sdk.constant.SystemConstants;
import com.meizu.push.sdk.exception.InvalidRequestException;
import com.meizu.push.sdk.utils.CollectionUtils;
import com.meizu.push.sdk.utils.DateUtils;
import com.meizu.push.sdk.utils.HttpClient;
import com.meizu.push.sdk.utils.StringUtils;
import com.meizu.push.sdk.vo.AdvanceInfo;
import com.meizu.push.sdk.vo.ClickTypeInfo;
import com.meizu.push.sdk.vo.NoticeBarInfo;
import com.meizu.push.sdk.vo.NoticeExpandInfo;
import com.meizu.push.sdk.vo.NotificationType;
import com.meizu.push.sdk.vo.PushTimeInfo;
import com.meizu.push.sdk.vo.UnVarnishedMessageJson;
import com.meizu.push.sdk.vo.VarnishedMessageJson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * @author wangxinguo <wangxinguo@meizu.com>
 * @version 1.0
 * @class IFlymePush
 * @date 2016-8-23 14:07
 */
public class IFlymePush extends HttpClient {

    private static final String SUCCESS_CODE = "200";

    /**
     * 是否使用https接口调用：true 使用https连接，false使用http连接；默认使用http
     */
    private boolean useSSL = Boolean.FALSE;


    public IFlymePush(String appSecret) {
        super(nonNull(appSecret));
    }

    public IFlymePush(String appSecret, boolean useSSL) {
        super(nonNull(appSecret));
        this.useSSL = useSSL;
    }

    /**
     * 通知栏推送 不重试
     *
     * @param message
     * @param pushIds
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessage(VarnishedMessage message, List<String> pushIds) throws IOException {
        return this.pushMessage(message, pushIds, 0);
    }


    /**
     * 通知栏推送 可重试
     *
     * @param message 推送通知栏消息
     * @param pushIds 推送目标用户
     * @param retries 重试次数
     * @return 推送结果
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessage(VarnishedMessage message, List<String> pushIds, int retries) throws IOException {
        if (CollectionUtils.isEmpty(pushIds)) {
            return ResultPack.failed("pushIds is empty");
        }
        if (message == null) {
            return ResultPack.failed("message is null");
        }
        String pushIdStr = list2Str(pushIds);
        return this.pushMessage(UserType.PUSHID, PushType.STATUSBAR, message, pushIdStr, retries);
    }

    /**
     * 别名通知栏推送 不重试
     *
     * @param message
     * @param alias
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessageByAlias(VarnishedMessage message, List<String> alias) throws IOException {
        return this.pushMessageByAlias(message, alias, 0);
    }


    /**
     * 别名通知栏推送 可重试
     *
     * @param message 推送通知栏消息
     * @param alias
     * @param retries 重试次数
     * @return 推送结果
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessageByAlias(VarnishedMessage message, List<String> alias, int retries) throws IOException {
        if (CollectionUtils.isEmpty(alias)) {
            return ResultPack.failed("alias is empty");
        }
        if (message == null) {
            return ResultPack.failed("message is null");
        }
        String aliasStr = list2Str(alias);
        return this.pushMessage(UserType.ALIAS, PushType.STATUSBAR, message, aliasStr, retries);
    }

    /**
     * 透传推送 不重试
     *
     * @param message 推送透传消息
     * @param pushIds 推送目标用户
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessage(UnVarnishedMessage message, List<String> pushIds) throws IOException {
        return this.pushMessage(message, pushIds, 0);
    }

    /**
     * 透传推送 可重试
     *
     * @param message 推送透传消息
     * @param pushIds 推送目标用户
     * @param retries 重试次数
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessage(UnVarnishedMessage message, List<String> pushIds, int retries) throws IOException {
        if (CollectionUtils.isEmpty(pushIds)) {
            return ResultPack.failed("pushIds is empty");
        }
        if (message == null) {
            return ResultPack.failed("message is null");
        }
        String pushIdStr = list2Str(pushIds);
        return this.pushMessage(UserType.PUSHID, PushType.DIRECT, message, pushIdStr, retries);
    }

    /**
     * 别名透传消息推送
     *
     * @param message 推送消息
     * @param alias   别名集合
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessageByAlias(UnVarnishedMessage message, List<String> alias) throws IOException {
        return this.pushMessageByALias(message, alias, 0);
    }

    /**
     * 别名透传消息推送
     *
     * @param message 推送消息
     * @param alias   别名集合
     * @param retries 失败重试次数
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessageByALias(UnVarnishedMessage message, List<String> alias, int retries) throws IOException {
        if (CollectionUtils.isEmpty(alias)) {
            return ResultPack.failed("alias is empty");
        }
        if (message == null) {
            return ResultPack.failed("message is null");
        }
        String aliasStr = list2Str(alias);
        return this.pushMessage(UserType.ALIAS, PushType.DIRECT, message, aliasStr, retries);
    }


    /**
     * 获取推送任务Id
     *
     * @param pushType
     * @param message
     * @return
     * @throws IOException
     */
    public ResultPack<Long> getTaskId(PushType pushType, Message message) throws IOException {
        if (pushType == null) {
            return ResultPack.failed("pushType is null");
        }
        if (message == null) {
            return ResultPack.failed("message is null");
        }
        String _url = SystemConstants.PUSH_APPID_GET_TASKID;
        StringBuilder body = newBody("pushType", String.valueOf(pushType.getDesc()));

        Long appId = message.getAppId();
        if (appId == null) {
            return ResultPack.failed("appId is null");
        }
        addParameter(body, "appId", String.valueOf(appId));

        if (PushType.DIRECT == pushType) {
            if (!(message instanceof UnVarnishedMessage)) {
                return ResultPack.failed("message must be instanceof UnVarnishedMessage");
            }
            UnVarnishedMessage msgInfo = (UnVarnishedMessage) message;

            PushTimeInfo pushTimeInfo = new PushTimeInfo(msgInfo.isOffLine(), msgInfo.getValidTime());
            AdvanceInfo advanceInfo = new AdvanceInfo(msgInfo.isFixSpeed(), msgInfo.getFixSpeedRate());

            UnVarnishedMessageJson messageJson = new UnVarnishedMessageJson(msgInfo.getTitle(), msgInfo.getContent(), pushTimeInfo, advanceInfo);

            addParameter(body, "messageJson", JSON.toJSONString(messageJson));
        } else if (PushType.STATUSBAR == pushType) {
            if (!(message instanceof VarnishedMessage)) {
                return ResultPack.failed("message must be instanceof VarnishedMessage");
            }
            VarnishedMessage msgInfo = (VarnishedMessage) message;

            NoticeBarInfo noticeBarInfo = new NoticeBarInfo(msgInfo.getNoticeBarType(), msgInfo.getTitle(), msgInfo.getContent());
            NoticeExpandInfo noticeExpandInfo = new NoticeExpandInfo(msgInfo.getNoticeExpandType(), msgInfo.getNoticeExpandContent());
            ClickTypeInfo clickTypeInfo = new ClickTypeInfo(msgInfo.getClickType(), msgInfo.getUrl(), msgInfo.getParameters(), msgInfo.getActivity(), msgInfo.getCustomAttribute());
            PushTimeInfo pushTimeInfo = new PushTimeInfo(msgInfo.isOffLine(), msgInfo.getValidTime());
            NotificationType notificationType = new NotificationType(msgInfo.isVibrate(), msgInfo.isLights(), msgInfo.isSound());
            AdvanceInfo advanceInfo = new AdvanceInfo(msgInfo.isFixSpeed(), msgInfo.getFixSpeedRate(), msgInfo.isSuspend(),
                    msgInfo.isClearNoticeBar(), notificationType, msgInfo.isFixDisplay(), msgInfo.getFixStartDisplayDate(), msgInfo.getFixEndDisplayDate());

            VarnishedMessageJson messageJson = new VarnishedMessageJson(noticeBarInfo, noticeExpandInfo, clickTypeInfo, pushTimeInfo, advanceInfo);
            addParameter(body, "messageJson", JSON.toJSONString(messageJson));
        }

        HttpResult httpResult = this.post(_url, body.toString());
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        if (SUCCESS_CODE.equals(code)) {
            JSONObject objValue = JSON.parseObject(value);
            if (objValue.containsKey("taskId")) {
                Long taskId = objValue.getLong("taskId");
                return ResultPack.succeed(code, msg, taskId);
            } else {
                return ResultPack.failed("error return value");
            }
        } else {
            return ResultPack.failed(code, msg);
        }
    }

    /**
     * 通过任务ID推送消息 不重试
     *
     * @param pushType
     * @param appId
     * @param taskId
     * @param pushIds
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessageByTaskId(PushType pushType, long appId, long taskId, List<String> pushIds) throws IOException {
        return this.pushMessageByTaskId(pushType, appId, taskId, pushIds, 0);
    }

    /**
     * 通过任务ID推送消息 可重试
     *
     * @param pushType
     * @param appId
     * @param taskId
     * @param pushIds
     * @param retries
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushMessageByTaskId(PushType pushType, long appId, long taskId, List<String> pushIds, int retries) throws IOException {
        if (pushType == null) {
            return ResultPack.failed("pushType is null");
        }
        if (CollectionUtils.isEmpty(pushIds)) {
            return ResultPack.failed("pushIds is empty");
        }
        String pushIdStr = list2Str(pushIds);
        return this.pushMessageByTaskId(UserType.PUSHID, pushType, appId, taskId, pushIdStr, retries);
    }


    /**
     * 通过任务ID推送别名消息 不重试
     *
     * @param pushType 消息类型
     * @param appId    应用Id
     * @param taskId   任务Id
     * @param alias    别名集合
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushAliasMessageByTaskId(PushType pushType, long appId, long taskId, List<String> alias) throws IOException {
        return this.pushAliasMessageByTaskId(pushType, appId, taskId, alias, 0);
    }

    /**
     * 通过任务ID推送别名消息 可重试
     *
     * @param pushType 消息类型
     * @param appId    应用Id
     * @param taskId   任务Id
     * @param alias    别名集合
     * @param retries  失败重试次数
     * @return
     * @throws IOException
     */
    public ResultPack<PushResult> pushAliasMessageByTaskId(PushType pushType, long appId, long taskId, List<String> alias, int retries) throws IOException {
        if (pushType == null) {
            return ResultPack.failed("pushType is null");
        }
        if (CollectionUtils.isEmpty(alias)) {
            return ResultPack.failed("alias is empty");
        }
        String aliasStr = list2Str(alias);
        return this.pushMessageByTaskId(UserType.ALIAS, pushType, appId, taskId, aliasStr, retries);
    }


    /**
     * 应用全网推送
     *
     * @param pushType
     * @param message
     * @return
     */
    public ResultPack<Long> pushToApp(PushType pushType, Message message) throws IOException {
        String _url = SystemConstants.PUSH_APPID_PUSH_TO_APP;
        if (pushType == null) {
            return ResultPack.failed("pushType is null");
        }
        if (message == null) {
            return ResultPack.failed("message is null");
        }
        Long appId = message.getAppId();
        if (appId == null) {
            return ResultPack.failed("appId is null");
        }

        StringBuilder body = newBody("pushType", String.valueOf(pushType.getDesc()));
        addParameter(body, "appId", String.valueOf(appId));

        if (PushType.DIRECT == pushType) {
            if (!(message instanceof UnVarnishedMessage)) {
                return ResultPack.failed("message must be instanceof UnVarnishedMessage");
            }

            UnVarnishedMessage msgInfo = (UnVarnishedMessage) message;

            String startTime = "";
            if (msgInfo.getStartTime() != null) {
                startTime = DateUtils.date2String(msgInfo.getStartTime());
            }
            PushTimeInfo pushTimeInfo = new PushTimeInfo(msgInfo.isOffLine(), msgInfo.getValidTime(), msgInfo.getPushTimeType(), startTime);
            AdvanceInfo advanceInfo = new AdvanceInfo(msgInfo.isFixSpeed(), msgInfo.getFixSpeedRate());

            UnVarnishedMessageJson messageJson = new UnVarnishedMessageJson(msgInfo.getTitle(), msgInfo.getContent(), pushTimeInfo, advanceInfo);

            addParameter(body, "messageJson", JSON.toJSONString(messageJson));


        } else if (PushType.STATUSBAR == pushType) {
            if (!(message instanceof VarnishedMessage)) {
                return ResultPack.failed("message must be instanceof VarnishedMessage");
            }
            VarnishedMessage msgInfo = (VarnishedMessage) message;

            NoticeBarInfo noticeBarInfo = new NoticeBarInfo(msgInfo.getNoticeBarType(), msgInfo.getTitle(), msgInfo.getContent());
            NoticeExpandInfo noticeExpandInfo = new NoticeExpandInfo(msgInfo.getNoticeExpandType(), msgInfo.getNoticeExpandContent());
            ClickTypeInfo clickTypeInfo = new ClickTypeInfo(msgInfo.getClickType(), msgInfo.getUrl(), msgInfo.getParameters(), msgInfo.getActivity(), msgInfo.getCustomAttribute());
            String startTime = "";
            if (msgInfo.getStartTime() != null) {
                startTime = DateUtils.date2String(msgInfo.getStartTime());
            }
            PushTimeInfo pushTimeInfo = new PushTimeInfo(msgInfo.isOffLine(), msgInfo.getValidTime(), msgInfo.getPushTimeType(), startTime);
            NotificationType notificationType = new NotificationType(msgInfo.isVibrate(), msgInfo.isLights(), msgInfo.isSound());
            AdvanceInfo advanceInfo = new AdvanceInfo(msgInfo.isFixSpeed(), msgInfo.getFixSpeedRate(), msgInfo.isSuspend(),
                    msgInfo.isClearNoticeBar(), notificationType, msgInfo.isFixDisplay(), msgInfo.getFixStartDisplayDate(), msgInfo.getFixEndDisplayDate());

            VarnishedMessageJson messageJson = new VarnishedMessageJson(noticeBarInfo, noticeExpandInfo, clickTypeInfo, pushTimeInfo, advanceInfo);
            addParameter(body, "messageJson", JSON.toJSONString(messageJson));

        }

        HttpResult httpResult = this.post(_url, body.toString());
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        if (SUCCESS_CODE.equals(code)) {
            JSONObject objValue = JSON.parseObject(value);
            if (objValue.containsKey("taskId")) {
                Long taskId = objValue.getLong("taskId");
                return ResultPack.succeed(code, msg, taskId);
            } else {
                return ResultPack.failed("error return value");
            }
        } else {
            return ResultPack.failed(code, msg);
        }
    }

    /**
     * 标签推送
     *
     * @param pushType  推送类型
     * @param message   推送消息
     * @param tagName   推送标签
     * @param scopeType 推送标签集合类型
     * @return
     * @throws IOException
     */
    public ResultPack<Long> pushToTag(PushType pushType, Message message, List<String> tagName, ScopeType scopeType) throws IOException {
        String _url = SystemConstants.PUSH_APPID_PUSH_TO_TAG;
        if (pushType == null) {
            return ResultPack.failed("pushType is null");
        }
        if (scopeType == null) {
            return ResultPack.failed("scopeType is null");
        }
        if (message == null) {
            return ResultPack.failed("message is null");
        }
        if (CollectionUtils.isEmpty(tagName)) {
            return ResultPack.failed("tagName is null");
        }
        Long appId = message.getAppId();
        if (appId == null) {
            return ResultPack.failed("appId is null");
        }

        StringBuilder body = newBody("pushType", String.valueOf(pushType.getDesc()));
        addParameter(body, "appId", String.valueOf(appId));
        addParameter(body, "tagNames", CollectionUtils.list2Str(tagName));
        addParameter(body, "scope", String.valueOf(scopeType.getDesc()));


        if (PushType.DIRECT == pushType) {
            if (!(message instanceof UnVarnishedMessage)) {
                return ResultPack.failed("message must be instanceof UnVarnishedMessage");
            }

            UnVarnishedMessage msgInfo = (UnVarnishedMessage) message;

            String startTime = "";
            if (msgInfo.getStartTime() != null) {
                startTime = DateUtils.date2String(msgInfo.getStartTime());
            }
            PushTimeInfo pushTimeInfo = new PushTimeInfo(msgInfo.isOffLine(), msgInfo.getValidTime(), msgInfo.getPushTimeType(), startTime);
            AdvanceInfo advanceInfo = new AdvanceInfo(msgInfo.isFixSpeed(), msgInfo.getFixSpeedRate());

            UnVarnishedMessageJson messageJson = new UnVarnishedMessageJson(msgInfo.getTitle(), msgInfo.getContent(), pushTimeInfo, advanceInfo);

            addParameter(body, "messageJson", JSON.toJSONString(messageJson));
        } else if (PushType.STATUSBAR == pushType) {
            if (!(message instanceof VarnishedMessage)) {
                return ResultPack.failed("message must be instanceof VarnishedMessage");
            }
            VarnishedMessage msgInfo = (VarnishedMessage) message;

            NoticeBarInfo noticeBarInfo = new NoticeBarInfo(msgInfo.getNoticeBarType(), msgInfo.getTitle(), msgInfo.getContent());
            NoticeExpandInfo noticeExpandInfo = new NoticeExpandInfo(msgInfo.getNoticeExpandType(), msgInfo.getNoticeExpandContent());
            ClickTypeInfo clickTypeInfo = new ClickTypeInfo(msgInfo.getClickType(), msgInfo.getUrl(), msgInfo.getParameters(), msgInfo.getActivity(), msgInfo.getCustomAttribute());
            String startTime = "";
            if (msgInfo.getStartTime() != null) {
                startTime = DateUtils.date2String(msgInfo.getStartTime());
            }
            PushTimeInfo pushTimeInfo = new PushTimeInfo(msgInfo.isOffLine(), msgInfo.getValidTime(), msgInfo.getPushTimeType(), startTime);
            NotificationType notificationType = new NotificationType(msgInfo.isVibrate(), msgInfo.isLights(), msgInfo.isSound());
            AdvanceInfo advanceInfo = new AdvanceInfo(msgInfo.isFixSpeed(), msgInfo.getFixSpeedRate(), msgInfo.isSuspend(),
                    msgInfo.isClearNoticeBar(), notificationType, msgInfo.isFixDisplay(), msgInfo.getFixStartDisplayDate(), msgInfo.getFixEndDisplayDate());

            VarnishedMessageJson messageJson = new VarnishedMessageJson(noticeBarInfo, noticeExpandInfo, clickTypeInfo, pushTimeInfo, advanceInfo);
            addParameter(body, "messageJson", JSON.toJSONString(messageJson));

        }

        HttpResult httpResult = this.post(_url, body.toString());
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        if (SUCCESS_CODE.equals(code)) {
            JSONObject objValue = JSON.parseObject(value);
            if (objValue.containsKey("taskId")) {
                Long taskId = objValue.getLong("taskId");
                return ResultPack.succeed(code, msg, taskId);
            } else {
                return ResultPack.failed("error return value");
            }
        } else {
            return ResultPack.failed(code, msg);
        }
    }

    /**
     * 取消任务推送
     *
     * @param pushType
     * @param appId
     * @param taskId
     * @return
     */
    public ResultPack<Boolean> cancelTaskPush(PushType pushType, long appId, long taskId) throws IOException {
        if (pushType == null) {
            return ResultPack.failed("pushType is null");
        }
        String _url = SystemConstants.PUSH_CANCEL_PUSH_TO_APP;

        StringBuilder body = newBody("pushType", String.valueOf(pushType.getDesc()));
        addParameter(body, "appId", String.valueOf(String.valueOf(appId)));
        addParameter(body, "taskId", String.valueOf(String.valueOf(taskId)));

        HttpResult httpResult = this.post(_url, body.toString());
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        if (SUCCESS_CODE.equals(code)) {
            return ResultPack.succeed("推送任务取消成功");
        } else {
            return ResultPack.failed(code, msg);
        }
    }

    /**
     * 获取任务Id推送统计结果
     *
     * @param appId
     * @param taskId
     * @return
     * @throws IOException
     */
    public ResultPack<TaskStatistics> getTaskStatistics(long appId, long taskId) throws IOException {

        String _url = SystemConstants.GET_PUSH_STATISTICS_BY_TASKID;

        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "taskId", String.valueOf(String.valueOf(taskId)));

        HttpResult httpResult = this.post(_url, body.toString());
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        TaskStatistics taskStatistics = new TaskStatistics();
        if (SUCCESS_CODE.equals(code)) {
            if (StringUtils.isNotBlank(value)) {
                try {
                    JSONObject jsonObject = JSONObject.parseObject(value);
                    taskStatistics.setTargetNo(jsonObject.getLong("targetNo"));
                    taskStatistics.setValidNo(jsonObject.getLong("validNo"));
                    taskStatistics.setPushedNo(jsonObject.getLong("pushedNo"));
                    taskStatistics.setAcceptNo(jsonObject.getLong("acceptNo"));
                    taskStatistics.setDisplayNo(jsonObject.getLong("displayNo"));
                    taskStatistics.setClickNo(jsonObject.getLong("clickNo"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return ResultPack.succeed(taskStatistics);
        } else {
            return ResultPack.failed(code, msg);
        }
    }


    private ResultPack<PushResult> pushMessage(UserType userType, PushType pushType, Message message, String targets, int retries) throws IOException {
        int attempt = 0;
        ResultPack<PushResult> result;
        int backoff = 1000;
        boolean tryAgain;
        do {
            ++attempt;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format("attempt [%s] to pushMessage [%s] to %s [%s]", attempt, message, userType.getValue(), targets));
            }
            result = this.pushMessageNoRetry(userType, pushType, message, targets);
            tryAgain = result == null && attempt <= retries;
            backoff = getBackoffTime(backoff, tryAgain);
        } while (tryAgain);
        if (result == null) {
            throw new IOException(String.format("Could not send message after [%s] attempts", attempt));
        } else {
            return result;
        }
    }

    private int getBackoffTime(int backoff, boolean tryAgain) {
        if (tryAgain) {
            int sleepTime = backoff / 2 + this.random.nextInt(backoff);
            this.sleep((long) sleepTime);
            if (2 * backoff < 60000) {
                backoff *= 2;
            }
        }
        return backoff;
    }

    private ResultPack<PushResult> pushMessageNoRetry(UserType userType, PushType pushType, Message message, String targets) throws IOException {
        String _url = null;
        StringBuilder body = null;
        if (UserType.PUSHID == userType) {
            body = newBody("pushIds", targets);
        } else if (UserType.ALIAS == userType) {
            body = newBody("alias", targets);
        }

        Long appId = message.getAppId();
        if (appId != null && appId > 0) {
            addParameter(body, "appId", String.valueOf(appId));
        } else {
            return ResultPack.failed("appId is empty");
        }
        if (PushType.DIRECT == pushType) {
            UnVarnishedMessage msgInfo = (UnVarnishedMessage) message;

            PushTimeInfo pushTimeInfo = new PushTimeInfo(msgInfo.isOffLine(), msgInfo.getValidTime());

            UnVarnishedMessageJson messageJson = new UnVarnishedMessageJson(msgInfo.getTitle(), msgInfo.getContent(), pushTimeInfo);
            addParameter(body, "messageJson", JSON.toJSONString(messageJson));

            if (UserType.PUSHID == userType) {
                _url = SystemConstants.PUSH_APPID_UNVARNISHED_PUSHIDS;
            } else if (UserType.ALIAS == userType) {
                _url = SystemConstants.PUSH_APPID_UNVARNISHED_ALIAS;
            }
        } else if (PushType.STATUSBAR == pushType) {
            VarnishedMessage msgInfo = (VarnishedMessage) message;

            NoticeBarInfo noticeBarInfo = new NoticeBarInfo(msgInfo.getNoticeBarType(), msgInfo.getTitle(), msgInfo.getContent());
            NoticeExpandInfo noticeExpandInfo = new NoticeExpandInfo(msgInfo.getNoticeExpandType(), msgInfo.getNoticeExpandContent());
            ClickTypeInfo clickTypeInfo = new ClickTypeInfo(msgInfo.getClickType(), msgInfo.getUrl(), msgInfo.getParameters(), msgInfo.getActivity(), msgInfo.getCustomAttribute());
            PushTimeInfo pushTimeInfo = new PushTimeInfo(msgInfo.isOffLine(), msgInfo.getValidTime());
            NotificationType notificationType = new NotificationType(msgInfo.isVibrate(), msgInfo.isLights(), msgInfo.isSound());
            AdvanceInfo advanceInfo = new AdvanceInfo(msgInfo.isFixSpeed(), msgInfo.getFixSpeedRate(), msgInfo.isSuspend(),
                    msgInfo.isClearNoticeBar(), notificationType, msgInfo.isFixDisplay(), msgInfo.getFixStartDisplayDate(), msgInfo.getFixEndDisplayDate());

            VarnishedMessageJson messageJson = new VarnishedMessageJson(noticeBarInfo, noticeExpandInfo, clickTypeInfo, pushTimeInfo, advanceInfo);
            addParameter(body, "messageJson", JSON.toJSONString(messageJson));

            if (UserType.PUSHID == userType) {
                _url = SystemConstants.PUSH_APPID_VARNISHED_PUSHIDS;
            } else if (UserType.ALIAS == userType) {
                _url = SystemConstants.PUSH_APPID_VARNISHED_ALIAS;
            }
        }

        HttpResult httpResult = this.post(_url, body.toString());
        if (httpResult == null) {
            return null;
        }

        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        String msgId = httpResult.getMsgId();
        if (SUCCESS_CODE.equals(code)) {
            Map<String, List<String>> respTarget = new HashMap<String, List<String>>();
            if (StringUtils.isNotBlank(value)) {
                respTarget = JSONObject.parseObject(value, Map.class);
            }
            return ResultPack.succeed(code, msg, PushResult.build(msgId, respTarget));
        } else {
            return ResultPack.failed(code, msg);
        }
    }


    /**
     * 超时以及服务不可用异常返回null
     *
     * @param url
     * @param body
     * @return
     * @throws IOException
     */
    private HttpResult post(String url, String body) throws IOException {
        if (useSSL) {
            if (url.startsWith("http://")) {
                url = url.replace("http://", "https://");
            }
        }

        String bodyParam = body;
        if (StringUtils.isNotBlank(bodyParam) && bodyParam.charAt(0) == 38) {
            bodyParam = body.toString().substring(1);
        }

        HttpURLConnection conn;
        int status;
        try {
            logger.fine("post to: " + url);
            conn = this.doPost(url, bodyParam);
            status = conn.getResponseCode();
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException posting to push", e);
            return null;
        }
       /*
        5xx（服务器错误）
        这些状态代码表示，服务器在尝试处理请求时发生内部错误。这些错误可能是服务器本身的错误，而不是请求出错。
        代码 说明
        500（服务器内部错误） 服务器遇到错误，无法完成请求。
        501（尚未实施） 服务器不具备完成请求的功能。例如，当服务器无法识别请求方法时，服务器可能会返回此代码。
        502（错误网关） 服务器作为网关或代理，从上游服务器收到了无效的响应。
        503（服务不可用） 目前无法使用服务器（由于超载或进行停机维护）。通常，这只是一种暂时的状态。
        504（网关超时） 服务器作为网关或代理，未及时从上游服务器接收请求。
        505（HTTP 版本不受支持） 服务器不支持请求中所使用的 HTTP 协议版本。
       */
        if (status / 100 == 5) {
            logger.fine("push service is unavailable (status " + status + ")");
            return null;
        } else {
            String responseBody;
            if (status != 200) {
                try {
                    responseBody = getAndClose(conn.getErrorStream());
                    logger.finest("Plain post error response: " + responseBody);
                } catch (IOException e) {
                    responseBody = "N/A";
                    logger.log(Level.FINE, "Exception reading response: ", e);
                }
                throw new InvalidRequestException(status, responseBody);
            } else {
                try {
                    responseBody = getAndClose(conn.getInputStream());
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Exception reading response: ", e);
                    return null;
                }
                try {
                    JSONObject json = parseObject(responseBody);
                    return (new HttpResult.Builder()).fromJson(json);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Exception parsing response: ", e);
                    throw new IOException("Invalid response from push: " + responseBody);
                }
            }
        }
    }

    private ResultPack<PushResult> pushMessageByTaskId(UserType userType, PushType pushType, long appId, long taskId, String targets, int retries) throws IOException {
        int attempt = 0;
        ResultPack<PushResult> result;
        int backoff = 1000;
        boolean tryAgain;
        do {
            ++attempt;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format("attempt [%s] to taskId [%s] to %s [%s]", attempt, taskId, userType, targets));
            }
            result = this.pushMessageByTaskIdNoRetry(userType, pushType, appId, taskId, targets);
            tryAgain = result == null && attempt <= retries;
            backoff = getBackoffTime(backoff, tryAgain);
        } while (tryAgain);
        if (result == null) {
            throw new IOException(String.format("Could not send message after [%s] attempts", attempt));
        } else {
            return result;
        }
    }

    private ResultPack<PushResult> pushMessageByTaskIdNoRetry(UserType userType, PushType pushType, long appId, long taskId, String targets) throws IOException {
        String _url = null;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "taskId", String.valueOf(taskId));

        if (UserType.PUSHID == userType) {
            addParameter(body, "pushIds", targets);
        } else if (UserType.ALIAS == userType) {
            addParameter(body, "alias", targets);
        }


        if (PushType.DIRECT == pushType) {
            if (UserType.PUSHID == userType) {
                _url = SystemConstants.PUSH_APPID_UNVARNISHED_TASKID_PUSHIDS;
            } else if (UserType.ALIAS == userType) {
                _url = SystemConstants.PUSH_APPID_UNVARNISHED_TASKID_ALIAS;
            }
        } else if (PushType.STATUSBAR == pushType) {
            if (UserType.PUSHID == userType) {
                _url = SystemConstants.PUSH_APPID_VARNISHED_TASKID_PUSHIDS;
            } else if (UserType.ALIAS == userType) {
                _url = SystemConstants.PUSH_APPID_VARNISHED_TASKID_ALIAS;
            }
        }
        HttpResult httpResult = this.post(_url, body.toString());
        if (httpResult == null) {
            return null;
        }
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        String msgId = String.valueOf(taskId);

        if (SUCCESS_CODE.equals(code)) {
            Map<String, List<String>> respTarget = new HashMap<String, List<String>>();
            if (StringUtils.isNotBlank(value)) {
                respTarget = JSONObject.parseObject(value, Map.class);
            }
            return ResultPack.succeed(code, msg, PushResult.build(msgId, respTarget));
        } else {
            return ResultPack.failed(code, msg);
        }
    }

    /**
     * List 转为 str
     *
     * @param pushIds
     * @return
     */
    private String list2Str(List<String> pushIds) {
        StringBuilder sb = new StringBuilder(pushIds.get(0));
        for (int i = 1; i < pushIds.size(); ++i) {
            sb.append(",").append(pushIds.get(i));
        }
        return sb.toString();
    }

    enum UserType {

        PUSHID(0, "pushId"), ALIAS(1, "alias");
        private Integer key;
        private String value;

        UserType(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
