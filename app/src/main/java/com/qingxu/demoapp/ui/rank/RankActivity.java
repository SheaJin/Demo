package com.qingxu.demoapp.ui.rank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.api.ApiService;
import com.qingxu.demoapp.api.ApiStore;
import com.qingxu.demoapp.api.HttpObserver;
import com.qingxu.demoapp.model.TestModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        ArrayMap<String,String> params = new ArrayMap<>();
        params.put("key","a4725062baeccf9cd0b038dc4a781062");
        params.put("menu","西红柿");
        params.put("rn","1");
        params.put("pn","3");

        ApiStore.createApi(ApiService.class)
                .testPost(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<TestModel>() {
                    @Override
                    public void onNext(TestModel testModel) {
                        Toast.makeText(RankActivity.this, "successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("jxy", "onError: " + e.getMessage());
                    }
                });

    }

}
