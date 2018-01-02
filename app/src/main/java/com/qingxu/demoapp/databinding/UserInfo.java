package com.qingxu.demoapp.databinding;

import android.databinding.BaseObservable;

/**
 * Created by jxy on 2018/1/2.
 */

public class UserInfo extends BaseObservable{
    private String name;
    private String age;
    private String sex;

    public UserInfo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
