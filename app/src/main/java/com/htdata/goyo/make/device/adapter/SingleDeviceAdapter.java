package com.htdata.goyo.make.device.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.util.LogUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-07 11:34
 * @描述： 单体设备
 */
public class SingleDeviceAdapter extends RecyclerView.Adapter<SingleDeviceAdapter.ViewHolder> {


    private Context mContext;
    private Activity mActivity;
    private List<String> mList;

    public SingleDeviceAdapter() {
    }

    public SingleDeviceAdapter(Context mContext, Activity mActivity, List<String> mList) {
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mList = mList;
    }

    public void upData(List<String> list) {
        if (list != null && list.size() != 0) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    public void upDataAll(List<String> list) {
        if (list != null && list.size() != 0) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_single_device_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.shopNumber.setText("车间10");
        holder.deviceName.setText("设备名称");
        holder.deviceModel.setText("10000078773893");
        holder.manufacturer.setText("厂商A");
        holder.measuringPointCount.setText("50");
        LogUtils.v("======onBindViewHolder=======");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sd_item_shop_name)
        TextView shopName;
        @BindView(R.id.sd_item_shop_number)
        TextView shopNumber;
        @BindView(R.id.sd_item_shop_layout)
        LinearLayout shopLayout;
        @BindView(R.id.sd_item_device_name)
        TextView deviceName;
        @BindView(R.id.sd_item_device_model)
        TextView deviceModel;
        @BindView(R.id.sd_item_manufacturer)
        TextView manufacturer;
        @BindView(R.id.sd_item_vendor_layout)
        LinearLayout vendorLayout;
        @BindView(R.id.sd_item_measuring_point_count)
        TextView measuringPointCount;
        @BindView(R.id.sd_item_point_number_layout)
        LinearLayout pointNumberLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
