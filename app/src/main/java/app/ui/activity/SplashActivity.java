package app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.util.app.JumpUtil;
import com.xy.libs.util.glide.GlideUtil;
import com.xy.libs.util.storage.SPs;

import java.util.concurrent.TimeUnit;

import app.model.constant.Constant;
import app.model.contract.LoginContract;
import app.model.data.UserInfo;
import app.presenter.LoginPresenter;
import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.card_time)
    CardView cardTime;
    private int count = 2;
    private Disposable timer;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initUI() {
        presenter = new LoginPresenter(this);
        GlideUtil.loadImage(activity,R.mipmap.splash,ivBg);
    }

    @Override
    protected void initData() {
        timer = Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(aLong -> count - aLong)
                .subscribe(aLong -> tvTime.setText("跳过" + (aLong + 1) + "S")
                        , throwable -> {}
                        , () -> {
                            if (!SPs.get(activity, Constant.TOKEN, "token").equals(Constant.TOKEN)) {
//                                JumpUtil.overlay(activity, MainActivity.class);
                                presenter.login("17621979783","1600");
                            } else {
                                JumpUtil.overlay(activity, LoginActivity.class);
                            }
//                            finish();
                        });
    }

    /**
     * 销毁界面、点击跳过 结束timer
     * */
    @OnClick(R.id.card_time)
    public void click() {
        if (timer != null && !timer.isDisposed()){
            timer.dispose();
        }
        if (!SPs.get(activity, Constant.TOKEN, "default").equals(Constant.TOKEN)) {
//            JumpUtil.overlay(activity, MainActivity.class);
            presenter.login("17621979783","1600");
        } else {
            JumpUtil.overlay(activity, LoginActivity.class);
        }
//        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null && !timer.isDisposed()){
            timer.dispose();
        }
    }

    @Override
    public void loginOk(UserInfo userInfo) {
        SPs.put(activity,Constant.TOKEN,userInfo.getToken());
        JumpUtil.overlay(activity, MainActivity.class);
        finish();
    }

    @Override
    public void loginErr(String errInfo) {

    }
}
