package com.xy.doll.annotation;

import android.content.Context;
import android.content.SharedPreferences;

import com.xy.libs.util.app.LogUtil;

import javax.inject.Inject;

/**
 * Created by jxy on 2018/7/17.
 */

public class LoginStore {

    private Context context;

    @Inject
    public LoginStore(Context context) {
        this.context = context;
    }

    public void store(String name, String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences("login", Context.MODE_PRIVATE).edit();
        editor.putString("name", name);
        editor.putString("pass", password);
        editor.apply();

        LogUtil.e("store name = " + context.getSharedPreferences("login", Context.MODE_PRIVATE).getString("name", "")
                + "password = " + context.getSharedPreferences("login", Context.MODE_PRIVATE).getString("pass", ""));
    }
}
