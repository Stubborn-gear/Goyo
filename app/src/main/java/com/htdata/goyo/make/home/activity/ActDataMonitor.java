package com.htdata.goyo.make.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseActivity;
import com.htdata.goyo.make.home.adapter.OneLeveDataMenuAdapter;
import com.htdata.goyo.make.home.adapter.TwoLeveDataMenuAdapter;
import com.htdata.goyo.make.home.model.MonitorModel;
import com.htdata.goyo.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-04 13:26
 * @描述： 数据监测
 */
public class ActDataMonitor extends BaseActivity {


    @BindView(R.id.m_recycler_view1)
    RecyclerView recyclerView1;
    @BindView(R.id.m_recycler_view2)
    RecyclerView recyclerView2;

    @Override
    protected int getContentViewId() {
        return R.layout.act_data_monitor;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initTitleBar();
        initRecyclerView();
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        tTitle.setText(setString(R.string.data_monitor));
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);

        OneLeveDataMenuAdapter myAdapter1 = new OneLeveDataMenuAdapter(this,getDataList1());
        recyclerView1.setAdapter(myAdapter1);

        TwoLeveDataMenuAdapter myAdapter2 = new TwoLeveDataMenuAdapter(this,getDataList2());
        recyclerView2.setAdapter(myAdapter2);

    }




    private List<String> getDataList1(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add("部门"+i);
        }
        return list ;
    }

    private List<MonitorModel> getDataList2(){
        MonitorModel mm = new MonitorModel();
        List<MonitorModel> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for (int i = 0; i <8 ; i++) {
            list2.add("设备"+i);
        }
        mm.setRegion("经轧区域一");
        mm.setModel("吐丝机器模型");
        mm.setList(list2);
        list.add(mm);

        MonitorModel mm2 = new MonitorModel();
        List<String> list3 = new ArrayList<>();
        for (int i = 0; i <8 ; i++) {
            list3.add("设备"+i);
        }
        mm2.setRegion("经轧区域er");
        mm2.setModel("吐丝机器模型er");
        mm2.setList(list3);
        list.add(mm2);
        return list ;
    }

}
