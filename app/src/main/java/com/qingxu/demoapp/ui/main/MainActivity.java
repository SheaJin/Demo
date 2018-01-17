package com.qingxu.demoapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.adapter.MainAdapter;
import com.qingxu.demoapp.ui.databinding.DataBindingActivity;
import com.qingxu.demoapp.ui.login.LoginActivity;
import com.qingxu.demoapp.ui.rank.RankActivity;
import com.qingxu.demoapp.ui.recycler.RecyclerActivity;
import com.qingxu.demoapp.ui.rx.RxActivity;
import com.qingxu.demoapp.ui.pic.UploadImageActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private List<String> list;
    private MainAdapter adapter;
    private String[] menus = {"RecyclerView嵌套RecyclerView", "dataBinding", "RxJava", "Login", "Ranking","upload"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        for (int i = 0; i < menus.length; i++) {
            list.add("");
        }
        adapter = new MainAdapter(this, list,menus);
        mRv.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            switch (position){
                case 0:
                    startActivity(new Intent(this,RecyclerActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(this,DataBindingActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(this,RxActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(this,LoginActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(this, RankActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(this, UploadImageActivity.class));
                    break;
            }
        });
    }
}
