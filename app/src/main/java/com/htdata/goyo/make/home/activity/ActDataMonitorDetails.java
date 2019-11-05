package com.htdata.goyo.make.home.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseActivity;
import com.htdata.goyo.make.home.fragment.FaultDataFragment;
import com.htdata.goyo.make.home.fragment.HistoryDataFragment;
import com.htdata.goyo.make.home.fragment.RealTimeDataFragment;
import com.htdata.goyo.make.home.fragment.StatisAnalysFragment;
import com.htdata.goyo.util.LogUtils;
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
 * @日期：2019-11-04 13:26
 * @描述： 数据监测 详情
 */
public class ActDataMonitorDetails extends BaseActivity {


    @BindView(R.id.md_view_pager)
    MyViewPager mViewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;


    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();


    @Override
    protected int getContentViewId() {
        return R.layout.act_data_monitor_details;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initTitleBar();
        initTabLayoutData();
        initMagicIndicator();
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        tTitle.setText(setString(R.string.data_monitor));
    }

    /**
     * 初始化 数据
     */
    private void initTabLayoutData() {
        mTitle.add(setString(R.string.real_time_data));
        mTitle.add(setString(R.string.history_data));
        mTitle.add(setString(R.string.fault_data));
        mTitle.add(setString(R.string.statistics_analyse));

        RealTimeDataFragment realTimeDataFragment = new RealTimeDataFragment();
        HistoryDataFragment historyDataFragment = new HistoryDataFragment();
        FaultDataFragment faultDataFragment = new FaultDataFragment();
        StatisAnalysFragment statisAnalysFragment = new StatisAnalysFragment();

        mFragment.add(realTimeDataFragment);
        mFragment.add(historyDataFragment);
        mFragment.add(faultDataFragment);
        mFragment.add(statisAnalysFragment);
        setViewPagerContent(mViewPager, null);
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
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }



}
