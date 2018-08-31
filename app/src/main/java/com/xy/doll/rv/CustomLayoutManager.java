package com.xy.doll.rv;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jxy on 2018/8/29.
 */

public class CustomLayoutManager extends RecyclerView.LayoutManager{

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {

        return null;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        View child = recycler.getViewForPosition(0);

    }

    @Override
    public void calculateItemDecorationsForChild(View child, Rect outRect) {
        super.calculateItemDecorationsForChild(child, outRect);

    }
}
