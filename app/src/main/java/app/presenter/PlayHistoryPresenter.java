package app.presenter;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.constant.Constant;
import app.model.contract.PlayHistoryContract;
import app.model.data.PlayHistory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jxy on 2018/3/15.
 */

public class PlayHistoryPresenter implements PlayHistoryContract.Presenter {

    private PlayHistoryContract.View view;

    public PlayHistoryPresenter(PlayHistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void getHistoryList(String page) {
        ApiStore.createApi(ApiService.class)
                .getHistoryList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<PlayHistory>>() {
                    @Override
                    public void onNext(BaseResp<PlayHistory> playHistoryBaseResp) {
                        if (playHistoryBaseResp.getStatus() == Constant.REQUEST_SUCCESS) {
                            view.getHistoryListOk(playHistoryBaseResp.getData());
                        } else {
                            view.getHistoryListErr(playHistoryBaseResp.getInfo());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getHistoryListErr("获取抓取记录失败");
                    }
                });
    }
}
