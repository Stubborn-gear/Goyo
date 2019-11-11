package com.htdata.goyo.main.make.adapter;

import android.app.Activity;
import android.content.Context;
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
 * @日期：2019-11-07 10:08
 * @描述：
 */
public class SparesGridAdapter extends RecyclerView.Adapter<SparesGridAdapter.ViewHolder> {


    private Context mContext;
    private Activity mActivity;
    private List<String> mList;

    public SparesGridAdapter() {
    }

    public SparesGridAdapter(Context mContext, Activity mActivity, List<String> mList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_home_spares_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText("使用厂商的名称");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.spares_item_name)
        TextView itemName;
        @BindView(R.id.spares_item_spare_parts)
        TextView itemSpareParts;
        @BindView(R.id.spares_item_spare)
        TextView itemSpare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemSpareParts.setOnClickListener(new View.OnClickListener() {// 备品
                @Override
                public void onClick(View v) {
                    UiUtil.toActivity(mActivity, ActSpareSarts.class,false);
                }
            });

            itemSpare.setOnClickListener(new View.OnClickListener() {// 备件
                @Override
                public void onClick(View v) {
                    UiUtil.toActivity(mActivity, ActSparePart.class,false);
                }
            });
        }
    }
}
