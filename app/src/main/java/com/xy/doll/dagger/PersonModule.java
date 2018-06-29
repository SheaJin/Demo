package com.xy.doll.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jxy on 2018/6/28.
 */

@Module
public class PersonModule {

    private Context mContext;

    public PersonModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    Person providePerson(Context context) {
        return new Person(context);
    }

}
