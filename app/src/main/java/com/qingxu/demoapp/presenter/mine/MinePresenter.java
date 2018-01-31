package com.qingxu.demoapp.presenter.mine;

import com.qingxu.demoapp.model.api.ApiService;
import com.qingxu.demoapp.model.api.ApiStore;
import com.qingxu.demoapp.model.api.HttpObserver;
import com.qingxu.demoapp.model.contract.MineContract;
import com.qingxu.demoapp.model.data.mine.ESUser;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jxy on 2018/1/25.
 */

public class MinePresenter implements MineContract.Presenter {


    @Override
    public void login(String username, String password) {
        ApiStore.createApi(ApiService.class)
                .getUserInfo(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<ESUser>() {
                    @Override
                    public void onNext(ESUser esUser) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
