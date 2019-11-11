package com.htdata.goyo.make.spares.activity;

import android.os.Bundle;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseActivity;
import com.htdata.goyo.make.spares.adapter.SartsAllAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-07 11:26
 * @描述： 备件
 */
public class ActSparePart extends BaseActivity {

    @BindView(R.id.part_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.act_spare_part;
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
        tTitle.setText(setString(R.string.spare));
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        SartsAllAdapter myAdapter = new SartsAllAdapter(mContext, this, getList());
        mRecyclerView.setAdapter(myAdapter);
    }


    private List<String> getList() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 59; i++) {
            list.add("" + i);
        }
        return list;
    }


}
