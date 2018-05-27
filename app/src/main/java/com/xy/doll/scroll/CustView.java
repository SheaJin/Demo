package com.xy.doll.scroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jxy on 2018/5/20.
 */
public class CustView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path = new Path();

    public CustView(Context context) {
        super(context);
    }

    public CustView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(100, 100, 700, 500, -135, 135, true, paint);
//
//        canvas.drawArc(100, 100, 700, 500, 90, 135, false, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

//        canvas.drawBitmap(new Bitmap(),new Rect(),new RectF(),paint);
//        path.lineTo(100, 100);
//        path.arcTo(100, 100, 300, 300, 0, 90, true); // 强制移动到弧形起点（无痕迹）
//        canvas.drawPath(path, paint);

//        paint.setStrokeWidth(5);
//        canvas.drawRect(0, 0, 500, 500, paint);
    }
}
