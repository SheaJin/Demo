package com.xy.doll.file;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xy.doll.R;

import java.util.ArrayList;
import java.util.List;

import app.ui.adapter.SimpleFragmentPagerAdapter;
import app.ui.base.BaseActivity;
import butterknife.BindView;

public class FileActivity extends BaseActivity {
    @BindView(R.id.pager)
    ViewPager pager;

    private List<Fragment> fragments = new ArrayList<>();
    private SimpleFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        fragments.add(new FileFragment());
        fragments.add(new File2Fragment());
        fragments.add(new File3Fragment());
        adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
    }
}
