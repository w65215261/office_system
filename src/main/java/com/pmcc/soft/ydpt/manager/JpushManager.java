package com.pmcc.soft.ydpt.manager;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import org.springframework.stereotype.Service;

/**
 * Created by sunyake on 16/1/11.
 */
@Service
public class JpushManager {

    public static final String _masterSecret = SystemParamsUtil.getSysConfig().get("masterSecret").toString();
    public static final String _appKey = SystemParamsUtil.getSysConfig().get("appKey").toString();

    public static PushPayload buildPushAllPayload(String alert) {
        return PushPayload.alertAll(alert);
    }

    public static PushPayload buildIOSPayload(String alias,String alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios_winphone())
                .setAudience(Audience.alias(alias.replace(".", "_")))
                .setNotification(Notification.newBuilder()
                        .setAlert(alert)
                        .build())
                .build();
    }


    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll("推送测试");
    }

    public static String getJPushMasterSecret() {
        return SystemParamsUtil.getSysConfig().get("masterSecret").toString();
    }


    public static String getJPushAppKey() {
        return SystemParamsUtil.getSysConfig().get("appKey").toString();
    }


    public static JPushClient jPushClient = new JPushClient(_masterSecret, _appKey);
    // public static JPushClient jPushClient=null;

    public PushResult sendAllPush() {
        PushResult result = null;

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_all_alert();

        try {
            result = jPushClient.sendPush(payload);

        } catch (APIConnectionException e) {
            // Connection error, should retry later

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
        }


        return result;
    }

    public PushResult sendAndroidMessageWithAlias(String title, String msgContent, String alias) {
        PushResult result = null;
        try {
            result = jPushClient.sendAndroidMessageWithAlias(title, msgContent, alias);
        } catch (APIConnectionException e) {
            //  e.printStackTrace();
        } catch (APIRequestException e) {
            // e.printStackTrace();
            System.out.println("123123123");
        }
        return result;

    }

}
