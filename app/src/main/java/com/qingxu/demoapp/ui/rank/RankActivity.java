package com.qingxu.demoapp.ui.rank;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.adapter.RankingAdapter;
import com.qingxu.demoapp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RankActivity extends BaseActivity {
    private RecyclerView mRv;
    private List<String> list;
    private RadioGroup group;
    private View mToday, mYes;
    private RankingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
    }

    @Override
    protected void initUI() {
        initTitle("排行榜");
        mRv = findViewById(R.id.rv);
        group = findViewById(R.id.group);
        mToday = findViewById(R.id.view_today);
        mYes = findViewById(R.id.view_yesterday);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
                super.getItemOffsets(outRect, itemPosition, parent);
                outRect.top = 60;
                if (itemPosition == list.size() - 1) {
                    outRect.bottom = 60;
                }
            }
        };
        mRv.addItemDecoration(itemDecoration);
        mRv.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        adapter = new RankingAdapter(this, list);

        initRadio();
        initData(0);

        mRv.setAdapter(adapter);
    }

    private void initRadio() {
        group.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_today:
                    mToday.setVisibility(View.VISIBLE);
                    mYes.setVisibility(View.INVISIBLE);
                    initData(0);
                    break;
                case R.id.rb_yesterday:
                    mToday.setVisibility(View.INVISIBLE);
                    mYes.setVisibility(View.VISIBLE);
                    initData(1);
                    break;
            }
        });
    }

    private void initData(int position) {
        if (position == 0) {
            list.clear();
            for (int i = 0; i < 4; i++) {
                list.add("");
                adapter.notifyDataSetChanged();
            }
        } else if (position == 1) {
            list.clear();
            for (int i = 0; i < 12; i++) {
                list.add("");
                adapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setFragmentContainerResId() {
        return 0;
    }
}
