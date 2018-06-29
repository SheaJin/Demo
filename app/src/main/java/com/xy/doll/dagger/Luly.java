package com.xy.doll.dagger;

import com.xy.libs.util.app.LogUtil;

import javax.inject.Inject;

/**
 * Created by jxy on 2018/6/28.
 */

public class Luly extends Teacher{

    public Luly() {
        LogUtil.e("create Luly from Constructor");
    }

    @Override
    public String teach() {
        return "teach luly ";
    }
}
