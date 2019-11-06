package com.htdata.goyo.make.home.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.github.mikephil.charting.charts.PieChart;
import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseActivity;
import com.htdata.goyo.make.home.fragment.FaultDataFragment;
import com.htdata.goyo.make.home.fragment.HistoryDataFragment;
import com.htdata.goyo.make.home.fragment.OneLevelFragment;
import com.htdata.goyo.make.home.fragment.RealTimeDataFragment;
import com.htdata.goyo.make.home.fragment.StatisAnalysFragment;
import com.htdata.goyo.make.home.model.MonitorModel;
import com.htdata.goyo.util.chart.LineChartMuchManager;
import com.htdata.goyo.util.chart.PiecChartManager;
import com.htdata.goyo.view.MyViewPager;
import com.htdata.goyo.view.indicator.DynamicPagerIndicator;
import com.htdata.goyo.view.indicator.IndicatorTabAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-05 15:26
 * @描述： 统计分析
 */
public class ActStatisAnalys extends BaseActivity {


    @BindView(R.id.sa_pie_chart)
    PieChart mPieChart;
    @BindView(R.id.sa_indicator)
    MagicIndicator mIndicator;
    @BindView(R.id.sa_view_pager)
    MyViewPager mViewPager;

    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();
    private OneLevelFragment oneLevelFragment1,oneLevelFragment2,oneLevelFragment3,oneLevelFragment4;

    @Override
    protected int getContentViewId() {
        return R.layout.act_statis_analys;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initTitleBar();
        initPieChart();
        initTabLayoutData();
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        tTitle.setText(setString(R.string.statistics_analyse));
    }

    private void initPieChart() {
        PiecChartManager piecChartManager = new PiecChartManager(mPieChart, mContext);
        Float oneLevel = 20.0f;
        Float twoLevel = 60.0f;
        Float threeLevel = 40.0f;
        piecChartManager.setPieChartData(oneLevel, twoLevel, threeLevel);
    }


    /**
     * 初始化 数据
     */
    private void initTabLayoutData() {
        mTitle.add(setString(R.string.one_level_alarm));
        mTitle.add(setString(R.string.two_level_alarm));
        mTitle.add(setString(R.string.three_level_alarm));
        mTitle.add(setString(R.string.four_level_alarm));

        oneLevelFragment1 = new OneLevelFragment();
        oneLevelFragment2 = new OneLevelFragment();
        oneLevelFragment3 = new OneLevelFragment();
        oneLevelFragment4 = new OneLevelFragment();

        mFragment.add(oneLevelFragment1);
        mFragment.add(oneLevelFragment2);
        mFragment.add(oneLevelFragment3);
        mFragment.add(oneLevelFragment4);
        setViewPagerContent(mViewPager, null);
        initMagicIndicator();
        setPageChangeListener();
    }

    private void setViewPagerContent(final ViewPager viewPager, final DynamicPagerIndicator dynamicPagerIndicator) {
        IndicatorTabAdapter myTabAdapter = new IndicatorTabAdapter(getSupportFragmentManager(), mTitle, mFragment);
        viewPager.setAdapter(myTabAdapter);
//        dynamicPagerIndicator.setViewPager(viewPager);
    }

    private void initMagicIndicator(){
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitle == null ? 0 : mTitle.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#4787F7"));
                colorTransitionPagerTitleView.setTextSize(12);
                colorTransitionPagerTitleView.setText(mTitle.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 40));
                indicator.setRoundRadius(UIUtil.dip2px(context, 8));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#4787F7"));
                return indicator;
            }
        });
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, mViewPager);
    }

    private void setPageChangeListener(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position)   {
                    case 0:
                        oneLevelFragment1.setAnalysState(1);
                        break;
                    case 1:
                        oneLevelFragment2.setAnalysState(2);
                        break;
                    case 2:
                        oneLevelFragment3.setAnalysState(3);
                        break;
                    case 3:
                        oneLevelFragment4.setAnalysState(4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

}
