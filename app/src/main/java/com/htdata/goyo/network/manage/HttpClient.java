package com.htdata.goyo.network.manage;

import com.htdata.goyo.network.httplog.HttpLog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2019/1/10.
 */

public class HttpClient {


    private static HttpClient mInstance;

    public static HttpClient getInstance(){
        if (mInstance == null){
            synchronized (HttpClient.class){
                if (mInstance == null){
                    mInstance = new HttpClient();
                }
            }
        }
        return mInstance;
    }

    public static OkHttpClient getOkHttpClient() {
        HttpLog.getHttpLog().setLevel(HttpLoggingInterceptor.Level.BODY);//设置log内容
        return new OkHttpClient.Builder()
                .addInterceptor(HttpLog.getHttpLog())
                .connectTimeout(30, TimeUnit.SECONDS)//连接超时
                .readTimeout(20, TimeUnit.SECONDS)//读取超时
                .writeTimeout(20, TimeUnit.SECONDS)//写超时
                .build();
    }
}
