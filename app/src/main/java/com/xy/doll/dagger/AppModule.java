package com.xy.doll.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jxy on 2018/7/2.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
