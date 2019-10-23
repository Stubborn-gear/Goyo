package com.htdata.goyo.network.schedulers;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/14.
 * 线程切换
 */

public class SchedulerProviderIo implements BaseSchedulerProvider {


    @Nullable
    private static SchedulerProviderIo instance;

    private SchedulerProviderIo() {}

    public static synchronized SchedulerProviderIo getInstance() {
        if (instance == null) {
            synchronized (SchedulerProviderIo.class){
                if (instance == null){
                    instance = new SchedulerProviderIo();
                }
            }
        }
        return instance;
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {

        return  new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(io())
                        .delay(3, TimeUnit.SECONDS)
                        .unsubscribeOn(io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                            }
                        })
                        .subscribeOn(ui())
                        .observeOn(ui());
            }
        };
    }
}
