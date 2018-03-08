package app.ui.activity;

import android.os.Bundle;

import com.xy.doll.R;

import app.ui.base.BaseActivity;

public class OtherReasonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_reason);

    }

    @Override
    protected void initUI() {
        initTitle("其他理由");
    }

    @Override
    protected void initData() {

    }
}
