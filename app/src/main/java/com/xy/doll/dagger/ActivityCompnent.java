package com.xy.doll.dagger;

import dagger.Component;

/**
 * Created by jxy on 2018/7/2.
 */


@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityCompnent {

    void inject(DaggerActivity activity);
}
