package com.qingxu.demoapp.main;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingxu.demoapp.R;

import java.util.List;

/**
 * Created by jxy on 2018/1/2.
 */

public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.MyViewHolder>{

    private Activity activity;
    private List<String> list;
    private String[] menus;
    private OnItemClickListener itemClickListener;

    public EmptyAdapter(Activity activity, List<String> list,String[] menus) {
        this.activity = activity;
        this.list = list;
        this.menus = menus;
    }

    interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public EmptyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.empty_item,null);
        return new EmptyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmptyAdapter.MyViewHolder holder, int position) {
        holder.emptyText.setText(menus[position]);
        holder.itemView.setOnClickListener(v -> itemClickListener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView emptyText;

        public MyViewHolder(View itemView) {
            super(itemView);
            emptyText = itemView.findViewById(R.id.text_empty);
        }
    }
}

