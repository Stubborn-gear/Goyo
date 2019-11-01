package com.htdata.goyo.util;

/**
 * cuibo
 * 2018/4/10 12:43
 */

public class SdkUtil {

    public static boolean isSdkVersion(){
        int version = android.os.Build.VERSION.SDK_INT;
        if (version >= 23) {
            return true ;
        }else{
            return false ;
        }
    }


    public static boolean isSdkVersion18(){
        int version = android.os.Build.VERSION.SDK_INT;
        if (version >= 18) {
            return true ;
        }else{
            return false ;
        }
    }



}
