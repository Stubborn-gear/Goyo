package com.htdata.goyo.main.make.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htdata.goyo.R;

import java.util.List;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-01 10:08
 * @描述：
 */
public class HomeGridAdapter extends BaseAdapter {


    private Context mContext;
    public List<String> mList;

    public HomeGridAdapter(){}

    public HomeGridAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.act_make_home_grid_item, null);
            holder = new ViewHolder(convertView);// 创建家庭组件对象
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.key.setText(mList.get(position)+"客户\n增长");
        holder.value.setText(mList.get(position));
        holder.comparison.setText(mList.get(position)+"%");
        if (Integer.parseInt(mList.get(position)) > 0){
            holder.comparison.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }else {
            holder.comparison.setTextColor(ContextCompat.getColor(mContext,R.color.red3));
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.home_grid_view_key)
        TextView key;
        @BindView(R.id.home_grid_view_value)
        TextView value;
        @BindView(R.id.home_grid_view_comparison)
        TextView comparison;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
