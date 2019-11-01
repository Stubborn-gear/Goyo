package com.htdata.goyo.util.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
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
import com.htdata.goyo.main.make.model.MakeHomeBarModel;
import com.htdata.goyo.util.ConversionUtil;
import com.htdata.goyo.util.TimeUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 *  垂直柱状图
 */
public class BarChartManager{


    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;
    private DecimalFormat mFormat;

    public BarChartManager(BarChart barChart) {
        this.mBarChart = barChart;
        leftAxis = mBarChart.getAxisLeft();
        rightAxis = mBarChart.getAxisRight();
        xAxis = mBarChart.getXAxis();
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        mFormat = new DecimalFormat("#,###.##");
        //背景颜色
//        mBarChart.setBackgroundColor(Color.WHITE);
        //是否显示网格背景
        mBarChart.setDrawGridBackground(false);
        //显示每条背景阴影
        mBarChart.setDrawBarShadow(false);
        //设置图标边框的颜色
        mBarChart.setBorderColor(Color.parseColor("#ff0000"));
//        mBarChart.setHighlightFullBarEnabled(false); //高亮显示
        mBarChart.setTouchEnabled(false); // 所有触摸事件,默认true
        mBarChart.setDragEnabled(true);    // 可拖动,默认true
        mBarChart.setScaleEnabled(false);   // 两个轴上的缩放,X,Y分别默认为true
        mBarChart.setScaleXEnabled(false);  // X轴上的缩放,默认true
        mBarChart.setScaleYEnabled(false);  // Y轴上的缩放,默认true
        mBarChart.setPinchZoom(false);  // X,Y轴同时缩放，false则X,Y轴单独缩放,默认false
        mBarChart.setDoubleTapToZoomEnabled(false); // 双击缩放,默认true
        mBarChart.setDragDecelerationEnabled(true);    // 抬起手指，继续滑动,默认true

//        mBarChart.getAxisLeft().setEnabled(false); // 隐藏左边 的坐标轴

        //显示边界
        mBarChart.setDrawBorders(false);
        //设置XY动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);
//      不显示描述信息
        mBarChart.getDescription().setEnabled(false);
//         图例设置
        Legend legend = mBarChart.getLegend();
        //不显示图例
        legend.setForm(Legend.LegendForm.NONE);
//        图例文字的大小
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

//      X轴最小间距
        xAxis.setGranularity(1f);
//      不绘制网格线
        xAxis.setDrawGridLines(false);
//      X轴字体样式
        xAxis.setTypeface(Typeface.DEFAULT_BOLD);
//      设置X轴文字剧中对齐
        xAxis.setCenterAxisLabels(true);
//
//       保证Y轴从0开始，不然会上移一点
        leftAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(Color.parseColor("#ffffff"));
//        // 线跟数据都不显示
        rightAxis.setEnabled(false); //右侧Y轴不显示
    }

