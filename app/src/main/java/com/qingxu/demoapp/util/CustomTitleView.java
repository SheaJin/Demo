package com.qingxu.demoapp.util;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingxu.demoapp.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

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
    public CustomTitleView(Builder builder){
        this.context = builder.context;
        this.left = builder.left;
        this.title = builder.title;
        this.right = builder.right;
        this.backClickListener = builder.backClickListener;
        this.leftClickListener = builder.leftClickListener;
        this.rightClickListener = builder.rightClickListener;
        configTitle();
    }

    public void configTitle() {
        View inflate = View.inflate(context,R.layout.custom_title_view,null);
        mTvLeft = inflate.findViewById(R.id.title_left);
        mTvTitle = inflate.findViewById(R.id.title);
        mTvRight = inflate.findViewById(R.id.title_right);
        mIvBack = inflate.findViewById(R.id.image_back);
    }


    public static class Builder{
        private AppCompatActivity context;
        private String left,title,right;
        private OnBackClickListener backClickListener;
        private OnLeftClickListener leftClickListener;
        private OnRightClickListener rightClickListener;

        public Builder(AppCompatActivity context){
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

        public CommonPagerTitleView build() {
            return new CommonPagerTitleView(context);
        }

    }

    public void show(){
        configTitle();
    }

}
