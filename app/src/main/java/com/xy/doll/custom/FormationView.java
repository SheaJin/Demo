package com.xy.doll.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

/**
 * Created by jxy on 2018/6/6.
 */

public class FormationView extends RelativeLayout {
    private int leftColor, rightColor, titleColor;
    private Drawable leftBackground, rightBackground;
    private String title, leftText, rightText;
    private float titleSize;
    private Button leftButton, rightButton;
    private TextView titleView;
    private LayoutParams leftLayoutParams, rightLayoutParams, titlelayoutParams;
    public OnToolBarClickListener listener;

    public FormationView(Context context) {
        this(context, null);
    }

    public FormationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        TypedArray ta = context.obtainStyledAttributes(R.styleable.FormationView);
        leftColor = ta.getColor(R.styleable.FormationView_left_color, Color.parseColor("#334455"));
        rightColor = ta.getColor(R.styleable.FormationView_right_color, 0);
        titleColor = ta.getColor(R.styleable.FormationView_title_color, 0);
        leftBackground = ta.getDrawable(R.styleable.FormationView_left_background);
        rightBackground = ta.getDrawable(R.styleable.FormationView_right_background);
        title = ta.getString(R.styleable.FormationView_title_text);
        leftText = ta.getString(R.styleable.FormationView_left_text);
        rightText = ta.getString(R.styleable.FormationView_right_text);
        titleSize = ta.getDimension(R.styleable.FormationView_title_size, 20f);
        ta.recycle();

        initView(context);
    }

    private void initView(Context context) {
//        leftButton = new Button(context);
//        leftButton.setText(leftText);
//        leftButton.setTextColor(leftColor);
//        leftButton.setBackground(leftBackground);
        LogUtil.e("getChildCount1111" );
//        rightButton = new Button(context);
//        rightButton.setText(rightText);
//        rightButton.setTextColor(rightColor);
//        rightButton.setBackground(rightBackground);
        titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextColor(titleColor);
        titleView.setTextSize(titleSize);

//        leftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//        leftLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, TRUE);
//        addView(leftButton, leftLayoutParams);
//        rightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//        rightLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, TRUE);
//        addView(rightButton, rightLayoutParams);
        titlelayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titlelayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(titleView, titlelayoutParams);

        LogUtil.e("getChildCount222") ;
//        leftButton.setOnClickListener(v -> listener.onLeftClick());
//        rightButton.setOnClickListener(v -> listener.onRightClick());
    }

    public interface OnToolBarClickListener {
        void onLeftClick();

        void onRightClick();
    }

    public void setOnToolBarClickListener(OnToolBarClickListener listener) {
        this.listener = listener;
    }
}
