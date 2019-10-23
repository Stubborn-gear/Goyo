package com.htdata.goyo.main.make;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.login.activity.ActLogin;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者：cb
 * @日期：2019-10-17 14:38
 * @描述：设备 制造商
 */
public class MakeDeviceFragment extends BaseFragment {

    @BindView(R.id.device_text1)
    TextView deviceText1;
    Unbinder unbinder ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getInflater(getContext(), R.layout.main_make_device_fragment, container, false);
        unbinder =  ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.home_btn1, R.id.home_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_btn1:
                break;
            case R.id.home_btn2:
                toActivity(ActLogin.class);
                break;
        }
    }

}
