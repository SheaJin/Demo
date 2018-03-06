package com.xy.libs.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by yangmu on 2017/8/3.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private CommonAdapter adapter;
    public int position;
    public Context context;

    public CommonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public CommonAdapter getAdapter() {
        return adapter;
    }

    public <T,VH extends CommonViewHolder>void setAdapter(CommonAdapter<T,VH> commonAdapter){
        adapter = commonAdapter;
    }

    public void OnItemClick(int position){

    }
}
