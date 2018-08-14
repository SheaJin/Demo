package com.xy.doll.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jxy on 2018/7/2.
 */

/**
 * 可以理解为注入器，在Activity使用注入器注入
 */
@Singleton
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {

    void injects(DaggerActivity activity);

}

/**
 * Component 两种方式定义方法
 * <p>
 * 1.void inject();
 * 2.Object getObj();
 */