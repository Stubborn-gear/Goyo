package com.htdata.goyo.util.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * cuibo
 * 2018/5/21 11:09
 * 多条线 的 折线图
 */

public class LineChartMuchManager {

    private LineChart lineChart;
    private YAxis leftAxis;   //左边Y轴
    private YAxis rightAxis;  //右边Y轴
    private XAxis xAxis;      //X轴
    private Context mContext;

    public LineChartMuchManager(LineChart mLineChart, Context context) {
        this.lineChart = mLineChart;
        if (lineChart != null) {
            leftAxis = lineChart.getAxisLeft();
            rightAxis = lineChart.getAxisRight();
            xAxis = lineChart.getXAxis();
        }
        this.mContext = context;
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        lineChart.setTouchEnabled(false);
        lineChart.setDrawGridBackground(false); // 是否设置背景
        lineChart.setExtraLeftOffset(13f);
        lineChart.setExtraRightOffset(15);//距视图窗口右部的偏移，类似与paddingRight
//        lineChart.setExtraOffsets(13, 0, 0, 0);//距离屏幕上下上下左右的距离
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
        // 去掉图表说明
        lineChart.getDescription().setEnabled(false);
        //设置动画效果
        lineChart.animateY(1000, Easing.EasingOption.Linear);
        lineChart.animateX(1000, Easing.EasingOption.Linear);
//        xAxis.setLabelRotationAngle(30);// x轴数据倾斜量
//        lineChart.setDescription(null);
//        lineChart.setNoDataText(mContext.getResources().getString(R.string.no_data));// 设置没有数据时候显示提示
        setNoData();
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
        xAxis.setTextColor(mContext.getResources().getColor(R.color.gray));
        xAxis.setTextSize(8.0f);
        int color = mContext.getResources().getColor(R.color.line);//
        xAxis.setAxisLineColor(color);// 设置 X轴 轴颜色
        xAxis.setAxisLineWidth(0.5f);// 设置X 轴 轴宽度
        leftAxis.setGridColor(color);
        leftAxis.setGridLineWidth(0.5f);
        leftAxis.setAxisLineColor(color);// 设置 Y轴 轴颜色
        leftAxis.setAxisLineWidth(0.5f);// 设置Y 轴 轴宽度



        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(mContext.getResources().getColor(R.color.gray));
        leftAxis.setTextSize(8.0f);
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
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setValueTextSize(9f);
        //设置折线图填充  渐变色
//        if (SdkUtil.isSdkVersion18()) {
//            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.chart_shade_bg);
//            lineDataSet.setFillDrawable(drawable);
//        }
//        lineDataSet.setFillColor(mContext.getResources().getColor(R.color.auditing_in));
        lineDataSet.setDrawFilled(false);// 设置是否支持填充背景色
//        lineDataSet.setFillAlpha(65);// 设置透明度
//        lineDataSet.setFillColor(mContext.getResources().getColor(R.color.auditing_in));//背景色
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        //线模式为圆滑曲线（默认折线）
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
    }

