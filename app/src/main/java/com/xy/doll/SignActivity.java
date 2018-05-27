package com.xy.doll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xy.libs.util.app.LogUtil;
import com.xy.libs.util.normal.TextUtil;

import java.io.PrintWriter;

import app.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class SignActivity extends BaseActivity {

    @BindView(R.id.image_point)
    ImageView imagePoint;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.text_obtain)
    TextView mTvObtain;
    private double progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
    }

    @Override
    protected void initUI() {
        progress = 0.7;
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.text_obtain)
    void click() {
        animate();
    }

    private void animate() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imagePoint, "translationX", 0, TextUtil.getPx((float) (660 * progress), TextUtil.Orientation.Width));
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                runOnUiThread(() -> progressBar.setProgress((int) (progress)));
            }
        });
//        animator.addUpdateListener(animation -> {
//                    float value = (float) animation.getAnimatedValue();
//                    LogUtil.e("value = " + value + ", = " + 660 * progress / 100);
//                    progressBar.setProgress((int) (progress));
//                }
//        );
        animator.start();
    }
}
