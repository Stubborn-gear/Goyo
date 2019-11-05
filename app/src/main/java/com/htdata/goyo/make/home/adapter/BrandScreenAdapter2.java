package com.htdata.goyo.make.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htdata.goyo.view.flow.OnInitSelectedPosition;

import java.util.ArrayList;


/**
 * cuibo
 * 2017/10/24 09:25
 */

/*
public class BrandScreenAdapter2<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<ParametersRes.DataBean.CategorySetListBean> mDataList;

    public BrandScreenAdapter2(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.act_brand_screen_tiem, null);

        TextView textView = (TextView) view.findViewById(R.id.brand_item_name);
        ParametersRes.DataBean.CategorySetListBean bean = mDataList.get(position);
        textView.setText(bean.getCategoryName());
        return view;
    }

    public void onlyAddAll(List<ParametersRes.DataBean.CategorySetListBean> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<ParametersRes.DataBean.CategorySetListBean> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
*/
