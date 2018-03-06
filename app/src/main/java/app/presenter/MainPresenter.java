package app.presenter;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.constant.Constant;
import app.model.contract.MainContract;
import app.model.data.FastEntrance;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jxy on 2018/2/2.
 */

public class MainPresenter implements MainContract.Presenter{
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getEntranceInfo() {
        ApiStore.createApi(ApiService.class)
                .getFastEntrance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<FastEntrance>>() {
                    @Override
                    public void onNext(BaseResp<FastEntrance> fastEntranceBaseResp) {
                        if (fastEntranceBaseResp.getStatus() == Constant.REQUEST_SUCCESS){
                            view.getEntranceInfoOk(fastEntranceBaseResp.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getEntranceInfoErr(e.getMessage());
                    }
                });

    }
}
