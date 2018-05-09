package com.xy.doll.banner;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxy on 2018/4/26.
 */

public class MyBanner extends RelativeLayout {
    /**
     * 数据源
     */
    private List<BannerInfo> mDataList;
    /**
     * 滑动的ViewPager
     */
    private BannerPager mScrollerPager;

    private OnPagerChangeListener mPageChangeListener = null;

    public MyBanner(Context context) {
        this(context, null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置数据源
     *
     * @param dataList 数据源
     * @return this
     */
    public MyBanner setInfoList(List<BannerInfo> dataList) {
        if (dataList != null) {
            this.mDataList = dataList;
        } else {
            this.mDataList = new ArrayList<>();
        }
        return this;
    }

    /**
     * 设置自动滑动间隔时间
     *
     * @param interval 间隔时间
     * @return this
     */
    public MyBanner setInterval(int interval) {
        BannerPager.mInterval = interval;
        return this;
    }

    /**
     * 设置滑动监听
     *
     * @param listener 滑动监听器
     * @return this
     */
//    public MyBanner setOnPagerChangeListener(OnPagerChangeListener listener) {
//        if (mScrollerPager != null) {
//            mScrollerPager.setmPageListener(listener);
//        } else {
//            mPageChangeListener = listener;
//        }
//        return this;
//    }

    /**
     * 设置是否自动播放
     * 默认为true 自动播放
     *
     * @param autoPlay 自动播放
     * @return this
     */
    public MyBanner setAutoPlay(boolean autoPlay) {
        BannerPager.mAutoPlay = autoPlay;
        return this;
    }

    /**
     * 设置是否可以手动滑动
     *
     * @param canScroll 是否可以手动滑动
     * @return this
     */
    public MyBanner setCanScroll(boolean canScroll) {
        BannerPager.canScroll = canScroll;
        return this;
    }

    /**
     * 装载AdPlayBanner
     */
    public void setUp() {
        mScrollerPager = new BannerPager(this, mDataList);
        mScrollerPager.show();
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (mScrollerPager == null) {
            return;
        }
        mScrollerPager.stop();
        // 清除所有的子view
        removeAllViews();
    }

    public interface OnPageClickListener {
        /**
         * 页面点击
         *
         * @param info     数据
         * @param position 位置
         */
        void onPageClick(BannerInfo info, int position);
    }

    public interface OnPagerChangeListener {
        void onPageSelected(final int position);

        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageScrollStateChanged(int state);
    }
}
