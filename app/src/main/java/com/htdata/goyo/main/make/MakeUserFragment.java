package com.htdata.goyo.main.make;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.util.LogUtils;
import com.htdata.goyo.util.ToastUtils;
import com.htdata.goyo.util.statusbar.ImmersionBar;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者：cb
 * @日期：2019-10-17 14:39
 * @描述：我的 制造商
 */
public class MakeUserFragment extends BaseFragment {


    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getInflater(getContext(), R.layout.main_make_user_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.m_user_set_pwd, R.id.m_user_qy_auth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_user_set_pwd:
                ToastUtils.showToastShort(getActivity(),"修改密码");
                break;
            case R.id.m_user_qy_auth:
                ToastUtils.showToastShort(getActivity(),"企业认证");
                break;
        }
    }
}
