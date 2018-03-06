package app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xy.doll.R;

import app.ui.base.BaseActivity;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.but, R.id.but2})
    void click(View view) {
        switch (view.getId()){
            case R.id.but:
                Intent intent1 = new Intent();
                intent1.putExtra("return", "23333333");
                setResult(RESULT_OK, intent1);
                finish();
                break;
            case R.id.but2:
                Intent intent2 = new Intent();
                intent2.putExtra("returnsss", "jdsdsdsndijnahfbahsubfhuasbdhbasdhj");
                setResult(RESULT_OK, intent2);
                finish();
                break;
        }
    }
}
