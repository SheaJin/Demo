package com.xy.doll;

import android.app.Application;

/**
 * Created by jxy on 2018/2/1.
 */

public class DollApplication extends Application {
    public static DollApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static DollApplication getInstance(){
        return application;
    }
}
