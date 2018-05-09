package com.xy.doll.bottom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jxy on 2018/4/19.
 */

public class Custom extends View {
    private Paint paint = new Paint();

    public Custom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画点
        paint.setAntiAlias(true);
//        paint.setColor(Color.BLACK);
//        paint.setStrokeWidth(20);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(100, 100, paint);
//        paint.setStrokeCap(Paint.Cap.SQUARE);
//        canvas.drawPoint(200, 100, paint);
//        //画线
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10);
//        canvas.drawLine(0, 150, 300, 120, paint);
//        float[] pts = {50, 50, 400, 50,
//                400, 50, 400, 600,
//                400, 600, 50, 600,
//                60, 600, 50, 50};
//        canvas.drawLines(pts, paint);
//        //圆角矩形
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//        canvas.drawRoundRect(100, 800, 500, 1200, 50, 50, paint);
        /**画弧形  扇形
         * drawArc() 是使用一个椭圆来描述弧形的。
         * left, top, right, bottom 描述的是这个弧形所在的椭圆；
         * startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
         * sweepAngle 是弧形划过的角度；
         * useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
         */
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawRect(600, 100, 900, 300, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(600, 100, 900, 300, 0, 90, true, paint);
        /**
         * 画自定义图形 drawPath
         */
        /**
         * Path方法第一类  直接描述路径
         */
        //1. addXxx() ——添加子图形
        Path path = new Path();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//        //x, y, radius 这三个参数是圆的基本信息，最后一个参数 dir 是画圆的路径的方向。 CW顺时针 CCW逆时针
//        path.addCircle(200, 200, 100, Path.Direction.CW);
//        canvas.drawPath(path, paint);

        //2. xxxTo() ——画线（直线或曲线）
        /**
         * lineTo
         * 从当前位置向目标位置画一条直线， x 和 y 是目标位置的坐标。
         * 这两个方法的区别是，lineTo(x, y) 的参数是绝对坐标，
         * 而 rLineTo(x, y) 的参数是相对当前位置的相对坐标 （前缀 r 指的就是  relatively 「相对地」)。
         *  rLineTo的起始点是上次结束后的点
         * 当前位置：所谓当前位置，即最后一次调用画 Path 的方法的终点位置。初始值为原点 (0, 0)。
         */
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
//        path.lineTo(100, 100);
//        path.lineTo(100, 200);
//        path.rLineTo(200, 300);
//        canvas.drawPath(path, paint);

        /**
         * quadTo rQuadTo 二次贝塞尔曲线
         * 画贝塞尔曲线 前两个坐标代表第一个转折点，后两个坐标代表结束点
         */
//        path.moveTo(300,300);
//        path.quadTo(600, 200, 800, 500);
////        path.rQuadTo(200, 200, 200, 200);
//        canvas.drawPath(path, paint);

        /**
         * cubicTo  rCubicTo 三次贝塞尔曲线
         */

        /**
         * arcTo() 和 addArc()
         * 它们也是用来画线的，但并不使用当前位置作为弧线的起点。
         * arcTo和canvas的drawArc() 少了一个参数 useCenter，而多了一个参数 forceMoveTo
         * arcTo是用来画矩形不画扇形
         * 多出来的这个forceMoveTo 参数的意思是 绘制是要「抬一下笔移动过去」，还是「直接拖着笔过去」，区别在于是否留下移动的痕迹。
         */
        path.lineTo(100, 100);
        path.arcTo(100, 100, 300, 300, -90, 90, false);
        canvas.drawPath(path, paint);

        /**
         * close封闭图形
         */

        /**
         * Path方法第二类  辅助的设置或计算
         *
         * 方法中填入不同的 FillType 值，就会有不同的填充效果。FillType 的取值有四个：
         *  EVEN_ODD WINDING （默认值）INVERSE_EVEN_ODD  INVERSE_WINDING
         */
//        path.setFillType();


    }
}
