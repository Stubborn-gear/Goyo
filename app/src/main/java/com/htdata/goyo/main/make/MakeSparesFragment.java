package com.htdata.goyo.main.make;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.main.ActMain;
import com.htdata.goyo.main.make.adapter.SparesGridAdapter;
import com.htdata.goyo.util.statusbar.ImmersionBar;

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
 * @日期：2019-10-17 14:39
 * @描述：备品备件管理 制造商
 */
public class MakeSparesFragment extends BaseFragment {

    @BindView(R.id.spares_search_edit)
    EditText searchEdit;
    @BindView(R.id.spares_recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getInflater(getContext(), R.layout.main_make_spares_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initImmersionBar();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getMyContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getMyContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getMyContext(),R.drawable.recycler_divider));//分割线
        mRecyclerView.addItemDecoration(decoration);

        SparesGridAdapter myAdapter = new SparesGridAdapter(getMyContext(),getActivity(),getList());
        mRecyclerView.setAdapter(myAdapter);


    }


    private List<String> getList(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            list.add("000"+i);
        }
        return  list ;
    }




    private void initImmersionBar() {
        ((ActMain) getActivity()).isDarkFont = 3;
        ImmersionBar.with(getActivity(), this).statusBarDarkFont(true).init();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {// 切换 实时刷新
            initImmersionBar();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ImmersionBar.with(getActivity(), this).destroy();
    }


}
