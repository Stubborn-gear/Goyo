package com.htdata.goyo.make.spares.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseActivity;
import com.htdata.goyo.make.home.fragment.OneLevelFragment;
import com.htdata.goyo.make.spares.adapter.SartsAllAdapter;
import com.htdata.goyo.make.spares.fragment.SartsAllFragment;
import com.htdata.goyo.make.spares.fragment.SartsConsignmentFragment;
import com.htdata.goyo.make.spares.fragment.SartsLeaseFragment;
import com.htdata.goyo.make.spares.fragment.SartsPurchaseFragment;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者：cb
 * @日期：2019-11-07 11:26
 * @描述： 备品
 */
public class ActSpareSarts extends BaseActivity {

    @BindView(R.id.sarts_recycler_view)
    RecyclerView mRecyclerView;
//    @BindView(R.id.sarts_indicator)
//    MagicIndicator mIndicator;
//    @BindView(R.id.sarts_view_pager)
//    MyViewPager mViewPager;

    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();

    @Override
    protected int getContentViewId() {
        return R.layout.act_spare_sarts;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initTitleBar();
//        initTabLayoutData();
        initRecyclerView();
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        tTitle.setText(setString(R.string.spare_parts));
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        SartsAllAdapter myAdapter = new SartsAllAdapter(mContext, this, getList());
        mRecyclerView.setAdapter(myAdapter);
    }


    private List<String> getList() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 59; i++) {
            list.add("" + i);
        }
        return list;
    }



    /**
     * 初始化 数据
     */
/*    private void initTabLayoutData() {
        mTitle.add(setString(R.string.all));
        mTitle.add(setString(R.string.purchase));
        mTitle.add(setString(R.string.lease));
        mTitle.add(setString(R.string.consignment));

        SartsAllFragment allFragment = new SartsAllFragment();
        SartsPurchaseFragment purchaseFragment = new SartsPurchaseFragment();
        SartsLeaseFragment leaseFragment = new SartsLeaseFragment();
        SartsConsignmentFragment consignFragment = new SartsConsignmentFragment();

        mFragment.add(allFragment);
        mFragment.add(purchaseFragment);
        mFragment.add(leaseFragment);
        mFragment.add(consignFragment);
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }*/




}
