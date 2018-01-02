package com.qingxu.demoapp.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.recycler.MyAdapter;

import java.util.ArrayList;
import java.util.List;


public class DataBindingActivity extends AppCompatActivity {
    private List<String> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        /**
         * TextView
         * */
        UserInfo userInfo = new UserInfo("username", "18");
        binding.setUser(userInfo);
        binding.setStr("str");
        binding.setCount(5555);
        binding.setErr(true);
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
                case R.id.button2:
                    Toast.makeText(DataBindingActivity.this, "click Button2", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

    }
}
