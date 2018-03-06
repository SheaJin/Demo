package app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

import app.model.contract.GameContract;
import app.model.data.MachineInfo;
import app.presenter.GamePresenter;
import app.ui.base.BaseActivity;
import app.ui.widget.TouchLayout;
import butterknife.BindView;

public class GameActivity extends BaseActivity implements GameContract.View, TouchLayout.OnTouchListener {
    @BindView(R.id.video_view)
    TXCloudVideoView frontVideoView;
    @BindView(R.id.video_loading)
    RelativeLayout mVideoLoading;
    @BindView(R.id.touch_close_game)
    TouchLayout mTouchCloseGame;

    private GamePresenter presenter;
    private String deviceId, toyId;
    private TXLivePlayer frontPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    protected void initUI() {
        mTouchCloseGame.setTouchListener(this);
        presenter = new GamePresenter(this);
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

    }

    @Override
    protected void initData() {
        deviceId = getIntent().getExtras().getString("deviceId");
        toyId = getIntent().getExtras().getString("toyId");
        presenter.getMachineInfo(deviceId, toyId);  //娃娃机信息

    }

    @Override
    public void onAction(TouchLayout.TouchAction action) {
        switch (action) {
            case CLOSE:
                finish();
                break;
        }
    }

    @Override
    public void getMachineInfoOk(MachineInfo machineInfo) {
        frontPlayer.startPlay(machineInfo.getFront_live(), TXLivePlayer.PLAY_TYPE_LIVE_FLV);
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
