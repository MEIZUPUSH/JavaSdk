package com.meizu.push.sdk.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meizu.push.sdk.constant.PushType;
import com.meizu.push.sdk.constant.SystemConstants;
import com.meizu.push.sdk.server.constant.ResultPack;
import com.meizu.push.sdk.server.model.sub.AliasInfo;
import com.meizu.push.sdk.server.model.HttpResult;
import com.meizu.push.sdk.server.model.sub.SwitchStatusInfo;
import com.meizu.push.sdk.server.model.sub.TagInfo;
import com.meizu.push.sdk.utils.CollectionUtils;
import com.meizu.push.sdk.utils.HttpClient;
import com.meizu.push.sdk.utils.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * 推送订阅服务接口
 *
 * @author wangxinguo <wangxinguo@meizu.com>
 * @date 2016-9-3
 */
public class IFlymePushSub extends HttpClient {

    private static final String SUCCESS_CODE = "200";

    public IFlymePushSub(long appId, String appSecret) {
        super(appId, appSecret);
    }

    public IFlymePushSub(long appId, String appSecret, boolean useSSL) {
        super(appId, appSecret, useSSL);
    }

    /**
     * 修改通知栏订阅开关状态
     *
     * @param pushId    订阅pushId
     * @param subSwitch true 打开  false 关闭
     * @return 修改后pushId对应的开关状态
     * @throws IOException
     */
    public ResultPack<SwitchStatusInfo> updateStatusbarSwitch(String pushId, Boolean subSwitch) throws IOException {
        return updateSwitch(PushType.STATUSBAR, pushId, subSwitch);
    }

    /**
     * 修改透传消息开关状态
     *
     * @param pushId    订阅pushId
     * @param subSwitch true 打开  false 关闭
     * @return 修改后pushId对应的开关状态
     * @throws IOException
     */
    public ResultPack<SwitchStatusInfo> updateDirectSwitch(String pushId, Boolean subSwitch) throws IOException {
        return updateSwitch(PushType.DIRECT, pushId, subSwitch);
    }

