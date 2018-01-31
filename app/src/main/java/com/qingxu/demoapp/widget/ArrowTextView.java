package com.qingxu.demoapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.qingxu.demoapp.R;


/**
 * Created by jxy on 2018/1/19.
 */

//@SuppressLint("AppCompatCustomView")
public class ArrowTextView extends TextView {
    private float radius;
    private float arrowWidth;
    private float arrowInWidth;
    private int color;

    public ArrowTextView(Context context) {
        super(context);
    }

    public ArrowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ArrowTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ArrowTextView);
        radius = array.getDimension(R.styleable.ArrowTextView_radius, 0);
        arrowWidth = array.getDimension(R.styleable.ArrowTextView_arrowWidth, 0);
        arrowInWidth = array.getDimension(R.styleable.ArrowTextView_arrowInHeight, 0);
        color = array.getColor(R.styleable.ArrowTextView_bg, Color.BLACK);
    }

    /**
     * arrowWidth 三角形箭头宽度
     */
    public void setArrowWidth(float width) {
        this.arrowWidth = width;
        invalidate();
    }

    /**
     * arrowInWidth 三角形箭头在此宽度居中
     */
    public void setArrowInHeight(float arrowInWidth) {
        this.arrowInWidth = arrowInWidth;
        invalidate();
    }

    /**
     * 矩形四角圆角的半径
     */
    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    /**
     * 箭头矩形的背景色
     */
    public void setBgColor(int color) {
        this.color = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        if (radius == 0) {
            radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        }
        if (arrowWidth == 0) {
            arrowWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        }
        //画矩形
        int left = (int) (getPaddingLeft() + arrowWidth);
        int height = getHeight();
        int width = getWidth();
        canvas.drawRoundRect(left, 0, width, height, radius, radius, paint);

        if (arrowInWidth == 0) {
            arrowWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        }

        //画三角形
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        float xMiddle = width / 2;
        float xTop = xMiddle + (arrowWidth / 2);
        float xBottom = xMiddle + (arrowWidth / 2);
        path.moveTo(xMiddle, width + arrowWidth);
        path.lineTo(left, xTop);
        path.lineTo(left, xBottom);
        path.lineTo(xMiddle, arrowWidth /2);
        path.close();
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }


//        if (arrowInHeight == 0) {
//            arrowInHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
//        }
//        width = (int) (width > arrowWidth ? arrowWidth : width);
////        height = (int) (height > arrowInHeight ? arrowInHeight : height);
//        //画三角形
//        Path path = new Path();
//        path.setFillType(Path.FillType.EVEN_ODD);
//        float xMiddle = width / 2;
//        float xTop = xMiddle - (arrowWidth / 2);
//        float xBottom = xMiddle + (arrowWidth / 2);
//        path.moveTo(0, xMiddle);
//        path.lineTo(left, xTop);
//        path.lineTo(left, xBottom);
//        path.lineTo(0, xMiddle);
//        path.close();
//        canvas.drawPath(path, paint);
//        // canvas.restore();
//        // canvas.translate(left, 0);
//        super.onDraw(canvas);
//
//    }
}
