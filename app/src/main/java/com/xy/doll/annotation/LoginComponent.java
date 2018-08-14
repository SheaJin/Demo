package com.xy.doll.annotation;

import dagger.Component;

/**
 * Created by jxy on 2018/7/17.
 */

@Component(modules = LoginModule.class)
public interface LoginComponent {

    void inject(AnnotationActivity activity);

}
