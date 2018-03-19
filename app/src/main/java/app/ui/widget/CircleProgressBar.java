package app.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xy.doll.R;


/**
 * Created by jxy on 2018/3/14.
 */

public class CircleProgressBar extends View {
    //当前进度
    private int currentProgress;
    //半径
    private float radius;
    //圆环宽度
    private float strokeWidth;
    //圆环颜色
    private int progressColor;
    //背景颜色
    private int backgroundColor;
    //文字颜色
    private int textColor;
    //文字长度
    private float textWidth;
    //文字高度
    private float textHeight;
    //总进度
    private int totalProgress = 100;
    //画圆环画笔
    private Paint ringPaint;
    //画背景画笔
    private Paint bgPaint;
    //画文字画笔
    private Paint textPaint;

    private Paint whitePaint;
    private int alpha = 25;

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypedArray(context, attrs);
        init();
    }

    public void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleProgressbar, 0, 0);
        currentProgress = a.getInteger(R.styleable.CircleProgressbar_progress, 0);
        radius = a.getDimension(R.styleable.CircleProgressbar_radius, 30);
        strokeWidth = a.getDimension(R.styleable.CircleProgressbar_strokeWidth, 10);
        progressColor = a.getColor(R.styleable.CircleProgressbar_progressColor, 0x00FF00);
        backgroundColor = a.getColor(R.styleable.CircleProgressbar_backgroundColor, 0x22FFFFFF);
        textColor = a.getColor(R.styleable.CircleProgressbar_textColor, 0xFFFFFF);
        a.recycle();
    }

    public void init() {
        //当前进度
        ringPaint = new Paint();
        ringPaint.setAntiAlias(true);
        ringPaint.setDither(true);
        ringPaint.setColor(progressColor);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeWidth(strokeWidth);
        //总进度
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setDither(true);
        bgPaint.setColor(backgroundColor);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(strokeWidth);
        //文字
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);
        textPaint.setTextSize(radius / 2);
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        textHeight = fm.descent + Math.abs(fm.ascent);
        //白色线
        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setColor(Color.parseColor("#000000"));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentProgress >= 0) {
            //画背景
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, 180, bgPaint);
            //画圆弧
            RectF oval = new RectF(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);
            canvas.drawArc(oval, 0, 0, false, ringPaint);
            canvas.drawArc(oval, -90, ((float) currentProgress / totalProgress) * 360, false, ringPaint);
            //文字
            String txt = currentProgress + "%";
            textWidth = textPaint.measureText(txt, 0, txt.length());
            canvas.drawText(txt, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 4, textPaint);
            //画白弧
            canvas.drawArc(getWidth() / 2 - radius , getHeight() / 2 - radius ,
                    getWidth() / 2 + radius , getHeight() / 2 + radius ,
                    ((float) currentProgress / totalProgress) * 360 - 95f, 10f, false, whitePaint);
        }
    }

    public void setProgress(int progress) {
        currentProgress = progress;
        postInvalidate();
    }
}
