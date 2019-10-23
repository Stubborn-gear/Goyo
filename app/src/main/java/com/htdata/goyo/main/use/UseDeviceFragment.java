package com.htdata.goyo.main.use;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.login.activity.ActLogin;
import com.htdata.goyo.network.manage.Command;
import com.htdata.goyo.network.manage.NetworkManage;
import com.htdata.goyo.network.manage.OnResponseResult;
import com.htdata.goyo.network.manage.ReqUrl;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者：cb
 * @日期：2019-10-17 14:38
 * @描述：设备  平台方
 */
public class UseDeviceFragment extends BaseFragment {

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
        View view = getInflater(getContext(), R.layout.main_use_device_fragment, container, false);
        unbinder =  ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.home_btn1, R.id.home_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_btn1:
                reqGet1();
                break;
            case R.id.home_btn2:
                toActivity(ActLogin.class);
                break;
        }
    }

    //Get请求 无参的
    private void reqGet1() {
        NetworkManage.getInstance().reqGetAsync(new OnResponseResult() {
            @Override
            public void onResponseSuccess(String result, int tag) {
                System.out.println("====1111========"+result+"======onResponse====="+tag);
            }

            @Override
            public void onResponseError(String result, int tag) {

            }
        }, ReqUrl.SOLUTION, Command.STATUS_CODE_200, null,false, getActivity());
    }

}
