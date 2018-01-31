package com.qingxu.demoapp.ui.main;

import android.os.Bundle;
import android.view.View;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseActivity;

public class SendSocketActivity extends BaseActivity {

    private DemoActivity mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_socket);
    }

    @Override
    protected void initUI() {
        setClick(R.id.but, R.id.close);
        mSocket = new DemoActivity();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.but:
                mSocket.sendMessage("hello world");
                mSocket.sendMessage("welcome");
                break;
            case R.id.close:
                mSocket.close(1000, "关闭");
                break;
        }
    }
}
