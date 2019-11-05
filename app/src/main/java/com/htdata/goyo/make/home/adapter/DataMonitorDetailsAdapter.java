package com.htdata.goyo.make.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.make.home.activity.ActDataMonitorDetails;
import com.htdata.goyo.make.home.activity.ActStatisAnalys;
import com.htdata.goyo.make.home.model.MonitorModel;
import com.htdata.goyo.util.UiUtil;
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
public class DataMonitorDetailsAdapter extends RecyclerView.Adapter<DataMonitorDetailsAdapter.TwoViewHolder> {



    private Context mContext;
    private Activity mActivity;
    private List<String> mList;

    public DataMonitorDetailsAdapter() {
    }

    public DataMonitorDetailsAdapter(Activity activity, Context mContext, List<String> list) {
        this.mContext = mContext;
        this.mList = list;
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public TwoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_data_monitor_details_item, parent, false);
        return new TwoViewHolder(view);
    }

    private DeviceFlowTagAdapter<String> myAdapter;

    @Override
    public void onBindViewHolder(@NonNull TwoViewHolder holder, int position) {
        holder.dmdName.setText("速度");
        holder.dmdDesc.setText("驱动端水平测点");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dmd_name)
        TextView dmdName;
        @BindView(R.id.dmd_desc)
        TextView dmdDesc;
        @BindView(R.id.dmd_state)
        ImageView dmdState;

        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UiUtil.toActivity(mActivity, ActStatisAnalys.class,false);
                }
            });
        }
    }

}
