package com.xy.doll.animate;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xy.doll.R;

import app.ui.base.BaseActivity;
import butterknife.OnClick;

public class AnimateActivity extends BaseActivity {
    /**
     * Animation{1.属性动画 PropertyAnimation
     * 2.视图动画 ViewAnimation
     * 2.1 TweenAnimation 补间动画
     * 2.2 FrameAnimation 帧动画
     */
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void initUI() {
        /**
         * 补间动画
         *  Animation类
         *  AnimationSet：代表着一组可以一起播放的动画。
         *  AlphaAnimation：控制一个对象透明度的动画。
         *  RotateAnimation：控制一个对象旋转的动画。
         *  ScaleAnimation：控制一个对象尺寸的动画。
         *  TranslateAnimation：控制一个对象位置的动画。
         */
        /**
         * 补间动画和属性动画区别
         * 1.补间动画改变的只是view的位置，但是改变后的位置却无法响应点击事件
         * 2.补间动画只能作用于view,属性动画不止是view,还有对象
         */
    }

    @Override
    protected void initData() {
        /**
         * 属性动画
         * 1.ValueAnimator 针对值变化的Animator
         * 2.ObjectAnimator 针对Object变化的Animator
         * 3.AnimatorSet
         */
        /**
         * Evaluators
         * 1.TypeEvaluator	求值器接口，所有求值器必须实现该接口。
         * 2.IntEvaluator	计算Int类型的求值器。
         * 3.FloatEvaluator	计算Float类型的求值器。
         * 4.ArgbEvaluator	计算颜色值类型的求值器。
         */
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    void click(View view) {
        switch (view.getId()) {
            case R.id.button1:
                //scaleAnimate();
                //transAnimate();
                //alphaAnimate();
                //rotateAnimate();
                setAnimation();
                break;
            case R.id.button2:

                break;
            case R.id.button3:
//                floatAnimator();
//                intAnimator();
//                argbAnimator();
//                objectAnimator();
//                transAnimator();
//                scaleAnimator();
//                objectScaleAnimator();
                propertyValuesHolder();
                break;
        }
    }

    private void propertyValuesHolder() {
        PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("rotation", 0, 180);
        PropertyValuesHolder tranX = PropertyValuesHolder.ofFloat("translationX", 0, 200);
        PropertyValuesHolder tranY = PropertyValuesHolder.ofFloat("translationY", 0, 200);
        PropertyValuesHolder scale = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 3.0f);
        ObjectAnimator property = ObjectAnimator.ofPropertyValuesHolder(textView, rotation, tranX, tranY, scale);
        property.setDuration(3000);
        property.setInterpolator(new AccelerateInterpolator());
        property.start();
    }

    private void objectScaleAnimator() {
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(textView, "scaleX", 1.0f, 2.0f, 1.0f);
        scaleAnimator.setDuration(2000);
        scaleAnimator.start();
    }

    private void scaleAnimator() {
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1.0f, 2.0f);
        scaleAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            textView.setScaleX(value);
            textView.setScaleY(value);
        });
        scaleAnimator.setDuration(2000);
        scaleAnimator.start();
    }

    private void transAnimator() {
        ValueAnimator transAnimator = ValueAnimator.ofFloat(0, 300, -100, 200);
        transAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            textView.setTranslationX(value);
        });
        transAnimator.setDuration(2000);
        transAnimator.start();
    }

    private void objectAnimator() {
        ValueObject object1 = new ValueObject(1.0f, 1.0f);
        ValueObject object2 = new ValueObject(0.5f, 2.0f);
        MyEvaluator myEvaluator = new MyEvaluator();
        ValueAnimator objectAnimator = ValueAnimator.ofObject(myEvaluator, object1, object2);
        objectAnimator.addUpdateListener(animation -> {
            ValueObject value = (ValueObject) animation.getAnimatedValue();
            imageView.setAlpha(value.alphaValue);
            imageView.setScaleX(value.scaleValue);
            imageView.setScaleY(value.scaleValue);
        });
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    class ValueObject {
        private float alphaValue;   //透明度的值
        private float scaleValue;   //伸缩变化的值

        public ValueObject(float alphaValue, float scaleValue) {
            this.alphaValue = alphaValue;
            this.scaleValue = scaleValue;
        }
    }

    class MyEvaluator implements TypeEvaluator<ValueObject> {
        @Override
        public ValueObject evaluate(float fraction, ValueObject startValue, ValueObject endValue) {
            // 计算某个时刻的alpha值和scale值。类似速度公式Vt = V0 + at
            float nowAlphaValue = startValue.alphaValue + (endValue.alphaValue - startValue.alphaValue) * fraction;
            float nowScaleValue = startValue.scaleValue + (endValue.scaleValue - startValue.scaleValue) * fraction;
            return new ValueObject(nowAlphaValue, nowScaleValue);
        }
    }

    private void argbAnimator() {
        ValueAnimator argbAnimator = ValueAnimator.ofArgb(0xffff0000, 0xff00ff00, 0xff0000ff);
        argbAnimator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            imageView.setBackgroundColor(value);
        });
        argbAnimator.setEvaluator(new ArgbEvaluator());
        argbAnimator.setDuration(3000);
        argbAnimator.start();
    }

    private void intAnimator() {
        ValueAnimator textAnimator = ValueAnimator.ofInt(0, 399);
        textAnimator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            textView.setText(String.valueOf(value));
        });
        textAnimator.setDuration(1000);
        textAnimator.start();
    }

    private void floatAnimator() {
        ValueAnimator alphaAnimator = ValueAnimator.ofFloat(1, 0.3f);
        alphaAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            imageView.setAlpha(value);
        });
        alphaAnimator.setDuration(1000);
        alphaAnimator.start();
    }

    //========================

    private void setAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RESTART, 0.5f, Animation.RESTART, 0.5f);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 500, 0, 500);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setFillAfter(true);
        animationSet.setDuration(2000);
        animationSet.setInterpolator(new BounceInterpolator());
        imageView.startAnimation(animationSet);
    }

    private void rotateAnimate() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(1000);
        imageView.startAnimation(rotateAnimation);
    }

    private void alphaAnimate() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);
        imageView.startAnimation(alphaAnimation);
    }

    private void transAnimate() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                0, 500, 0, 500);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(1000);
        imageView.startAnimation(translateAnimation);
    }

    private void scaleAnimate() {
       /*
        android:fromXScale：动画开始前在X坐标的大小。
        android:fromYScale：动画开始前在Y坐标的大小。
        android:toXScale：动画结束后在X坐标的大小。
        android:toYScale：动画结束后在Y坐标的大小。
        android:pivotX：缩放中心点的X坐标。       50%为中心点
        android:pivotY：缩放中心点的Y坐标。
        */
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.5f, 1.0f, 1.5f,
                Animation.RESTART, 0.5f,
                Animation.RESTART, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setDuration(1000);
        imageView.startAnimation(scaleAnimation);
    }

}
