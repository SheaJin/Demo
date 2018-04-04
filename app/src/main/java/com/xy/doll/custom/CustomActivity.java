package com.xy.doll.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.xy.doll.R;
import com.xy.doll.emoji.EmojiWidget;

import app.ui.base.BaseActivity;

public class CustomActivity extends BaseActivity {
//    @BindView(R.id.view_pager)
//    ViewPager pager;
//    @BindView(R.id.bottom)
//    BottomView bottom;
//    private FragmentPagerAdapter simpleFragmentPagerAdapter;
//    private List<Fragment> dataList;

    private final static int ON_EMOJI_CHANGE = 0xc1;
    private EmojiWidget emojiWidget;
    private EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
    }

    @Override
    protected void initUI() {
//        dataList = new ArrayList<>();
//        dataList.add(new BlankFragment());
//        dataList.add(new GroupFragment());
//        dataList.add(new BlankFragment());
//        dataList.add(new BlankFragment());
//        simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),dataList);
//        pager.setAdapter(simpleFragmentPagerAdapter);
//        bottom.setUpViewPager(pager);
        etContent = (EditText) findViewById(R.id.et_content);
        this.emojiWidget = new EmojiWidget(this, this, ON_EMOJI_CHANGE, this.mUIHandler, this.etContent);
    }

    @Override
    protected void initData() {

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
                    emojiWidget.refreshWidgetUI(msg);
                    break;
                }
            }
        }
    };

    //隐藏键盘
    private void hideKeyboardAndEmoji() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
