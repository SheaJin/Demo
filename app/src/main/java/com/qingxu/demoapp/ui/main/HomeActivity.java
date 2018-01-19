package com.qingxu.demoapp.ui.main;

import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseActivity;
import com.qingxu.demoapp.ui.base.BaseFragment;
import com.qingxu.demoapp.ui.fragment.CommunityFragment;
import com.qingxu.demoapp.ui.fragment.InfoFragment;
import com.qingxu.demoapp.ui.fragment.MatchFragment;
import com.qingxu.demoapp.ui.fragment.MineFragment;
import com.qingxu.demoapp.ui.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.bnb_home)
    BottomNavigationBar bottomHome;
    private List<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initUI() {
        fragments = new ArrayList<>();
        fragments.add(new MatchFragment());
        fragments.add(new NewsFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new InfoFragment());
        fragments.add(new MineFragment());
    }

    @Override
    protected void initData() {
        /**
         * 底部导航栏
         */
        bottomHome.addItem(newBnbItem("比赛", R.drawable.ic_svg_match_bg_dark_24)).setInActiveColor(R.color.text_grey)
                .addItem(newBnbItem("新闻", R.drawable.ic_svg_news_bg_dark_24)).setInActiveColor(R.color.black_255)
                .addItem(newBnbItem("社区", R.drawable.ic_svg_info_bg_dark_24)).setInActiveColor(R.color.text_grey)
                .addItem(newBnbItem("数据", R.drawable.ic_svg_forum_bg_dark_24)).setInActiveColor(R.color.black_255)
                .addItem(newBnbItem("我的", R.drawable.ic_svg_mine_bg_dark_24)).setInActiveColor(R.color.text_grey)
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .initialise();
        showFragment(fragments.get(0), 0);
        bottomHome.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                showFragment(fragments.get(position), position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    protected int setFragmentContainerResId() {
        return R.id.frame;
    }

    private BottomNavigationItem newBnbItem(String title, int resId) {
        return new BottomNavigationItem(resId, title);
    }

}
