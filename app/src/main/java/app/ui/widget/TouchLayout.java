package app.ui.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by jxy on 2018/2/27.
 */

public class TouchLayout extends AutoRelativeLayout {
    private OnTouchListener touchListener;
    private String currentAction;
    private TouchAction[] actions;

    public TouchLayout(Context context) {
        this(context, null);
    }

    public TouchLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TouchLayout);
        currentAction = typedArray.getString(R.styleable.TouchLayout_action);
        typedArray.recycle();

        actions = new TouchAction[]{TouchAction.CLOSE, TouchAction.FEEDBACK, TouchAction.EXCHANGE, TouchAction.REFRESH, TouchAction.CHAT,
                TouchAction.FASTSELECT, TouchAction.GAMESTART, TouchAction.DETAILS, TouchAction.RECHARGE};
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionDown();
                break;
            case MotionEvent.ACTION_UP:
                actionUp();
                break;
        }
        return true;
    }

    /**
     * 抬起
     */
    private void actionUp() {

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(this, "scaleX", 0.9f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(this, "scaleY", 0.9f, 1.0f);
        animatorSet.setDuration(80);
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
        for (TouchAction touchAction : actions) {
            if (touchAction.getAction().equals(currentAction)) {
                touchListener.onAction(touchAction, true);
            }
        }
        LogUtil.e("actionUp" );
    }

    /**
     * 按下
     */
    private void actionDown() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 0.9f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 0.9f);
        animatorSet.setDuration(80);
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
        for (TouchAction touchAction : actions) {
            if (touchAction.getAction().equals(currentAction)) {
                touchListener.onAction(touchAction, false);
            }
        }
        LogUtil.e("actionDown" );
    }

    public interface OnTouchListener {
        void onAction(TouchAction action, boolean canTouch);
    }

    public void setTouchListener(OnTouchListener touchListener) {
        this.touchListener = touchListener;
    }

    public enum TouchAction {
        CLOSE(0, "CLOSE"), FEEDBACK(1, "FEEDBACK"), EXCHANGE(2, "EXCHANGE"), REFRESH(3, "REFRESH"), CHAT(4, "CHAT"),
        FASTSELECT(5, "FASTSELECT"), GAMESTART(6, "GAMESTART"), DETAILS(7, "DETAILS"), RECHARGE(8, "RECHARGE");
        public String action;
        public int position;

        TouchAction(int position, String action) {
            this.position = position;
            this.action = action;
        }

        public String getAction() {
            return action;
        }

        public int getPosition() {
            return position;
        }

    }
}
