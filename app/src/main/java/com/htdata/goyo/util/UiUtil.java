package com.htdata.goyo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import com.htdata.goyo.application.GoyoApplication;

/**
 * 屏幕相关
 */

public class UiUtil {


    /**
     * 获取屏幕最大宽度
     * @param context
     * @return
     */
    public static int getMaxWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService( Context.WINDOW_SERVICE );
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics( dm );
        return dm.widthPixels;
    }

    /**
     * 获取屏幕最大高度
     * @param context
     * @return
     */
    public static int getMaxHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService( Context.WINDOW_SERVICE );
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics( dm );
        return dm.heightPixels;
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, int pxValue) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics()));
    }

    /**
     * sp转换成px
     */
    public static int sp2px(Context context,float spValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*fontScale+0.5f);
    }
    /**
     * px转换成sp
     */
    public static int px2sp(Context context,float pxValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/fontScale+0.5f);
    }

    /**
     * 加载布局返回
     * @param id  布局
     * @return
     */
    public static View inflate(int id) {
        return View.inflate(GoyoApplication.getContext(), id, null);
    }

    /**
     * 在主线程中执行代码
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (isRunOnMainThread()) {
            // 执行代码
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public static void post(Runnable runnable) {
        Handler handler = getHandler();
        handler.post(runnable);
    }

    /** 延迟执行 */
    public static void postDelay(Runnable runnable, long delay) {
        Handler handler = getHandler();
        handler.postDelayed(runnable, delay);
    }

    /** 获取 Handler */
    private static Handler getHandler() {
        return GoyoApplication.getHandler();
    }

    /** 判断是否是主线程 */
    public static boolean isRunOnMainThread() {
        return android.os.Process.myTid() == getMainThreadTid();
    }

    /** 判断是否是主线程 */
    private static int getMainThreadTid() {
        return GoyoApplication.getMainThreadId();
    }

    /**
     *  跳转到指定Activity，是否需要关闭！
     * @param context
     * @param cls
     * @param closedFlag
     */
    public static void toActivity(Activity context , Class<?> cls, boolean closedFlag) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
        if (closedFlag){
            context.finish();
        }
    }

}
