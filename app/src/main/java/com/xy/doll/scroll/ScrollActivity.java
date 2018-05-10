package com.xy.doll.scroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.util.Property;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;
import com.zhy.autolayout.config.AutoLayoutConifg;

import app.ui.adapter.ImageViewHolder;
import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class ScrollActivity extends BaseActivity {

    @BindView(R.id.view_left)
    View viewLeft;
    @BindView(R.id.view_right)
    View viewRight;
    @BindView(R.id.image)
    ImageView image;

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


    @OnClick(R.id.text)
    void click(View v){
        switch (v.getId()){
            case R.id.text:
                leftAnimate();
//                rightAnimate();
//                imageAnimate();
                break;
        }
    }

    private void imageAnimate() {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",5.0f,1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",5.0f,1.0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",0.1f,1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(image,scaleX,scaleY,alpha);
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

    private void rightAnimate() {
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",1f,1f);
        PropertyValuesHolder transX = PropertyValuesHolder.ofFloat("translationX",691,0);
        PropertyValuesHolder transY = PropertyValuesHolder.ofFloat("translationY",-955,0);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(viewRight,alpha,transX,transY);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageAnimate();
            }
        });
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

    private void leftAnimate() {
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha",1f,1f);
        PropertyValuesHolder transX = PropertyValuesHolder.ofFloat("translationX",-765,0);
        PropertyValuesHolder transY = PropertyValuesHolder.ofFloat("translationY",920,0);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(viewLeft,alpha,transX,transY);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                rightAnimate();
            }
        });
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
}

























