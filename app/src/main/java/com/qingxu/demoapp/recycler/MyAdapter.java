package com.qingxu.demoapp.recycler;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qingxu.demoapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxy on 2018/1/2.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Activity activity;
    private List<String> list;
    private List<String> secondList;
    private SecondAdapter adapter;

    public MyAdapter(Activity activity, List<String> list) {
        secondList = new ArrayList<>();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        GridLayoutManager manager = new GridLayoutManager(activity,3);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        holder.itemRv.setLayoutManager(manager);
        holder.itemRv.setLayoutManager(new LinearLayoutManager(activity));
        secondList.clear();
        for (int i = 0; i < 3; i++) {
            secondList.add("");
        }
        adapter = new SecondAdapter(activity,secondList);
        holder.itemRv.setAdapter(adapter);
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
