# 魅族开放平台PUSH系统JAVA版本SDK

**文档变更记录**

| 日期 | 作者 | 版本 | 变更描述 | 下载
| --- | --- | --- | --- | --- |
| 2016-08-26 | JasperXgwang | 1.0.0-RC01 | 撰写文档 | [download](https://github.com/MEIZUPUSH/JavaSdk/raw/master/version/Flyme_Push_JAVA_SDK_V1.0.0-RC01.zip)

# 目录 <a name="index"/>
* [一.类型定义](#type_def_index)
    * [推送服务(IFlymePush)](#IFlymePush_index)
    * [通知栏消息体(Message)](#Message_index)
      * [通知栏消息(VarnishedMessage)](#VarnishedMessage_index)
      * [透传消息(UnVarnishedMessage)](#UnVarnishedMessage_index)
    * [接口返回值(ResultPack)](#ResultPack_index)
    * [接口响应码定义(ErrorCode)](#ErrorCode_index)
    * [推送响应码定义(PushResponseCode)](#PushResponseCode_index)    
    * [推送类型(PushType)](#PushType_index) 
* [二.接口说明](#api_def_index) 
    * [非任务推送](#UnTaskPush_index)      
         * [通知栏消息推送(pushMessage)](#VarnishedMessage_push_index)    
         * [透传消息推送(pushMessage)](#UnVarnishedMessage_push_index)
    * [任务推送](#taskPush_index)      
         * [获取推送taskId(getTaskId)](#getTaskId_index)    
         * [任务消息推送(pushMessageByTaskId）](#pushMessageByTaskId_index)    
         * [应用全部推送(pushToApp)](#pushToApp_index) 
         * [取消推送任务(cancelTaskPush)](#cancelTaskPush_index) 

    

# 类型定义 <a name="type_def_index"/>
## 推送服务(IFlymePush) <a name="IFlymePush_index"/>
> 调用该类实例的方法来推送消息， 构造函数说明如下：

参数名称|类型|必填|默认|描述
---|---|---|---|---
appSecret|String|是|null|注册应用appSecret
useSSL|Boolean|否|false| 是否使用https接口调用：true 使用https连接，false使用http连接

## 通知栏消息体(Message) <a name="Message_index"/>
> 推送消息实体（抽象类）

子类|说明
---|---
VarnishedMessage|通知栏消息体
UnVarnishedMessage|透传消息体

### 通知栏消息(VarnishedMessage) <a name="VarnishedMessage_index"/>

参数名称|类型|必填|默认|描述
---|---|---|---|---
appId|Long|是|null|注册应用appId
title|String|是|null|推送标题, 【字数限制1~32】
content|String|是|null|推送内容, 【字数限制1~100】
noticeBarType|int|否|0|通知栏样式(0, "标准")【非必填，默认值为0】
noticeExpandType|int|否|0|展开方式 (0, "标准"),(1, "文本")【非必填，默认值为0】
noticeExpandContent|String|否|null|展开内容, 【noticeExpandType为文本时，必填】
clickType|int|否|0|点击动作 (0,"打开应用"),(1,"打开应用页面"),(2,"打开URI页面"),【非必填，默认值为0】
url|String|否|null|URI页面地址, 【clickType为打开URI页面时，必填, 长度限制1000byte】
parameters|JSONObject|否|null|透传参数 【JSON格式，非必填】
activity|String|否|null|应用页面地址, 【clickType为打开应用页面时，必填, 长度限制1000byte】
isOffLine|Boolean|否|true|是否进离线消息, (false 否 true 是) 【非必填，默认值为true】
validTime|int|否|24|有效时长 (1~72小时内的正整数), 【isOffLine值为true时，必填，值的范围1~72】
pushTimeType|int|否|0|定时推送 (0, "即时"),(1, "定时"), 【只对全部用户推送生效】
startTime|Date|否|null|任务定时开始时间,【非必填，pushTimeType为True必填】只对全部用户推送生效
isFixSpeed|Boolean|否|false|是否定速推送, 【非必填，默认值为False】
fixSpeedRate|Long|否|0|定速速率,【isFixSpeed为true时，必填】
isSuspend|Boolean|否|true|是否通知栏悬浮窗显示 (true显示，false不显示) 【非必填，默认True】
isClearNoticeBar|Boolean|否|true|是否可清除通知栏 (true可以，false不可以) ，【非必填，默认true】
vibrate|Boolean|否|true|震动 (false关闭  true 开启) , 【非必填，默认true】
lights|Boolean|否|true|闪光 (false关闭  true 开启) , 【非必填，默认true】
sound|Boolean|否|true|声音 (false关闭  true 开启) , 【非必填，默认true】

### 透传消息(UnVarnishedMessage) <a name="UnVarnishedMessage_index"/>
参数名称|类型|必填|默认|描述
---|---|---|---|---
appId|Long|是|null|注册应用appId
title|String|否|null|推送标题,任务推送建议填写，方便数据查询,【字数限制1~32】
content|String|是|null|推送内容,【必填，字数限制2000byte以内】
isOffLine|Boolean|否|true|是否进离线消息,【非必填，默认为true】
validTime|int|否|24|有效时长 (1~72小时内的正整数),【isOffLine值为true时，必填，值的范围1--72】
pushTimeType|int|否|0|定时推送 (0, "即时"),(1, "定时"), 【只对全部用户推送生效】
startTime|Date|否|null|任务定时开始时间, 【pushTimeType为1必填】只对全部用户推送生效
isFixSpeed|Boolean|否|false|是否定速推送,【非必填，默认值为false】
fixSpeedRate|Long|否|0|定速速率 【isFixSpeed为true时，必填】

## 接口返回值(ResultPack) <a name="ResultPack_index"/>
方法名称|类型|描述
---|---|--- 
code()|String|接口响应码
Comment()|String|接口响应描述
value()|T|接口响应内容
errorCode()|Enum|接口响应异常枚举 详见ErrorCode

## 接口响应码定义(ErrorCode) <a name="ErrorCode_index"/>
名称|Code|Commen
---|---|--- 
UNKNOWN_ERROR|-1|未知错误
SUCCESS|200|成功
SYSTEM_ERROR|1001|系统错误
SYSTEM_BUSY|1003|服务器忙
PARAMETER_ERROR|1005|参数错误，请参考API文档
INVALID_SIGN|1006|签名认证失败
INVALID_APPLICATION_ID|110000|appId不合法
INVALID_APPLICATION_KEY|110001|appKey不合法
UNSUBSCRIBE_PUSHID|110002|pushId未注册
INVALID_PUSHID|110003|pushId非法
PARAM_BLANK|110004|参数不能为空
APP_IN_BLACK_LIST|110009|应用被加入黑名单
APP_REQUEST_EXCEED_LIMIT|110010|应用请求频率过快
APP_PUSH_TIME_EXCEED_LIMIT|110051|超过该应用的次数限制
APP_REQUEST_PUSH_LIMIT|110019|超过该应用每天推送次数限制
INVALID_APPLICATION_PACKAGENAME|110031|packageName不合法
INVALID_TASK_ID|110032|非法的taskId
INVALID_APPLICATION_SECRET|110033|非法的appSecret

## 推送响应码定义（PushResponseCode） <a name="PushResponseCode_index"/>
名称|Code|Commen
---|---|--- 
RSP_NO_AUT|201|没有权限，服务器主动拒绝
RSP_DB_ERROR|501|推送消息失败（db_error）
RSP_INTERNAL_ERROR|513|推送消息失败
RSP_SPEED_LIMIT|518|推送超过配置的速率
RSP_OVERFLOW|519|推送消息失败服务过载
RSP_REPEATED|520|消息折叠（短时间内同一设备同一消息收到多次）
RSP_UNSUBSCRIBE_PUSHID|110002|pushId未订阅
RSP_INVALID_PUSHID|110003|pushId非法

## 推送类型（PushType）<a name="PushType_index"/>
枚举|类型|描述
---|---|--- 
STATUSBAR|Enum|通知栏消息类型
DIRECT|Enum|透传消息类型

# 接口说明 <a name="api_def_index"/>
## 非任务推送 <a name="UnTaskPush_index"/>
### 描述
> 向指定的pushId推送消息
```
注：推送平台使用pushId来标识每个独立的用户，每一台终端上每一个app拥有一个独立的pushId
```

### 应用场景
> - 场景1：查找手机业务需要远程定位位置，可发送消息指令到对应的设备
> - 场景2：社区用户回帖消息提醒，用户对发表的帖子有最新回复时，消息提醒发帖者

#### 通知栏消息推送（pushMessage） <a name="VarnishedMessage_push_index"/>
- 接口说明

接口|说明
---|---
`ResultPack<Map<Integer, List<String>>> pushMessage(VarnishedMessage message, List<String> pushIds)`|推送通知栏消息
`ResultPack<Map<Integer, List<String>>> pushMessage(VarnishedMessage message, List<String> pushIds, int retries)`|推送通知栏消息

- 参数说明


参数名称|类型|必需|默认|描述
---|---|---|---|---
message|VarnishedMessage|是|null|推送消息
pushIds|List<String>|是|null|推送目标
retries|int|否|0|超时or异常重试次数

- 返回值

```
Map<Integer, List<String>>

key：推送响应码
value：响应码对应的目标用户 
注：只返回不合法、超速以及推送失败的目标用户，业务一般对超速的pushId处理。
```

- 示例


```java
    /**
     * 通知栏消息推送（pushMessage）
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
                .clickType(2).url("http://www.baidu.com").parameters(JSON.parseObject("{\"k1\":\"value1\",\"k2\":0,\"k3\":\"value3\"}"))
                .offLine(true).validTime(12)
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
```


#### 透传消息推送（pushMessage） <a name="UnVarnishedMessage_push_index"/>
- 接口说明

接口|说明
---|---
`ResultPack<Map<Integer, List<String>>> pushMessage(UnVarnishedMessage message, List<String> pushIds)`|推送透传消息
`ResultPack<Map<Integer, List<String>>> pushMessage(UnVarnishedMessage message, List<String> pushIds, int retries)`|推送透传消息

- 参数说明

参数名称|类型|必需|默认|描述
---|---|---|---|---
message|UnVarnishedMessage|是|null|推送消息
pushIds|List<String>|是|null|推送目标
retries|int|否|0|超时or异常重试次数

- 返回值

```
Map<Integer, List<String>>

key：推送响应码
value：响应码对应的目标用户 
注：只返回不合法、超速以及推送失败的目标用户，业务一般对超速的pushId处理。
```

- 示例


```java
    /**
     * 透传消息推送（pushMessage）
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
```


## 任务推送 <a name="taskPush_index"/>
### 描述

> ```
> 首先获取推送的taskId,然后通过taskId向指定的pushId推送消息。
> ```

### 应用场景
> 浏览器对指定的某一大批量pushId用户推送活动或者新闻消息，通过先获取taskId，然后通过taskId批量推送，推送过程中可以根据taskId时时获取推送统计结果。

#### 获取推送taskId(getTaskId) <a name="getTaskId_index"/>
- 接口说明

接口|说明
---|---
ResultPack<Long> getTaskId(PushType pushType, Message message)|获取推送taskId

- 参数说明

参数名称|类型|必需|默认|描述
---|---|---|---|---
pushType|PushType|是|null|消息类型
message|Message|是|null|消息体

- 返回值

```
Long  任务ID
```

- 示例


```java
    /**
     * 获取通知栏推送taskId(getTaskId)
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
                .clickType(2).url("http://www.baidu.com").parameters(JSON.parseObject("{\"k1\":\"value1\",\"k2\":0,\"k3\":\"value3\"}"))
                .offLine(true).validTime(12)
                .suspend(true).clearNoticeBar(true).vibrate(false).lights(false).sound(false)
                .fixSpeed(true).fixSpeedRate(20)
                .build();

        ResultPack<Long> result = push.getTaskId(PushType.STATUSBAR, message);
        System.out.println(result);
    }
```

```java
    /**
     * 获取透传推送taskId(getTaskId)
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
```


#### 任务消息推送（pushMessageByTaskId）<a name="pushMessageByTaskId_index"/>
- 接口说明

接口|说明
---|---
`ResultPack<Map<Integer, List<String>>> pushMessageByTaskId(PushType pushType, long appId, long taskId, List<String> pushIds)`|任务消息推送
`ResultPack<Map<Integer, List<String>>> pushMessageByTaskId(PushType pushType, long appId, long taskId, List<String> pushIds, int retries)`|任务消息推送
- 参数说明

参数名称|类型|必需|默认|描述
---|---|---|---|---
pushType|PushType|是|null|消息类型
appId|Long|是|null|推送应用ID
taskId|Long|是|null|推送任务ID
pushIds|List<String>|是|null|推送目标
retries|int|否|0|超时or异常重试次数

- 返回值


```
Map<Integer, List<String>> 

key：推送响应码
Value：响应码对应的目标用户 
注：只返回不合法、超速以及推送失败的目标用户，业务一般对超速的pushId对处理
```

- 示例


```java
    /**
     * 任务消息推送（pushMessageByTaskId）
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
```


#### 应用全部推送(pushToApp) <a name="pushToApp_index"/>
- 接口说明

接口|说明
---|---
`ResultPack<Long> pushToApp(PushType pushType, Message message)`|应用全部推送

- 参数说明

参数名称|类型|必需|默认|描述
---|---|---|---|---
pushType|PushType|是|null|消息类型
message|Message|是|null|消息体

- 返回值

```
Long  任务ID
```

- 示例

```java
    /**
     * 应用全部推送(pushToApp)
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
                .clickType(2).url("http://www.baidu.com").parameters(JSON.parseObject("{\"k1\":\"value1\",\"k2\":0,\"k3\":\"value3\"}"))
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
```


#### 取消推送任务(cancelTaskPush) <a name="cancelTaskPush_index"/>
- 接口说明

接口|说明
---|---
`ResultPack<Boolean> cancelTaskPush(PushType pushType, long appId, long taskId)`|只针对全部用户推送且推送状态为待推送或者推送中的任务取消

- 参数说明

参数名称|类型|必需|默认|描述
---|---|---|---|---
pushType|PushType|是|null|消息类型
appId|Long|是|null|应用ID
taskId|Long|是|null|任务ID

- 返回值

```
Boolean  true:成功  false：失败
```

- 示例

```java
    /**
     * 取消推送任务(cancelTaskPush) 只针对全网推送生效
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
```


