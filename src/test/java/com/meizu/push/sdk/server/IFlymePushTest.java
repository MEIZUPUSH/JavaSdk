package com.meizu.push.sdk.server;


import com.alibaba.fastjson.JSON;
import com.meizu.push.sdk.constant.PushType;
import com.meizu.push.sdk.constant.ScopeType;
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
    public static final String APP_SECRET_KEY = "appSecret";
    /**
     * 平台注册应用ID
     */
    public static final Long appId = 100999l;


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
                .noticeExpandType(1)
                .noticeExpandContent("展开文本内容")
                .clickType(2).url("http://push.meizu.com").parameters(JSON.parseObject("{\"k1\":\"value1\",\"k2\":0,\"k3\":\"value3\"}"))
                .offLine(true).validTime(12)
                .isFixDisplay(true).fixDisplayTime(str2Date("2017-10-01 12:00:00"), str2Date("2017-10-01 12:30:00"))
                .suspend(true).clearNoticeBar(true).vibrate(true).lights(true).sound(true)
                .build();

        //目标用户
        List<String> pushIds = new ArrayList<String>();
        pushIds.add("pushId_1");
        pushIds.add("pushId_2");
        try {
            ResultPack<Map<Integer, List<String>>> result = push.pushMessage(message, pushIds);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        try {
            ResultPack<Map<Integer, List<String>>> result = push.pushMessage(message, pushIds);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                .noticeExpandType(1)
                .noticeExpandContent("展开文本内容")
                .clickType(2).url("http://push.meizu.com").parameters(JSON.parseObject("{\"k1\":\"value1\",\"k2\":0,\"k3\":\"value3\"}"))
                .offLine(true).validTime(12)
                .isFixDisplay(true).fixDisplayTime(str2Date("2017-10-01 12:00:00"), str2Date("2017-10-01 12:30:00"))
                .suspend(true).clearNoticeBar(true).vibrate(false).lights(false).sound(false)
                .fixSpeed(true).fixSpeedRate(20)
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
     * 任务消息推送（pushMessageByTaskId）
     *
     * @throws IOException
     */
    @Test
    public void testPushPyTaskId() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //目标用户
        List<String> pushIds = new ArrayList<String>();
        pushIds.add("pushId_1");
        pushIds.add("pushId_2");

        //通知栏任务消息推送
        Long taskId = 123l;
        ResultPack<Map<Integer, List<String>>> result = push.pushMessageByTaskId(PushType.STATUSBAR, appId, taskId, pushIds, 0);
        System.out.println(result);

        //透传消息任务推送
        taskId = 123l;
        result = push.pushMessageByTaskId(PushType.DIRECT, appId, taskId, pushIds, 0);
        System.out.println(result);
    }

    /**
     * 应用全部推送(pushToApp)
     *
     * @throws IOException
     */
    @Test
    public void testPushToApp() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //通知栏全部消息推送
        VarnishedMessage message = new VarnishedMessage.Builder().appId(appId)
                .title("java Sdk 全部推送标题").content("java Sdk 全部推送内容")
                .noticeExpandType(1)
                .noticeExpandContent("展开文本内容")
                .clickType(2).url("http://push.meizu.com").parameters(JSON.parseObject("{\"k1\":\"value1\",\"k2\":0,\"k3\":\"value3\"}"))
                .offLine(true).validTime(12)
                .suspend(true).clearNoticeBar(true).vibrate(false).lights(false).sound(false)
                .fixSpeed(true).fixSpeedRate(30)
                .pushTimeType(1)
                .startTime(new Date())
                .build();
        ResultPack<Long> result = push.pushToApp(PushType.STATUSBAR, message);
        System.out.println(result);

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
        result = push.pushToApp(PushType.DIRECT, message2);
        System.out.println(result);
    }

    /**
     * 标签推送(pushToTag)
     *
     * @throws IOException
     */
    @Test
    public void testPushToTag() throws IOException {
        //推送对象
        IFlymePush push = new IFlymePush(APP_SECRET_KEY);

        //推送标签
        List<String> tagName = new ArrayList<String>();
        tagName.add("news");
        tagName.add("tech");

        //通知栏标签推送
        VarnishedMessage varnishedMessage = new VarnishedMessage.Builder().appId(appId)
                .title("java Sdk 标签推送标题").content("java Sdk 标签推送内容")
                .noticeExpandType(1)
                .noticeExpandContent("展开文本内容")
                .offLine(true).validTime(12)
                .suspend(true).clearNoticeBar(true).vibrate(false).lights(false).sound(false)
                .fixSpeed(true).fixSpeedRate(30)
                .pushTimeType(1)
                .startTime(new Date())
                .build();
        ResultPack<Long> result = push.pushToTag(PushType.STATUSBAR, varnishedMessage, tagName, ScopeType.INTERSECTION);
        System.out.println(result);

        //透传标签推送
        UnVarnishedMessage unVarnishedMessage = new UnVarnishedMessage.Builder()
                .appId(appId)
                .title("Java SDK 标签推送标题")
                .content("Java Sdk标签推送内容")
                .isOffLine(true)
                .validTime(10)
                .pushTimeType(1)
                .startTime(new Date())
                .build();
        result = push.pushToTag(PushType.DIRECT, unVarnishedMessage, tagName, ScopeType.UNION);
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

    private static Date str2Date(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_STRING);
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("时间转化格式错误" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
        }
    }

}