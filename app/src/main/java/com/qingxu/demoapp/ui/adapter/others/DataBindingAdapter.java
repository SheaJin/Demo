package com.qingxu.demoapp.ui.adapter.others;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingxu.demoapp.BR;
import com.qingxu.demoapp.R;
import app.databinding.UserInfo;

import java.util.List;

/**
 * Created by jxy on 2018/1/3.
 */

public class DataBindingAdapter extends RecyclerView.Adapter<DataBindingAdapter.MyViewHolder> {
    private Activity activity;
    private List<UserInfo> list;

    public DataBindingAdapter(Activity activity, List<UserInfo> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_data_fragment, parent, false);
        MyViewHolder holder = new MyViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.user,list.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }
}