    /**
     * 同步修改所有开关状态
     *
     * @param pushId
     * @param subSwitch
     * @return
     * @throws IOException
     */
    public ResultPack<SwitchStatusInfo> updateAllSwitch(String pushId, Boolean subSwitch) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        if (subSwitch == null) {
            return ResultPack.failed("subSwitch is null");
        }
        String url = SystemConstants.CHANGE_ALL_SWITCH;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);
        addParameter(body, "subSwitch", subSwitch.toString());

        return http2SwitchResultPack(url, body);
    }

    /**
     * 获取订阅开关状态
     *
     * @param pushId
     * @return
     * @throws IOException
     */
    public ResultPack<SwitchStatusInfo> getRegisterSwitch(String pushId) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        String url = SystemConstants.GET_REGISTER_SWITCH;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);

        return http2SwitchResultPack(url, body);
    }

    /**
     * 别名订阅
     *
     * @param pushId
     * @param alias 60字符限制
     * @return
     * @throws IOException
     */
    public ResultPack<AliasInfo> subscribeAlias(String pushId, String alias) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        if (StringUtils.isEmpty(alias)) {
            return ResultPack.failed("alias is empty");
        }
        String url = SystemConstants.SUBSCRIBE_ALIAS;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);
        addParameter(body, "alias", alias);

        return http2AliasResultPack(url, body);
    }

    /**
     * 取消别名订阅
     *
     * @param pushId
     * @param alias
     * @return
     * @throws IOException
     */
    public ResultPack<AliasInfo> unSubscribeAlias(String pushId) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        String url = SystemConstants.UN_SUBSCRIBE_ALIAS;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);

        return http2AliasResultPack(url, body);
    }

    /**
     * 获取订阅别名
     *
     * @param pushId
     * @return
     * @throws IOException
     */
    public ResultPack<AliasInfo> getSubAlias(String pushId) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        String url = SystemConstants.GET_SUB_ALIAS;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);

        return http2AliasResultPack(url, body);
    }


    /**
     * 标签订阅
     *
     * @param pushId
     * @param tags   单个标签20个字符限制，100个标签数量限制
     * @return 订阅的标签
     * @throws IOException
     */
    public ResultPack<TagInfo> subscribeTags(String pushId, List<String> tags) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        if (CollectionUtils.isEmpty(tags)) {
            return ResultPack.failed("tags is empty");
        }
        String url = SystemConstants.SUBSCRIBE_TAGS;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);
        addParameter(body, "tags", CollectionUtils.list2Str(tags));

        return http2TagResultPack(url, body);
    }

    /**
     * 取消标签订阅
     *
     * @param pushId
     * @param tags
     * @return 取消后订阅的标签
     * @throws IOException
     */
    public ResultPack<TagInfo> unSubscribeTags(String pushId, List<String> tags) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        if (CollectionUtils.isEmpty(tags)) {
            return ResultPack.failed("tags is empty");
        }
        String url = SystemConstants.UN_SUBSCRIBE_TAGS;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);
        addParameter(body, "tags", CollectionUtils.list2Str(tags));

        return http2TagResultPack(url, body);
    }

    /**
     * 获取订阅标签
     *
     * @param pushId
     * @return
     * @throws IOException
     */
    public ResultPack<TagInfo> getSubTags(String pushId) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        String url = SystemConstants.GET_SUBSCRIBE_TAGS;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);

        return http2TagResultPack(url, body);
    }

    /**
     * 取消订阅所有标签
     *
     * @param pushId
     * @return
     * @throws IOException
     */
    public ResultPack<Boolean> unSubAllTags(String pushId) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        String url = SystemConstants.UN_SUB_ALL_TAGS;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);

        HttpResult httpResult = super.post(useSSL, url, body.toString());
        if (httpResult == null) {
            return ResultPack.failed("httpResult is null");
        }
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        if (SUCCESS_CODE.equals(code)) {
            if (StringUtils.isNotBlank(value)) {
                return ResultPack.succeed(code, msg, Boolean.parseBoolean(value));
            }
            return ResultPack.failed("response value is blank");
        } else {
            return ResultPack.failed(code, msg);
        }
    }

    private ResultPack<TagInfo> http2TagResultPack(String url, StringBuilder body) throws IOException {
        HttpResult httpResult = super.post(useSSL, url, body.toString());
        if (httpResult == null) {
            return ResultPack.failed("httpResult is null");
        }
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        if (SUCCESS_CODE.equals(code)) {
            if (StringUtils.isNotBlank(value)) {
                TagInfo tagInfo = JSON.parseObject(value, TagInfo.class);
                return ResultPack.succeed(code, msg, tagInfo);
            }
            return ResultPack.failed("response value is blank");
        } else {
            return ResultPack.failed(code, msg);
        }
    }


    private ResultPack<AliasInfo> http2AliasResultPack(String url, StringBuilder body) throws IOException {
        HttpResult httpResult = super.post(useSSL, url, body.toString());
        if (httpResult == null) {
            return ResultPack.failed("httpResult is null");
        }
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        if (SUCCESS_CODE.equals(code)) {
            if (StringUtils.isNotBlank(value)) {
                JSONObject jsonObject = JSON.parseObject(value);
                AliasInfo aliasInfo = new AliasInfo(jsonObject.getString("pushId"),
                        jsonObject.getString("alias"));
                return ResultPack.succeed(code, msg, aliasInfo);
            }
            return ResultPack.failed("response value is blank");
        } else {
            return ResultPack.failed(code, msg);
        }
    }


    private ResultPack<SwitchStatusInfo> updateSwitch(PushType pushType, String pushId, Boolean subSwitch) throws IOException {
        if (StringUtils.isEmpty(pushId)) {
            return ResultPack.failed("pushId is empty");
        }
        if (subSwitch == null) {
            return ResultPack.failed("subSwitch is null");
        }
        String _url = SystemConstants.CHANGE_REGISTER_SWITCH;
        StringBuilder body = newBody("appId", String.valueOf(appId));
        addParameter(body, "pushId", pushId);
        addParameter(body, "msgType", String.valueOf(pushType.getDesc()));
        addParameter(body, "subSwitch", subSwitch.toString());

        return http2SwitchResultPack(_url, body);
    }

    private ResultPack<SwitchStatusInfo> http2SwitchResultPack(String url, StringBuilder body) throws IOException {
        HttpResult httpResult = super.post(useSSL, url, body.toString());
        if (httpResult == null) {
            return ResultPack.failed("httpResult is null");
        }
        String code = httpResult.getCode();
        String msg = httpResult.getMessage();
        String value = httpResult.getValue();
        if (SUCCESS_CODE.equals(code)) {
            if (StringUtils.isNotBlank(value)) {
                JSONObject jsonObject = JSON.parseObject(value);
                SwitchStatusInfo switchStatusInfo = new SwitchStatusInfo(jsonObject.getString("pushId"),
                        jsonObject.getBoolean("barTypeSwitch"),
                        jsonObject.getBoolean("directTypeSwitch"));
                return ResultPack.succeed(code, msg, switchStatusInfo);
            }
            return ResultPack.failed("response value is blank");
        } else {
            return ResultPack.failed(code, msg);
        }
    }

}
