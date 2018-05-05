package is.hello.go99;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import is.hello.go99.animators.AnimatorContext;
import is.hello.go99.animators.AnimatorTemplate;
import is.hello.go99.animators.MultiAnimator;
import is.hello.go99.animators.OnAnimationCompleted;
import is.hello.go99.animators.Rotatable;

/**
 * Created on 17/1/17 上午11:24.
 * @author: this util author by TianZhen ,if you have any problem that send google Email: ryantianzhen@gmail.com
 * @description: AnimationTools 是一个动画的封装工具，部分效果只支持 LOLLIPOP（LeveL>=21）以上，使用方法非常简单，
 *               只需调用getInstance方法，当然为了避免重复创建AnimatorContext对象耗费内存，你可以利用此方法事先检查isInited，
 *               本类会自动完成初始化以及一些默认设置，本类提供了一个动画播放的监听，开发者可根据需要或设定为null，
 *               下面枚举了一些插值器供开发者参考。
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AnimationTools {

    private final int DEFAULT_DURATION = 1500;
    private static volatile AnimationTools instance;
    private AnimatorContext animatorContext;
    private Rotatable rotatable;
    private AnimatorTemplate animatorTemplate;
    private TimeInterpolator interpolator;
    public enum InterpolatorTZ {
        ACCELERATE_DECELERATE,
        ACCELERATE,
        ANTICIPATE,
        ANTICIPATE_OVERSHOOT,
        BOUNCE,
        CYCLE,
        DECELERATE,
        LINEAR,
        OVERSHOOT
    }

    public static AnimationTools getInstance() {
        if(instance == null) {
            Class var0 = AnimationTools.class;
            synchronized(AnimationTools.class) {
                if(instance == null) {
                    instance = new AnimationTools();
                }
            }
        }
        return instance;
    }

    protected AnimationTools() {
    }

    public boolean isInited() {
        return this.animatorContext != null;
    }

    public boolean checkConfiguration(){
        if(animatorTemplate == null){
            throw new IllegalStateException("AnimationTools must be init with configuration before using");
        }
        return true;
    }

    /***
     * AnimationTools must be init with configuration before using
     ****/
    public synchronized AnimationTools init() {
        return init(this.getClass().getSimpleName(), false);
    }

    public synchronized AnimationTools init(String tag) {
        return init(tag, false);
    }

    public synchronized AnimationTools init(String tag, boolean enableLongAnimations) {
        if (tag == null || tag.equals("")) {
            tag = "AnimatorContext";
        }
        if(this.animatorContext==null){
            AnimatorContext animatorContext = new AnimatorContext(tag);
            animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
            this.animatorContext = animatorContext;
        }
        if (enableLongAnimations) {
            setEnableLongAnimations(true);
        }
        return getInstance();
    }

    /**
     * configuration 1
     * **/
    public void setEnableLongAnimations(boolean enableLongAnimations) {
        try {
            if(animatorContext==null){
                throw new IllegalArgumentException("-1");
            }
            final AnimatorTemplate oldTemplate = animatorContext.getTransactionTemplate();
            if (enableLongAnimations) {
                animatorTemplate = oldTemplate.withDuration(oldTemplate.duration * 2L);//Long
            } else {
                animatorTemplate = oldTemplate.withDuration(oldTemplate.duration / 2L);//Short
            }
            animatorContext.setTransactionTemplate(animatorTemplate);
        } catch (IllegalArgumentException e) {
            Log.e("AnimationTools","setEnableLongAnimations animatorContext can not be initialized with null");
            e.printStackTrace();
        }

    }

    /**
     * configuration 2
     * **/
    public AnimationTools setInterpolator(InterpolatorTZ var0) {
        this.interpolator = chooseInterpolator(var0);
        return this;
    }

    public TimeInterpolator chooseInterpolator(InterpolatorTZ var0) {
        if(var0==null){
            throw new IllegalArgumentException("AnimationTools setInterpolator config fail, can not be initialized with null");
        }
        TimeInterpolator mInterpolator = null;
        switch(var0){
            case ACCELERATE_DECELERATE:
                //在动画开始与结束的地方速率改变比较慢，在中间的时候加速
                mInterpolator = new AccelerateDecelerateInterpolator();
                break;
            case ACCELERATE:
                //在动画开始的地方速率改变比较慢，然后开始加速
                mInterpolator = new AccelerateInterpolator();
                break;
            case ANTICIPATE:
                //开始的时候向后然后向前甩
                mInterpolator = new AnticipateInterpolator();
                break;
            case ANTICIPATE_OVERSHOOT:
                //开始的时候向后然后向前甩一定值后返回最后的值
                mInterpolator = new AnticipateOvershootInterpolator();
                break;
            case BOUNCE:
                //动画结束的时候弹起
                mInterpolator = new BounceInterpolator();
                break;
            case CYCLE:
                //动画循环播放特定的次数，速率改变沿着正弦曲线
                mInterpolator = new CycleInterpolator(1.0f);
                break;
            case DECELERATE:
                //在动画开始的地方快然后慢
                mInterpolator = new DecelerateInterpolator();
                break;
            case LINEAR:
                //以常量速率改变
                mInterpolator = new LinearInterpolator();
                break;
            case OVERSHOOT:
                //向前甩一定值后再回到原来位置
                mInterpolator = new OvershootInterpolator();
                break;
        }
        return mInterpolator;
    }

    /**
     * 1  scale hide
     */
    public void hideScaleAnimation(final View mView){
        if(mView.isShown())
        hideScaleAnimation(mView,this.interpolator == null ? new LinearInterpolator() : this.interpolator,null);
    }

    /**
     * 1  scale hide
     */
    public void hideScaleAnimation(final View mView, @NonNull TimeInterpolator interpolator, final AnimationsListener animationsListener) {
        if (mView.isShown()) {
            if(this.animatorContext == null){
                AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
                animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
                this.animatorContext = animatorContext;
            }
            MultiAnimator.animatorFor(mView, this.animatorContext).withInterpolator(interpolator)
                    .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                        @Override
                        public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                            if(animationsListener!=null){
                                animationsListener.onAnimationStart();
                            }
                        }
                    })
                    .addOnAnimationCompleted(new OnAnimationCompleted() {
                        @Override
                        public void onAnimationCompleted(boolean finished) {
                            if (finished) {
                                if(animationsListener!=null){
                                    animationsListener.onAnimationCompleted();
                                }
                                mView.setVisibility(View.GONE);
                                mView.setScaleX(1f);
                                mView.setScaleY(1f);
                                mView.setAlpha(1f);
                            }
                        }
                    })
                    .alpha(0f)
                    .scale(0f)
                    .start();
        }
    }

    /**
     * 1  scale show
     */
    public void showScaleAnimation(final View mView) {
        if(mView.isShown())
            return;
        showScaleAnimation(mView,this.interpolator == null ? new LinearInterpolator() : this.interpolator,null);
    }

    /**
     * 1  scale show
     */
    public void showScaleAnimation(final View mView, @NonNull TimeInterpolator interpolator,final AnimationsListener animationsListener) {
        if (mView.isShown())
            return;
        if(this.animatorContext == null){
            AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
            animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
            this.animatorContext = animatorContext;
        }
        MultiAnimator.animatorFor(mView, animatorContext).withInterpolator(interpolator)
                .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                    @Override
                    public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                        if(animationsListener!=null){
                            animationsListener.onAnimationStart();
                        }
                        mView.setAlpha(0f);
                        mView.setScaleX(0f);
                        mView.setScaleY(0f);
                        mView.setVisibility(View.VISIBLE);
                    }
                })
                .addOnAnimationCompleted(new OnAnimationCompleted() {
                    @Override
                    public void onAnimationCompleted(boolean finished) {
                        if(finished && animationsListener!=null){
                            animationsListener.onAnimationCompleted();
                        }
                    }
                })
                .alpha(1f)
                .scale(1f)
                .start();
    }

    /**
     * 2 trans show
     */
    public void showTransAnimation(final View mView) {
        if (mView.isShown())
            return;
        showTransAnimation(mView,this.interpolator == null ? new LinearInterpolator() : this.interpolator,null);
    }

    /**
     * 2 trans show
     */
    public void showTransAnimation(final View mView,@NonNull TimeInterpolator interpolator,final AnimationsListener animationsListener) {
        if (mView.isShown())
            return;
        showTransAnimation(mView,interpolator,animationsListener, 0, mView.getHeight() * 2);
    }

    /**
     * 2 trans show
     */
    public void showTransAnimation(final View mView,@NonNull TimeInterpolator interpolator, final AnimationsListener animationsListener,final float trans_x, final float trans_y) {
        if (mView.isShown())
            return;
        if(this.animatorContext == null){
            AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
            animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
            this.animatorContext = animatorContext;
        }
        MultiAnimator.animatorFor(mView, animatorContext).withInterpolator(interpolator)
                .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                    @Override
                    public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                        if(animationsListener!=null){
                            animationsListener.onAnimationStart();
                        }
                        mView.setTranslationX(trans_x);
                        mView.setTranslationY(trans_y);
                        mView.setAlpha(0f);
                        mView.setVisibility(View.VISIBLE);
                    }
                })
                .addOnAnimationCompleted(new OnAnimationCompleted() {
                    @Override
                    public void onAnimationCompleted(boolean finished) {
                        if(finished && animationsListener!=null){
                            animationsListener.onAnimationCompleted();
                        }
                    }
                })
                .alpha(1f)
                .translationX(0f)
                .translationY(0f)
                .start();
    }


    /**
     * 3 trans show force
     */
    public void showTransAnimationForce(final View mView,@NonNull TimeInterpolator interpolator, final AnimationsListener animationsListener,final float trans_x, final float trans_y) {
        if(this.animatorContext == null){
            AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
            animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
            this.animatorContext = animatorContext;
        }
        MultiAnimator.animatorFor(mView, animatorContext).withInterpolator(interpolator)
                .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                    @Override
                    public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                        if(animationsListener!=null){
                            animationsListener.onAnimationStart();
                        }
                        mView.setTranslationX(trans_x);
                        mView.setTranslationY(trans_y);
                        mView.setAlpha(0f);
                        mView.setVisibility(View.VISIBLE);
                    }
                })
                .addOnAnimationCompleted(new OnAnimationCompleted() {
                    @Override
                    public void onAnimationCompleted(boolean finished) {
                        if(finished && animationsListener!=null){
                            animationsListener.onAnimationCompleted();
                        }
                    }
                })
                .alpha(1f)
                .translationX(0f)
                .translationY(0f)
                .start();
    }



    /**
     * 2 trans hide
     */
    public void hideTransAnimation(final View mView) {
        if (mView.isShown()) {
            hideTransAnimation(mView,this.interpolator == null ? new LinearInterpolator() : this.interpolator,null);
        }
    }

    /**
     * 2 trans hide
     */
    public void hideTransAnimation(final View mView ,@NonNull TimeInterpolator interpolator,final AnimationsListener animationsListener) {
        if (mView.isShown()) {
            hideTransAnimation(mView,interpolator,animationsListener, 0, mView.getHeight() * 3);
        }
    }

    /**
     * 2 trans hide
     */
    public void hideTransAnimation(final View mView, @NonNull TimeInterpolator interpolator,final AnimationsListener animationsListener, final float trans_x, final float trans_y) {
        if (mView.isShown()) {
            if(this.animatorContext == null){
                AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
                animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
                this.animatorContext = animatorContext;
            }
            MultiAnimator.animatorFor(mView, animatorContext).withInterpolator(interpolator)
                    .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                        @Override
                        public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                            if(animationsListener!=null){
                                animationsListener.onAnimationStart();
                            }
                        }
                    })
                    .addOnAnimationCompleted(new OnAnimationCompleted() {
                        @Override
                        public void onAnimationCompleted(boolean finished) {
                            if (finished) {
                                if(animationsListener!=null){
                                    animationsListener.onAnimationCompleted();
                                }
                                mView.setTranslationX(0f);
                                mView.setTranslationY(0f);
                                mView.setAlpha(1f);
                                mView.setVisibility(View.GONE);
                            }
                        }
                    })
                    .alpha(0f)
                    .translationX(trans_x)
                    .translationY(trans_y)
                    .start();
        }
    }

    /**
     * 3 Alpha show
     */
    public void showAlphaAnimation(final View mView) {
        if(mView.isShown())
            return;
        showAlphaAnimation(mView,this.interpolator == null ? new LinearInterpolator() : this.interpolator,null);
    }

    /**
     * 3 Alpha show force
     */
    public void showAlphaAnimationForce(final View mView, @NonNull TimeInterpolator interpolator,final AnimationsListener animationsListener) {
        if(this.animatorContext == null){
            AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
            animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
            this.animatorContext = animatorContext;
        }
        MultiAnimator.animatorFor(mView, animatorContext).withInterpolator(interpolator)
                .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                    @Override
                    public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                        if(animationsListener!=null){
                            animationsListener.onAnimationStart();
                        }
                        mView.setAlpha(0f);
                        mView.setVisibility(View.VISIBLE);
                    }
                })
                .addOnAnimationCompleted(new OnAnimationCompleted() {
                    @Override
                    public void onAnimationCompleted(boolean finished) {
                        if(finished && animationsListener!=null){
                            animationsListener.onAnimationCompleted();
                        }
                    }
                })
                .alpha(1f)
                .start();
    }

    /**
     * 3 Alpha show
     */
    public void showAlphaAnimation(final View mView, @NonNull TimeInterpolator interpolator,final AnimationsListener animationsListener) {
        if (mView.isShown())
            return;
        if(this.animatorContext == null){
            AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
            animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
            this.animatorContext = animatorContext;
        }
        MultiAnimator.animatorFor(mView, animatorContext).withInterpolator(interpolator)
                .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                    @Override
                    public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                        if(animationsListener!=null){
                            animationsListener.onAnimationStart();
                        }
                        mView.setAlpha(0f);
                        mView.setVisibility(View.VISIBLE);
                    }
                })
                .addOnAnimationCompleted(new OnAnimationCompleted() {
                    @Override
                    public void onAnimationCompleted(boolean finished) {
                        if(finished && animationsListener!=null){
                            animationsListener.onAnimationCompleted();
                        }
                    }
                })
                .alpha(1f)
                .start();
    }

    /**
     * 3 Alpha hide
     */
    public void hideAlphaAnimation(final View mView ) {
        if(mView.isShown()){
            hideAlphaAnimation(mView,this.interpolator == null ? new LinearInterpolator() : this.interpolator,null);
        }
    }

    /**
     * 3 Alpha hide
     */
    public void hideAlphaAnimation(final View mView, @NonNull TimeInterpolator interpolator,final AnimationsListener animationsListener) {
        if (mView.isShown()) {
            if(this.animatorContext == null){
                AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
                animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
                this.animatorContext = animatorContext;
            }
            MultiAnimator.animatorFor(mView, animatorContext).withInterpolator(interpolator)
                    .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                        @Override
                        public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                            if(animationsListener!=null){
                                animationsListener.onAnimationStart();
                            }
                        }
                    })
                    .addOnAnimationCompleted(new OnAnimationCompleted() {
                        @Override
                        public void onAnimationCompleted(boolean finished) {
                            if(finished && animationsListener!=null){
                                animationsListener.onAnimationCompleted();
                            }
                            if (finished) {
                                mView.setAlpha(1f);
                                mView.setVisibility(View.GONE);
                            }
                        }
                    })
                    .alpha(0f)
                    .start();
        }
    }

    /**
     * 4 Alpha-Trans-Scale hide
     */
    public void showATSAnimation(final View mView, @NonNull TimeInterpolator interpolator,final AnimationsListener animationsListener,
                                 final float trans_x, final float trans_y){
        if(mView.isShown())
            return;
        if(this.animatorContext == null){
            AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
            animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
            this.animatorContext = animatorContext;
        }
        MultiAnimator.animatorFor(mView,animatorContext).withInterpolator(interpolator)
                .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                    @Override
                    public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                        if(animationsListener!=null){
                            animationsListener.onAnimationStart();
                        }
                        mView.setTranslationX(trans_x);
                        mView.setTranslationY(trans_y);
                        mView.setAlpha(0f);
                        mView.setScaleX(0f);
                        mView.setScaleY(0f);
                        mView.setVisibility(View.VISIBLE);
                    }
                })
                .addOnAnimationCompleted(new OnAnimationCompleted() {
                    @Override
                    public void onAnimationCompleted(boolean finished) {
                        if(finished){
                            if(animationsListener!=null){
                                animationsListener.onAnimationCompleted();
                            }
                        }
                    }
                })
                .alpha(1.0f)
                .translationX(0)
                .translationY(0)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .start();

    }

    /**
     * 4 Alpha-Trans-Scale hide
     */
    public void hideATSAnimation(final View mView, @NonNull TimeInterpolator interpolator, final AnimationsListener animationsListener
    ,final float trans_x, final float trans_y) {
        if (mView.isShown()) {
            if(this.animatorContext == null){
                AnimatorContext animatorContext = new AnimatorContext(this.getClass().getSimpleName());
                animatorContext.setTransactionTemplate(new AnimatorTemplate(new FastOutSlowInInterpolator()));
                this.animatorContext = animatorContext;
            }
            MultiAnimator.animatorFor(mView,animatorContext).withInterpolator(interpolator)
                    .addOnAnimationWillStart(new MultiAnimator.WillRunListener() {
                        @Override
                        public void onMultiAnimatorWillRun(@NonNull MultiAnimator animator) {
                            if(animationsListener!=null){
                                animationsListener.onAnimationStart();
                            }
                        }
                    })
                    .addOnAnimationCompleted(new OnAnimationCompleted() {
                        @Override
                        public void onAnimationCompleted(boolean finished) {
                            if(finished){
                                if(animationsListener!=null){
                                    animationsListener.onAnimationCompleted();
                                }
                                mView.setTranslationX(0);
                                mView.setTranslationY(0);
                                mView.setAlpha(1f);
                                mView.setScaleX(1f);
                                mView.setScaleY(1f);
                                mView.setVisibility(View.GONE);
                            }
                        }
                    })
                    .alpha(0f)
                    .translationX(trans_x)
                    .translationY(trans_y)
                    .scaleX(0f)
                    .scaleY(0f)
                    .start();
        }
    }

    public AnimationTools rollingReset(){
        if(this.rotatable!=null){
            this.rotatable.drop();
            this.rotatable = null;
        }
        return getInstance();
    }

    /**
     * 1 rollingOver single
     * @author TianZhen ,this is custom method,you must define every param.
     * @param direction - Rotatable.ROTATE_X , Rotatable.ROTATE_Y , Rotatable.ROTATE_BOTH
     * @param degree - one round(0-360)
     * @param ANIM_DURATION - Duration time
     * @description: rollingOverAnimationSingle(view,Rotatable.ROTATE_X,360,1000);
     * **/
    public void rollingOverAnimationSingle(final View mView , int direction, int degree ,int ANIM_DURATION,
                                    final AnimationsListener animationsListener){
        if(this.rotatable==null){
            this.rotatable = new Rotatable.Builder(mView)
                    .direction(direction)
                    .build();
        }
        rotatable.rotate(direction, degree, ANIM_DURATION, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(animationsListener!=null){
                    animationsListener.onAnimationStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animationsListener != null) {
                    animationsListener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
    }

    /**
     * 2 rollingOver Twins
     * @author TianZhen ,this is custom method , you must define every param.
     * @param parentView the parent view
     * @param frontViewId the top view's resource
     * @param bgViewId the bottom view's resource
     * @param degree one around 0-360
     * @param DURATION 1000
     * @param direction - Rotatable.ROTATE_X , Rotatable.ROTATE_Y , Rotatable.ROTATE_BOTH
     * @description: rollingOverAnimationTwins(parentView,frontViewId,bgViewId,360,1500,Rotatable.ROTATE_X,null)
     * **/
    public void rollingOverAnimationTwins(View parentView, int frontViewId, int bgViewId,int degree,int DURATION,
                                                @Rotatable.Direction int direction,final AnimationsListener animationsListener){
        if(this.rotatable == null){
            Rotatable.Builder builder = new Rotatable.Builder(parentView);
            builder.direction(direction);
            builder.sides(frontViewId,bgViewId);
            builder.listener(new Rotatable.RotationListener() {
                @Override
                public void onRotationChanged(float newRotationX, float newRotationY) {

                }
            });
            this.rotatable = builder.build();
        }
        rotatable.rotate(direction, degree, DURATION, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(animationsListener!=null){
                    animationsListener.onAnimationStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(animationsListener!=null){
                    animationsListener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * if you want to exit that is able to destroy animation object.
     * ***/
    public void DestroyAtsrAnimation(){
        if(this.rotatable!=null){
            this.rotatable.drop();
            this.rotatable = null;
        }
        if(this.animatorContext!=null){
            this.animatorContext = null;
        }
    }

    /**
     * AnimationsListener
     * */
    public interface AnimationsListener{
        void onAnimationStart();
        void onAnimationCompleted();
    }
}
