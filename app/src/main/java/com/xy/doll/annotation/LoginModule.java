package com.xy.doll.annotation;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jxy on 2018/7/17.
 */

@Module
public class LoginModule {

    private Context mContext;

    public LoginModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    Context providerContext() {
        return mContext;
    }

    @Provides
    public LoginCtrl provideLoginCtrl(LoginService service, LoginStore store) {

        return new LoginCtrl(service, store);
    }
}
