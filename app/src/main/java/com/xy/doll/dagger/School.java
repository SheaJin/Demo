package com.xy.doll.dagger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jxy on 2018/6/28.
 */

public class School {

    private Teacher  teacher;

    @Inject
    public School(@Named("person") Teacher teacher) {
        this.teacher = teacher;
    }

    public String show(){
        return teacher.teach();
    }
}
