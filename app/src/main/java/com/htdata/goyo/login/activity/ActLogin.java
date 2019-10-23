package com.htdata.goyo.login.activity;

import android.os.Bundle;
import android.view.View;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseActivity;
import com.htdata.goyo.main.ActMain;
import com.htdata.goyo.network.manage.NetworkManage;
import com.htdata.goyo.network.utils.NetUtil;
import com.htdata.goyo.util.UserUtil;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者：cb
 * @日期：2019-10-17 16:15
 * @描述：登录
 */
public class ActLogin extends BaseActivity {


    @Override
    protected int getContentViewId() {
        System.out.println("===========getContentViewId==========");
        return R.layout.act_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("===========onCreate==========");
//        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
    }




    @OnClick({R.id.login_btn1, R.id.login_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn1:
                UserUtil.setUserType(1);
                toActivity(ActMain.class,true);
                break;
            case R.id.login_btn2:
                UserUtil.setUserType(2);
                toActivity(ActMain.class,true);
                break;
        }
    }
}
