package com.xy.doll.pk;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonAdapter;
import com.xy.libs.ui.adapter.CommonViewHolder;
import com.xy.libs.ui.adapter.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

import app.ui.base.BaseActivity;
import butterknife.BindView;

public class PksActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<String> list;
    private CommonAdapter<String, MyHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pk);
    }

    //11
    @Override
    protected void initUI() {
        GridLayoutManager manager = new GridLayoutManager(activity, 1);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerViewUtil.nestedRecyclerView(mRv, manager);
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("");
        }
    }

    @Override
    protected void initData() {
        adapter = new CommonAdapter<String, MyHolder>(activity, list) {
            @Override
            public int layoutResId(int viewType) {
                return R.layout.item_pk;
            }

            @Override
            public MyHolder holderInstance(View itemView, int viewType) {
                return new MyHolder(itemView);
            }

            @Override
            public void fillView(MyHolder holder, String data, int position) {

            }
        };
        mRv.setAdapter(adapter);
    }


    class MyHolder extends CommonViewHolder {

        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
