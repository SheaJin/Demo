package com.xy.doll.dagger;

import android.content.Context;

import com.xy.libs.util.app.LogUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.model.contract.MainContract;


/**
 * Created by jxy on 2018/6/28.
 */

public class Person {

    private Context mContext;

    public Person(Context context) {
        this.mContext = context;
        LogUtil.e("create person from Constructor");
    }

}
