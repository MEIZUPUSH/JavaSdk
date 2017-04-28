package com.meizu.push.sdk.server;

import com.meizu.push.sdk.server.constant.ResultPack;
import com.meizu.push.sdk.server.model.sub.AliasInfo;
import com.meizu.push.sdk.server.model.sub.SwitchStatusInfo;
import com.meizu.push.sdk.server.model.sub.TagInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxinguo <wangxinguo@meizu.com>
 * @date 2017-4-28
 */
public class IFlymePushSubTest {

    /**
     * 平台注册应用secretKey
     */
    public static final String APP_SECRET_KEY = "secret";
    /**
     * 平台注册应用ID
     */
    public static final Long APP_ID = 100999L;

    /**
     * 订阅pushId
     */
    private static final String pushId = "RA50c6348036344485d01776773577c64740465480a6b";

    IFlymePushSub sub = new IFlymePushSub(APP_ID, APP_SECRET_KEY);


    @Test
    public void updateStatusbarSwitch() throws Exception {
        ResultPack<SwitchStatusInfo> resultPack = sub.updateStatusbarSwitch(pushId, true);
        System.out.println("updateStatusbarSwitch:" + resultPack);
    }

    @Test
    public void updateDirectSwitch() throws Exception {
        ResultPack<SwitchStatusInfo> resultPack = sub.updateDirectSwitch(pushId, false);
        System.out.println("updateDirectSwitch:" + resultPack);
    }

    @Test
    public void updateAllSwitch() throws Exception {
        ResultPack<SwitchStatusInfo> resultPack = sub.updateAllSwitch(pushId, true);
        System.out.println("updateAllSwitch:" + resultPack);
    }

    @Test
    public void getRegisterSwitch() throws Exception {
        ResultPack<SwitchStatusInfo> resultPack = sub.getRegisterSwitch(pushId);
        System.out.println("getRegisterSwitch:" + resultPack);
    }

    @Test
    public void subscribeAlias() throws Exception {
        ResultPack<AliasInfo> resultPack = sub.subscribeAlias(pushId, "flyme");
        System.out.println("subscribeAlias:" + resultPack);
    }

    @Test
    public void unSubscribeAlias() throws Exception {
        ResultPack<AliasInfo> resultPack = sub.unSubscribeAlias(pushId);
        System.out.println("unSubscribeAlias:" + resultPack);
    }

    @Test
    public void getSubAlias() throws Exception {
        ResultPack<AliasInfo> resultPack = sub.getSubAlias(pushId);
        System.out.println("getSubAlias:" + resultPack);
    }

    @Test
    public void subscribeTags() throws Exception {
        List<String> tags = new ArrayList<String>(2);
        tags.add("tag1");
        tags.add("tag2");
        ResultPack<TagInfo> resultPack = sub.subscribeTags(pushId, tags);
        System.out.println("subscribeTags:" + resultPack);
    }

    @Test
    public void unSubscribeTags() throws Exception {
        List<String> tags = new ArrayList<String>(2);
        tags.add("tag1");
        tags.add("tag2");
        ResultPack<TagInfo> resultPack = sub.unSubscribeTags(pushId, tags);
        System.out.println("unSubscribeTags:" + resultPack);
    }

    @Test
    public void getSubTags() throws Exception {
        ResultPack<TagInfo> resultPack = sub.getSubTags(pushId);
        System.out.println("getSubTags:" + resultPack);
    }

    @Test
    public void unSubAllTags() throws Exception {
        ResultPack<Boolean> resultPack = sub.unSubAllTags(pushId);
        System.out.println("unSubAllTags:" + resultPack);
    }

}