package app.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.xy.doll.R;

/**
 * Created by jxy on 2018/4/3.
 */

public class BubbleView extends TextView {
    private String text;
    private float textsize;
    private Paint paint = new Paint();

    public BubbleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BubbleView);
//        this.text = array.getString(R.styleable.BubbleView_text);
//        this.textsize = array.getDimension(R.styleable.BubbleView_textSize,16f);

        init(context);
    }

    private void init(Context context) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawCircle(40, 40, 30, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(32f);
        canvas.drawText(text, 35, 45, paint);
    }
}
