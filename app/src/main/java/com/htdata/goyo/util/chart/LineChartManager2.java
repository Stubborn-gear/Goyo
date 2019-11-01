package com.htdata.goyo.util.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.htdata.goyo.R;
import com.htdata.goyo.util.LogUtils;
import com.htdata.goyo.util.SdkUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * cuibo
 * 2018/5/21 11:09
 */

public class LineChartManager2 {

    private LineChart lineChart;
    private YAxis leftAxis;   //左边Y轴
    private YAxis rightAxis;  //右边Y轴
    private XAxis xAxis;      //X轴
    private Context mContext ;

    public LineChartManager2(LineChart mLineChart, Context context) {
        this.lineChart = mLineChart;
        leftAxis = lineChart.getAxisLeft();
        rightAxis = lineChart.getAxisRight();
        xAxis = lineChart.getXAxis();
        this.mContext = context ;
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        lineChart.setTouchEnabled(false);
        lineChart.setDrawGridBackground(false); // 是否设置背景
        //显示边界
        lineChart.setDrawBorders(false);// 设置边界线
        //去掉纵向网格线和顶部边线
//        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getXAxis().setDrawGridLines(false);
        // 去掉左右边线
        lineChart.getAxisLeft().setDrawAxisLine(true);
        lineChart.getAxisRight().setDrawAxisLine(false);
        // 隐藏右侧Y轴（只在左侧的Y轴显示刻度）
        rightAxis.setEnabled(false);
        xAxis.setEnabled(false);
        // 去掉图表说明
        lineChart.getDescription().setEnabled(false);
        //设置动画效果
        lineChart.animateY(1000, Easing.EasingOption.Linear);
        lineChart.animateX(1000, Easing.EasingOption.Linear);
        lineChart.setNoDataText(mContext.getResources().getString(R.string.no_data));// 设置没有数据时候显示提示
//        lineChart.setExtraRightOffset(25);//距视图窗口右部的偏移，类似与paddingRight
//        xAxis.setLabelRotationAngle(30);

        leftAxis.setTextColor(mContext.getResources().getColor(R.color.gray));
        leftAxis.setTextSize(7.0f);

        //折线图例 标签 设置
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);// 去掉颜色说明
        legend.setTextSize(0f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
//        rightAxis.setAxisMinimum(0f);
    }

    /**
     * 初始化曲线 每一个LineDataSet代表一条线
     *
     * @param lineDataSet
     * @param color
     * @param mode        折线图是否填充
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, boolean mode) {
        lineDataSet.setColor(mContext.getResources().getColor(R.color.blue_bg3));
        lineDataSet.setCircleColor(mContext.getResources().getColor(R.color.blue_bg3));
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setValueTextSize(9f);
        //设置折线图填充
        if (SdkUtil.isSdkVersion18()){
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.chart_shade_bg);
            lineDataSet.setFillDrawable(drawable);
        }
        lineDataSet.setDrawFilled(mode);// 设置是否支持透明度
//        lineDataSet.setFillAlpha(10);// 设置透明度
//        lineDataSet.setFillColor(Color.RED);//透明颜色
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        //线模式为圆滑曲线（默认折线）
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
    }

    /**
     * 展示折线图(一条)
     * @param xValues
     * @param yValues
//     * @param label
//     * @param color
     */
    public void showLineChart(final List<String> xValues, List<String> yValues/*, String label, int color*/) {
        initLineChart();

//       final ArrayList<String> xValues = new ArrayList<>();
//        xValues.add("2018-05-01");
//        xValues.add("2018-05-02");
//        xValues.add("2018-05-03");
//        xValues.add("2018-05-04");

//        ArrayList<String> yValues = new ArrayList<>();
//        yValues.add("1200");
//        yValues.add("1400");
//        yValues.add("1600");
//        yValues.add("1800");

        ArrayList<Entry> yListData = new ArrayList<>();

        for(int i=0;i<yValues.size();i++){
            yListData.add(new Entry(i, Float.parseFloat(yValues.get(i))));
        }

        LineData lineData = new LineData();
        LineDataSet lineDataSet = new LineDataSet(yListData,"");
        lineData.addDataSet(lineDataSet);
        initLineDataSet(lineDataSet, Color.BLUE,true);
        if (yValues.size() >= 1){
            lineDataSet.setDrawCircles(false); //显示圆点
        }else {
            lineDataSet.setDrawCircles(true); //显示圆点
        }
        //不显示圆点
//        lineDataSet.setDrawCircles(false);
        //折线图不显示数值
        lineData.setDrawValues(false);
        lineChart.setData(lineData);

        if (yValues.size() == 1){
            xAxis.setCenterAxisLabels(true);
        }

//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return xValues.get((int) value % xValues.size());
//            }
//        });

        //通知数据已经改变
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();


//        ArrayList<Entry> entries = new ArrayList<>();
//        for (int i = 0; i < xAxisValues.size(); i++) {
//            LogUtils.v(xAxisValues.get(i)+"========...======"+yAxisValues.get(i));
//            entries.add(new Entry(xAxisValues.get(i), yAxisValues.get(i)));
//        }
//        // 每一个LineDataSet代表一条线
//        LineDataSet lineDataSet = new LineDataSet(entries, label);
//        initLineDataSet(lineDataSet, color, true);
//
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet);
//        LineData data = new LineData(dataSets);
//        //设置X轴的刻度数
//        xAxis.setLabelCount(xAxisValues.size(), true);
//        lineChart.setData(data);
    }

    /** 自定义 图表数据格式 */
    class MyAxisValueFormatter implements IAxisValueFormatter {
        List<String> mValues;
        public MyAxisValueFormatter(List<String> values) {
            this.mValues = values;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int i = (int) value % mValues.size();

            int v2 = (int)value ;
            LogUtils.v("==========="+v2);
            return  String.valueOf(v2);
        }
    }





    /**
     * 展示线性图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues 多条曲线Y轴数据集合的集合
     * @param labels
     * @param colours
     */
    public void showLineChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                if (j >= xAxisValues.size()) {
                    j = xAxisValues.size() - 1;
                }
                entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, labels.get(i));

            initLineDataSet(lineDataSet, colours.get(i), false);
            dataSets.add(lineDataSet);
        }
        LineData data = new LineData(dataSets);
        xAxis.setLabelCount(xAxisValues.size(), true);
        lineChart.setData(data);
    }

    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

//        rightAxis.setAxisMaximum(max);
//        rightAxis.setAxisMinimum(min);
//        rightAxis.setLabelCount(labelCount, false);
        lineChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, true);

        lineChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(2f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }

    public void setNoData(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lineChart.setNoDataText(mContext.getResources().getString(R.string.no_data));
                lineChart.setNoDataTextColor(ContextCompat.getColor(mContext,R.color.line));
                lineChart.invalidate();
                lineChart.setNoDataTextTypeface(Typeface.DEFAULT);
//              mChart.setDescription(null);
//              mChart.refreshDrawableState();
            }
        },0);
    }


}
