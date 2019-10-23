package com.htdata.goyo.main;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.htdata.goyo.R;
import com.htdata.goyo.main.make.MakeDeviceFragment;
import com.htdata.goyo.main.make.MakeHomeFragment;
import com.htdata.goyo.main.make.MakeSparesFragment;
import com.htdata.goyo.main.use.UseDeviceFragment;
import com.htdata.goyo.main.use.UseHomeFragment;
import com.htdata.goyo.main.use.UseMaintenanceFragment;
import com.htdata.goyo.main.use.UseSparesFragment;
import com.htdata.goyo.util.UserUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActMain extends AppCompatActivity {

    @BindView(R.id.main_fl_content)
    FrameLayout mainFlContent;
    //使用方 1到4
    @BindView(R.id.main_tab1)
    RadioButton mainTab1;
    @BindView(R.id.main_tab2)
    RadioButton mainTab2;
    @BindView(R.id.main_tab3)
    RadioButton mainTab3;
    @BindView(R.id.main_tab4)
    RadioButton mainTab4;
    //制造方 5到7
    @BindView(R.id.main_tab5)
    RadioButton mainTab5;
    @BindView(R.id.main_tab6)
    RadioButton mainTab6;
    @BindView(R.id.main_tab7)
    RadioButton mainTab7;

    @BindView(R.id.main_bottom_layout)
    RadioGroup mainBottomLayout;
    @BindView(R.id.main_view)
    View mainView;


    private Fragment useHomeFragment1, useDeviceFragment1, useSparesFragment1, useMaintenanceFragment1,makeHomeFragment2,makeDeviceFragment2,makeSparesFragment2, mCurrentFragment;


    // ruai chou fei te
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
    }


    /**
     * 初始化 要显示的fragment
     */
    public void initFragment() {
        showUserType();
    }



    @OnClick({R.id.main_tab1, R.id.main_tab2, R.id.main_tab3, R.id.main_tab4, R.id.main_tab5, R.id.main_tab6, R.id.main_tab7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_tab1:
                switchFragment(1);
                break;
            case R.id.main_tab2:
                switchFragment(2);
                break;
            case R.id.main_tab3:
                switchFragment(3);
                break;
            case R.id.main_tab4:
                switchFragment(4);
                break;
            case R.id.main_tab5:
                switchFragment(5);
                break;
            case R.id.main_tab6:
                switchFragment(6);
                break;
            case R.id.main_tab7:
                switchFragment(7);
                break;
        }
    }

    /**
     * 替换 Fragment
     *
     * @param to Fragment
     */
    protected void switchContent(Fragment to) {
        if (mCurrentFragment == null) {
            mCurrentFragment = to;
            // 转换Fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_fl_content, mCurrentFragment);
            transaction.commitAllowingStateLoss();
        } else {
            if (mCurrentFragment != to) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (!to.isAdded()) { // 先判断是否被add过
                    transaction.hide(mCurrentFragment).add(R.id.main_fl_content, to)
                            .commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                } else {
                    transaction.hide(mCurrentFragment).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                }
                mCurrentFragment = to;
            }
        }
    }

    /**
     * 切换点击状态
     */
    private void switchClickState(int type) {
        switch (type) {
            case 1:
                mainTab1.setChecked(true);
                mainTab2.setChecked(false);
                mainTab3.setChecked(false);
                mainTab4.setChecked(false);
                mainTab5.setChecked(false);
                mainTab6.setChecked(false);
                mainTab7.setChecked(false);
                break;
            case 2:
                mainTab1.setChecked(false);
                mainTab2.setChecked(true);
                mainTab3.setChecked(false);
                mainTab4.setChecked(false);
                mainTab5.setChecked(false);
                mainTab6.setChecked(false);
                mainTab7.setChecked(false);
                break;
            case 3:
                mainTab1.setChecked(false);
                mainTab2.setChecked(false);
                mainTab3.setChecked(true);
                mainTab4.setChecked(false);
                mainTab5.setChecked(false);
                mainTab6.setChecked(false);
                mainTab7.setChecked(false);
                break;
            case 4:
                mainTab1.setChecked(false);
                mainTab2.setChecked(false);
                mainTab3.setChecked(false);
                mainTab4.setChecked(true);
                mainTab5.setChecked(false);
                mainTab6.setChecked(false);
                mainTab7.setChecked(false);
                break;
            case 5:
                mainTab1.setChecked(false);
                mainTab2.setChecked(false);
                mainTab3.setChecked(false);
                mainTab4.setChecked(false);
                mainTab5.setChecked(true);
                mainTab6.setChecked(false);
                mainTab7.setChecked(false);
                break;
            case 6:
                mainTab1.setChecked(false);
                mainTab2.setChecked(false);
                mainTab3.setChecked(false);
                mainTab4.setChecked(false);
                mainTab5.setChecked(false);
                mainTab6.setChecked(true);
                mainTab7.setChecked(false);
                break;
            case 7:
                mainTab1.setChecked(false);
                mainTab2.setChecked(false);
                mainTab3.setChecked(false);
                mainTab4.setChecked(false);
                mainTab5.setChecked(false);
                mainTab6.setChecked(false);
                mainTab7.setChecked(true);
                break;
        }
    }

    /**
     * 选择要显示的 Fragment
     */
    private void switchFragment(int fType) {
        switch (fType) {
            case 1:
                switchClickState(1);
                if (useHomeFragment1 == null) {
                    useHomeFragment1 = new UseHomeFragment();
                }
                switchContent(useHomeFragment1);
                break;
            case 2:
                switchClickState(2);
                if (useDeviceFragment1 == null) {
                    useDeviceFragment1 = new UseDeviceFragment();
                }
                switchContent(useDeviceFragment1);
                break;
            case 3:
                switchClickState(3);
                if (useSparesFragment1 == null) {
                    useSparesFragment1 = new UseSparesFragment();
                }
                switchContent(useSparesFragment1);
                break;
            case 4:
                switchClickState(4);
                if (useMaintenanceFragment1 == null) {
                    useMaintenanceFragment1 = new UseMaintenanceFragment();
                }
                switchContent(useMaintenanceFragment1);
                break;
            case 5:
                switchClickState(5);
                if (makeHomeFragment2 == null) {
                    makeHomeFragment2 = new MakeHomeFragment();
                }
                switchContent(makeHomeFragment2);
                break;
            case 6:
                switchClickState(6);
                if (makeDeviceFragment2 == null) {
                    makeDeviceFragment2 = new MakeDeviceFragment();
                }
                switchContent(makeDeviceFragment2);
                break;
            case 7:
                switchClickState(7);
                if (makeSparesFragment2 == null) {
                    makeSparesFragment2 = new MakeSparesFragment();
                }
                switchContent(makeSparesFragment2);
                break;
        }
    }

    /**
     * 根据用户类型显示界面
     */
    private void showUserType(){
        switch (UserUtil.getUserType()){
            case 1://使用方
                switchFragment(1);
                mainTab1.setVisibility(View.VISIBLE);
                mainTab2.setVisibility(View.VISIBLE);
                mainTab3.setVisibility(View.VISIBLE);
                mainTab4.setVisibility(View.VISIBLE);
                mainTab5.setVisibility(View.GONE);
                mainTab6.setVisibility(View.GONE);
                mainTab7.setVisibility(View.GONE);
                break;
            case 2://制造方
                switchFragment(5);
                mainTab1.setVisibility(View.GONE);
                mainTab2.setVisibility(View.GONE);
                mainTab3.setVisibility(View.GONE);
                mainTab4.setVisibility(View.GONE);
                mainTab5.setVisibility(View.VISIBLE);
                mainTab6.setVisibility(View.VISIBLE);
                mainTab7.setVisibility(View.VISIBLE);
                break;
        }
    }

}
