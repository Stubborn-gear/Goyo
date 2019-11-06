package com.htdata.goyo.make.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.make.home.adapter.DataMonitorDetailsAdapter;
import com.htdata.goyo.make.home.adapter.StatisAnalysAdapter;
import com.htdata.goyo.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
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
 * @日期：2019-11-06 9:09
 * @描述： 报警
 */
public class OneLevelFragment extends BaseFragment {

    @BindView(R.id.sa_ol_recyclerview)
    RecyclerView mRecyclerview;
    Unbinder unbinder;

    private int analysState = 1 ;
    private StatisAnalysAdapter myAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getInflater(getContext(), R.layout.act_one_level_fragment, container, false);
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

        myAdapter = new StatisAnalysAdapter(getMyContext(),null);
        mRecyclerview.setAdapter(myAdapter);
        getList();
    }

    private void getList(){
        List<String> list = new ArrayList<String>();
        switch (getAnalysState())   {
            case 1:
                for (int i = 0; i <59 ; i++) {
                    list.add("1") ;
                }
                break;
            case 2:
                for (int i = 0; i <59 ; i++) {
                    list.add("2") ;
                }
                break;
            case 3:
                for (int i = 0; i <59 ; i++) {
                    list.add("3") ;
                }
                break;
            case 4:
                for (int i = 0; i <59 ; i++) {
                    list.add("4") ;
                }
                break;
        }
        if (myAdapter != null){
            myAdapter.upData(list);
        }
    }

    public void setAnalysState(int analysState) {
        this.analysState = analysState;
        getList();
    }

    public int getAnalysState() {
        return analysState;
    }
}
