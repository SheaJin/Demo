package com.qingxu.demoapp.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.api.ApiService;
import com.qingxu.demoapp.api.ApiStore;
import com.qingxu.demoapp.api.HttpObserver;
import com.qingxu.demoapp.model.Register;
import com.qingxu.demoapp.ui.base.BaseActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JinXinYi on 2018/1/7.
 */

public class RegisterActivity extends BaseActivity{

    private EditText mEtName, mEtMail, mEtPsd, mEtCode;
    private String name, mail, password, code;
    private RadioGroup group;
    private RadioButton mRbLogin, mRbRegister;
    private View viewMail,viewPsd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        group = findViewById(R.id.group);
        mRbLogin = findViewById(R.id.rb_login);
        mRbRegister = findViewById(R.id.rb_register);
        viewPsd = findViewById(R.id.view_psd);
        viewMail = findViewById(R.id.view_mail);
    }

    @Override
    protected void initView() {
        group.setOnCheckedChangeListener((group, checkedId) -> {
            mRbLogin.setTextSize(checkedId == R.id.rb_login ? 24 : 18);
            mRbLogin.setTextSize(checkedId == R.id.rb_register ? 24 : 18);
            viewMail.setVisibility(checkedId == R.id.rb_login ? View.GONE : View.VISIBLE);
            viewPsd.setVisibility(checkedId == R.id.rb_login ? View.GONE : View.VISIBLE);
        });
    }

    @Override
    protected void initData() {

    }

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
