package com.htdata.goyo.make.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.make.home.adapter.DataMonitorDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @作者：cb
 * @日期：2019-11-05 10:20
 * @描述： 统计分析
 */
public class StatisAnalysFragment extends BaseFragment {

    @BindView(R.id.s_a_d_recyclerview)
    RecyclerView mRecyclerview;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getInflater(getContext(), R.layout.act_statis_analys_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerview();
        return view;
    }

    private void initRecyclerview(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMyContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerview.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getMyContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getMyContext(),R.drawable.recycler_divider));//分割线
        mRecyclerview.addItemDecoration(decoration);

        DataMonitorDetailsAdapter myAdapter = new DataMonitorDetailsAdapter(getActivity(),getMyContext(),getList());
        mRecyclerview.setAdapter(myAdapter);
    }

    private List<String> getList(){
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <59 ; i++) {
            list.add("11"+i) ;
        }
        return list ;
    }

}
