package com.xy.doll.custom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.xy.doll.R;

import java.util.ArrayList;
import java.util.List;

import app.ui.adapter.SimpleFragmentPagerAdapter;
import app.ui.base.BaseActivity;
import app.ui.fragment.BlankFragment;
import app.ui.widget.BottomView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager pager;
    @BindView(R.id.bottom)
    BottomView bottom;
    private FragmentPagerAdapter simpleFragmentPagerAdapter;
    private List<Fragment> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
    }

    @Override
    protected void initUI() {
        dataList = new ArrayList<>();
        dataList.add(new BlankFragment());
        dataList.add(new BlankFragment());
        dataList.add(new BlankFragment());
        dataList.add(new BlankFragment());
        simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),dataList);
        pager.setAdapter(simpleFragmentPagerAdapter);
        bottom.setUpViewPager(pager);
    }

    @Override
    protected void initData() {

    }
}
