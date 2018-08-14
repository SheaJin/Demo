package com.xy.doll.rv;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.xy.doll.R;

import java.util.ArrayList;
import java.util.List;

import app.ui.base.BaseActivity;
import butterknife.BindView;

public class RecyclerActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;

    private List<String> list;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

    }

    @Override
    protected void initUI() {

        /**
         * GridLayoutManager
         */
//        GridLayoutManager manager = new GridLayoutManager(this, 3);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position == 3) {
//                    return 2;
//                }
//                if (position == 7) {
//                    return 3;
//                }
//                return 1;
//            }
//
//            @Override
//            public int getSpanIndex(int position, int spanCount) {
//
//                return super.getSpanIndex(position, spanCount);
//            }
//
//            @Override
//            public int getSpanGroupIndex(int adapterPosition, int spanCount) {
//
//                return super.getSpanGroupIndex(adapterPosition, spanCount);
//            }
//        });
//        //官方建议说，如果延用默认的 getSpanIndxe() 的实现逻辑的话，
//        // 那么建议调用下述方法来进行优化，否则每次布局计算时会很耗性能。
//        manager.getSpanSizeLookup().setSpanIndexCacheEnabled(false);
//        mRv.setLayoutManager(manager);

        /**
         * 瀑布流
         */
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);

    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add("");
        }
        adapter = new RecyclerAdapter(activity, list);
        mRv.setAdapter(adapter);
    }
}
