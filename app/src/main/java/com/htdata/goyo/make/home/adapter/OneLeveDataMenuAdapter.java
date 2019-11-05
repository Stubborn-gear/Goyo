package com.htdata.goyo.make.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.util.LogUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-04 15:28
 * @描述：
 */
public class OneLeveDataMenuAdapter extends RecyclerView.Adapter<OneLeveDataMenuAdapter.OneViewHolder> {

    private Context mContext;
    private List<String> mList;
    private boolean isClick = false ;
    private int mPosition = 0 ;


    public OneLeveDataMenuAdapter() {
    }

    public OneLeveDataMenuAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public void upData(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }

    // 引入布局，传给viewHolder
    @NonNull
    @Override
    public OneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_data_monitor_one_item, parent, false);
        OneViewHolder viewHolder = new OneViewHolder(view);
        return viewHolder;
    }

    //操作ITEM
    @Override
    public void onBindViewHolder(@NonNull OneViewHolder holder, int position) {
        holder.department.setText(mList.get(position));
        if (!isClick){
            if (position == 0){
                holder.layout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
                holder.image.setVisibility(View.VISIBLE);
            }else {
                holder.layout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.gray2));
                holder.image.setVisibility(View.GONE);
            }
        }else {
            if (position == mPosition){
                holder.layout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
                holder.image.setVisibility(View.VISIBLE);
            }else {
                holder.layout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.gray2));
                holder.image.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.m_one_image)
        ImageView image;
        @BindView(R.id.m_one_text)
        TextView department;
        @BindView(R.id.m_one_layout)
        LinearLayout layout;

        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isClick = true ;
                    mPosition = getLayoutPosition() ;
                    notifyDataSetChanged();
//                    onItemClickListener.onItemClick(v, mList.get(getLayoutPosition()));
                }
            });
        }
    }


    //点击 RecyclerView 某条的监听
    public interface OnItemClickListener {
        /**
         * 当RecyclerView某个被点击的时候回调
         *
         * @param view 点击item的视图
         * @param data 点击得到的数据
         */
        void onItemClick(View view, String data);
    }

    private OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
