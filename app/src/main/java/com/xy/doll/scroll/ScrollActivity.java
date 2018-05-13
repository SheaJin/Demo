package com.xy.doll.scroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
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
    @BindView(R.id.point1)
    ImageView point1;
    @BindView(R.id.point2)
    ImageView point2;
    @BindView(R.id.point3)
    ImageView point3;

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
//                pointAnimate();
                leftAnimate();
//                rightAnimate();
//                imageAnimate();
                break;
        }
    }

    private void pointAnimate() {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",1.4f,1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",1.4f,1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(point1,scaleX,scaleY);
//        animator.setDuration(300);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateInterpolator());
        PropertyValuesHolder scaleX1 = PropertyValuesHolder.ofFloat("scaleX",1.4f,1.0f);
        PropertyValuesHolder scaleY1 = PropertyValuesHolder.ofFloat("scaleY",1.4f,1.0f);
        ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(point2,scaleX1,scaleY1);
//        animator1.setDuration(300);
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.setInterpolator(new AccelerateInterpolator());
        PropertyValuesHolder scaleX2 = PropertyValuesHolder.ofFloat("scaleX",1.4f,1.0f);
        PropertyValuesHolder scaleY2 = PropertyValuesHolder.ofFloat("scaleY",1.4f,1.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(point3,scaleX2,scaleY2);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setInterpolator(new AccelerateInterpolator());
        AnimatorSet set = new AnimatorSet();
//        set.play(animator).after(100).before(animator1).after(100).before(animator2);
//        set.playTogether(animator,animator1,animator2);
        set.play(animator1).after(animator);
        set.setDuration(300);
        set.start();
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
        PropertyValuesHolder transX = PropertyValuesHolder.ofFloat("translationX",920,0);
        PropertyValuesHolder transY = PropertyValuesHolder.ofFloat("translationY",-659,0);
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
        PropertyValuesHolder transY = PropertyValuesHolder.ofFloat("translationY",513,0);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

























