package com.xy.doll.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

import app.ui.base.BaseActivity;
import butterknife.OnClick;

public class OtherServiceActivity extends BaseActivity {
    private boolean isBind = false;
    private TwoService twoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_service);
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isBind = true;
            TwoService.MyBinder binder1 = (TwoService.MyBinder) binder;
            twoService = binder1.getService();
            LogUtil.e("OtherServiceActivity onServiceConnected  ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            LogUtil.e("OtherServiceActivity onServiceDisconnected  ");
        }
    };

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    void click(View view) {
        switch (view.getId()) {
//            case R.id.button1:
//                Intent intent = new Intent(activity, OtherServiceActivity.class);
//                startActivity(intent);
//                break;
            case R.id.button2:
                Intent intent1 = new Intent(getApplicationContext(), TwoService.class);
                getApplicationContext().bindService(intent1, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.button3:
                if (twoService.isBind())
                    getApplicationContext().unbindService(serviceConnection);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (twoService.isBind())
            getApplicationContext().unbindService(serviceConnection);
    }
}
