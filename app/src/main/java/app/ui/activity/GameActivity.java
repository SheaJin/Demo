package app.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;
import com.xy.libs.util.normal.ToastUtil;

import app.model.contract.GameContract;
import app.model.data.MachineInfo;
import app.presenter.GamePresenter;
import app.ui.base.BaseActivity;
import app.ui.widget.FeedBackWindow;
import app.ui.widget.TouchLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import is.hello.go99.AnimationTools;

public class GameActivity extends BaseActivity implements GameContract.View, TouchLayout.OnTouchListener, FeedBackWindow.OnChooseListener {
    @BindView(R.id.video_view)
    TXCloudVideoView frontVideoView;
    @BindView(R.id.video_view_side)
    TXCloudVideoView sideVideoView;
    @BindView(R.id.video_loading)
    RelativeLayout mVideoLoading;
    @BindView(R.id.touch_close_game)
    TouchLayout mTouchCloseGame;
    @BindView(R.id.touch_feedback)
    TouchLayout mTouchFeedback;
    @BindView(R.id.touch_exchange)
    TouchLayout mTouchExchange;
    @BindView(R.id.touch_refresh)
    TouchLayout mTouchRefresh;
    @BindView(R.id.touch_chat)
    TouchLayout mTouchChat;
    @BindView(R.id.touch_select)
    TouchLayout mTouchSelect;
    @BindView(R.id.touch_start)
    TouchLayout mTouchStart;
    @BindView(R.id.touch_details)
    TouchLayout mTouchDetails;
    @BindView(R.id.touch_recharge)
    TouchLayout mTouchRecharge;
    @BindView(R.id.view_empty)
    View mViewEmpty;

    private GamePresenter presenter;
    private String deviceId, toyId;
    private int currentDirection = 1;
    private TXLivePlayer frontPlayer, sidePlayer;
    private FeedBackWindow feedBackWindow;
    private MachineInfo machineInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        mTouchCloseGame.setTouchListener(this);
        mTouchFeedback.setTouchListener(this);
        mTouchExchange.setTouchListener(this);
        mTouchRefresh.setTouchListener(this);
        mTouchChat.setTouchListener(this);
        mTouchSelect.setTouchListener(this);
        mTouchStart.setTouchListener(this);
        mTouchDetails.setTouchListener(this);
        mTouchRecharge.setTouchListener(this);
        presenter = new GamePresenter(this);
        feedBackWindow = new FeedBackWindow(activity);
        feedBackWindow.setListener(this);
        /**
         * 初始化播放器
         */
        frontPlayer = new TXLivePlayer(this);
        frontPlayer.setPlayerView(frontVideoView);
        frontPlayer.setRenderRotation(90);
        frontPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        frontPlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {
                switch (i) {
                    case TXLiveConstants.PLAY_EVT_PLAY_BEGIN:
                        LogUtil.e("video开始播放");
                        mVideoLoading.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNetStatus(Bundle bundle) {
            }
        });

        sidePlayer = new TXLivePlayer(this);
        sidePlayer.setPlayerView(sideVideoView);
        sidePlayer.setRenderRotation(90);
        sidePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        sidePlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {
                switch (i) {
                    case TXLiveConstants.PLAY_EVT_PLAY_BEGIN:
                        LogUtil.e("video开始播放");
                        mVideoLoading.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNetStatus(Bundle bundle) {
            }
        });
    }

    @Override
    protected void initData() {
        deviceId = getIntent().getExtras().getString("deviceId");
        toyId = getIntent().getExtras().getString("toyId");
        presenter.getMachineInfo(deviceId, toyId);  //娃娃机信息
        feedBackWindow.setOnDismissListener(() -> AnimationTools.getInstance().hideAlphaAnimation(mViewEmpty));
    }

    @OnClick({R.id.view_empty})
    void click(View view) {
        switch (view.getId()) {
            case R.id.view_empty:
                AnimationTools.getInstance().hideAlphaAnimation(mViewEmpty);
                break;
        }
    }

    @Override
    public void onAction(TouchLayout.TouchAction action, boolean canTouch) {
        switch (action) {
            case CLOSE:
                finish();
                break;
            case FEEDBACK:
                feedBackWindow.showAtLocation(mViewEmpty, Gravity.BOTTOM, 0, 0);
                AnimationTools.getInstance().showAlphaAnimation(mViewEmpty);
                break;
            case EXCHANGE:
                switch (currentDirection) {
                    case 1:
                        frontVideoView.setVisibility(View.VISIBLE);
                        sideVideoView.setVisibility(View.GONE);
                        currentDirection = 2;
                        LogUtil.e("正currentDirection = " + currentDirection);
                        break;
                    case 2:
                        frontVideoView.setVisibility(View.GONE);
                        sideVideoView.setVisibility(View.VISIBLE);
                        currentDirection = 1;
                        LogUtil.e("侧currentDirection = " + currentDirection);
                        break;
                }
                break;
            case REFRESH:

                break;
            case CHAT:

                break;
            case FASTSELECT:

                break;
            case GAMESTART:

                break;
            case DETAILS:

                break;
            case RECHARGE:

                break;
        }
    }

    @Override
    public void choose(String msg) {
        ToastUtil.show(activity, msg);
        feedBackWindow.dismiss();
    }

    @Override
    public void getMachineInfoOk(MachineInfo machineInfo) {
        machineInfos = machineInfo;
        frontPlayer.startPlay(machineInfo.getFront_live(), TXLivePlayer.PLAY_TYPE_LIVE_FLV);
        sidePlayer.startPlay(machineInfo.getSide_live(), TXLivePlayer.PLAY_TYPE_LIVE_FLV);
    }

    @Override
    public void getMachineInfoErr(String info) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        frontVideoView.onDestroy();
        frontPlayer.stopPlay(true);
    }
}
