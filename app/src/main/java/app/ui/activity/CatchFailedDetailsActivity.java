package app.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;
import com.xy.libs.util.normal.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import app.model.constant.EventConstant;
import app.model.constant.ObjectEvent;
import app.model.data.PlayHistory;
import app.ui.base.BaseActivity;
import app.ui.widget.CustomWindow;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CatchFailedDetailsActivity extends BaseActivity implements CustomWindow.OnChooseListener {

    @BindView(R.id.order_num)
    TextView mTvOrderNum;
    @BindView(R.id.doll_thumb)
    ImageView mIvThumb;
    @BindView(R.id.doll_play)
    ImageView mIvPlay;
    @BindView(R.id.doll_name)
    TextView mTvName;
    @BindView(R.id.doll_status)
    TextView mTvStatus;
    @BindView(R.id.doll_surplus)
    TextView mTvSurplus;
    @BindView(R.id.doll_time)
    TextView mTvTime;

    private CustomWindow diamondWindow, dollWindow;
    private List<String> diamondList, dollList;
    private PlayHistory.LogListBean.ArrayChildBean playHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_failed_details);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
//    public void onEvent(ObjectEvent event) {
//        LogUtil.e("111111EventConstant.PLAYHISTORY");
//        if (event.getCode() == EventConstant.PLAYHISTORY){
//            LogUtil.e("122222EventConstant.PLAYHISTORY");
//        }
//    }

    @OnClick({R.id.return_diamond, R.id.return_doll})
    void click(View view) {
        switch (view.getId()) {
            case R.id.return_diamond:
                diamondWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.return_doll:
                dollWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @Override
    protected void initUI() {
        initTitle("抓取详情");
        diamondList = new ArrayList<>();
        dollList = new ArrayList<>();
        diamondWindow = new CustomWindow(activity, diamondList);
        dollWindow = new CustomWindow(activity, dollList);
        diamondWindow.setListener(this);
        dollWindow.setListener(this);
    }

    @Override
    protected void initData() {
        diamondList.add("1");
        diamondList.add("2");
        diamondList.add("3");
        diamondList.add("4");
        dollList.add("5");
        dollList.add("6");
        dollList.add("7");
    }

    @Override
    public void chooseItem(String msg) {
        ToastUtil.show(activity, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
