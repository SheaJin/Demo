package com.qingxu.demoapp.widget;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingxu.demoapp.R;

/**
 * Created by jxy on 2018/1/8.
 */

public class CustomTitleView {
    private AppCompatActivity context;
    private String left,title,right;
    private TextView mTvLeft,mTvTitle,mTvRight;
    private ImageView mIvBack;
    private OnBackClickListener backClickListener;
    private OnLeftClickListener leftClickListener;
    private OnRightClickListener rightClickListener;

    public interface OnBackClickListener {
        void onBackClick();
    }

    public interface OnLeftClickListener {
        void onLeftClick();
    }

    public interface OnRightClickListener {
        void onRightClick();
    }

    /**
     * 构造器
     * */
    private CustomTitleView(AppCompatActivity context,String left,String title,String right,OnBackClickListener backClickListener,OnLeftClickListener leftClickListener,OnRightClickListener rightClickListener){
        this.context = context;
        this.left = left;
        this.title = title;
        this.right = right;
        this.backClickListener = backClickListener;
        this.leftClickListener = leftClickListener;
        this.rightClickListener = rightClickListener;
    }

    public static Builder create(AppCompatActivity context){
        return new Builder(context);
    }

    public static class Builder{
        private AppCompatActivity context;
        private String left,title,right;
        private OnBackClickListener backClickListener;
        private OnLeftClickListener leftClickListener;
        private OnRightClickListener rightClickListener;

        private Builder(AppCompatActivity context){
            this.context = context;
        }

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setBackClick(OnBackClickListener listener){
            this.backClickListener = listener;
            return this;
        }

        public Builder setLeft(String left){
            this.left = left;
            return this;
        }

        public Builder setLeftClick(OnLeftClickListener listener){
            this.leftClickListener = listener;
            return this;
        }

        public Builder setRight(String right){
            this.right = right;
            return this;
        }

        public Builder setRightClick(OnRightClickListener listener){
            this.rightClickListener = listener;
            return this;
        }

        public CustomTitleView build() {
            CustomTitleView view = new CustomTitleView(context,left,title,right,backClickListener,leftClickListener,rightClickListener);
            return view;
        }
    }

    public CustomTitleView configTitle() {
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL;
        View view = LayoutInflater.from(context).inflate(R.layout.custom_title_view, null);
        mTvLeft = view.findViewById(R.id.title_left);
        mTvTitle = view.findViewById(R.id.title);
        mTvRight = view.findViewById(R.id.title_right);
        mIvBack = view.findViewById(R.id.image_back);
        mTvTitle.setText(title);
        ActionBar actionBar = context.getSupportActionBar();
        actionBar.setCustomView(view, lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setElevation(0);
        Toolbar parent = (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        return this;
    }

}
