package com.xy.doll.dagger;

import android.content.Context;

import com.xy.libs.util.app.LogUtil;

/**
 * Created by jxy on 2018/7/2.
 */

public class Rose {

    private Context context;

    public Rose(Context context) {
        this.context = context;
        LogUtil.e("create by constructor");
    }

    public String whisper() {
        return "热恋";
    }

}
