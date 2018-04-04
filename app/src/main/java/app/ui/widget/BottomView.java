package app.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;
import com.xy.libs.util.normal.TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxy on 2018/4/2.
 */

@SuppressLint("DrawAllocation")
public class BottomView extends View implements ViewPager.OnPageChangeListener {
    private Paint paint;
    private Bitmap bgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_tab_bg);
    private Bitmap[] bitmaps = {BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_tab_1), BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_tab_2)
            , BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_tab_3), BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_tab_4)};
    private String[] tabs = {"娃娃", "公会", "消息", "个人"};
    private List<BottomView> bottomViewList;
    private float iconSize;
    private int currentIndex = 0, lastIndex = 0;
    private ViewPager pager;
    private RectF[] rectFs;
    private RectF[] touchRectFs;
    private Paint.FontMetricsInt pfi;

    public BottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(TextUtil.getPx(36, TextUtil.Orientation.Width));
        paint.setColor(Color.parseColor("#34040A"));
        pfi = paint.getFontMetricsInt();
        bottomViewList = new ArrayList<>();
        rectFs = new RectF[4];
        touchRectFs = new RectF[4];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        iconSize = getHeight() * 0.69444f;
        //画背景
        canvas.drawBitmap(bgBitmap, null, new RectF(0, getHeight() * 0.16666f, getWidth(), getHeight()), paint);
        for (int i = 0; i < 4; i++) {
            //触摸区域RectF
            touchRectFs[i] = new RectF((getWidth() / 4) * i, getHeight() * 0.16666f, (getWidth() / 4) * (i + 1), getHeight());
            //图片RectF
            if (i == currentIndex) {
                rectFs[i] = new RectF(getWidth() / 4 * i + (getWidth() / 4) / 2 - iconSize * 0.5f, 0, getWidth() / 4 * i + (getWidth() / 4) / 2 + iconSize * 0.5f, iconSize);
            } else {
                rectFs[i] = new RectF(getWidth() / 4 * i + (getWidth() / 4) / 2 - iconSize * 0.35f, iconSize - iconSize * 0.7f, getWidth() / 4 * i + (getWidth() / 4) / 2 + iconSize * 0.35f, iconSize);
            }
        }
        for (int i = 0; i < 4; i++) {
            //画四张图片
            canvas.drawBitmap(bitmaps[i], null, rectFs[i], paint);
            canvas.drawText(tabs[i], getWidth() / 4 * i + getWidth() / 4 / 2 - paint.measureText(tabs[i]) / 2, (float) (getHeight() * 0.75 + (pfi.bottom - pfi.top) / 2), paint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < 4; i++) {
                    if (touchRectFs[i].contains(event.getX(), event.getY())) {
                        if (currentIndex != i) {
                            currentIndex = i;
                            pager.setCurrentItem(currentIndex);
                            changeIcon();
                        }
                    }
                }
                break;
        }
        return true;
    }

    private void changeIcon() {
        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator largeAnimator = ValueAnimator.ofFloat(0.35f, 0.5f);
        ValueAnimator smallAnimator = ValueAnimator.ofFloat(0.5f, 0.35f);
        largeAnimator.addUpdateListener(animation -> {
            rectFs[currentIndex].left = getWidth() / 4 * currentIndex + getWidth() / 4 / 2 - iconSize * ((float) largeAnimator.getAnimatedValue());
            rectFs[currentIndex].top = iconSize - iconSize * ((float) largeAnimator.getAnimatedValue()) * 2f;
            rectFs[currentIndex].right = getWidth() / 4 * currentIndex + getWidth() / 4 / 2 + iconSize * ((float) largeAnimator.getAnimatedValue());
            invalidate();
        });
        smallAnimator.addUpdateListener(animation -> {
            rectFs[lastIndex].left = getWidth() / 4 * lastIndex + getWidth() / 4 / 2 - iconSize * ((float) smallAnimator.getAnimatedValue());
            rectFs[lastIndex].top = iconSize - iconSize * ((float) smallAnimator.getAnimatedValue()) * 2f;
            rectFs[lastIndex].right = getWidth() / 4 * lastIndex + getWidth() / 4 / 2 + iconSize * ((float) smallAnimator.getAnimatedValue());
        });
        animatorSet.setDuration(5000);
        animatorSet.play(largeAnimator).with(smallAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                lastIndex = currentIndex;
            }
        });
        animatorSet.start();
    }


    public void setUpViewPager(ViewPager pager) {
        this.pager = pager;
        pager.setOnPageChangeListener(this);
    }

    /**
     * @param position
     * @param positionOffset       滑动偏移量 介于 0-1
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LogUtil.e("BottomView onPageScrolled position = " + position + ",positionOffset = " + positionOffset);
    }

    /**
     * @param position position 确定被选择的界面的position
     *                 如果没有完成切换页面,不走该方法
     */
    @Override
    public void onPageSelected(int position) {
        currentIndex = position;
        changeIcon();
//        LogUtil.e("BottomView onPageSelected position = " + position );
    }

    /**
     * @param state 1.viewpager 开始滑动
     *              2.viewpager 松手,自己滑动过程中
     *              0.viewpager 停止滑动
     */
    @Override
    public void onPageScrollStateChanged(int state) {
//        LogUtil.e("BottomView onPageScrollStateChanged state = " + state );
    }
}
