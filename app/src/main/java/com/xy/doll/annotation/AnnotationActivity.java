package com.xy.doll.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xy.doll.R;

import javax.inject.Inject;

public class AnnotationActivity extends AppCompatActivity {
    /**
     * 普通注入流程
     * 1.创建类和方法
     * 2.创建Module,实例化该类。
     * 3.创建Component接口,创建方法,方法中必须包含在哪个类中使用的参数。并引入module
     * 4.在Activity @Inject类，如果没参数，Dagger+Component.create().方法名(参数)
     * 5.调用方法
     */

    @Inject
    LoginCtrl loginCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        /**
         * 普通注入,直接create
         */
//        DaggerLoginComponent.create().inject(this);
//        loginCtrl.login("jxy", "123456");


        /**
         * 当Module需要构造方法传参的时候，使用builder的方式初始化Dagger
         */
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);

        loginCtrl.login("jxy", "123456");

    }
}
