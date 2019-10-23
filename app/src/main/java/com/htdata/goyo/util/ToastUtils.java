package com.htdata.goyo.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    /** 之前显示的内容 */
    private static String oldMsg ;
    /** Toast对象 */
    private static Toast toast = null ;
    /** 第一次时间 */
    private static long oneTime = 0 ;
    /** 第二次时间 */
    private static long twoTime = 0 ;


    /**
     * 显示Toast
     * @param context
     * @param message
     */
    public static void showToastLong(Context context, String message){
        if(toast == null){
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_LONG){
                    toast.show() ;
                }
            }else{
                oldMsg = message ;
                toast.setText(message) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }

    /**
     * 显示Toast
     * @param context
     * @param message
     */
    public static void showToastShort(Context context, String message){
        if(toast == null){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
//                LogUtils.v(twoTime+"=========0000======="+oneTime);
//                LogUtils.v("=========0000====11==="+(twoTime - oneTime));
                if(twoTime - oneTime > Toast.LENGTH_SHORT){
                    toast.show() ;
                }
            }else{
                oldMsg = message ;
                toast.setText(message) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }



}
