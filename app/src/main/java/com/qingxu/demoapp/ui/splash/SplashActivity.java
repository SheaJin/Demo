package com.qingxu.demoapp.ui.splash;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseActivity;
import com.qingxu.demoapp.ui.main.HomeActivity;
import com.qingxu.demoapp.util.app.JumpUtil;
import com.qingxu.demoapp.util.glide.GlideUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.card_time)
    CardView cardTime;
    private int count = 4;
    private Disposable timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initUI() {
        GlideUtil.loadImage(activity,R.mipmap.dog,ivBg);
    }

    @Override
    protected void initData() {
        timer = Observable.interval(0, 1, TimeUnit.SECONDS)
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
    protected int setFragmentContainerResId() {
        return 0;
    }

    @OnClick(R.id.card_time)
    public void click() {
        if (timer != null && !timer.isDisposed()){
            timer.dispose();
        }
        JumpUtil.overlay(activity, HomeActivity.class);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null && !timer.isDisposed()){
            timer.dispose();
        }
    }
}
