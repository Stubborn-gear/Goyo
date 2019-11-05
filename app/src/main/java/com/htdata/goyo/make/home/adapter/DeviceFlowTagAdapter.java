package com.htdata.goyo.make.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.view.flow.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.List;



/**
 * cuibo
 * 2017/10/24 09:25
 */

public class DeviceFlowTagAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<T> mDataList;

    public DeviceFlowTagAdapter(Context context) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_data_monitor_two_item_flow, null);
        TextView dName = view.findViewById(R.id.fl_device_name);
        T t = mDataList.get(position);
        if (t instanceof String) {
            dName.setText((String) t);
        }
        return view;
    }

    public void onlyAddAll(List<T> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<T> datas) {
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
