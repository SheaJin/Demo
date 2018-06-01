package com.xy.doll.sign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xy.doll.R;
import com.xy.libs.util.normal.TextUtil;

/**
 * Created by jxy on 2018/5/28.
 */
@SuppressLint("DrawAllocation")
public class SignView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap pointBitmap;
    private RectF bgRectF, bitmapRect;

    public SignView(Context context) {
        this(context, null);
    }

    public SignView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        pointBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.signin_zuobiao);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(TextUtil.getPx(684, TextUtil.Orientation.Width), TextUtil.getPx(72, TextUtil.Orientation.Height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(R.color.homepage_color));
        bgRectF = new RectF(TextUtil.getPx(12, TextUtil.Orientation.Width), TextUtil.getPx(60, TextUtil.Orientation.Height), getWidth() - TextUtil.getPx(12, TextUtil.Orientation.Width), TextUtil.getPx(72, TextUtil.Orientation.Height));
        canvas.drawRoundRect(bgRectF, 60, 60, paint);
        bitmapRect = new RectF(TextUtil.getPx(0, TextUtil.Orientation.Width), TextUtil.getPx(0, TextUtil.Orientation.Height), TextUtil.getPx(24, TextUtil.Orientation.Width), TextUtil.getPx(60, TextUtil.Orientation.Height));
        canvas.drawBitmap(pointBitmap, null, bitmapRect, paint);

    }
}
