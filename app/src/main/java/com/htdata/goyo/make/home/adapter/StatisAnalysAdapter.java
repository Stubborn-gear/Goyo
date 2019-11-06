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
public class StatisAnalysAdapter extends RecyclerView.Adapter<StatisAnalysAdapter.OneViewHolder> {

    private Context mContext;
    private List<String> mList;
    private boolean isClick = false ;
    private int mPosition = 0 ;


    public StatisAnalysAdapter() {
    }

    public StatisAnalysAdapter(Context mContext, List<String> list) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_statis_analys_item, parent, false);
        OneViewHolder viewHolder = new OneViewHolder(view);
        return viewHolder;
    }

    //操作ITEM
    @Override
    public void onBindViewHolder(@NonNull OneViewHolder holder, int position) {
        switch (mList.get(position))   {
            case "1":
                holder.name.setText("一级报警");
                holder.desc.setText("“全球未来机场计划”是指未来游客在海外机场，通过支付宝使用航班提醒等服务。" +
                        "“全球未来机场计划”是指未来游客在海外机场，通过支付宝使用航班提醒等服务。");
                break;
            case "2":
                holder.name.setText("二级报警");
                holder.desc.setText("“全球未来机场计划”是指未来游客在海外机场，通过支付宝使用航班提醒等服务。" +
                        "“全球未来机场计划”是指未来游客在海外机场，通过支付宝使用航班提醒等服务。");
                break;
            case "3":
                holder.name.setText("三级报警");
                holder.desc.setText("“全球未来机场计划”是指未来游客在海外机场，通过支付宝使用航班提醒等服务。" +
                        "“全球未来机场计划”是指未来游客在海外机场，通过支付宝使用航班提醒等服务。");
                break;
            case "4":
                holder.name.setText("四级报警");
                holder.desc.setText("“全球未来机场计划”是指未来游客在海外机场，通过支付宝使用航班提醒等服务。" +
                        "“全球未来机场计划”是指未来游客在海外机场，通过支付宝使用航班提醒等服务。");
                break;
        }


    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sa_item_name)
        TextView name;
        @BindView(R.id.sa_item_desc)
        TextView desc;

        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
