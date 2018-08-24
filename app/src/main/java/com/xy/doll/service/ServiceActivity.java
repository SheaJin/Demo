package com.xy.doll.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

import java.lang.ref.WeakReference;

import app.ui.base.BaseActivity;
import butterknife.OnClick;

public class ServiceActivity extends BaseActivity {
    private TwoService twoService;
    private boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceSt) {
        super.onCreate(savedInstanceSt);
        setContentView(R.layout.activity_start_service);
    }

    @Override
    protected void initUI() {
//        Intent intent1 = new Intent(activity, OneService.class);
//        startService(intent1);

        MyHandler handler = new MyHandler(this);
        handler.sendEmptyMessage(1);
    }

    @Override
    protected void initData() {

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder ibinder) {
            isBind = true;
            TwoService.MyBinder myBinder = (TwoService.MyBinder) ibinder;
            twoService = myBinder.getService();
            LogUtil.e("ServiceActivity onServiceConnected  ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            LogUtil.e("ServiceActivity onServiceDisconnected  ");
        }
    };

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    void click(View view) {
        switch (view.getId()) {
            case R.id.button1:
//                Intent intent = new Intent(activity, OtherServiceActivity.class);
//                startActivity(intent);


                break;
            case R.id.button2:
                Intent intent1 = new Intent(getApplicationContext(), TwoService.class);
                getApplicationContext().bindService(intent1, connection, BIND_AUTO_CREATE);
                break;
            case R.id.button3:
                if (twoService.isBind())
                    getApplicationContext().unbindService(connection);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (twoService.isBind())
            unbindService(connection);
    }

    private static class MyHandler extends Handler {

        private WeakReference<ServiceActivity> weakReference;

        public MyHandler(ServiceActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ServiceActivity activity = weakReference.get();

        }
    }

    private static class TeHandler extends Handler{

        private WeakReference<ServiceActivity> weakReference;

        private TeHandler(ServiceActivity activity){
            weakReference = new WeakReference<ServiceActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
