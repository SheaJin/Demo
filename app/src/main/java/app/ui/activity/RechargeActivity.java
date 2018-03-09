package app.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xy.doll.R;

import app.ui.base.BaseActivity;

public class RechargeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
    }

    @Override
    protected void initUI() {
        initTitle("充值");
    }

    @Override
    protected void initData() {

    }
}
