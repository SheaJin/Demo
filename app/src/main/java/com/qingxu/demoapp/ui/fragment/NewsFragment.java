package com.qingxu.demoapp.ui.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseFragment;

public class NewsFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);
    }
}
