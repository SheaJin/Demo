package com.xy.doll.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jxy on 2018/6/28.
 */

@Module
public class LulyModule {

    @Provides
    @Named("luly")
    Teacher provideLuly() {
        return new Luly();
    }

}
