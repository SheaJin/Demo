package com.xy.doll.bottom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xy.doll.R;
import com.xy.libs.util.normal.TextUtil;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by jxy on 2018/4/18.
 */

public class BottomView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF[] touchRectFS = new RectF[5];
    private RectF[] rectFs = new RectF[5];
    private Bitmap[] bitmaps = {BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_tab_normal_1),
            BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_tab_normal_2),
            BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_normal_5),
            BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_tab_normal_3),
            BitmapFactory.decodeResource(getResources(), R.mipmap.bottom_tab_normal_4)};
    private String[] strs = {"抓娃娃", "工会", "PK", "消息", "个人"};
    private Paint.FontMetricsInt pfi;
    private int currentIndex;
    private float iconSize = TextUtil.getPx(90, TextUtil.Orientation.Width);
    private Point point = new Point(getWidth() / 5, TextUtil.getPx(195, TextUtil.Orientation.Height));

    public BottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(AutoLayoutConifg.getInstance().getScreenWidth(), TextUtil.getPx(195, TextUtil.Orientation.Height));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画触摸区域
        for (int i = 0; i < 5; i++) {
            paint.setColor(getResources().getColor(R.color.white_255));
            touchRectFS[i] = new RectF(getWidth() / 5 * i, getHeight() * 0.05f, (getWidth() / 5) * (i + 1), point.y);
            canvas.drawRect(touchRectFS[i], paint);
            //画图片
            if (i == currentIndex) {
                rectFs[i] = new RectF((getWidth() / 5) * i + getWidth() / 5 / 2 - iconSize / 2 - TextUtil.getPx(18, TextUtil.Orientation.Width), TextUtil.getPx(0, TextUtil.Orientation.Width),
                        (getWidth() / 5) * i + getWidth() / 5 / 2 + iconSize / 2 + TextUtil.getPx(18, TextUtil.Orientation.Width), TextUtil.getPx(122, TextUtil.Orientation.Width));
            } else {
                rectFs[i] = new RectF((getWidth() / 5) * i + getWidth() / 5 / 2 - iconSize / 2, TextUtil.getPx(23, TextUtil.Orientation.Width), (getWidth() / 5) * i + getWidth() / 5 / 2 + iconSize / 2, TextUtil.getPx(113, TextUtil.Orientation.Width));
            }
            canvas.drawBitmap(bitmaps[i], null, rectFs[i], paint);
        }
        //画文字
        pfi = paint.getFontMetricsInt();
        paint.setTextSize(TextUtil.getPx(36, TextUtil.Orientation.Width));
        paint.setColor(getResources().getColor(R.color.theme));
        for (int i = 0; i < 5; i++) {
            canvas.drawText(strs[i], (getWidth() / 5) * i + (getWidth() / 5) / 2 - paint.measureText(strs[i]) / 2, (float) (getHeight() * 0.85 + (pfi.bottom - pfi.top) / 2), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < 5; i++) {
                    if (touchRectFS[i].contains(event.getX(), event.getY())) {
                        currentIndex = i;
                        change();
                    }
                }
                break;
        }
        return true;
    }

    private void change() {

    }
}
