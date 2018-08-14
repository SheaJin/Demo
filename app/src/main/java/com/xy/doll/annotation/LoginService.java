package com.xy.doll.annotation;

import com.xy.libs.util.app.LogUtil;

import javax.inject.Inject;

/**
 * Created by jxy on 2018/7/17.
 */

public class LoginService {

    @Inject
    public LoginService() {
    }

    public void login(String name, String password) {

        LogUtil.e("网络登录.." + name + ",password = " + password);

    }
}
