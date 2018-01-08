package com.qingxu.demoapp.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.api.ApiService;
import com.qingxu.demoapp.api.ApiStore;
import com.qingxu.demoapp.api.HttpObserver;
import com.qingxu.demoapp.model.Register;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JinXinYi on 2018/1/7.
 */

public class RegisterActivity extends AppCompatActivity{

    private EditText mEtName,mEtMail,mEtPsd;
    private String name,mail,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEtName = findViewById(R.id.et_name);
        mEtMail = findViewById(R.id.et_mail);
        mEtPsd = findViewById(R.id.et_password);

    }

    public void click(View view){
        switch (view.getId()){
            case R.id.btn_register:
                name = mEtName.getText().toString().trim();
                mail = mEtMail.getText().toString().trim();
                password = mEtPsd.getText().toString().trim();
                register();
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("name",name);
        params.put("email",mail);
        params.put("password",password);

        ApiStore.createApi(ApiService.class)
                .register(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<Register>() {
                    @Override
                    public void onNext(Register register) {
                        Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("jxy", "onError: " + e.getMessage());
                    }
                });
    }
}
