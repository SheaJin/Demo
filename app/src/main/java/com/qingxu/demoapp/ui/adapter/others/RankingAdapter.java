package com.qingxu.demoapp.ui.adapter.others;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qingxu.demoapp.R;

import java.util.List;

/**
 * Created by jxy on 2018/1/11.
 */

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.MyHolder> {
    private Activity activity;
    private List<String> list;

    public RankingAdapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.item_rank, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        switch (position) {
            case 0:
                holder.mIvRank.setVisibility(View.VISIBLE);
                holder.mTvRank.setVisibility(View.GONE);
                holder.mIvRank.setImageResource(R.mipmap.big_one_icon);
                break;
            case 1:
                holder.mIvRank.setVisibility(View.VISIBLE);
                holder.mTvRank.setVisibility(View.GONE);
                holder.mIvRank.setImageResource(R.mipmap.second_icon);
                break;
            case 2:
                holder.mIvRank.setVisibility(View.VISIBLE);
                holder.mTvRank.setVisibility(View.GONE);
                holder.mIvRank.setImageResource(R.mipmap.third_icon);
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                holder.mIvRank.setVisibility(View.GONE);
                holder.mTvRank.setVisibility(View.VISIBLE);
                holder.mTvRank.setText("0" + String.valueOf(position + 1));
                break;
            default:
                holder.mIvRank.setVisibility(View.GONE);
                holder.mTvRank.setVisibility(View.VISIBLE);
                holder.mTvRank.setText(String.valueOf(position + 1));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView mTvRank;
        ImageView mIvRank;

        public MyHolder(View itemView) {
            super(itemView);
            mTvRank = itemView.findViewById(R.id.tv_rank);
            mIvRank = itemView.findViewById(R.id.iv_rank);
        }
    }
}
