package com.qingxu.demoapp.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseActivity;
import com.qingxu.demoapp.widget.CommonWindow;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.but)
    Button mBut;
    private Activity activity;
    private CommonWindow window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = LoginActivity.this;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.but)
    void click(View view){
        switch (view.getId()){
            case R.id.but:
                window = new CommonWindow(activity);
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                window.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (window != null) {
            window = null;
        }
    }
}
