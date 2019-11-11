package com.htdata.goyo.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.htdata.goyo.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

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

    public static int mMainThreadId;
    public static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        mHandler = new Handler();
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

    /**
     * 返回主线程的pid
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
    /**
     * 返回Handler
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

}
