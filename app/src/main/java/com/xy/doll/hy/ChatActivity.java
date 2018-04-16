package com.xy.doll.hy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.xy.doll.R;

import java.util.ArrayList;
import java.util.List;

import app.ui.base.BaseActivity;

public class ChatActivity extends BaseActivity {
    private final static int ON_EMOJI_CHANGE = 0xc1;
    private String chatName, waitSendMsg;
    private EditText mEtContent;
    private SwipyRefreshLayout swipyRefreshLayout;
    private View viewBottom, viewEmoji, viewInner;
    private boolean isInnerShow = false, refreshing;
    private int marginBottom, lastIndex, currentIndex;
    private EmojiView emojiView;
    private RecyclerView mRv;
    private List<ChatBean> chatList;
    private ChatAdapter chatAdapter;
//    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    @Override
    protected void initUI() {
        mEtContent = findViewById(R.id.et_content);
        viewBottom = findViewById(R.id.view_bottom);
        viewEmoji = findViewById(R.id.layout_emoji);
        viewInner = findViewById(R.id.view_inner);
//        rxPermissions = new RxPermissions(activity);
        swipyRefreshLayout = findViewById(R.id.swipyRefreshLayout);
        swipyRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        mRv = findViewById(R.id.recyclerView);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        chatName = getIntent().getStringExtra("name");
        chatList = new ArrayList<>();
        chatAdapter = new ChatAdapter(activity);
        chatAdapter.notifyDataSetChanged();
        mRv.setAdapter(chatAdapter);
        this.emojiView = new EmojiView(this, this, ON_EMOJI_CHANGE, this.mUIHandler, this.mEtContent);
        setTitle(chatName);
        setClick(R.id.view_talk, R.id.et_content, R.id.view_emoji, R.id.view_more);
        swipyRefreshLayout.setOnRefreshListener(direction -> {
            if (direction == SwipyRefreshLayoutDirection.TOP) {
                swipyRefreshLayout.setRefreshing(false);
                refreshing = false;
            }
            refreshing = true;
            swipyRefreshLayout.postDelayed(() -> {
                if (refreshing) {
                    swipyRefreshLayout.setRefreshing(false);
                }
            }, 10000);
        });
        mEtContent.setFocusable(true);
        mEtContent.setFocusableInTouchMode(true);
        mEtContent.requestFocus();
        //监听软键盘
        mEtContent.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage();
            }
            return false;
        });
        requestPermission();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.view_talk:

                break;
            case R.id.et_content:
                if (isInnerShow) {
                    hideInnerAnimate();
                }
                break;
            case R.id.view_emoji:
                currentIndex = 0;
                if (currentIndex != lastIndex) {
                    viewEmoji.setVisibility(View.VISIBLE);
                    viewBottom.setVisibility(View.GONE);
                    if (!isInnerShow)
                        showInnerAnimate();
                } else {
                    if (isInnerShow) {
                        hideInnerAnimate();
                    } else {
                        viewEmoji.setVisibility(View.VISIBLE);
                        viewBottom.setVisibility(View.GONE);
                        showInnerAnimate();
                    }
                }
                lastIndex = 0;
                break;
            case R.id.view_more:
                currentIndex = 1;
                if (currentIndex != lastIndex) {
                    viewEmoji.setVisibility(View.GONE);
                    viewBottom.setVisibility(View.VISIBLE);
                    if (!isInnerShow)
                        showInnerAnimate();
                } else {
                    if (isInnerShow) {
                        hideInnerAnimate();
                    } else {
                        viewEmoji.setVisibility(View.GONE);
                        viewBottom.setVisibility(View.VISIBLE);
                        showInnerAnimate();
                    }
                }
                lastIndex = 1;
                break;
        }
    }

    private void requestPermission() {
//        rxPermissions.request(Manifest.permission.RECORD_AUDIO).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean isGranted) throws Exception {
//                if (!isGranted) {
//                    showMess("未开通授权,无法使用录音功能");
//                }
//            }
//        });
    }

    /**
     * 发送消息
     */
    private void sendMessage() {
        waitSendMsg = mEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(waitSendMsg)) {
            showMess("内容不能为空");
            return;
        }
        ChatBean chatBean = new ChatBean();
        chatBean.setMessage(waitSendMsg);
        chatAdapter.setChatBean(chatBean);
        chatAdapter.notifyDataSetChanged();
        mRv.scrollToPosition(chatAdapter.getItemCount() - 1);
        mEtContent.setText("");
    }

    /**
     * 展示动画
     */
    private void showInnerAnimate() {
        if (marginBottom == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewInner.getLayoutParams();
            marginBottom = layoutParams.bottomMargin;
        }
        ValueAnimator animator;
        animator = ValueAnimator.ofFloat(marginBottom, 0);
        animator.addUpdateListener(animation ->
                viewInner.post(() -> {
                    float value = (float) animation.getAnimatedValue();
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -marginBottom);
                    layoutParams.bottomMargin = (int) value;
                    viewInner.setLayoutParams(layoutParams);
                }));
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isInnerShow = true;
                viewInner.setEnabled(true);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewInner.setEnabled(false);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }
        });
        animator.setDuration(200);
        animator.start();
        mRv.scrollToPosition(chatAdapter.getItemCount() - 1);
//        InputTools.hideKeyboardAndEmoji(activity);
    }

    /**
     * 隐藏动画
     */
    private void hideInnerAnimate() {
        if (marginBottom == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewBottom.getLayoutParams();
            marginBottom = layoutParams.bottomMargin;
        }
        ValueAnimator animator;
        animator = ValueAnimator.ofFloat(0, marginBottom);
        animator.addUpdateListener(animation ->
                viewInner.post(() -> {
                    float value = (float) animation.getAnimatedValue();
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -marginBottom);
                    layoutParams.bottomMargin = (int) value;
                    viewInner.setLayoutParams(layoutParams);
                }));
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isInnerShow = false;
                viewInner.setEnabled(true);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewInner.setEnabled(false);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }
        });
        animator.setDuration(200);
        animator.start();
    }

    /**
     * 更新的Handler
     */
    @SuppressLint("HandlerLeak")
    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ON_EMOJI_CHANGE: { // 监听表情界面的变化
                    emojiView.refreshWidgetUI(msg);
                    break;
                }
            }
        }
    };
}
