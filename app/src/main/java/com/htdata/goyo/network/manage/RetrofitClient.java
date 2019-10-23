package com.htdata.goyo.network.manage;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2019/1/10.
 */

public class RetrofitClient {

    private static RetrofitClient mInstance;

    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitClient();
                }
            }
        }
        return mInstance;
    }

    public static Retrofit RetrofitClient() {

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ReqUrl.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpClient.getOkHttpClient())
                .build();
        return mRetrofit ;
    }




}
