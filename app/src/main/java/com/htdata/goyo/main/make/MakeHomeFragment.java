package com.htdata.goyo.main.make;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.main.ActMain;
import com.htdata.goyo.main.make.adapter.HomeGridAdapter;
import com.htdata.goyo.main.make.model.MakeHomeBarModel;
import com.htdata.goyo.make.home.activity.ActDataMonitor;
import com.htdata.goyo.network.manage.Command;
import com.htdata.goyo.network.manage.NetworkManage;
import com.htdata.goyo.network.manage.OnResponseResult;
import com.htdata.goyo.network.manage.ReqUrl;
import com.htdata.goyo.network.utils.NetUtil;
import com.htdata.goyo.util.LogUtils;
import com.htdata.goyo.util.chart.BarChartManager;
import com.htdata.goyo.util.chart.LineChartMuchManager;
import com.htdata.goyo.util.statusbar.ImmersionBar;
import com.htdata.goyo.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

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
 * @作者： cb
 * @日期： 2019-10-17 14:37
 * @描述： 制造商 主页
 */
public class MakeHomeFragment extends BaseFragment {


    @BindView(R.id.home_bar_chart)
    BarChart barChart;
    @BindView(R.id.home_buy_qy_count)
    TextView homeBuyQyCount;
    @BindView(R.id.home_sell_device_count)
    TextView homeSellDeviceCount;
    @BindView(R.id.home_gridview)
    MyGridView homeGridview;
    @BindView(R.id.home_refresh)
    ImageView homeRefresh;
    @BindView(R.id.home_abnormal_rate)
    TextView homeAbnormalRate;
    @BindView(R.id.home_run_rate)
    TextView homeRunRate;
    @BindView(R.id.home_run_number)
    TextView homeRunNumber;
    @BindView(R.id.home_tv_run_number)
    TextView homeTvRunNumber;
    @BindView(R.id.home_standby_number)
    TextView homeStandbyNumber;
    @BindView(R.id.home_fault_number)
    TextView homeFaultNumber;
    @BindView(R.id.home_line_chart)
    LineChart lineChart;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getInflater(getContext(), R.layout.main_make_home_fragment, container, false);
        initImmersionBar();
        unbinder = ButterKnife.bind(this, view);
        initGridView();
        initBarChart();
        initLineChart();
        return view;
    }

    private void initGridView(){
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("-55");
        HomeGridAdapter homeGridAdapter = new HomeGridAdapter(getMyContext(), list);
        homeGridview.setAdapter(homeGridAdapter);
        homeGridview.setFocusable(false);
    }

    private void initBarChart(){
        MakeHomeBarModel model = new MakeHomeBarModel();

        model.getKeyList().add("预精轧机");
        model.getKeyList().add("精轧机");
        model.getKeyList().add("吐丝机");
        model.getKeyList().add("小型轧机");
        model.getKeyList().add("打包机");

        model.getValueList().add("1");
        model.getValueList().add("5");
        model.getValueList().add("10");
        model.getValueList().add("15");
        model.getValueList().add("20");

        List<BarEntry> yVals = new ArrayList<>();
        List<String> valueList = model.getValueList();

        for (int i = 0; i <valueList.size() ; i++) {
            yVals.add(new BarEntry(i, Float.parseFloat(valueList.get(i))));
        }
        BarChartManager barChartManager = new BarChartManager(barChart);
        barChartManager.showBarChart(yVals,"",R.drawable.bar_chart_color);//Color.parseColor("#233454")
    }

    private void initLineChart(){

        LineChartMuchManager lineChartManager = new LineChartMuchManager(lineChart, getMyContext());

        List<String> xValues = new ArrayList<>();
         List<Double> list1 = new ArrayList<>();
         List<Double> list2 = new ArrayList<>();
         List<Double> list3 = new ArrayList<>();
        for (int i = 0; i <7 ; i++) {
            list1.add(i+1.0);
            list2.add(i+2.0);
            list3.add(i+5.0);
        }
        xValues.add("12-01");
        xValues.add("12-02");
        xValues.add("12-03");
        xValues.add("12-04");
        xValues.add("12-05");
        xValues.add("12-06");
        xValues.add("12-07");

        lineChartManager.showLineChart(xValues, list1, list2, list3);
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
        }, ReqUrl.SOLUTION, Command.STATUS_CODE_200, null, false, getActivity());

        //创建被观察者   3种创建方式
        // 第一种
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                System.out.println(NetUtil.isMainThread() + "======xc======" + getCurProcessName(getContext()));
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
                System.out.println(NetUtil.isMainThread() + "======xc=2=====" + getCurProcessName(getActivity()));
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
        int pid = Process.myPid();
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

    @OnClick({R.id.home_my_user, R.id.home_data_check, R.id.home_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_my_user:
                ((ActMain)getActivity()).openDrawerLayout();
                break;
            case R.id.home_data_check:
                toActivity(ActDataMonitor.class);
                break;
            case R.id.home_refresh:
                break;
        }
    }

    private void initImmersionBar(){
        ((ActMain)getActivity()).isDarkFont = 1 ;
        ImmersionBar.with(getActivity(), this).statusBarDarkFont(false).init();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {// 切换 实时刷新
            initImmersionBar();
        }
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser){
//            initImmersionBar();
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ImmersionBar.with(getActivity(), this).destroy();
    }
}
