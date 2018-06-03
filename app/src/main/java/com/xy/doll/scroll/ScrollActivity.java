package com.xy.doll.scroll;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xy.doll.R;

import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class ScrollActivity extends BaseActivity {
    @BindView(R.id.text)
    TextView textView;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button)
    void click(View v){
        switch (v.getId()){
            case R.id.button:
                pointAnimate();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void pointAnimate() {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView, "translationY", 0, -textView.getHeight());
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(textView, "translationY", textView.getHeight(), 0);
//        animator1.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                int text = Integer.parseInt(textView.getText().toString());
//                if (text < 9) {
//                    textView.setText("0" + (text + 1));
//                } else {
//                    textView.setText("" + (text + 1));
//                }
//            }
//        });
        set.play(animator2).after(animator1);
//        set.playTogether(animator1,animator2);
        set.setDuration(1000);
        set.start();
    }

}