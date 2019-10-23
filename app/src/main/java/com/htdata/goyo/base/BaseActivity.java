package com.htdata.goyo.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.application.GoyoApplication;
import com.htdata.goyo.network.manage.NetworkManage;
import com.htdata.goyo.network.manage.OnResponseResult;
import com.htdata.goyo.util.eventbus.BindEventBus;
import com.htdata.goyo.util.eventbus.EventBusUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @作者：cb
 * @日期：2019-10-17 13:47
 * @描述：
 */
public abstract class BaseActivity extends AppCompatActivity implements OnResponseResult {

    protected LinearLayout tRight;
    protected TextView tTitle, tLeft,tRightText;
    protected ImageView tRightLeftImage ,tRightRightImage;
    protected Context mContext;
    protected NetworkManage netClient;



//    protected ImmersionBar mImmersionBar;// 沉浸式状态栏  操作类

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        GoyoApplication.addAct(this);
        mContext = GoyoApplication.getContext();
        netClient = NetworkManage.getInstance();
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.init();
    }

    /**
     * 初始化 标题栏
     */
    protected void initTitleBar() {
         tRight = findViewById(R.id.title_right);
         tTitle = findViewById(R.id.title_text);
         tLeft = findViewById(R.id.title_left);
         tRightLeftImage = findViewById(R.id.title_right_left_image);
         tRightText = findViewById(R.id.title_right_text);
         tRightRightImage = findViewById(R.id.title_right_image);

        if (tLeft != null) {
            tLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed(); // 回退键，相当于finish()
                }
            });
        }
    }

    /**
     * 跳转到指定Activity，是否需要关闭！
     *
     * @param cls        目标Activity
     * @param closedFlag 是否关闭当前Activity
     */
    protected void toActivity(Class<?> cls, boolean closedFlag) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        if (closedFlag){
            finish();
        }
    }

    /**
     * 根据标示，判断跳转到下个Activity，是否需要关闭！
     *
     * @param cls        目标Activity
     * @param bundle     存储数据对象Bundle
     * @param closedFlag 是否关闭当前Activity
     */
    protected void toActivity(Class<?> cls, Bundle bundle, boolean closedFlag) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        if (closedFlag)
            finish();
    }

    /**
     * 跳转到指定的activity, 不关闭当前activity
     * @param cls 目标Activity
     */
    protected void toActivity(Class<?> cls) {
        toActivity(cls, false);
    }

    /**
     * 跳转到下指定Activity，并希望返回数据
     *
     * @param cls         目标Activity
     * @param bundle      存储数据对象Bundle
     * @param requestCode
     */
    protected void toActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 关闭除 mainactivity 所有界面
     */
    public void closeActivity() {
        for (int i = GoyoApplication.actList.size() - 1; i >= 0; i--) {//不是mainactivity 就移除
            if ("com.htdata.industry.main.ActMain".equals(GoyoApplication.actList.get(i).getClass().getName())) {
                return;
            } else {
                GoyoApplication.actList.get(i).finish();
            }
        }
    }

    /**
     * 未登录--跳转处理(登录成功后进入目标页面)
     * @param intent 目标activity所需intent
     */
    public void startActivityAfterLogin(Intent intent) {
        // 未登录
//        ComponentName componentName = new ComponentName(this, ActLogin.class);
//        intent.putExtra("className", intent.getComponent().getClassName());
//        intent.setComponent(componentName);
//        startActivity(intent);
    }

    /**
     * 隐藏软键盘
     * @param view
     */
    public void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected abstract int getContentViewId();

    public void initView() {
    }


    public void initData() {
    }


    public void setData(Object obj) {
    }


    public Resources getRes() {
        return getResources();
    }

    /**
     * EventBus 注册
     */
    private void initRegisterEventBus() {
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.register(this);
        }
    }

    /**
     * EventBus 取消注册
     */
    private void initUnregisterEventBus() {
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.unregister(this);
        }
    }

    @Override
    public void onResponseSuccess(String result, int tag) {

    }

    @Override
    public void onResponseError(String result, int tag) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        initUnregisterEventBus();
//        if (mImmersionBar != null){
//            mImmersionBar.destroy();  //在BaseActivity里销毁
//        }
    }


}
