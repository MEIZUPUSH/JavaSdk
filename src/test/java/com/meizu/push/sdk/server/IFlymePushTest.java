package com.meizu.push.sdk.server;


import com.meizu.push.sdk.constant.CallBackType;
import com.meizu.push.sdk.constant.ExtraParam;
import com.meizu.push.sdk.constant.PushType;
import com.meizu.push.sdk.constant.ScopeType;
import com.meizu.push.sdk.server.constant.ErrorCode;
import com.meizu.push.sdk.server.constant.PushResponseCode;
import com.meizu.push.sdk.server.constant.ResultPack;
import com.meizu.push.sdk.server.model.push.PushResult;
import com.meizu.push.sdk.server.model.push.UnVarnishedMessage;
import com.meizu.push.sdk.server.model.push.VarnishedMessage;
import com.meizu.push.sdk.server.model.statistics.DailyPushStatics;
import com.meizu.push.sdk.server.model.statistics.TaskStatistics;
import com.meizu.push.sdk.utils.DateUtils;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.meizu.push.sdk.utils.DateUtils.FORMAT_STRING;

/**
 * Created by wangxinguo on 2016-8-21.
 */
public class IFlymePushTest {


    /**
     * 平台注册应用secretKey
     */
    public static final String APP_SECRET_KEY = "secret";
    /**
     * 平台注册应用ID
     */
    public static final Long appId = 100999L;

    public static final String callbackURL = "callbackurl";


    /**
     * 通知栏消息推送（pushMessage）
     *
     * @throws Exception
     */
    @Test
    public void testVarnishedMessagePush() throws Exception {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //组装消息
        VarnishedMessage message = new VarnishedMessage.Builder().appId(appId)
                .title("Java SDK 推送标题").content("Java SDK 推送内容")
                .build();

        //目标用户
        List<String> pushIds = new ArrayList<String>();
        pushIds.add("pushId_1");
        pushIds.add("pushId_2");

        // 1 调用推送服务
        ResultPack<PushResult> result = push.pushMessage(message, pushIds,2);
        handleResult(result);
    }


    /**
     * 别名通知栏消息推送（pushMessage）
     *
     * @throws Exception
     */
    @Test
    public void testVarnishedMessagePushByAlias() throws Exception {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //组装消息
        VarnishedMessage message = new VarnishedMessage.Builder().appId(appId)
                .title("Java SDK 推送标题").content("Java SDK 推送内容")
                .build();

        //目标用户
        List<String> alias = new ArrayList<String>();
        alias.add("alias1");
        alias.add("alias2");

        // 1 调用推送服务
        ResultPack<PushResult> result = push.pushMessageByAlias(message, alias);
        // 2 处理推送结果
        handleResult(result);
    }

    /**
     * 透传消息推送（pushMessage）
     *
     * @throws Exception
     */
    @Test
    public void testUnVarnishedMessagePush() throws Exception {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);
        //组装透传消息
        UnVarnishedMessage message = new UnVarnishedMessage.Builder()
                .appId(appId)
                .title("Java SDK 透传推送标题")
                .content("Java Sdk透传推送内容")
                .isOffLine(true)
                .validTime(10)
                .build();

        //目标用户
        List<String> pushIds = new ArrayList<String>();
        pushIds.add("pushId_1");
        pushIds.add("pushId_2");

