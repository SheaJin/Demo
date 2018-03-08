package app.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.xy.doll.DollApplication;
import com.xy.doll.R;
import com.xy.libs.util.app.JumpUtil;

import app.ui.activity.OtherReasonActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jxy on 2018/3/6.
 */

public class FeedBackWindow extends PopupWindow {
    private View view;
    private OnChooseListener listener;

    public FeedBackWindow(Context context) {
        super(context);
        init(context);
    }

    @OnClick({R.id.reason_1, R.id.reason_2, R.id.reason_3, R.id.reason_4, R.id.feedback_cancel})
    void click(View v) {
        switch (v.getId()) {
            case R.id.reason_1:
                listener.choose("画面黑屏或定格");
                break;
            case R.id.reason_2:
                listener.choose("操作按键失灵");
                break;
            case R.id.reason_3:
                listener.choose("爪子卡住动不了");
                break;
            case R.id.reason_4:
                JumpUtil.overlay(DollApplication.getInstance(), OtherReasonActivity.class);
                dismiss();
                break;
            case R.id.feedback_cancel:
                dismiss();
                break;
        }
    }

    private void init(Context context) {
        view = View.inflate(context, R.layout.window_feedback, null);
        ButterKnife.bind(this, view);
        this.setContentView(view);

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(false);
        /**设置动画*/
        this.setAnimationStyle(R.style.showPopupFromBottom);

        /**背景阴影*/
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#00000000"));
        this.setBackgroundDrawable(dw);
    }

    public interface OnChooseListener {
        void choose(String msg);
    }

    public void setListener(OnChooseListener listener) {
        this.listener = listener;
    }
}
