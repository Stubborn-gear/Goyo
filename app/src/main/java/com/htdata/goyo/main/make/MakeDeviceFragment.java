package com.htdata.goyo.main.make;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseFragment;
import com.htdata.goyo.main.ActMain;
import com.htdata.goyo.main.make.fragment.DeviceAllFragment;
import com.htdata.goyo.make.device.activity.ActSingleDevice;
import com.htdata.goyo.util.statusbar.ImmersionBar;
import com.htdata.goyo.view.MyViewPager;
import com.htdata.goyo.view.indicator.DynamicPagerIndicator;
import com.htdata.goyo.view.indicator.IndicatorTabAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者：cb
 * @日期：2019-10-17 14:38
 * @描述：设备 制造商
 */
public class MakeDeviceFragment extends BaseFragment {


    @BindView(R.id.dv_search_edit)
    EditText searchEdit;
    @BindView(R.id.dv_indicator)
    MagicIndicator mIndicator;
    @BindView(R.id.dv_view_pager)
    MyViewPager mViewPager;
    Unbinder unbinder;
    @BindView(R.id.dv_refresh_layout)
    SmartRefreshLayout refreshLayout;

    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getInflater(getContext(), R.layout.main_make_device_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initImmersionBar();
        initTabLayoutData();
        initRefresh();
        return view;
    }

    private void initImmersionBar() {
        ((ActMain) getActivity()).isDarkFont = 2;
        ImmersionBar.with(getActivity(), this).statusBarDarkFont(true).init();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {// 切换 实时刷新
            initImmersionBar();
        }
    }

    @OnClick({R.id.dv_screen, R.id.dv_sets_layout, R.id.dv_single_device_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dv_screen:
                break;
            case R.id.dv_sets_layout:
                break;
            case R.id.dv_single_device_layout:
                toActivity(ActSingleDevice.class);
                break;
        }
    }

    private void initTabLayoutData() {
        mTitle.add(getString(R.string.all));
        mTitle.add(getString(R.string.ai_opex));
        mTitle.add(getString(R.string.ai_diagnosis));

        DeviceAllFragment allFragment1 = new DeviceAllFragment();
        DeviceAllFragment allFragment2 = new DeviceAllFragment();
        DeviceAllFragment allFragment3 = new DeviceAllFragment();

        mFragment.add(allFragment1);
        mFragment.add(allFragment2);
        mFragment.add(allFragment3);
        setViewPagerContent(mViewPager, null);
        initMagicIndicator();
        setPageChangeListener();
    }

    private void setViewPagerContent(final ViewPager viewPager, final DynamicPagerIndicator dynamicPagerIndicator) {
        IndicatorTabAdapter myTabAdapter = new IndicatorTabAdapter(getChildFragmentManager(), mTitle, mFragment);
        viewPager.setAdapter(myTabAdapter);
//        dynamicPagerIndicator.setViewPager(viewPager);
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getMyContext());
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

    private void setPageChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ImmersionBar.with(getActivity(), this).destroy();
    }

    private void initRefresh(){
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

}
