package com.qingxu.demoapp.presenter.match;

import com.qingxu.demoapp.model.api.ApiService;
import com.qingxu.demoapp.model.api.ApiStore;
import com.qingxu.demoapp.model.api.HttpObserver;
import com.qingxu.demoapp.model.contract.MatchContract;
import com.qingxu.demoapp.model.data.match.MatchListBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jxy on 2018/1/25.
 */

public class MatchPresenter implements MatchContract.Presenter {

    private MatchContract.View view;

    public MatchPresenter(MatchContract.View view) {
        this.view = view;
    }

    @Override
    public void getMatchList(String date) {
        ApiStore.createApi(ApiService.class)
                .getMatchsByData(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<MatchListBean>() {
                    @Override
                    public void onNext(MatchListBean matchListBean) {
                        view.getMatchListOk(matchListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getMatchListErr();
                    }
                });
    }


}
