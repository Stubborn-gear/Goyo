package com.htdata.goyo.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：cb
 * @日期：2019-10-17 13:47
 * @描述：
 */
public class GoyoApplication extends Application {

    /**
     * 全局 引用
     */
    public static Context mContext;
    /**
     * 用来存储用activity
     */
    public static List<Activity> actList = new ArrayList<Activity>();



    @Override
    public void onCreate() {
        super.onCreate();
        init();

    }

    private void init(){
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 添加activity到集合
     * @param act Activity
     */
    public static void addAct(Activity act) {
        actList.add(act);
    }

    /**
     * 删除指定activity
     * @param cls class
     */
    public static void deleteAct(Class<?> cls) {
        if (actList.size() > 0) {
            for (Activity activity : actList) {
                if (activity.getClass().equals(cls)) {
                    actList.remove(activity);
                    activity.finish();
                    break;
                }
            }
        }
    }

    public static List<Activity> getActList() {
        return actList;
    }

}
