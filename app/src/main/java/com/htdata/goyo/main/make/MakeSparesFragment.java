package com.htdata.goyo.main.make;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;

import androidx.annotation.Nullable;

/**
 * @作者：cb
 * @日期：2019-10-17 14:39
 * @描述：备品备件管理   制造商
 */
public class MakeSparesFragment extends BaseFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getInflater(getContext(), R.layout.main_make_spares_fragment, container, false);
        return view;
    }
}
