package app.ui.activity;

import android.os.Bundle;

import com.xy.doll.R;

import app.ui.base.BaseActivity;

public class SendActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

    }

    @Override
    protected void initUI() {
        initTitle("配送娃娃");
    }

    @Override
    protected void initData() {

    }
}
