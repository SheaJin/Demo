package com.xy.doll.annotation;

/**
 * Created by jxy on 2018/7/17.
 */

public class LoginCtrl {

    private LoginService loginService;
    private LoginStore loginStore;

    public LoginCtrl(LoginService loginService, LoginStore loginStore) {
        this.loginService = loginService;
        this.loginStore = loginStore;
    }

    public void login(String name, String pass) {

        loginService.login(name, pass);

        loginStore.store(name, pass);

    }

}
