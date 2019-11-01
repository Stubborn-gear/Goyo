package com.htdata.goyo.util.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.htdata.goyo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * cuibo
 * 2018/5/21 11:09
 */

public class PiecChartManager {

    private PieChart pieChart;
    private Context mContext ;

    public PiecChartManager(PieChart mPieChart, Context context) {
        this.pieChart = mPieChart;
        this.mContext = context ;
        initLineChart();
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        //饼状图
        pieChart.setUsePercentValues(true);//设置为TRUE的话，图标中的数据自动变为百分比
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 6, 5, 5);//圆环距离屏幕上下上下左右的距离
        pieChart.setDragDecelerationFrictionCoef(0.95f);//设置滑动减速摩擦系数，在0~1之间
        setCenterTextStr();
        pieChart.setDrawSliceText(true);//设置隐藏饼图上文字，只显示百分比
        pieChart.setDrawHoleEnabled(true);//是否显示圆环中间的洞
        pieChart.setHoleColor(Color.WHITE);//设置饼中心颜色
        //设置圆环透明度及半径
        pieChart.setTransparentCircleColor(Color.WHITE);//透明的圆
        pieChart.setTransparentCircleAlpha(0);// //设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
//        pieChart.setTransparentCircleRadius(61f);// 半透明圈
//        pieChart.setHoleRadius(50f);//中间圆的半径占总半径的百分数
        pieChart.setHoleRadius(66f);//设置圆环中间洞的半径

        pieChart.setDrawCenterText(true);//绘制显示在饼图中心的文本
        pieChart.setRotationAngle(0);//设置pieChart图表起始角度
        // 触摸旋转
        pieChart.setRotationEnabled(false);//通过触摸使图表旋转
        pieChart.setHighlightPerTapEnabled(false);//通过点击手势突出显示的值
    }

    /**
     *
     * @param run  运行
     * @param fault 故障
     * @param standby 待机
     * @param percnetOff    停机
     */
    public void setPieChartData(Float run, Float fault, Float standby, Float percnetOff){

        List<Integer> colorList = new ArrayList<>();
        //添加数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        if (run != 0 && run != 0.0){
            entries.add(new PieEntry(run, ""));//运行
            colorList.add(Color.parseColor("#4787F7"));
        }
        if (fault != 0 && fault != 0.0){
            entries.add(new PieEntry(fault, ""));//故障
            colorList.add(Color.parseColor("#F63D31"));
        }
        if (standby != 0 && standby != 0.0){
            entries.add(new PieEntry(standby, ""));//待机
            colorList.add(Color.parseColor("#abd4fb"));
        }
        if (percnetOff != 0 && percnetOff != 0.0){
            entries.add(new PieEntry(percnetOff, ""));//停机
            colorList.add(Color.parseColor("#DDDDDD"));
        }

//        entries.add(new PieEntry(80, "运行"));//运行
//        colorList.add(Color.parseColor("#4787F7"));
//        entries.add(new PieEntry(5, "故障"));//故障
//        colorList.add(Color.parseColor("#F63D31"));
//        entries.add(new PieEntry(3, "待机"));//待机
//        colorList.add(Color.parseColor("#abd4fb"));
//        entries.add(new PieEntry(2, "停机"));//停机
//        colorList.add(Color.parseColor("#DDDDDD"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(2f);//饼图区块之间的距离
        dataSet.setSelectionShift(5f);//

//        dataSet.setDrawIcons(true);
//        dataSet.setIconsOffset(new MPPointF(0, 40));

        //这一段代码就是实现加一个横线然后将模块的数据放在外面的效果
        dataSet.setValueLinePart1OffsetPercentage(90f);
        dataSet.setValueLinePart1Length(0.4f);//第一段线的长度
        dataSet.setValueLinePart2Length(0.6f);// 第二段线的长度
        dataSet.setValueLineColor(mContext.getResources().getColor(R.color.blue_bg3));//设置连接线的颜色
        //当值显示在界面外面的时候是否允许改变量行长度
        dataSet.setValueLineVariableLength(false);
        //设置线的宽度
        dataSet.setValueLineWidth(0.5f);
        //设置项X值拿出去
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //设置将Y轴的值拿出去
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        dataSet.setSliceSpace(3);// 色块与色块间距
//        dataSet.setSelectionShift(0);// 不知道什么意思



        //数据和颜色
//        Integer[] colors =new Integer[]{Color.parseColor("#4787F7"),
//                Color.parseColor("#F63D31"),
//                Color.parseColor("#abd4fb"),
//                Color.parseColor("#DDDDDD"),};

        //添加对应的颜色值
        List<Integer> colorSum = new ArrayList<>();
        for (Integer color : colorList) {
            colorSum.add(color);
        }
        dataSet.setColors(colorSum);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);
        data.setValueTextColor(mContext.getResources().getColor(R.color.blue_bg3));

        pieChart.setData(data);
//        pieChart.highlightValues(new Highlight[entries.size()]);//在给定的数据集中突出显示给定索引的值



        //刷新
        pieChart.invalidate();
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        setLegend();
    }



    //设置中间文字
    private SpannableString generateCenterSpannableText() {
        int blueColor = mContext.getResources().getColor(R.color.blue_bg3);
        int grayColor = mContext.getResources().getColor(R.color.gray6);
        SpannableString s = new SpannableString("近7天\n运行状态占比 ");
        s.setSpan(new RelativeSizeSpan(1f), 0, 3, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 0, 3, 0);
        s.setSpan(new ForegroundColorSpan(blueColor), 0, 3, 0);
        s.setSpan(new RelativeSizeSpan(0.8f), 3, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 3, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(grayColor), 3, s.length(), 0);
        return s;
    }

    /**
     *  // 图表色块说明
     */
    private void setLegend(){
        // 图表色块说明
        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setEnabled(false);// 设置不显示 图表色块说明
        pieChart.setEntryLabelColor(mContext.getResources().getColor(R.color.gray));//设置饼图每一块的说明的字体颜色
        pieChart.setEntryLabelTextSize(10f);//设置饼图每一块说明的字体大小
//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
    }

    /**
     * 设置中间文字
     */
    private void setCenterTextStr(){
        //设置中间文字
        pieChart.setCenterText(generateCenterSpannableText());
//        pieChart.setCenterTextSize(10.0f);//设置中心文字的字体大小
//        pieChart.setCenterTextColor(Color.RED);//设置中间文字的颜色
//        pieChart.setCenterTextRadiusPercent(0.5f);//设置文字显示的角度，180横着，默认是竖着
//        pieChart.setCenterTextTypeface(null);//设置字体
//        pieChart.setDrawCenterText(true);//中心字使能开关，false时中间无法显示文字
    }


    public void setNoData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (pieChart != null) {
                    pieChart.setNoDataText(mContext.getResources().getString(R.string.no_data));
                    pieChart.setNoDataTextColor(ContextCompat.getColor(mContext, R.color.line));
                    pieChart.invalidate();
                    pieChart.setNoDataTextTypeface(Typeface.DEFAULT);
//              mChart.setDescription(null);
//              mChart.refreshDrawableState();
                }
            }
        }, 0);
    }


}
