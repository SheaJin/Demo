package com.xy.doll.rv;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xy.doll.R;

import java.util.List;

/**
 * Created by jxy on 2018/8/1.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private Activity activity;
    private List<String> list;

    public RecyclerAdapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.item_commin_details, null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }
}
