package app.ui.activity;

import android.os.Bundle;

import com.xy.doll.R;

import app.ui.base.BaseActivity;

public class ExchangeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
    }

    @Override
    protected void initUI() {
        initTitle("兑换钻石");
    }

    @Override
    protected void initData() {

    }
}
