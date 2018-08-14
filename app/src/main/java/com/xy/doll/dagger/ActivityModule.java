package com.xy.doll.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jxy on 2018/7/2.
 */

@Module
public class ActivityModule {

    @Provides
    Rose providerRose(Context context) {
        return new Rose(context);

    }

}
