package com.xy.doll.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xy.libs.util.app.LogUtil;

/**
 * Created by jxy on 2018/8/21.
 */

public class OneService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("OneService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.e("OneService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.e("OneService onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        LogUtil.e("OneService onDestroy");
        super.onDestroy();
    }
}
