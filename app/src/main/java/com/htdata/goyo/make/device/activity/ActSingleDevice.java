package com.htdata.goyo.make.device.activity;

import android.os.Bundle;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseActivity;
import com.htdata.goyo.make.device.adapter.SingleDeviceAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-11 09:48
 * @描述： 单体设备
 */
public class ActSingleDevice extends BaseActivity {


    @BindView(R.id.single_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.dv_refresh_layout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected int getContentViewId() {
        return R.layout.act_single_device;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initTitleBar();
        initRecyclerView();
        initRefresh();
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        tTitle.setText(setString(R.string.single_device));
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        SingleDeviceAdapter myAdapter = new SingleDeviceAdapter(mContext,this, getList());
        mRecyclerView.setAdapter(myAdapter);
    }


    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("1" + i);
        }
        return list;
    }


    private void initRefresh(){
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

}
