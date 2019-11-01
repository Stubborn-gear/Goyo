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
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.htdata.goyo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * cuibo
 * 2018/5/21 11:09
 */

public class LineChartManager3 {

    private LineChart lineChart;
    private YAxis leftAxis;   //左边Y轴
    private YAxis rightAxis;  //右边Y轴
    private XAxis xAxis;      //X轴
    private Context mContext ;

    public LineChartManager3(LineChart mLineChart, Context context) {
        this.lineChart = mLineChart;
        if (lineChart != null){
            leftAxis = lineChart.getAxisLeft();
            rightAxis = lineChart.getAxisRight();
            xAxis = lineChart.getXAxis();
        }
        this.mContext = context ;
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        lineChart.setTouchEnabled(false);
        lineChart.setDrawGridBackground(false); // 是否设置背景
        lineChart.setExtraLeftOffset(13f);
        lineChart.setExtraRightOffset(15);//距视图窗口右部的偏移，类似与paddingRight
        lineChart.setExtraBottomOffset(-5f);
        lineChart.setExtraTopOffset(-5);
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
//        xAxis.setAvoidFirstLastClipping(false);//x轴上起点和终点坐标数显示不完整
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
        lineDataSet.setColor(mContext.getResources().getColor(R.color.red2));
        lineDataSet.setCircleColor(mContext.getResources().getColor(R.color.red2));
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setValueTextSize(9f);
        //设置折线图填充
//        if (SdkUtil.isSdkVersion18()){
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
     * @param xValues
     * @param yValues
    //     * @param label
    //     * @param color
     */
    public void showLineChart(final List<String> xValues, final List<String> yValues, boolean isUseInteger/*, String label, int color*/) {
        initLineChart();
//       final ArrayList<String> xValues = new ArrayList<>();
//        xValues.add("05-01");
//        xValues.add("05-02");
//        xValues.add("05-03");
//        xValues.add("05-04");
//        ArrayList<String> yValues = new ArrayList<>();
//        yValues.add("1200");
//        yValues.add("1400");
//        yValues.add("1600");
//        yValues.add("1800");
        if (isUseInteger){// 是否对左Y轴使用 int类型数值
//            String max = Collections.max(yValues);
//            int max2 = Integer.parseInt(max);
//            lineChart.getAxisLeft().setAxisMaximum(getZeroInteger(max2,max.length()));
//            lineChart.getAxisLeft().setAxisMinimum(0);

            String max = Collections.max(yValues);
            int max2 = Integer.parseInt(max);
//            if (max2 < 10){
//                lineChart.getAxisLeft().setAxisMaximum(getZeroInteger(max2,max.length()));
//            }else {
//                lineChart.getAxisLeft().setAxisMaximum(max2);
//            }
            lineChart.getAxisLeft().setAxisMaximum(getZeroInteger(max2,max.length()));
            lineChart.getAxisLeft().setAxisMinimum(0);
            YAxis yAxis = lineChart.getAxisLeft();
            yAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    int round = Math.round(value);
                    return String.valueOf(round);
                }
            });
        }

        ArrayList<Entry> yListData = new ArrayList<>();
        for(int i=0;i<yValues.size();i++){
            yListData.add(new Entry(i, Float.parseFloat(yValues.get(i))));
        }

        LineData lineData = new LineData();
        LineDataSet lineDataSet = new LineDataSet(yListData,"");
        initLineDataSet(lineDataSet, Color.BLUE,false);
        lineData.addDataSet(lineDataSet);
        lineDataSet.setValueFormatter(new IValueFormatter() {// 定义数据 类型
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.valueOf((int)value);
            }
        });

        if (yValues.size() == 1){
//            xAxis.setAvoidFirstLastClipping(false);
//            xAxis.setCenterAxisLabels(true);
//            xAxis.setLabelCount(1,true);
            xAxis.setCenterAxisLabels(true);
        }
        lineDataSet.setLineWidth(1.5f);// 折线宽度
        lineData.setValueTextSize(8f);
        lineData.setValueTextColor(mContext.getResources().getColor(R.color.gray));
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
            return  String.valueOf(v2);
        }
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
                if (lineChart != null){
                    lineChart.setNoDataText(mContext.getResources().getString(R.string.no_data));
                    lineChart.setNoDataTextColor(ContextCompat.getColor(mContext,R.color.line));
                    lineChart.invalidate();
                    lineChart.setNoDataTextTypeface(Typeface.DEFAULT);
//              mChart.setDescription(null);
//              mChart.refreshDrawableState();
                }
            }
        },0);

    }

    private int getZeroInteger(int max , int length){
        int i = 10;
        switch (length){
            case 1://十
                i = (max + 10 )-max ;
                break;
            case 2://百
                if (max > 50){
                    i = (max + 100 )-max ;
                }else {
                    i = (max + 50 )-max ;
                }
                break;
            case 3://千
                if (max > 500){
                    i = (max + 1000 )-max ;
                }else {
                    i = (max + 500 )-max ;
                }
                break;
            case 4:
                if (max > 5000){
                    i = (max + 10000 )-max ;
                }else {
                    i = (max + 5000 )-max ;
                }
                break;
            case 5:
                if (max > 50000){
                    i = (max + 100000 )-max ;
                }else {
                    i = (max + 50000 )-max ;
                }
                break;
            case 6:
                if (max > 500000){
                    i = (max + 1000000 )-max ;
                }else {
                    i = (max + 500000 )-max ;
                }
                break;
            case 7:
                i = (max + 10000000 )-max ;
                break;
            case 8:
                i = (max + 100000000 )-max ;
                break;
        }
        return i ;
    }

}
