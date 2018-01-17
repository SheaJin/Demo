package com.qingxu.demoapp.ui.splash;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseActivity;
import com.qingxu.demoapp.ui.main.HomeActivity;
import com.qingxu.demoapp.util.app.JumpUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.card_time)
    CardView cardTime;
    private SplashActivity activity;
    private int count = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        activity = this;
        Glide.with(activity).load("").into(ivBg);

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(aLong -> count - aLong)
                .subscribe(aLong -> tvTime.setText("跳过" + aLong + "S")
                        , throwable -> {
                        }
                        , () -> {
                            JumpUtil.overlay(activity, HomeActivity.class);
                            finish();
                        });
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.card_time)
    public void click() {
        JumpUtil.overlay(activity, HomeActivity.class);
        finish();
    }
}
