package com.qingxu.demoapp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.qingxu.demoapp.util.CustomTitleView;

/**
 * Created by jxy on 2018/1/8.
 */

public class BaseActivity extends AppCompatActivity{
    public String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setTitle(String title){
        new CustomTitleView.Builder(this).setTitle(title).setBackClick(() -> finish()).build();
    }

    public void showToast(String mess){
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
