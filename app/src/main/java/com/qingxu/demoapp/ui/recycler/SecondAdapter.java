package com.qingxu.demoapp.ui.recycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qingxu.demoapp.R;

import java.util.List;

/**
 * Created by jxy on 2018/1/2.
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.MyViewHolder>{
    private Activity activity;
    private List<String> list;

    public SecondAdapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.second_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        RecyclerView itemRv;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemRv = itemView.findViewById(R.id.recyclerView_item);
        }
    }
}
