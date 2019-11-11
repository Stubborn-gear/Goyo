package com.htdata.goyo.make.spares.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.make.home.adapter.StatisAnalysAdapter;
import com.htdata.goyo.make.spares.adapter.SartsAllAdapter;

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
 * @日期：2019-11-07 10:46
 * @描述： 购买
 */
public class SartsPurchaseFragment extends BaseFragment {

    @BindView(R.id.curr_recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    private SartsAllAdapter myAdapter;

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
        View view = getInflater(getContext(), R.layout.act_currency_recycler_view, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerview();
        return view;
    }

    private void initRecyclerview() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMyContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        myAdapter = new SartsAllAdapter(getMyContext(), getActivity(),getList());
        mRecyclerView.setAdapter(myAdapter);
    }

    private List<String>  getList() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 59; i++) {
            list.add("0");
        }
        return list ;
    }


}
