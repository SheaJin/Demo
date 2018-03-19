package app.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xy.doll.DollApplication;
import com.xy.libs.R;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.model.constant.MessageEvent;
import app.model.constant.ObjectEvent;
import butterknife.ButterKnife;

/**
 * Created by jxy on 2018/1/8.
 */

public abstract class BaseActivity extends AutoLayoutActivity implements View.OnClickListener{
    private TextView mTvTitle;
    private ImageView mIvBack;
    protected BaseActivity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        activity = this;
        initBind();
        initUI();
        initData();

    }

    public void initBind(){
        ButterKnife.bind(this);
//        EventBus.getDefault().register(this);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
//    public void onEvent(ObjectEvent event) {
//    }

    /**
     * 界面初始化
     */
    protected abstract void initUI();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    /**
     * 点击事件
     */
    protected void setClick(int... resId) {
        for (int id : resId) {
            View view = this.findViewById(id);
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 标题栏
     *
     * @param s
     */
    public void initTitle(String s) {
        mTvTitle = findViewById(R.id.titlessssss);
        mIvBack = findViewById(R.id.image_back);
        if (s != null) {
            mTvTitle.setText(s);
        }
        mIvBack.setOnClickListener(v -> finish());
    }


    public void showMess(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    public void showLog(String mess){
        Log.e(DollApplication.getInstance().getClass().getSimpleName(), "LogInfo: " + mess);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

}
