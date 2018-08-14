package com.xy.doll.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

//    @Inject
//    Rose rose1;
//
//    @Inject
//    Rose rose2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

//        MainComponent component = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
//
//        component.injects(this);
//
//
//        LogUtil.e("rose1 = " + rose1.toString() + ",rose2 = " + rose2.toString());

//        AppComponent appComponent = DaggerAppComponent.builder().app
//
//        DaggerMainComponent.builder().appComponent

//        AppComponent appComponent =
    }

}
//Dagger2
/**
 * 1.创建Component(桥梁) 调用注入方法
 * 2.查找当前类中带有@inject的成员变量
 * 3.根据成员变量的类型从Module中查找哪个有@Provides注解的方法的返回值为当前类型
 * <p>
 * <p>
 * Module对象提供对象的实例化
 * Component为注入器
 */