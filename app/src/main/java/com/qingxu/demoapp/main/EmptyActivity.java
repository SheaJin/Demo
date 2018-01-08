package com.qingxu.demoapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.databinding.DataBindingActivity;
import com.qingxu.demoapp.databinding.DataFragmentActivity;
import com.qingxu.demoapp.loadmore.TextLoadMoreActivity;
import com.qingxu.demoapp.recycler.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class EmptyActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private List<String> list;
    private EmptyAdapter adapter;
    private String[] menus = {"RecyclerView嵌套RecyclerView","dataBinding","Fragment 使用dataBinding","TextView加载更多"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        for (int i = 0; i < menus.length; i++) {
            list.add("");
        }
        adapter = new EmptyAdapter(this, list,menus);
        mRv.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            switch (position){
                case 0:
                    startActivity(new Intent(this,MainActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(this,DataBindingActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(this,DataFragmentActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(this, TextLoadMoreActivity.class));
                    break;
            }
        });
    }
}
