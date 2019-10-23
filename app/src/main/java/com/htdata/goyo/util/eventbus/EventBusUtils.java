package com.htdata.goyo.util.eventbus;

import com.htdata.goyo.util.LogUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * cuibo
 * 2018/5/3 15:44
 */

public class EventBusUtils {

    /** 注册 EventBus */
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            LogUtils.v("========注册EventBus======");
            EventBus.getDefault().register(subscriber);
        }
    }

    /** 取消 EventBus */
    public static void unregister(Object subscriber) {
        if(EventBus.getDefault().isRegistered(subscriber)){
            EventBus.getDefault().unregister(subscriber);
        }
    }

    /** 发送消息 */
    public static void sendEvent(EventBusMessage event) {
        EventBus.getDefault().post(event);
        LogUtils.v("===Event==发送消息========"+event.getCode());
    }

    public static void sendStickyEvent(EventBusMessage event) {
        EventBus.getDefault().postSticky(event);
    }



    // 接收消息
//    @Subscribe
//    public void onMessageEvent(EventBusMessage event){
//        if (event.getCode() == 2){
//        }
//    }



}
