package com.xy.doll.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jxy on 2018/6/28.
 */

@Singleton
@Component(modules = PersonModule.class)
public interface MainComponent {

    void inject(DaggerActivity daggerActivity);

}
