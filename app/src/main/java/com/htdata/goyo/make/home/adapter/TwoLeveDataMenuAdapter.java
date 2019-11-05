package com.htdata.goyo.make.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.make.home.activity.ActDataMonitorDetails;
import com.htdata.goyo.make.home.model.MonitorModel;
import com.htdata.goyo.view.flow.FlowTagLayout;
import com.htdata.goyo.view.flow.OnTagClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-04 15:28
 * @描述：
 */
public class TwoLeveDataMenuAdapter extends RecyclerView.Adapter<TwoLeveDataMenuAdapter.TwoViewHolder> {


    private Context mContext;
    private List<MonitorModel> mList;

    public TwoLeveDataMenuAdapter() {
    }

    public TwoLeveDataMenuAdapter(Context mContext, List<MonitorModel> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @NonNull
    @Override
    public TwoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_data_monitor_two_item, parent, false);
        return new TwoViewHolder(view);
    }
    private DeviceFlowTagAdapter<String> myAdapter ;
    @Override
    public void onBindViewHolder(@NonNull TwoViewHolder holder, int position) {
        holder.region.setText(mList.get(position).getRegion());
        holder.model.setText(mList.get(position).getModel());

        myAdapter = new DeviceFlowTagAdapter<String>(mContext);
        holder.device.setAdapter(myAdapter);
        myAdapter.clearAndAddAll(mList.get(position).getList());

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.t_m_region)
        TextView region;
        @BindView(R.id.t_m_model)
        TextView model;
        @BindView(R.id.t_m_device)
        FlowTagLayout device;

        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            device.setOnTagClickListener(new OnTagClickListener() {
                @Override
                public void onItemClick(FlowTagLayout parent, View view, int position) {
                    Intent intent = new Intent(mContext, ActDataMonitorDetails.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
