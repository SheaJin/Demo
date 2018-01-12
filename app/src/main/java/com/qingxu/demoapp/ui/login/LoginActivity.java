package com.qingxu.demoapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseActivity;
import com.qingxu.demoapp.ui.pic.ImpressPicActivity;
import com.qingxu.demoapp.ui.rank.RankActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.but)
    Button mBut;
    @BindView(R.id.tvsssss)
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initUI() {
        mTv.setText("ssssssssssssssssssss");
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.but)
    void click(View view){
        switch (view.getId()){
            case R.id.but:
                startActivity(new Intent(this,RankActivity.class));
                break;
        }
    }

}
