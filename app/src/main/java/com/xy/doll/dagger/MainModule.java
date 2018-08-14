package com.xy.doll.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jxy on 2018/7/2.
 */
@Module
public class MainModule {

    private Context mContext;

    public MainModule(Context context) {
        this.mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    Rose provideRose(Context context1) {

        return new Rose(context1);
    }


}
