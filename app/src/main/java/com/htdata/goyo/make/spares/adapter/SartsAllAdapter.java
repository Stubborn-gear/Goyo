package com.htdata.goyo.make.spares.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htdata.goyo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-07 11:34
 * @描述：
 */
public class SartsAllAdapter extends RecyclerView.Adapter<SartsAllAdapter.ViewHolder> {


    private Context mContext;
    private Activity mActivity;
    private List<String> mList;

//    private int tabType = 1 ;// 1全部 ，2购买 ，3租赁 ，4寄售

    public SartsAllAdapter() {
    }

    public SartsAllAdapter(Context mContext, Activity mActivity, List<String> mList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.act_sarts_all_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        switch (tabType){
//            case 1:
//                setAll(holder,position);
//                break;
//            case 2:
//                setPurchase(holder,position);
//                break;
//            case 3:
//                setLease(holder,position);
//                break;
//            case 4:
//                setConsignment(holder,position);
//                break;
//        }
        setAll(holder,position);
    }
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ss_item_stock_number)
        TextView itemStockNumber;
        @BindView(R.id.ss_item_name)
        TextView itemName;
        @BindView(R.id.ss_item_model)
        TextView itemModel;
        @BindView(R.id.ss_item_purchase)
        TextView itemPurchase;
        @BindView(R.id.ss_item_minimum_stock)
        TextView itemMinimumStock;
        @BindView(R.id.ss_item_use_life)
        TextView itemUseLife;
        @BindView(R.id.ss_item_use_layout)
        LinearLayout itemUseLayout;
        @BindView(R.id.ss_item_min_stock_layout)
        LinearLayout itemMinStockLayout;
        @BindView(R.id.ss_item_stock_layout)
        LinearLayout itemStockLayout;
        @BindView(R.id.ss_item_stock_number_key)
        TextView itemStockNumberKey;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void setAll(ViewHolder holder, int position){
        if (mList.get(position).equals("0")) { //购买
            holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemPurchase.setText(mContext.getString(R.string.purchase));
        }

        if (mList.get(position).equals("1")) { //租赁
            holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.gray11));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.main_color_text));
            holder.itemPurchase.setText(mContext.getString(R.string.lease));
        }

        if (Integer.parseInt(mList.get(position)) > 1) {//寄售
            if (mList.get(position).equals("15") || mList.get(position).equals("18")){
                holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
                holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            }else {
                holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.gray11));
                holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.main_color_text));
            }
            holder.itemPurchase.setText(mContext.getString(R.string.consignment));
        }

        String str2 = "1000<font color='#ffffff'><small>个</small></font>";
        holder.itemStockNumber.setText(Html.fromHtml(str2));


        String str = "1000个";
        SpannableString spannableString = new SpannableString("1000个");
        spannableString.setSpan(new AbsoluteSizeSpan(10,true), str.length() -1, str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 2, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(new BackgroundColorSpan(Color.parseColor("#999999")), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        holder.itemStockNumber.setText(spannableString);


//        holder.itemStockNumber.setText("1000个");
        holder.itemName.setText("三角钢板");
        holder.itemModel.setText("HW1022");
        holder.itemUseLife.setText("399天");
        holder.itemMinimumStock.setText("100个");
    }

    private void setPurchase(ViewHolder holder, int position){
        if (mList.get(position).equals("0")) { //购买
            holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemPurchase.setText(mContext.getString(R.string.purchase));
        }

        if (mList.get(position).equals("1")) { //租赁
            holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.gray11));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.main_color_text));
            holder.itemPurchase.setText(mContext.getString(R.string.lease));
        }

        if (Integer.parseInt(mList.get(position)) > 1) {//寄售
            if (mList.get(position).equals("15") || mList.get(position).equals("18")){
                holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
            }else {
                holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            }
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.gray11));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.main_color_text));
            holder.itemPurchase.setText(mContext.getString(R.string.consignment));
        }

        holder.itemStockNumber.setText("1000个");
        holder.itemName.setText("三角钢板");
        holder.itemModel.setText("HW1022");
        holder.itemUseLife.setText("399天");
        holder.itemMinimumStock.setText("100个");
    }

    private void setLease(ViewHolder holder, int position){
        if (mList.get(position).equals("0")) { //购买
            holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemPurchase.setText(mContext.getString(R.string.purchase));
        }

        if (mList.get(position).equals("1")) { //租赁
            holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.gray11));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.main_color_text));
            holder.itemPurchase.setText(mContext.getString(R.string.lease));
        }

        if (Integer.parseInt(mList.get(position)) > 1) {//寄售
            if (mList.get(position).equals("15") || mList.get(position).equals("18")){
                holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
            }else {
                holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            }
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.gray11));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.main_color_text));
            holder.itemPurchase.setText(mContext.getString(R.string.consignment));
        }

        holder.itemStockNumber.setText("10");
        holder.itemName.setText("三角钢板");
        holder.itemModel.setText("HW1022");
        holder.itemMinimumStock.setText("100个");
    }

    private void setConsignment(ViewHolder holder, int position){
        if (mList.get(position).equals("0")) { //购买
            holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemPurchase.setText(mContext.getString(R.string.purchase));
        }

        if (mList.get(position).equals("1")) { //租赁
            holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.gray11));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.main_color_text));
            holder.itemPurchase.setText(mContext.getString(R.string.lease));
        }

        if (Integer.parseInt(mList.get(position)) > 1) {//寄售
            if (mList.get(position).equals("15") || mList.get(position).equals("18")){
                holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
            }else {
                holder.itemStockLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            }
            holder.itemStockNumberKey.setTextColor(ContextCompat.getColor(mContext, R.color.gray11));
            holder.itemStockNumber.setTextColor(ContextCompat.getColor(mContext, R.color.main_color_text));
            holder.itemPurchase.setText(mContext.getString(R.string.consignment));
        }

        holder.itemStockNumber.setText("10");
        holder.itemName.setText("三角钢板");
        holder.itemModel.setText("HW1022");
        holder.itemMinimumStock.setText("100个");
    }





}