    /**
     * 展示柱状图(一条)
     */
    public void showBarChart(List<BarEntry> yList, String label, int color) {
        initLineChart();

        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(yList, label);
        barDataSet.setColor(color);
        //是否显示顶部的值
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextColor(Color.parseColor("#ffffff"));
//        文字的大小
        barDataSet.setValueTextSize(10f);

        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.0f);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
//      设置宽度
        data.setBarWidth(0.3f);// 柱宽度
        //设置X轴的刻度数
        String[] xValues = {"预精轧机", "精轧机", "吐丝机", "小型轧机","打包机"};
//        String[] yValues = {"1", "5", "10", "15", "25", "30"};
        xAxis.setLabelCount(yList.size() + 1, true);
        xAxis.setDrawLabels(true);
        IAxisValueFormatter xAxisFormatter = new XAxisValueFormatter(xValues);
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setTextColor(Color.parseColor("#ffffff"));
        xAxis.setAxisLineColor(Color.parseColor("#ffffff"));
//        IAxisValueFormatter custom = new MyYAxisValueFormatter(yValues);
//        leftAxis.setValueFormatter(custom);
//        leftAxis.setLabelCount(yValues.length + 1, false);
        leftAxis.setAxisLineColor(Color.parseColor("#ffffff"));
//        设置Y轴的最小值和最大值
//        leftAxis.setAxisMaximum(80f);
//        leftAxis.setAxisMinimum(50f);
        mBarChart.setData(data);
    }


    public class MyYAxisValueFormatter implements IAxisValueFormatter {

        private String[] yValues;

        public MyYAxisValueFormatter(String[] values) {
            yValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
//            Log.e("TAG", "xValues[(int) value]====="+xValues[(int) value]);
            System.out.println(value+"============="+mFormat.format(value) + "%");
            return mFormat.format(value) + "%";
        }
    }


    public class XAxisValueFormatter implements IAxisValueFormatter {
        private String[]xValues;

        public XAxisValueFormatter(String[]  xValues) {
            this.xValues = xValues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return xValues[(int) value];
        }
    }

    /**
     * 展示柱状图(多条)
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    public void showMoreBarChart(final List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart();
        BarData data = new BarData();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {

                entries.add(new BarEntry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));

            barDataSet.setColor(colours.get(i));
            barDataSet.setValueTextColor(colours.get(i));
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            data.addDataSet(barDataSet);
        }
        int amount = yAxisValues.size();

        float groupSpace = 0.3f; //柱状图组之间的间距
        float barSpace = (float) ((1 - 0.12) / amount / 10); // x4 DataSet
        float barWidth = (float) ((1 - 0.3) / amount / 10 * 9); // x4 DataSet

        // (0.2 + 0.02) * 4 + 0.08 = 1.00 -> interval per "group"
        xAxis.setLabelCount(xAxisValues.size() - 1, false);
        data.setBarWidth(barWidth);
//        final String[] xValues = {"小学", "初中", "高中", "专科", "本科"};
        final String[] xValues = {};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                for (int i=0;i<xAxisValues.size();i++){
                    if(value==(xAxisValues.get(i)-1)) {
                        return xValues[i];
                    }
                }
                return "";
            }
        });
        data.groupBars(0, groupSpace, barSpace);
        mBarChart.setData(data);
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

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);
        mBarChart.invalidate();
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
        xAxis.setLabelCount(labelCount, false);

        mBarChart.invalidate();
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
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置低限制线
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
        mBarChart.invalidate();
    }

    /**
     * 设置描述信息
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        mBarChart.setDescription(description);
        mBarChart.invalidate();
    }

























    /*private BarChart bineChart;
    private YAxis leftAxis;   //左边Y轴
    private YAxis rightAxis;  //右边Y轴
    private XAxis xAxis;      //X轴
    private Context mContext ;

    public BarChartManager(BarChart mBineChart, MakeHomeBarModel model, Context context) {
        this.bineChart = mBineChart;
        leftAxis = bineChart.getAxisLeft();
        rightAxis = bineChart.getAxisRight();
        xAxis = bineChart.getXAxis();
        this.mContext = context ;
        initBarChart(model);
    }

    *//** 初始化 柱状图 *//*
    private void initBarChart(MakeHomeBarModel model){
        bineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
            }
            @Override
            public void onNothingSelected() {
            }
        });
        bineChart.animateXY(1000,1000);// 动画
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
        xAxis.setTextColor(mContext.getResources().getColor(R.color.white));
        xAxis.setTextSize(10f);
        rightAxis.setTextColor(mContext.getResources().getColor(R.color.white));
        rightAxis.setTextSize(10f);

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
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setFormSize(4f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(2f);
        setData(model);
    }

    *//** 设置柱状图数据 *//*
    private void setData(MakeHomeBarModel model){
        List<String> strDate = model.getKeyList();
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        List<String> xValueList = new ArrayList<>();
        if (strDate.size() == 0){
            setNoData();
            return;
        }

        for (int i = 0; i < strDate.size(); i++) {
//            LogUtils.v(historyModel.getRun().get(i) + "=========" + historyModel.getStop().get(i)
//                    + "=====" + historyModel.getStandby().get(i));
            Float val1 = ConversionUtil.toFloat(model.getValueList().get(i));// 运行
//            Float val2 = ConversionUtil.toFloat(historyModel.getStandby().get(i));//待机
//            Float val3 = ConversionUtil.toFloat(historyModel.getStop().get(i));//停机
//            LogUtils.v(val1+ "=========" + val2 + "=====" + val3);
//            xValueList.add(TimeUtil.getMonthDay(strDate.get(i)));
            yVals1.add(new BarEntry(i, new float[]{val1}));
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

    *//** 自定义 图表数据格式 *//*
    class MyAxisValueFormatter implements IAxisValueFormatter {
        List<String> mValues;
        public MyAxisValueFormatter(List<String> values) {
            this.mValues = values;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
//            int i = (int) value % mValues.size();
//            return mValues.get(i);
            return String.valueOf(value);
        }
    }

    private int[] getColors() {
        int stacksize = 1;
        //有尽可能多的颜色每项堆栈值
        int[] colors = new int[stacksize];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = COLORS[i];
        }
        return colors;
    }

    public static final int[] COLORS = {
            rgb("#ff4787F7")
    };

    *//**
     * Converts the given hex-color-string to rgb.
     * @param hex
     * @return
     *//*
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
    }*/

}
