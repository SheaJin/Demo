package com.qingxu.demoapp.presenter.info;

import com.qingxu.demoapp.model.api.ApiService;
import com.qingxu.demoapp.model.api.ApiStore;
import com.qingxu.demoapp.model.api.HttpObserver;
import com.qingxu.demoapp.model.contract.InfoContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by jxy on 2018/1/25.
 */

public class InfoPresenter implements InfoContract.Presenter {

    @Override
    public void getRankList(String statusType, int rows, String tagType, String seasonId) {
//        ApiStore.createApi(ApiService.class)
//                .getStatsRank(statusType,rows,tagType,seasonId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new HttpObserver<ResponseBody>() {
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
    }

    @Override
    public void getTeamRank() {
        ApiStore.createApi(ApiService.class)
                .getTeamsRank()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
