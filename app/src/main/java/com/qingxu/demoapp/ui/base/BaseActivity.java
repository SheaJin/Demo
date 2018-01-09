package com.qingxu.demoapp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.util.CustomTitleView;

/**
 * Created by jxy on 2018/1/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public CustomTitleView bar;
    private TextView mTvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

//    protected void setClick(int... resId) {
//        for (int id : resId) {
//            View view = this.findViewById(id);
//            view.setOnClickListener(listener);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }

    /**
     * 标题栏
     *
     * @param s
     */
    public void initTitle(String s) {
        mTvTitle = findViewById(R.id.titlessssss);
        if (s != null) {
            mTvTitle.setText(s);
        }
    }

    /**
     * 标题栏
     *
     * @param title
     */
    public void setTitle(String title) {
        bar = CustomTitleView.create(this).setTitle(title).setBackClick(() -> finish()).build().configTitle();
    }

    public void showMess(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    public void showLog(Context context,String mess){
        Log.e(context.getClass().getSimpleName(), "LogInfo: " + mess);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
