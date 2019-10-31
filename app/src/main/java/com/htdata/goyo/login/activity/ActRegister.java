package com.htdata.goyo.login.activity;

import android.os.Bundle;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseActivity;

import androidx.annotation.Nullable;

/**
 * @作者：cb
 * @日期：2019-10-29 13:11
 * @描述：
 */
public class ActRegister extends BaseActivity {


    @Override
    protected int getContentViewId() {
        return R.layout.act_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleBar();
        tTitle.setText("注册");
    }

}
