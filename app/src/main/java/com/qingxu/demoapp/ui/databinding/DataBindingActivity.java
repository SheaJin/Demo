package com.qingxu.demoapp.ui.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.databinding.ActivityDataBindingBinding;
import com.qingxu.demoapp.ui.recycler.MyAdapter;

import java.util.ArrayList;
import java.util.List;


public class DataBindingActivity extends AppCompatActivity {
    private List<String> list;
    private MyAdapter adapter;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        /**
         * TextView
         * */
        userInfo = new UserInfo("username", "18");
        binding.setUser(userInfo);
        binding.setStr("str");
        binding.setCount(5555);
        binding.setErr(true);
        binding.setSrc(getResources().getDrawable(R.drawable.exchange_btn));
        binding.setHandler(new EventHandlers());
        /**
         * RecyclerView
         * */
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        list.add("");
        adapter = new MyAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
        /**
         * 点击事件
         * */
        binding.setOnClick(v -> {
            switch (v.getId()){
                case R.id.button1:
                    Toast.makeText(DataBindingActivity.this, "click Button1", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    public class EventHandlers {
        public void handleClick(View view) {
            Toast.makeText(DataBindingActivity.this, "you clicked text", Toast.LENGTH_LONG).show();
            userInfo.setName("namenamenamenamename");
        }
    }
}
