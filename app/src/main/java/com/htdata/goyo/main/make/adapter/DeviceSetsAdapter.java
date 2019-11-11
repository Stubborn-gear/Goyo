package com.htdata.goyo.main.make.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.make.spares.activity.ActSparePart;
import com.htdata.goyo.make.spares.activity.ActSpareSarts;
import com.htdata.goyo.util.UiUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-08 14:28
 * @描述：
 */
public class DeviceSetsAdapter extends RecyclerView.Adapter<DeviceSetsAdapter.ViewHolder> {


    private Context mContext;
    private Activity mActivity;
    private List<String> mList;

    public DeviceSetsAdapter() {
    }

    public DeviceSetsAdapter(Context mContext, Activity mActivity, List<String> mList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_device_sets_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText("数据采集节点1");
        holder.number.setText("10000078773893");
        String str = "20台";
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(12,true), str.length() -1, str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.count.setText(spannableString);
        holder.desc.setText("智能维护部/精轧区域");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dv_item_name)
        TextView name;
        @BindView(R.id.dv_item_number)
        TextView number;
        @BindView(R.id.dv_item_count)
        TextView count;
        @BindView(R.id.dv_item_desc)
        TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("=======点击====itemView=====");
                }
            });
        }
    }
}
