package com.qingxu.demoapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseFragment;

import butterknife.BindView;

public class MatchFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_match);
    }


}
