package com.xy.libs.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by yangmu on 2017/8/3.
 */

public class RecyclerViewUtil {
    public static void nestedRecyclerView(RecyclerView view,RecyclerView.LayoutManager manager) {
        view.setLayoutManager(manager);
        manager.setAutoMeasureEnabled(true);
        view.setHasFixedSize(true);
        view.setNestedScrollingEnabled(false);
    }
}
