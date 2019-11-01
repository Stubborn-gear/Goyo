package com.htdata.goyo.util.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.htdata.goyo.R;
import com.htdata.goyo.util.ConversionUtil;
import com.htdata.goyo.util.TimeUtil;


import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * cuibo
 * 2018/5/22 10:51
 */

public class HorizontalBarChartManager {

    private HorizontalBarChart bineChart;
    private YAxis leftAxis;   //左边Y轴
    private YAxis rightAxis;  //右边Y轴
    private XAxis xAxis;      //X轴
    private Context mContext ;

    public HorizontalBarChartManager(HorizontalBarChart horizontalBarChart, RunHistoryModel historyModel, Context context) {
        this.bineChart = horizontalBarChart;
        leftAxis = bineChart.getAxisLeft();
        rightAxis = bineChart.getAxisRight();
        xAxis = bineChart.getXAxis();
        this.mContext = context ;
        initBarChart(historyModel);
    }

    /** 初始化 柱状图 */
    private void initBarChart(RunHistoryModel historyModel){
        bineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
            }
            @Override
            public void onNothingSelected() {
            }
        });
        bineChart.setTouchEnabled(false);
        bineChart.getDescription().setEnabled(false);
        bineChart.setMaxVisibleValueCount(20);
        // 扩展现在只能分别在x轴和y轴
        bineChart.setPinchZoom(false);
        bineChart.setDrawGridBackground(false);
        bineChart.setDrawBarShadow(false);
        bineChart.setDrawValueAboveBar(false);
        bineChart.setHighlightFullBarEnabled(false);
        bineChart.setNoDataText(mContext.getResources().getString(R.string.no_data));// 设置没有数据时候显示提示

//        xAxis.setXOffset(8);
//        bineChart.setExtraOffsets(8, 0, 0, 0);//距离屏幕上下上下左右的距离

        // 改变y标签的位置
        leftAxis.setAxisMinimum(0f);
        bineChart.getAxisRight().setEnabled(false);
        XAxis xLabels = bineChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(mContext.getResources().getColor(R.color.gray));
        xAxis.setTextSize(7.0f);
        rightAxis.setTextColor(mContext.getResources().getColor(R.color.gray));
        rightAxis.setTextSize(7.0f);

        int color = mContext.getResources().getColor(R.color.line);//
        rightAxis.setAxisLineColor(color);// 设置 Y轴 轴颜色
        rightAxis.setAxisLineWidth(0.5f);// 设置Y 轴 轴宽度
        rightAxis.setGridColor(color);
        rightAxis.setGridLineWidth(0.5f);

//        bineChart.setDrawBorders(false); // 启用/禁用绘制图表边框（chart周围的线）
//        bineChart.setBorderColor() // 设置 chart 边框线的颜色。
        rightAxis.setEnabled(true);
        leftAxis.setEnabled(false);
        rightAxis.setAxisMinimum(0.0f);
        rightAxis.setAxisMaximum(24.0f);
        leftAxis.setAxisMinimum(0.0f);
        leftAxis.setAxisMaximum(24.0f);


//        leftAxis.setCenterAxisLabels(true);
//        rightAxis.setCenterAxisLabels(true);
//        rightAxis.setSpaceTop(50);
//        leftAxis.setSpaceTop(50);

        Legend l = bineChart.getLegend();
        bineChart.getLegend().setEnabled(false);// 去掉颜色说明
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(4f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(2f);
        setData(historyModel);
    }

    /** 设置柱状图数据 */
    private void setData(RunHistoryModel historyModel){
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        List<String> strDate = historyModel.getDate();
        List<String> xValueList = new ArrayList<>();
        if (strDate.size() == 0){
            setNoData();
            return;
        }

        for (int i = 0; i < strDate.size(); i++) {
//            LogUtils.v(historyModel.getRun().get(i) + "=========" + historyModel.getStop().get(i)
//                    + "=====" + historyModel.getStandby().get(i));
            Float val1 = ConversionUtil.toFloat(historyModel.getRun().get(i));// 运行
            Float val2 = ConversionUtil.toFloat(historyModel.getStandby().get(i));//待机
            Float val3 = ConversionUtil.toFloat(historyModel.getStop().get(i));//停机
//            LogUtils.v(val1+ "=========" + val2 + "=====" + val3);
            xValueList.add(TimeUtil.getMonthDay(strDate.get(i)));
            yVals1.add(new BarEntry(i, new float[]{val1, val2, val3}));
        }

        BarDataSet set1;
        if (bineChart.getData() != null && bineChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) bineChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            bineChart.getXAxis().setValueFormatter(new MyAxisValueFormatter(xValueList));
            bineChart.getData().notifyDataChanged();
            bineChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");
            set1.setColors(getColors());
//            set1.setStackLabels(new String[]{"及格", "优秀", "不及格"});
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            xAxis.setValueFormatter(new MyAxisValueFormatter(xValueList));
            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return value+"";
                }
            });

//            data.setDrawValues(false);//是否显示柱状图上的数值

            data.setValueTextColor(mContext.getResources().getColor(R.color.white));
            data.setValueTextSize(8.0f);
            data.setBarWidth(0.42F);// 设置柱状的宽度
            bineChart.setData(data);
        }

//        bineChart.groupBars(0,1,1);
        bineChart.setFitBars(true);
        bineChart.invalidate();
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
            return mValues.get(i);
        }
    }

    private int[] getColors() {
        int stacksize = 3;
        //有尽可能多的颜色每项堆栈值
        int[] colors = new int[stacksize];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = COLORS[i];
        }
        return colors;
    }

    public static final int[] COLORS = {
            rgb("#4787F7"), rgb("#abd4fb"), rgb("#DDDDDD")
    };

    /**
     * Converts the given hex-color-string to rgb.
     * @param hex
     * @return
     */
    public static int rgb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return Color.rgb(r, g, b);
    }

    public void setNoData(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bineChart.setNoDataText(mContext.getResources().getString(R.string.no_data));
                bineChart.setNoDataTextColor(ContextCompat.getColor(mContext,R.color.line));
                bineChart.invalidate();
                bineChart.setNoDataTextTypeface(Typeface.DEFAULT_BOLD);
//              mChart.setDescription(null);
//              mChart.refreshDrawableState();
            }
        },0);
    }

}
