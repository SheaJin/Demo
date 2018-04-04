package app.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.xy.libs.util.app.LogUtil;

/**
 * Created by jxy on 2018/4/3.
 */

public class BadgeView extends TextView {
    private Paint paint = new Paint();
    private String textSize;
    //    private int textColor;
    private float radius;

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray a = context.obtainStyledAttributes(R.styleable.BadgeView);
//        textSize = a.getString(R.styleable.BadgeView_textSize);
//        radius = a.getFloat(R.styleable.BadgeView_radius1, 30f);
//        a.recycle();
        init();
    }

    private void init() {
        setTextColor(Color.WHITE);
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawCircle( 45, 45, 30, paint);
        paint.setTextSize(32);
        paint.setColor(Color.WHITE);
        canvas.drawText("12",42,50,paint);
    }
}
