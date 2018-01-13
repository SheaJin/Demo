package com.qingxu.demoapp.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.qingxu.demoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jxy on 2018/1/13.
 */

public class CommonWindow extends PopupWindow {
    @BindView(R.id.window_cancel)
    ImageView windowCancel;
    private View view;
    private Context context;

    public CommonWindow(Context context) {
        super(context);
        view = View.inflate(context, R.layout.window_common, null);
        ButterKnife.bind(this,view);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        this.setOutsideTouchable(true);
        this.setFocusable(true);
        /**设置动画*/
        this.setAnimationStyle(R.style.showPopupFromBottom);

        /**背景阴影*/
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.popup_window));
        this.setBackgroundDrawable(dw);
    }

    @OnClick(R.id.window_cancel)
    public void click(View view) {
        switch (view.getId()){
            case R.id.window_cancel:
                this.dismiss();
                break;
        }
    }
}
