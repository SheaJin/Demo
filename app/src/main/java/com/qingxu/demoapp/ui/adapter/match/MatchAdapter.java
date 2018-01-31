package com.qingxu.demoapp.ui.adapter.match;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qingxu.demoapp.model.data.match.MatchListBean;

import java.util.ArrayList;

/**
 * Created by jxy on 2018/1/27.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private Activity activity;
    private ArrayList<MatchListBean.DataBean> dataList;

    public MatchAdapter(ArrayList<MatchListBean.DataBean> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity = activity;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MatchViewHolder extends RecyclerView.ViewHolder {

        public MatchViewHolder(View itemView) {
            super(itemView);
        }
    }
}
