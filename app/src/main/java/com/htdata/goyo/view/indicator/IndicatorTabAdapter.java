package com.htdata.goyo.view.indicator;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * cuibo
 * 2016/10/11 09:50
 */

public class IndicatorTabAdapter extends FragmentPagerAdapter {

    private List<String> title;
    private List<Fragment> views;

    public IndicatorTabAdapter(FragmentManager fm, List<String> title, List<Fragment> views) {
        super(fm);
        this.title = title;
        this.views = views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }


}