    /**
     * 展示折线图(一条)
     *
     * @param xValues
     * @param yValues //     * @param label
     *                //     * @param color
     */
    public void showLineChart(final List<String> xValues, List<String> yValues/*, String label, int color*/) {
        initLineChart();
        ArrayList<Entry> yListData = new ArrayList<>();
        for (int i = 0; i < xValues.size(); i++) {
            yListData.add(new Entry(i, Float.parseFloat(yValues.get(i))));
        }
        LineData lineData = new LineData();
        LineDataSet lineDataSet = new LineDataSet(yListData, "");
        lineData.addDataSet(lineDataSet);
        initLineDataSet(lineDataSet, Color.BLUE, false);
        lineChart.setData(lineData);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues.get((int) value % xValues.size());
            }
        });

        //通知数据已经改变
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
    }

    /**
     * 展示线性图(多条)
     */
    public void showLineChart(final List<String> xAxisValues, List<Double> yAxisValues1,
                              List<Double> yAxisValues2, List<Double> yAxisValues3/*,
                              List<Double> yAxisValues4*/) {
        initLineChart();
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        ArrayList<Entry> yEntry1 = new ArrayList<>();
        ArrayList<Entry> yEntry2 = new ArrayList<>();
        ArrayList<Entry> yEntry3 = new ArrayList<>();
        ArrayList<Entry> yEntry4 = new ArrayList<>();

        boolean isDataZero1 = false ; // 判断 集合数据是否全部是零
        boolean isDataZero2 = false ;
        boolean isDataZero3 = false ;
        boolean isDataZero4 = false ;
        for (int j = 0; j < yAxisValues1.size(); j++) {
            String str = String.valueOf(yAxisValues1.get(j));
            float yData = Float.parseFloat(str);
            isMinusNumber(yData);
            if (isDataZero1){
                break;
            }
            if (yData > 0){
                isDataZero1 = true ;
            }else {
                isDataZero1 = false ;
            }
        }

        for (int j = 0; j < yAxisValues2.size(); j++) {
            String str = String.valueOf(yAxisValues2.get(j));
            float yData = Float.parseFloat(str);
            isMinusNumber(yData);
            if (isDataZero2){
                break;
            }
            if (yData > 0){
                isDataZero2 = true ;
            }else {
                isDataZero2 = false ;
            }
        }

        for (int j = 0; j < yAxisValues3.size(); j++) {
            String str = String.valueOf(yAxisValues3.get(j));
            float yData = Float.parseFloat(str);
            isMinusNumber(yData);
            if (isDataZero3){
                break;
            }
            if (yData > 0){
                isDataZero3 = true ;
            }else {
                isDataZero3 = false ;
            }
        }

//        for (int j = 0; j < yAxisValues4.size(); j++) {
//            String str = String.valueOf(yAxisValues4.get(j));
//            float yData = Float.parseFloat(str);
//            isMinusNumber(yData);
//            if (isDataZero4){
//                break;
//            }
//            if (yData > 0){
//                isDataZero4 = true ;
//            }else {
//                isDataZero4 = false ;
//            }
//        }

        for (int j = 0; j < yAxisValues1.size(); j++) {
            String str = String.valueOf(yAxisValues1.get(j));
            yEntry1.add(new Entry( j, Float.parseFloat(str)));
        }
        for (int j = 0; j < yAxisValues2.size(); j++) {
            String str = String.valueOf(yAxisValues2.get(j));
            yEntry2.add(new Entry( j, Float.parseFloat(str)));
        }
        for (int j = 0; j < yAxisValues3.size(); j++) {
            String str = String.valueOf(yAxisValues3.get(j));
//            LogUtils.v("=========off=========="+Float.parseFloat(str));
            yEntry3.add(new Entry( j, Float.parseFloat(str)));
        }
//        for (int j = 0; j < yAxisValues4.size(); j++) {
//            String str = String.valueOf(yAxisValues4.get(j));
//            yEntry4.add(new Entry( j, Float.parseFloat(str)));
//        }

        LineDataSet lineDataSet1 = new LineDataSet(yEntry1, "");
        LineDataSet lineDataSet2 = new LineDataSet(yEntry2, "");
        LineDataSet lineDataSet3 = new LineDataSet(yEntry3, "");
        LineDataSet lineDataSet4 = new LineDataSet(yEntry4, "");

        if (isDataZero1){
            initLineDataSet(lineDataSet1, mContext.getResources().getColor(R.color.blue_bg3), false);
            iLineDataSets.add(lineDataSet1);
        }
        if (isDataZero2){
            initLineDataSet(lineDataSet2, mContext.getResources().getColor(R.color.blue4), false);
            iLineDataSets.add(lineDataSet2);
        }
        if (isDataZero3){
            initLineDataSet(lineDataSet3, mContext.getResources().getColor(R.color.red2), false);
            iLineDataSets.add(lineDataSet3);
        }
//        if (isDataZero4){
//            initLineDataSet(lineDataSet4, mContext.getResources().getColor(R.color.red2), false);
//            iLineDataSets.add(lineDataSet4);
//        }

        lineDataSet1.setLineWidth(1.5f);// 折线宽度
        lineDataSet2.setLineWidth(1.5f);
        lineDataSet3.setLineWidth(1.5f);
        lineDataSet4.setLineWidth(1.5f);
//        lineDataSet4.setColor(1.5f);// 折线显示颜色
//        lineDataSet4.setCircleSize(2f);// 折线的圆形大小


        if (!isDataZero1 && !isDataZero2 && !isDataZero3 && !isDataZero4){
            setNoData();
        }else {
            LineData data = new LineData(iLineDataSets);
//        xAxis.setLabelCount(xAxisValues.size(), true);

            data.setValueTextSize(8f);
            data.setValueTextColor(mContext.getResources().getColor(R.color.gray));
            lineChart.setData(data);
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisValues.get((int) value % xAxisValues.size());
            }
        });
    }

    /**
     * 展示线性图(多条)
     */
    public void showLineChart2() {
        initLineChart();
        final ArrayList<String> xAxisValues = new ArrayList<>();
        xAxisValues.add("05-01");
        xAxisValues.add("05-02");
        xAxisValues.add("05-03");
        xAxisValues.add("05-04");
        xAxisValues.add("05-05");
        xAxisValues.add("05-06");
        xAxisValues.add("05-07");

        ArrayList<String> yAxisValues1 = new ArrayList<>();
        yAxisValues1.add("1000");
        yAxisValues1.add("1200");
        yAxisValues1.add("1400");
        yAxisValues1.add("1600");
        yAxisValues1.add("1800");
        yAxisValues1.add("2000");
        yAxisValues1.add("2200");

        ArrayList<String> yAxisValues2 = new ArrayList<>();
        yAxisValues2.add("1000");
        yAxisValues2.add("1500");
        yAxisValues2.add("2100");
        yAxisValues2.add("1600");
        yAxisValues2.add("1800");
        yAxisValues2.add("2000");
        yAxisValues2.add("2600");

        ArrayList<String> yAxisValues3 = new ArrayList<>();
        yAxisValues2.add("1000");
        yAxisValues2.add("1500");
        yAxisValues2.add("2100");
        yAxisValues2.add("1600");
        yAxisValues2.add("1800");
        yAxisValues2.add("2000");
        yAxisValues2.add("2600");

        ArrayList<String> yAxisValues4 = new ArrayList<>();
        yAxisValues2.add("1000");
        yAxisValues2.add("1500");
        yAxisValues2.add("2100");
        yAxisValues2.add("1600");
        yAxisValues2.add("1800");
        yAxisValues2.add("2000");
        yAxisValues2.add("2600");

    //======================================================================
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        ArrayList<Entry> yEntry1 = new ArrayList<>();
        ArrayList<Entry> yEntry2 = new ArrayList<>();
        ArrayList<Entry> yEntry3 = new ArrayList<>();
        ArrayList<Entry> yEntry4 = new ArrayList<>();
        for (int j = 0; j < yAxisValues1.size(); j++) {
            yEntry1.add(new Entry( j, Float.parseFloat(yAxisValues1.get(j))));
        }
        for (int j = 0; j < yAxisValues2.size(); j++) {
            yEntry2.add(new Entry( j, Float.parseFloat(yAxisValues2.get(j))));
        }
        for (int j = 0; j < yAxisValues3.size(); j++) {
            yEntry3.add(new Entry( j, Float.parseFloat(yAxisValues3.get(j))));
        }
        for (int j = 0; j < yAxisValues4.size(); j++) {
            yEntry4.add(new Entry( j, Float.parseFloat(yAxisValues4.get(j))));
        }

        LineDataSet lineDataSet1 = new LineDataSet(yEntry1, "");
        LineDataSet lineDataSet2 = new LineDataSet(yEntry2, "");
        LineDataSet lineDataSet3 = new LineDataSet(yEntry3, "");
        LineDataSet lineDataSet4 = new LineDataSet(yEntry4, "");
        initLineDataSet(lineDataSet1, mContext.getResources().getColor(R.color.blue_bg3), false);
        initLineDataSet(lineDataSet2, mContext.getResources().getColor(R.color.blue4), false);
        initLineDataSet(lineDataSet3, mContext.getResources().getColor(R.color.line), false);
        initLineDataSet(lineDataSet4, mContext.getResources().getColor(R.color.red2), false);
        iLineDataSets.add(lineDataSet1);
        iLineDataSets.add(lineDataSet2);
        iLineDataSets.add(lineDataSet3);
        iLineDataSets.add(lineDataSet4);

        LineData data = new LineData(iLineDataSets);
//        xAxis.setLabelCount(xAxisValues.size(), true);
        lineChart.setData(data);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisValues.get((int) value % xAxisValues.size());
            }
        });
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
     * 自定义 图表数据格式
     */
    class MyAxisValueFormatter implements IAxisValueFormatter {
        List<String> mValues;

        public MyAxisValueFormatter(List<String> values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int i = (int) value % mValues.size();
            int v2 = (int) value;
            return String.valueOf(v2);
        }
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

    public void setNoData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (lineChart != null) {
                    lineChart.setNoDataText(mContext.getResources().getString(R.string.no_data));
                    lineChart.setNoDataTextColor(ContextCompat.getColor(mContext, R.color.line));
                    lineChart.invalidate();
                    lineChart.setNoDataTextTypeface(Typeface.DEFAULT);
//              mChart.setDescription(null);
//              mChart.refreshDrawableState();
                }
            }
        }, 0);

    }

    /** 是否是负数 */
    private void isMinusNumber(Float number){
        if (number < 0){
            leftAxis.setAxisMinimum(number+(-50));
            //        leftAxis.setStartAtZero(false);设置Y轴是否从0开始
        }
    }


}
