package app.ui.activity;

import android.os.Bundle;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import app.model.constant.EventConstant;
import app.model.constant.MessageEvent;
import app.model.constant.ObjectEvent;
import app.ui.base.BaseActivity;
import app.ui.widget.CircleProgressBar;
import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class RechargeActivity extends BaseActivity {
    @BindView(R.id.progress)
    CircleProgressBar mProgressBar;
    private int count = 0;
    private Disposable timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(ObjectEvent event) {
        if (event.getCode() == EventConstant.PLAYHISTORY){
            LogUtil.e("122222EventConstant.PLAYHISTORY");
        }
    }

    @Override
    protected void initUI() {
        initTitle("充值");
    }

    @Override
    protected void initData() {
        runOnUiThread(() ->{}
//                timer = Observable.interval(5, TimeUnit.SECONDS).subscribe(aLong -> {
//                    count += 5;
//                    mProgressBar.setProgress(count);
//                    if (count == 100) {
//                        timer.dispose();
//                    }
//                })
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null) {
            timer.dispose();
        }
        EventBus.getDefault().unregister(this);
    }
}
