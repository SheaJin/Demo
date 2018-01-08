package com.qingxu.demoapp.util;

import android.support.v7.app.AppCompatActivity;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

/**
 * Created by jxy on 2018/1/8.
 */

public class CustomTitleView {
    private AppCompatActivity context;
    private String left,title,right;
    private OnBackClickListener backClickListener;
    private OnLeftClickListener lefttClickListener;
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

    public static class Builder{
        private AppCompatActivity context;
        private String left,title,right;
        private OnBackClickListener backClickListener;
        private OnLeftClickListener lefttClickListener;
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
            this.lefttClickListener = listener;
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

}
