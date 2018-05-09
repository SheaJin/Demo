package com.xy.doll.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxy on 2018/4/26.
 */

public class BannerPager extends ViewPager {
    /**
     * 父布局
     */
    private RelativeLayout mContainer;
    /**
     * 数据源
     */
    private List<BannerInfo> mDataList;
    /**
     * UI线程的Handler
     */
    private Handler mUIHandler = new Handler(Looper.myLooper());
    /**
     * 是否自动播放
     */
    public static boolean mAutoPlay = true;
    /**
     * 是否可以手动滑动
     */
    public static boolean canScroll = true;
    /**
     * 间隔时间，默认2000ms
     */
    public static int mInterval = 2000;
    /**
     * 当前下标
     */
    private int mSelectedIndex = 0;

    public BannerPager(@NonNull Context context) {
        this(context, null);
    }

    public BannerPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerPager(RelativeLayout mContainer, List<BannerInfo> infos) {
        super(mContainer.getContext());
        this.mContainer = mContainer;
        if (infos != null) {
            this.mDataList = infos;
        } else {
            this.mDataList = new ArrayList<>();
        }
        init();
    }

    private void init() {
        // 初始化切换时间
        initScrollTime(new AutoPlayScroller(getContext(), new LinearInterpolator()));
        // 设置Adapter
        MyBannerAdapter adapter = new MyBannerAdapter(getContext(), mDataList);
        setAdapter(adapter);
    }

    /**
     * 图片A开始滑动到图片B之间的时间
     * 主要是保证两张图片之间的切换动画有足够的时间显示
     *
     * @param mScroller 滑动Scroller
     */
    private void initScrollTime(Scroller mScroller) {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mField.set(this, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自动播放任务
     */
    private Runnable autoScrollTask = new Runnable() {
        @Override
        public void run() {
            if (mSelectedIndex == Integer.MAX_VALUE) {
                if (mDataList.isEmpty() && mDataList == null) {
                    return;
                }
                int rightPos = mSelectedIndex % mDataList.size();
                setCurrentItem(getInitPosition() + rightPos + 1, true);
            } else {
                setCurrentItem(mSelectedIndex + 1, true);
            }
        }
    };

    /**
     * 开始广告滚动任务
     */
    private void startAdvertPlay() {
        if (mAutoPlay) {
            stopAdvertPlay();
            mUIHandler.postDelayed(autoScrollTask, mInterval);
        }
    }

    /**
     * 停止广告滚动任务
     */
    private void stopAdvertPlay() {
        mUIHandler.removeCallbacks(autoScrollTask);
    }

    /**
     * 获取banner的初始位置
     *
     * @return banner的初始位置
     */
    private int getInitPosition() {
        if (mDataList.isEmpty() && mDataList == null) {
            return 0;
        }
        int halfValue = Integer.MAX_VALUE / 2;
        int position = halfValue % mDataList.size();
        return halfValue - position;
    }

    /**
     * 显示播放ScrollerPager
     */
    public void show() {
        mContainer.removeAllViews();
        if (null == mDataList || 0 == mDataList.size()) {
            stopAdvertPlay();
            mContainer.setVisibility(GONE);
        } else {
            addScrollerPager(); // 把ScrollerPager装载到外布局中
            if (mDataList.size() == 1) {
                stopAdvertPlay();
            } else {
                startAdvertPlay();
            }
            mContainer.setVisibility(VISIBLE);
            // 设置初始化的位置
            setCurrentItem(getInitPosition());
        }
    }

    public void stop() {
        stopAdvertPlay();
    }

    /**
     * 装载ScrollerPager
     */
    private void addScrollerPager() {
        ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewPager.LayoutParams.MATCH_PARENT;
        mContainer.addView(this, layoutParams);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (canScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 如果点击
                performClick();
                stopAdvertPlay();
                break;
            case MotionEvent.ACTION_UP:
                startAdvertPlay();
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
