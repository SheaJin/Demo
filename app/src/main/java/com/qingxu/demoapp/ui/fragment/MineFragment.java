package com.qingxu.demoapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseFragment;
import com.qingxu.demoapp.ui.main.DemoActivity;
import com.qingxu.demoapp.ui.main.SendSocketActivity;

public class MineFragment extends BaseFragment {

    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragement_mine);
    }

    @Override
    protected void initUI() {
        button = getView(R.id.but);
        button.setText("button");
        setClick(R.id.but,R.id.send);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but:
                startActivity(new Intent(activity, DemoActivity.class));
                break;
            case R.id.send:
                startActivity(new Intent(activity, SendSocketActivity.class));
                break;
        }

    }

}

