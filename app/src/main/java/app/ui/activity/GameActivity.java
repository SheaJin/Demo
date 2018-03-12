package app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.xy.doll.R;
import com.xy.libs.ui.adapter.CommonAdapter;
import com.xy.libs.ui.adapter.RecyclerViewUtil;
import com.xy.libs.util.app.JumpUtil;
import com.xy.libs.util.app.LogUtil;
import com.xy.libs.util.glide.GlideUtil;
import com.xy.libs.util.normal.KeyBoardUtil;
import com.xy.libs.util.normal.TextUtil;
import com.xy.libs.util.normal.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import app.model.api.AppConfig;
import app.model.contract.GameContract;
import app.model.data.Machine;
import app.model.data.MachineInfo;
import app.model.data.UserInfo;
import app.presenter.GamePresenter;
import app.ui.adapter.FastSelectViewHolder;
import app.ui.base.BaseActivity;
import app.ui.widget.FeedBackWindow;
import app.ui.widget.ShadowTextView;
import app.ui.widget.TouchLayout;
import app.util.AppUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import is.hello.go99.AnimationTools;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

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
    @BindView(R.id.tv_person)
    ShadowTextView mTvPerson;
    @BindView(R.id.tv_diamond)
    ShadowTextView mTvDiamond;
    @BindView(R.id.tv_coin)
    ShadowTextView mTvCoin;
    @BindView(R.id.game_price)
    ShadowTextView mTvPrice;
    @BindView(R.id.game_newprice)
    ShadowTextView mTvNewprice;
    @BindView(R.id.iv_head)
    CircleImageView mIvHead;
    @BindView(R.id.image_signal)
    ImageView mIvSignal;
    @BindView(R.id.danmuku)
    DanmakuView danmukuView;
    @BindView(R.id.empty_machine_count)
    ShadowTextView mTvEmptyMachineCount;
    @BindView(R.id.all_machine_count)
    ShadowTextView mTvAllMachineCount;
    @BindView(R.id.rv_select)
    RecyclerView mRvSelect;
    @BindView(R.id.view_select)
    RelativeLayout viewSelect;
    @BindView(R.id.edit_chat)
    EditText mEtChat;
    @BindView(R.id.btn_send)
    ImageView nBtnSend;
    @BindView(R.id.view_send)
    RelativeLayout viewSend;

    private GamePresenter presenter;
    private String deviceId, toyId;
    private int currentDirection = 1;
    private TXLivePlayer frontPlayer, sidePlayer;
    private FeedBackWindow feedBackWindow;
    private MachineInfo machineInfos;
    private Disposable timer;
    private DanmakuContext danmakuContext;
    private BaseDanmakuParser parser;
    private boolean fastSelectIsShow = false;
    private LinearLayoutManager selectManager;
    private List<Machine.DeviceListBean> selectList;
    private CommonAdapter<Machine.DeviceListBean, FastSelectViewHolder> selectAdapter;
    private int[] signal = {R.mipmap.signal_bad_btn, R.mipmap.signal_normal_btn, R.mipmap.signal_good_btn};
    private int[] machineStatus = new int[]{0, R.mipmap.machine_status_empty, R.mipmap.machine_status_using, R.mipmap.machine_status_fixing};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("GameActivity onCreate");
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
        selectManager = new LinearLayoutManager(this);
        selectManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerViewUtil.nestedRecyclerView(mRvSelect, selectManager);
        selectList = new ArrayList<>();
        //初始化播放器
        initPlayer();
        //初始化弹幕
        initDanmuku();
    }

    /**
     * 初始化播放器
     */
    private void initPlayer() {
        frontPlayer = new TXLivePlayer(this);
        frontPlayer.setPlayerView(frontVideoView);
        frontPlayer.setRenderRotation(90);
        frontPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        frontPlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {
                switch (i) {
                    case TXLiveConstants.PLAY_EVT_PLAY_BEGIN:
                        LogUtil.e("frontVideo start");
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
                        LogUtil.e("sideVideo start");
                        mVideoLoading.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNetStatus(Bundle bundle) {
            }
        });
    }

    /**
     * 初始化弹幕
     */
    private void initDanmuku() {
        danmukuView.setKeepScreenOn(true);
        danmukuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {

            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        parser = new BaseDanmakuParser() {
            @Override
            protected IDanmakus parse() {
                return new Danmakus();
            }
        };
        danmukuView.prepare(parser, danmakuContext);
        danmakuContext.setDanmakuBold(true);
    }

    /**
     * 添加弹幕
     */
    private void addDanmaku(String content) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku != null && danmukuView != null) {
            danmaku.text = content;
            danmaku.priority = 1;
            danmaku.padding = 5;
            danmaku.setTime(danmukuView.getCurrentTime());
            danmaku.textSize = 15;
            danmaku.textColor = TextUtil.randomColor();
            danmaku.textShadowColor = Color.WHITE;
            danmukuView.addDanmaku(danmaku);
        }
    }

    @Override
    protected void initData() {
        deviceId = getIntent().getExtras().getString("deviceId");
        toyId = getIntent().getExtras().getString("toyId");
        presenter.getMachineInfo(deviceId, toyId);  //娃娃机信息
        presenter.getUserInfo();    //个人信息
        /**
         * 监听网络状态
         * */
        timer = Observable.interval(5, TimeUnit.SECONDS).subscribe(aLong -> {
            runOnUiThread(() -> mIvSignal.setImageResource(signal[AppUtil.getWifiState(activity)]));
        });
        feedBackWindow.setOnDismissListener(() -> AnimationTools.getInstance().hideAlphaAnimation(mViewEmpty));
    }

    @OnClick({R.id.view_empty, R.id.btn_send})
    void click(View view) {
        switch (view.getId()) {
            case R.id.view_empty:
                AnimationTools.getInstance().hideAlphaAnimation(mViewEmpty);
                break;
            case R.id.btn_send:
                viewSend.setVisibility(View.GONE);
                KeyBoardUtil.closeKeybord(mEtChat, activity);
                break;
        }
    }

    @Override
    public void onAction(TouchLayout.TouchAction action, boolean canTouch) {
        if (canTouch) {
            switch (action) {
                case CLOSE:
                    finish();
                    break;
                case FEEDBACK://申诉
                    feedBackWindow.showAtLocation(mViewEmpty, Gravity.BOTTOM, 0, 0);
                    AnimationTools.getInstance().showAlphaAnimation(mViewEmpty);
                    break;
                case EXCHANGE://切换摄像头
                    switch (currentDirection) {
                        case 1:
                            frontVideoView.setVisibility(View.VISIBLE);
                            sideVideoView.setVisibility(View.GONE);
                            currentDirection = 2;
                            break;
                        case 2:
                            frontVideoView.setVisibility(View.GONE);
                            sideVideoView.setVisibility(View.VISIBLE);
                            currentDirection = 1;
                            break;
                    }
                    break;
                case REFRESH://刷新视频

                    break;
                case CHAT://聊天
                    viewSend.setVisibility(View.VISIBLE);
                    KeyBoardUtil.openKeybord(mEtChat, activity);
                    mEtChat.setFocusableInTouchMode(true);
                    mEtChat.setFocusable(true);
                    mEtChat.requestFocus();
                    break;
                case FASTSELECT://同款
                    if (fastSelectIsShow) {
                        viewSelect.setVisibility(View.GONE);
                        fastSelectIsShow = false;
                    } else {
                        viewSelect.setVisibility(View.VISIBLE);
                        fastSelectIsShow = true;
                    }
                    break;
                case GAMESTART://开始游戏

                    break;
                case DETAILS://详情
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("list", (ArrayList<String>) machineInfos.getDetail_img());
                    JumpUtil.overlay(activity, GiftDetailsActivity.class, bundle);
                    break;
                case RECHARGE://充值
                    JumpUtil.overlay(activity, RechargeActivity.class);
                    break;
            }
        }
    }

    /**
     * 反馈 回调
     *
     * @param msg
     */
    @Override
    public void choose(String msg) {
        ToastUtil.show(activity, msg);
        feedBackWindow.dismiss();
    }

    @Override
    public void getMachineInfoOk(MachineInfo machineInfo) {
        if (machineInfo != null) {
            machineInfos = machineInfo;
            mTvPerson.setText(String.valueOf(machineInfo.getPeople_number() + 1));
            mTvPrice.setText(String.valueOf(machineInfo.getBrilliant()));
            GlideUtil.loadImage(activity, machineInfo.getEmploy_info().getImg(), mIvHead);
            sidePlayer.startPlay(machineInfo.getSide_live(), TXLivePlayer.PLAY_TYPE_LIVE_FLV);
            frontPlayer.startPlay(machineInfo.getFront_live(), TXLivePlayer.PLAY_TYPE_LIVE_FLV);
            //获取同款列表
            ArrayMap<String, String> params = new ArrayMap<>();
            params.put("toy_id", machineInfos.getToy_id());
            params.put("device_id", machineInfos.getDevice_id());
            presenter.getFastSelectList(params);
        }
    }

    @Override
    public void getMachineInfoErr(String info) {
        ToastUtil.show(activity, info);
    }

    @Override
    public void getUserInfoOk(UserInfo userInfo) {
        if (userInfo != null) {
            mTvDiamond.setText(String.valueOf(userInfo.getBrilliant()));
            mTvCoin.setText(String.valueOf(userInfo.getGold()));
        }
    }

    @Override
    public void getUserInfoErr(String info) {
        ToastUtil.show(activity, info);
    }

    @Override
    public void getFastSelectListOk(Machine machine) {
        mTvAllMachineCount.setText(machine.getDevice_list().size() + " 台");
        mTvEmptyMachineCount.setText(machine.getFree_num() + " 台");
        if (machine != null) {
            selectList.clear();
            selectList.addAll(machine.getDevice_list());
            selectAdapter = new CommonAdapter<Machine.DeviceListBean, FastSelectViewHolder>(activity, selectList) {
                @Override
                public int layoutResId(int viewType) {
                    return R.layout.item_fast_select;
                }

                @Override
                public FastSelectViewHolder holderInstance(View itemView, int viewType) {
                    return new FastSelectViewHolder(itemView) {
                        @Override
                        public void OnItemClick(int position) {
                            if (selectList.get(position).getState() == 3) {
                                ToastUtil.show(activity, "娃娃机正在维护，无法进入");
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putString("deviceId", machineInfos.getDevice_id());
                                bundle.putString("toyId", "0");
                                JumpUtil.overlay(activity, GameActivity.class, bundle);
                                finish();
                            }
                        }
                    };
                }

                @Override
                public void fillView(FastSelectViewHolder holder, Machine.DeviceListBean data, int position) {
                    Machine.DeviceListBean machine = selectList.get(position);
                    holder.mIvState.setImageResource(machineStatus[machine.getState()]);
                    GlideUtil.loadImage(activity, AppConfig.BASE_URL_PIC + machine.getFront_cover(), holder.mIvThumb);
                    switch (machine.getInfinite()) {
                        case 0:
                            holder.mIvInfinite.setVisibility(View.GONE);
                            break;
                        case 1:
                        case 2:
                            holder.mIvInfinite.setVisibility(View.VISIBLE);
                            GlideUtil.loadImage(activity, AppConfig.BASE_URL_PIC + machine.getCharacteristic(), holder.mIvInfinite);
                            break;
                    }
                }
            };
            selectAdapter.notifyDataSetChanged();
            mRvSelect.setAdapter(selectAdapter);
        }

    }

    @Override
    public void getFastSelectListErr(String info) {
        ToastUtil.show(activity, info);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("GameActivity onDestroy");
        if (sideVideoView != null)
            sideVideoView.onDestroy();
        if (sidePlayer != null)
            sidePlayer.stopPlay(true);
        if (frontVideoView != null)
            frontVideoView.onDestroy();
        if (frontPlayer != null)
            frontPlayer.stopPlay(true);
        if (timer != null && !timer.isDisposed())
            timer.dispose();
    }
}
