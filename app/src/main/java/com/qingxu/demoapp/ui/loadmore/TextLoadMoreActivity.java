package com.qingxu.demoapp.ui.loadmore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qingxu.demoapp.R;

public class TextLoadMoreActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_load_more);
        mTv = findViewById(R.id.text);
        StringBuffer sb = new StringBuffer();
        sb.append("内容:");
        for (int i = 0; i < 100; i++) {
            sb.append("但是当时大家都你说的你上课都没开始的你就你多多交流但是看模拟卷");
        }
        mTv.setText(sb);

        mTv.setMaxWidth(600);
        mTv.setSingleLine(true);
    }

    @Override
    public void onClick(View v) {
        boolean flag = true;
        switch (v.getId()) {
            case R.id.text:
                if (flag) {
                    flag = false;
                    mTv.setEllipsize(null);// 展开
                    mTv.setSingleLine(flag);
                } else {
                    flag = true;
                    mTv.setEllipsize(TextUtils.TruncateAt.END); // 收缩
                    mTv.setSingleLine(flag);
                }
                break;
        }
    }
}
