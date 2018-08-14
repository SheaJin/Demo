package com.xy.doll.dagger;

import android.content.Context;

import dagger.Component;

/**
 * Created by jxy on 2018/7/2.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {

    Context proContext();

}
