package com.qingxu.demoapp.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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

    /**
     * onTouch事件
     *
     * ● 按下（ACTION_DOWN）
     * ● 移动（ACTION_MOVE）
     * ● 抬起（ACTION_UP）
     * ● 取消手势（ACTION_CANCEL）
     * ● 滑出屏幕（ACTION_OUTSIDE）
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_CANCEL:

                break;
            case MotionEvent.ACTION_OUTSIDE:

                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }
}
