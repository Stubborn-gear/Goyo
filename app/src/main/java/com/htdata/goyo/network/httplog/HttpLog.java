package com.htdata.goyo.network.httplog;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2019/1/10.
 */

public class HttpLog implements Interceptor {

    private static HttpLoggingInterceptor httpLoggingInterceptor ;

    public static HttpLoggingInterceptor getHttpLog(){
        if (httpLoggingInterceptor == null){
            synchronized (HttpLog.class){
                if (httpLoggingInterceptor == null){
                    httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
//                            LogUtils.v("=======请求日志======="+message);
                        }
                    });
                }
            }
        }

        return httpLoggingInterceptor ;
    }


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        return null;
    }
}
