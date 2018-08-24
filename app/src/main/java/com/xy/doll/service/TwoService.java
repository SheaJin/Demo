package com.xy.doll.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xy.libs.util.app.LogUtil;

import java.util.Random;

/**
 * Created by jxy on 2018/8/21.
 */

public class TwoService extends Service {

    class MyBinder extends Binder {
        public TwoService getService() {
            LogUtil.e("TwoService  getService");
            return TwoService.this;
        }
    }

    private MyBinder binder = new MyBinder();
    private boolean isBind;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("TwoService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.e("TwoService onStartCommand");
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.e("TwoService onBind");
        isBind = true;
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.e("TwoService onUnbind");
        isBind = false;
        return false;
    }

    @Override
    public void onDestroy() {
        LogUtil.e("TwoService onDestroy");
        super.onDestroy();
    }

    public int getRandom() {
        LogUtil.e("TwoService  getRandom");
        return new Random().nextInt(10);
    }

    public boolean isBind() {
        return isBind;
    }
}