        ResultPack<PushResult> result = push.pushMessage(message, pushIds);
        // 2 处理推送结果
        handleResult(result);
    }

    /**
     * 别名透传推送
     *
     * @throws Exception
     */
    @Test
    public void testUnVarnishedMessagePushByALias() throws Exception {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);
        //组装透传消息
        UnVarnishedMessage message = new UnVarnishedMessage.Builder()
                .appId(appId)
                .title("Java SDK 透传推送标题")
                .content("Java Sdk透传推送内容")
                .build();

        //目标用户
        List<String> alias = new ArrayList<String>();
        alias.add("alias");
        alias.add("alias2");

        ResultPack<PushResult> result = push.pushMessageByAlias(message, alias);
        // 2 处理推送结果
        handleResult(result);
    }

    /**
     * 获取通知栏推送taskId(getTaskId)
     *
     * @throws Exception
     */
    @Test
    public void testGetVarnishedMessageTaskId() throws Exception {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //组装消息
        VarnishedMessage message = new VarnishedMessage.Builder().appId(appId)
                .title("java Sdk推送标题").content("java Sdk 推送内容")
                .build();

        ResultPack<Long> result = push.getTaskId(PushType.STATUSBAR, message);
        System.out.println(result);
    }

    /**
     * 获取透传推送taskId(getTaskId)
     *
     * @throws Exception
     */
    @Test
    public void testGetUnVarnishedMessageTaskId() throws Exception {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //组装消息
        UnVarnishedMessage message = new UnVarnishedMessage.Builder()
                .appId(appId)
                .title("java sdk 推送标题")
                .content("java sdk 推送内容")
                .build();

        ResultPack<Long> result = push.getTaskId(PushType.DIRECT, message);
        System.out.println(result);
    }

    /**
     * 任务透传消息推送（pushMessageByTaskId）
     *
     * @throws IOException
     */
    @Test
    public void testUnVarnishedMessagePushByTaskId() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //目标用户
        List<String> pushIds = new ArrayList<String>();
        pushIds.add("pushId_1");
        pushIds.add("pushId_2");

        //透传消息任务推送
        Long taskId = 123l;
        ResultPack<PushResult> result = push.pushMessageByTaskId(PushType.DIRECT, appId, taskId, pushIds);
        // 2 处理推送结果
        handleResult(result);
    }

    /**
     * 任务通知栏消息推送（pushMessageByTaskId）
     *
     * @throws IOException
     */
    @Test
    public void testVarnishedMessagePushByTaskId() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //目标用户
        List<String> pushIds = new ArrayList<String>();
        pushIds.add("pushId_1");
        pushIds.add("pushId_2");

        //通知栏任务消息推送
        Long taskId = 123l;
        ResultPack<PushResult> result = push.pushMessageByTaskId(PushType.STATUSBAR, appId, taskId, pushIds);
        // 2 处理推送结果
        handleResult(result);
    }

    /**
     * 别名任务通知栏消息推送
     *
     * @throws IOException
     */
    @Test
    public void testVarnishedMessagePushAliasByTaskId() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //目标用户
        List<String> alias = new ArrayList<String>();
        alias.add("alias1");
        alias.add("alias2");

        //通知栏任务消息推送
        Long taskId = 45361L;
        ResultPack<PushResult> result = push.pushAliasMessageByTaskId(PushType.STATUSBAR, appId, taskId, alias);
        // 2 处理推送结果
        handleResult(result);
    }

    /**
     * 别名任务透传消息推送
     *
     * @throws IOException
     */
    @Test
    public void testUnVarnishedMessagePushAliasByTaskId() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //目标用户
        List<String> alias = new ArrayList<String>();
        alias.add("alias1");
        alias.add("alias2");

        //透传消息任务推送
        long taskId = 45407L;
        ResultPack<PushResult> result = push.pushAliasMessageByTaskId(PushType.DIRECT, appId, taskId, alias);
        // 2 处理推送结果
        handleResult(result);
    }


    /**
     * 应用全部推送(pushToApp)
     *
     * @throws IOException
     */
    @Test
    public void testVarnishedMessagePushToApp() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //通知栏全部消息推送
        VarnishedMessage message = new VarnishedMessage.Builder().appId(appId)
                .title("java Sdk 全部推送标题").content("java Sdk 全部推送内容")
                .build();
        ResultPack<Long> result = push.pushToApp(PushType.STATUSBAR, message);
        System.out.println(result);
    }


    /**
     * 应用全部推送(pushToApp)
     *
     * @throws IOException
     */
    @Test
    public void testUnVarnishedMessagePushToApp() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //透传全部推送
        UnVarnishedMessage message2 = new UnVarnishedMessage.Builder()
                .appId(appId)
                .title("Java SDK 全部推送标题")
                .content("Java Sdk全部推送内容")
                .isOffLine(true)
                .validTime(10)
                .pushTimeType(1)
                .startTime(new Date())
                .build();
        ResultPack<Long> result = push.pushToApp(PushType.DIRECT, message2);
        System.out.println(result);
    }

    /**
     * 标签推送(pushToTag)
     *
     * @throws IOException
     */
    @Test
    public void testVarnishedMessagePushToTag() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //推送标签
        List<String> tagName = new ArrayList<String>();
        tagName.add("news");
        tagName.add("tech");

        //通知栏标签推送
        VarnishedMessage varnishedMessage = new VarnishedMessage.Builder().appId(appId)
                .title("java Sdk 标签推送标题").content("java Sdk 标签推送内容")
                .build();
        ResultPack<Long> result = push.pushToTag(PushType.STATUSBAR, varnishedMessage, tagName, ScopeType.INTERSECTION);
        System.out.println(result);
    }

    /**
     * 标签推送(pushToTag)
     *
     * @throws IOException
     */
    @Test
    public void testUnVarnishedMessagePushToTag() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //推送标签
        List<String> tagName = new ArrayList<String>();
        tagName.add("news");
        tagName.add("tech");

        //透传标签推送
        UnVarnishedMessage unVarnishedMessage = new UnVarnishedMessage.Builder()
                .appId(appId)
                .title("Java SDK 标签推送标题")
                .content("Java Sdk标签推送内容")
                .build();
        ResultPack<Long> result = push.pushToTag(PushType.DIRECT, unVarnishedMessage, tagName, ScopeType.UNION);
        System.out.println(result);
    }


    /**
     * 取消推送任务(cancelTaskPush) 只针对全网推送生效
     *
     * @throws IOException
     */
    @Test
    public void testCancelTaskPush() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);
        long taskId = 123l;
        ResultPack resultPack = push.cancelTaskPush(PushType.STATUSBAR, appId, taskId);
        System.out.println(resultPack);
    }

    /**
     * 获取任务统计结果
     *
     * @throws IOException
     */
    @Test
    public void testGetTaskStatistics() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);
        long taskId = 44760L;
        ResultPack<TaskStatistics> resultPack = push.getTaskStatistics(appId, taskId);
        System.out.println(resultPack);
    }

    @Test
    public void testDailyPushStatics() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);
        Date startTime = DateUtils.str2Date("2017-06-03", "yyyy-MM-dd");
        Date endTime = DateUtils.str2Date("2017-06-10", "yyyy-MM-dd");
        ResultPack<List<DailyPushStatics>> resultPack = push.dailyPushStatics(appId, startTime, endTime);
        System.out.println(resultPack);
    }

    @Test
    public void testPushIDCallback() throws Exception {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //组装消息
        VarnishedMessage message = new VarnishedMessage.Builder().appId(appId)
                .title("Java SDK 推送标题").content("Java SDK 推送内容")
                .extra(ExtraParam.CALLBACK.getKey(), callbackURL)
                .extra(ExtraParam.CALLBACK_PARAM.getKey(), "param")
                .extra(ExtraParam.CALLBACK_TYPE.getKey(), CallBackType.RECEIVE.getKey())
                .build();

        //目标用户
        List<String> pushIds = new ArrayList<String>();
        pushIds.add("pushId_1");
        pushIds.add("pushId_2");

        // 1 调用推送服务
        ResultPack<PushResult> result = push.pushMessage(message, pushIds);
        handleResult(result);
    }

    @Test
    public void testAliasCallback() throws Exception {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //组装消息
        VarnishedMessage message = new VarnishedMessage.Builder().appId(appId)
                .title("Java SDK 推送标题").content("Java SDK 推送内容")
                .extra(ExtraParam.CALLBACK.getKey(), callbackURL)
                .extra(ExtraParam.CALLBACK_PARAM.getKey(), "param")
                .extra(ExtraParam.CALLBACK_TYPE.getKey(), CallBackType.RECEIVE.getKey())
                .build();

        //目标用户
        List<String> alias = new ArrayList<String>();
        alias.add("alias_1");
        alias.add("alias_2");

        // 1 调用推送服务
        ResultPack<PushResult> result = push.pushMessageByAlias(message, alias);
        handleResult(result);
    }

    private static Date str2Date(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_STRING);
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("时间转化格式错误" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
        }
    }

    /**
     * 处理推送结果
     *
     * @param result
     */
    private void handleResult(ResultPack<PushResult> result) {
        if (result.isSucceed()) {
            // 2 调用推送服务成功 （其中map为设备的具体推送结果，一般业务针对超速的code类型做处理）
            PushResult pushResult = result.value();
            String msgId = pushResult.getMsgId();//推送消息ID，用于推送流程明细排查
            Map<String, List<String>> targetResultMap = pushResult.getRespTarget();//推送结果，全部推送成功，则map为empty
            System.out.println("push msgId:" + msgId);
            System.out.println("push targetResultMap:" + targetResultMap);
            if (targetResultMap != null && !targetResultMap.isEmpty()) {
                // 3 判断是否有获取超速的target
                if (targetResultMap.containsKey(PushResponseCode.RSP_SPEED_LIMIT.getValue())) {
                    // 4 获取超速的target
                    List<String> rateLimitTarget = targetResultMap.get(PushResponseCode.RSP_SPEED_LIMIT.getValue());
                    System.out.println("rateLimitTarget is :" + rateLimitTarget);
                    //TODO 5 业务处理，重推......
                }
            }
        } else {
            // 调用推送接口服务异常 eg: appId、appKey非法、推送消息非法.....
            // result.code(); //服务异常码
            // result.comment();//服务异常描述

            //全部超速
            if (String.valueOf(ErrorCode.APP_REQUEST_EXCEED_LIMIT.getValue()).equals(result.code())) {
                //TODO 5 业务处理，重推......
            }
            System.out.println(String.format("pushMessage error code:%s comment:%s", result.code(), result.comment()));
        }
    }

}