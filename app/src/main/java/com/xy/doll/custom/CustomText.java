package com.xy.doll.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jxy on 2018/6/4.
 */

public class CustomText extends View {

    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String text = "文字文字文字文字";

    public CustomText(Context context) {
        super(context);
    }

    public CustomText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        paint1.setColor(Color.parseColor("#000000"));
        paint2.setColor(Color.parseColor("#FFFFFF"));

        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint1);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, paint2);
        paint1.setColor(Color.BLACK);
        canvas.drawLine(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight() / 2, paint1);
        canvas.drawLine(getMeasuredWidth() / 2, 0, getMeasuredWidth() / 2, getMeasuredHeight(), paint1);
        paint1.setTextSize(40f);
        canvas.drawText(text, getMeasuredWidth() / 2 - paint1.measureText(text) / 2,
                getMeasuredHeight() / 2 + (paint1.getFontMetrics().bottom - paint1.getFontMetrics().top) / 4, paint1);
        super.onDraw(canvas);

    }
}
