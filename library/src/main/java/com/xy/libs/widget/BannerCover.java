package com.xy.libs.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.xy.libs.util.normal.TextUtil;

/**
 * Created by chaohe on 2017/12/16.
 */

public class BannerCover extends View {
    private Paint paint;

    public BannerCover(Context context) {
        super(context);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(Math.min(TextUtil.getPx(40, TextUtil.Orientation.Width), TextUtil.getPx(40, TextUtil.Orientation.Height)));
    }

    public BannerCover(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(Math.min(TextUtil.getPx(40, TextUtil.Orientation.Width), TextUtil.getPx(40, TextUtil.Orientation.Height)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null) {
            canvas.drawRoundRect(TextUtil.getPx(20, TextUtil.Orientation.Width), TextUtil.getPx(20, TextUtil.Orientation.Height),
                    getWidth() - TextUtil.getPx(20, TextUtil.Orientation.Width), getHeight() - TextUtil.getPx(20, TextUtil.Orientation.Height),
                    Math.min(TextUtil.getPx(50, TextUtil.Orientation.Width), TextUtil.getPx(50, TextUtil.Orientation.Height)),
                    Math.min(TextUtil.getPx(50, TextUtil.Orientation.Width), TextUtil.getPx(50, TextUtil.Orientation.Height)), paint);
        }
        super.onDraw(canvas);
    }
}
