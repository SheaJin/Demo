package com.xy.doll.bottom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xy.doll.DollApplication;
import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;
import com.xy.libs.util.normal.TextUtil;
import com.xy.libs.util.normal.ToastUtil;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by jxy on 2018/4/18.
 */

public class BottomView extends View {
    private Paint paint = new Paint();
    private RectF[] touchRectFS;
    private RectF[] rectFs;
    private int color[] = {getResources().getColor(R.color.theme), getResources().getColor(R.color.black_15), getResources().getColor(R.color.drawbrilliant), getResources().getColor(R.color.text_green), getResources().getColor(R.color.blue)};
    private Bitmap[] bitmaps = {BitmapFactory.decodeResource(getResources(), R.mipmap.zww),
            BitmapFactory.decodeResource(getResources(), R.mipmap.gh),
            BitmapFactory.decodeResource(getResources(), R.mipmap.pk),
            BitmapFactory.decodeResource(getResources(), R.mipmap.chat),
            BitmapFactory.decodeResource(getResources(), R.mipmap.gr)};
    private String[] strs = {"抓娃娃", "工会", "PK", "消息", "个人"};
    private int index;
    private Paint.FontMetricsInt pfi;
    private double distance;

    public BottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(AutoLayoutConifg.getInstance().getScreenWidth(), TextUtil.getPx(300, TextUtil.Orientation.Height));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        touchRectFS = new RectF[5];
        rectFs = new RectF[5];
        //画圆
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(TextUtil.getPx(2, TextUtil.Orientation.Width));
        paint.setColor(getResources().getColor(R.color.color_grey));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, TextUtil.getPx(129.5f, TextUtil.Orientation.Width), paint);
        //画背景矩形
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.black_50));
        canvas.drawRect(0, getHeight() * 0.3f, getWidth(), getHeight(), paint);
        for (int i = 0; i < 5; i++) {
            //画触摸区域
            paint = new Paint();
            paint.setColor(color[i]);
            paint.setColor(getResources().getColor(R.color.white_255));
            touchRectFS[i] = new RectF((getWidth() / 5) * i, getHeight() * 0.3f, (getWidth() / 5) * (i + 1), getHeight());
            canvas.drawRect(touchRectFS[i], paint);
        }
        //画图片
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.black_255));
        rectFs[0] = new RectF((getWidth() / 5) * 0 + TextUtil.getPx(80, TextUtil.Orientation.Width), getHeight() * 0.40f, (getWidth() / 5) * 1 - TextUtil.getPx(80, TextUtil.Orientation.Width), getHeight() * 0.7f);
        rectFs[1] = new RectF((getWidth() / 5) * 1 + TextUtil.getPx(80, TextUtil.Orientation.Width), getHeight() * 0.40f, (getWidth() / 5) * 2 - TextUtil.getPx(80, TextUtil.Orientation.Width), getHeight() * 0.7f);
        rectFs[2] = new RectF((getWidth() / 5) * 2 + TextUtil.getPx(50, TextUtil.Orientation.Width), getHeight() * 0.15f, (getWidth() / 5) * 3 - TextUtil.getPx(60, TextUtil.Orientation.Width), getHeight() * 0.7f);
        rectFs[3] = new RectF((getWidth() / 5) * 3 + TextUtil.getPx(80, TextUtil.Orientation.Width), getHeight() * 0.40f, (getWidth() / 5) * 4 - TextUtil.getPx(80, TextUtil.Orientation.Width), getHeight() * 0.7f);
        rectFs[4] = new RectF((getWidth() / 5) * 4 + TextUtil.getPx(80, TextUtil.Orientation.Width), getHeight() * 0.40f, (getWidth() / 5) * 5 - TextUtil.getPx(80, TextUtil.Orientation.Width), getHeight() * 0.7f);
        paint.setColor(getResources().getColor(R.color.white_255));
        for (int i = 0; i < 5; i++) {
            canvas.drawBitmap(bitmaps[i], null, rectFs[i], paint);
        }
        //画文字
        paint = new Paint();
        paint.setAntiAlias(true);
        pfi = paint.getFontMetricsInt();
        paint.setTextSize(TextUtil.getPx(36, TextUtil.Orientation.Width));
        paint.setColor(getResources().getColor(R.color.text_color));
        for (int i = 0; i < 5; i++) {
            canvas.drawText(strs[i], (getWidth() / 5) * i + (getWidth() / 5) / 2 - paint.measureText(strs[i]) / 2, (float) (getHeight() * 0.85 + (pfi.bottom - pfi.top) / 2), paint);
        }
        //画线
        distance = Math.sqrt(Math.pow(TextUtil.getPx(129.5f, TextUtil.Orientation.Width), 2.0) - Math.pow(getHeight() * 0.2, 2.0));
        LogUtil.e("distance = " + distance);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.color_grey));
        canvas.drawLine(0, getHeight() * 0.3f, (float) (getWidth() / 2 - distance), getHeight() * 0.3f, paint);
        canvas.drawLine(getWidth() / 2 + (float) distance, getHeight() * 0.3f, getWidth(), getHeight() * 0.3f, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < 5; i++) {
                    if (touchRectFS[i].contains(event.getX(), event.getY())) {
                        ToastUtil.show(DollApplication.getInstance(), "position = " + i);
                    }
                }
                break;
        }
        return true;
    }
}
