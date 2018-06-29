package com.xy.doll.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    @Inject
    Person person;

    @Inject
    Person person1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

//        DaggerMainComponent.create().inject(this);

        MainComponent component = DaggerMainComponent.builder().personModule(new PersonModule(this)).build();
        component.inject(this);

        LogUtil.e("person = " + person.toString() + "\n" + "person1 = " + person1.toString());

    }
}
