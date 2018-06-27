package com.pmcc.soft.rest;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 推送
 * Created by LvXL on 2016/5/26.
 */
public class JPush {

    protected static final Logger LOG = LoggerFactory.getLogger(JPush.class);

    public static final String _masterSecret = SystemParamsUtil.getSysConfig().get("masterSecret").toString();
    public static final String _appKey = SystemParamsUtil.getSysConfig().get("appKey").toString();

    public static void main(String[] args) {
        Map<String, String> extras = CommonUtils.getExtras("PushJsonType_SP", "11111", "22222222", "3", "ok", "1", "0");
//        sendPushToAndroidAll("啊阿斯大法阿斯大法阿斯达发", "这是标题", extras);
//        sendPushToAndroidWithAlias("5a66e75bfc6b4054842a012a374929b3", "啊阿斯大法阿斯大法阿斯达发", "这是标题", extras);
        sendPushToIOSWithAlias("3c8bcf3279e740f8bca6d483c55a9391", "提示信息提示信息121111", "标题标题222222222", extras);
//        sendPushToIOSAll("提示信息提示信息121111", "标题标题222222222", extras);
    }

    /**
     * IOS推送 指定别名
     *
     * @param alias  别名
     * @param alert  内容
     * @param title  标题
     * @param extras 其他参数
     */
    public static void sendPushToIOSWithAlias(String alias, String alert, String title, Map<String, String> extras) {

        JPushClient jpushClient = new JPushClient(_masterSecret, _appKey);

        IosAlert iosAlert = IosAlert.newBuilder()
                .setTitleAndBody(title, alert)
                .build();
        PushPayload payload = buildPushObject_ios_alias_alert_TitleWithExtras(alias, iosAlert, extras);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * IOS推送 所有
     *
     * @param alert  内容
     * @param title  标题
     * @param extras 其他参数
     */
    public static void sendPushToIOSAll(String alert, String title, Map<String, String> extras) {

        JPushClient jpushClient = new JPushClient(_masterSecret, _appKey);

        IosAlert iosAlert = IosAlert.newBuilder()
                .setTitleAndBody(title, alert)
                .build();
        PushPayload payload = buildPushObject_ios_all_alert_TitleWithExtras(iosAlert, extras);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 安卓，指定名称
     *
     * @param alias  别名
     * @param alert  内容
     * @param title  标题
     * @param extras 其他参数
     */
    public static void sendPushToAndroidWithAlias(String alias, String alert, String title, Map<String, String> extras) {

        JPushClient jpushClient = new JPushClient(_masterSecret, _appKey);

        PushPayload payload = buildPushObject_android_alias_alert_TitleWithExtras(alias, alert, title, extras);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

    /**
     * 安卓推送，推送到所有人
     *
     * @param alert  内容
     * @param title  标题
     * @param extras 其他参数
     */
    public static void sendPushToAndroidAll(String alert, String title, Map<String, String> extras) {

        JPushClient jpushClient = new JPushClient(_masterSecret, _appKey);

        PushPayload payload = buildPushObject_android_all_alert_TitleWithExtras(alert, title, extras);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

    /**
     * 安卓推送，指定别名
     *
     * @param alias  别名
     * @param alert  内容
     * @param title  标题
     * @param extras 其他参数
     * @return
     */
    public static PushPayload buildPushObject_android_alias_alert_TitleWithExtras(String alias, String alert, String title, Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.android(alert, title, extras))
                .build();
    }

    /**
     * 安卓推送，推送到所有人
     *
     * @param alert  内容
     * @param title  标题
     * @param extras 其他参数
     * @return
     */
    public static PushPayload buildPushObject_android_all_alert_TitleWithExtras(String alert, String title, Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(alert, title, extras))
                .build();
    }

    /**
     * IOS推送，指定别名
     *
     * @param alias  别名
     * @param alert  内容
     * @param extras 其他参数
     * @return
     */
    public static PushPayload buildPushObject_ios_alias_alert_TitleWithExtras(String alias, IosAlert alert, Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setSound("sound")
                                .addExtras(extras)
                                .build())
                        .build())
                .build();
    }

    /**
     * IOS推送，推送到所有人
     *
     * @param alert  内容
     * @param extras 其他参数
     * @return
     */
    public static PushPayload buildPushObject_ios_all_alert_TitleWithExtras(IosAlert alert, Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder().
                                setAlert(alert)
                                .setSound("sound")
                                .addExtras(extras)
                                .build())
                        .build())
                .build();
    }


}
