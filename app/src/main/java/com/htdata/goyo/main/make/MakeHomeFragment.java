package com.htdata.goyo.main.make;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.network.manage.Command;
import com.htdata.goyo.network.manage.NetworkManage;
import com.htdata.goyo.network.manage.OnResponseResult;
import com.htdata.goyo.network.manage.ReqUrl;
import com.htdata.goyo.network.utils.NetUtil;
import com.htdata.goyo.util.statusbar.ImmersionBar;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @作者：  cb
 * @日期：  2019-10-17 14:37
 * @描述：  制造商 主页
 */
public class MakeHomeFragment extends BaseFragment {



    Unbinder unbinder ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getInflater(getContext(), R.layout.main_make_home_fragment, container, false);
        ImmersionBar.with(getActivity(), this).statusBarDarkFont(false).init();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }




    //Get请求 无参的
    private void reqGet1() {
        NetworkManage.getInstance().reqGetAsync(new OnResponseResult() {
            @Override
            public void onResponseSuccess(String result, int tag) {

            }

            @Override
            public void onResponseError(String result, int tag) {

            }
        },ReqUrl.SOLUTION, Command.STATUS_CODE_200, null,false, getActivity());

        //创建被观察者   3种创建方式
        // 第一种
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                System.out.println(NetUtil.isMainThread()+"======xc======"+getCurProcessName(getContext()));
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onComplete();
            }
        });


        //创建观察者
        Observer<String> observer = new Observer<String>() {
            // 观察者接收事件前，默认最先调用复写 onSubscribe（）
            @Override
            public void onSubscribe(Disposable d) {
//                LogUtils.v("======开始采用subscribe连接========");
            }

            // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onNext(String s) {
//                LogUtils.v("======对Next事件作出响应========"+s);
                System.out.println(NetUtil.isMainThread()+"======xc=2====="+getCurProcessName(getActivity()));
            }

            // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onError(Throwable e) {
//                LogUtils.v("======对Error事件作出响应========");
            }

            // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onComplete() {
//                LogUtils.v("======对Complete事件作出响应========");
            }
        };

        observable1.subscribeOn(Schedulers.newThread()) // 1. 指定被观察者 生产事件的线程
                .observeOn(AndroidSchedulers.mainThread())// 2. 指定观察者 接收 & 响应事件的线程
                .subscribe(observer);// 3. 最后再通过订阅（subscribe）连接观察者和被观察者
//        call1.cancel();
    }


    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
