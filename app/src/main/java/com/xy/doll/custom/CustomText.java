package com.xy.doll.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jxy on 2018/6/4.
 */

public class CustomText extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    String text = "abcdefghigklmn";
    String text1 = "Android Shader TextView";
    Paint.FontMetricsInt metrics = paint1.getFontMetricsInt();
    int mViewWidth = 0;
    int mTranslate = 0;
    Shader shader;
    Matrix matrix = new Matrix();

    public CustomText(Context context) {
        super(context);
    }

    public CustomText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(800, 100);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                shader = new LinearGradient(0, 0, paint1.measureText(text1, 0, text1.length()), (paint1.descent() - paint1.ascent()),
                        new int[]{Color.parseColor("#E91E63"),
                                Color.parseColor("#2196F3")}, null, Shader.TileMode.CLAMP);
                paint1.setShader(shader);
            }
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawShaderText(canvas);
    }

    private void drawShaderText(Canvas canvas) {
        /**
         * bound.height()获取的是最真实的文字高度
         * paint1.descent() - paint1.ascent() 和paint1.getFontSpacing()
         * 和paint1.getFontMetrics().descent - paint1.getFontMetrics().ascent 值都是相等的
         */
        paint1.setTextSize(60f);
        Rect bound = new Rect();
        paint1.getTextBounds(text1, 0, text1.length(), bound);
        if (matrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            matrix.setTranslate(mTranslate, 0);
            shader.setLocalMatrix(matrix);
            postInvalidateDelayed(100);
        }
        canvas.drawText(text1, 0, paint1.descent() - paint1.ascent(), paint1);
    }


    private void drawNormalText(Canvas canvas) {
        paint1.setColor(Color.parseColor("#000000"));
        paint2.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint1);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, paint2);
        paint1.setColor(Color.BLACK);
        canvas.drawLine(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight() / 2, paint1);
        canvas.drawLine(getMeasuredWidth() / 2, 0, getMeasuredWidth() / 2, getMeasuredHeight(), paint1);
        paint1.setTextSize(50f);
        canvas.drawText(text, getMeasuredWidth() / 2 - paint1.measureText(text) / 2,
                getMeasuredHeight() / 2 + (metrics.descent - metrics.ascent) / 2, paint1);
        Log.e("tag", metrics.bottom + "," + metrics.top + "," + metrics.descent + "," + metrics.ascent);
    }
}
