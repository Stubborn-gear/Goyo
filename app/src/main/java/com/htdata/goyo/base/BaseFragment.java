package com.htdata.goyo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * @作者：cb
 * @日期：2019-10-17 13:47
 * @描述：
 */
public class BaseFragment extends Fragment {

    public ImageButton tLeftBtn , tRightBtn ;
    public TextView tRightText,tCenterTitle ;
//    public OkHttpManager okManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtils.with(getActivity()).init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        okManager = OkHttpManager.getInstance();
//        initRegisterEventBus();
        return  super.onCreateView(inflater,container,savedInstanceState);
    }

    public void initTitleBar(View view){
//        tLeftBtn = (ImageButton) view.findViewById(R.id.title_left_btn);
//        tCenterTitle = (TextView) view.findViewById(R.id.title_center_text);
//        tRightBtn = (ImageButton) view.findViewById(R.id.title_right_image);
//        tRightText = (TextView) view.findViewById(R.id.title_right_text);
    }

    /** 获取 LayoutInflater */
    public View getInflater(Context context,int layoutView,ViewGroup container,boolean attachToRoot){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null){
            throw new AssertionError("LayoutInflater not found.");
        }
        return  inflater.inflate(layoutView, container, attachToRoot);
    }

    /**
     * 跳转到指定Activity，是否需要关闭！
     * @param cls        目标Activity
     * @param closedFlag 是否关闭当前Activity
     */
    protected void toActivity(Class<?> cls, boolean closedFlag) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        startActivity(intent);
        if (closedFlag)
            getActivity().finish();
    }

    /**
     * 根据标示，判断跳转到下个Activity，是否需要关闭！
     * @param cls        目标Activity
     * @param bundle     存储数据对象Bundle
     * @param closedFlag 是否关闭当前Activity
     */
    protected void toActivity(Class<?> cls, Bundle bundle, boolean closedFlag) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        if (closedFlag)
            getActivity().finish();
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
     * @param cls         目标Activity
     * @param bundle      存储数据对象Bundle
     * @param requestCode
     */
    protected void toActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public Context getMyContext(){
        return getContext();
    }


    public void initView(){};
    public void initData(){};
    public void setData(Object obj){};

    /** EventBus 注册 */
    private void initRegisterEventBus(){
//        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
//            EventBusUtils.register(this);
//        }
    }


    /** EventBus 取消注册 */
    private void initUnregisterEventBus(){
//        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
//            EventBusUtils.unregister(this);
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initUnregisterEventBus();
    }
}
