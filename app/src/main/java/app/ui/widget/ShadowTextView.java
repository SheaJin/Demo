package app.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xy.doll.R;
import java.lang.reflect.Field;

/**
 * Created by jxy on 2018/3/5.
 */

public class ShadowTextView extends TextView {

    private TextPaint mPaint;
    private int mInnerColor;
    private int mOuterColor;
    private boolean isDrawSideLine = true; // 默认采用描边

    public ShadowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = this.getPaint();
        //获取自定义的XML属性名称
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShadowTextView);
        //获取对应的属性值
        this.mInnerColor = a.getColor(R.styleable.ShadowTextView_innnerColor, 0xffffff);
        this.mOuterColor = a.getColor(R.styleable.ShadowTextView_outerColor, 0xffffff);
        a.recycle();
    }

    public ShadowTextView(Context context, int outerColor, int innerColor) {
        super(context);
        mPaint = this.getPaint();
        this.mInnerColor = innerColor;
        this.mOuterColor = outerColor;
    }

    public ShadowTextView(Context context, AttributeSet attrs, int defStyle, int outerColor, int innerColor) {
        super(context, attrs, defStyle);
        mPaint = this.getPaint();
        this.mInnerColor = innerColor;
        this.mOuterColor = outerColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isDrawSideLine) {
            // 描外层
            // super.setTextColor(Color.BLUE); // 不能直接这么设，如此会导致递归
            setTextColorUseReflection(mOuterColor);
            mPaint.setStrokeWidth(5); // 描边宽度
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE); // 描边种类
            mPaint.setFakeBoldText(true); // 外层text采用粗体
            mPaint.setShadowLayer(1, 0, 0, 0); // 字体的阴影效果，可以忽略
            super.onDraw(canvas);

            // 描内层，恢复原先的画笔
            // super.setTextColor(Color.BLUE); // 不能直接这么设，如此会导致递归
            setTextColorUseReflection(mInnerColor);
            mPaint.setStrokeWidth(0);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setFakeBoldText(false);
            mPaint.setShadowLayer(0, 0, 0, 0);

        }
        super.onDraw(canvas);
    }

    /**
     * 使用反射的方法进行字体颜色的设置
     *
     * @param color
     */
    private void setTextColorUseReflection(int color) {
        Field textColorField;
        try {
            textColorField = TextView.class.getDeclaredField("mCurTextColor");
            textColorField.setAccessible(true);
            textColorField.set(this, color);
            textColorField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mPaint.setColor(color);
    }
}
