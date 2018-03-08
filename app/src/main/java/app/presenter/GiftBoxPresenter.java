package app.presenter;

import java.util.List;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.constant.Constant;
import app.model.contract.GiftBoxContract;
import app.model.data.Gift;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jxy on 2018/3/7.
 */

public class GiftBoxPresenter implements GiftBoxContract.Presenter {
    private GiftBoxContract.View view;

    public GiftBoxPresenter(GiftBoxContract.View view) {
        this.view = view;
    }

    @Override
    public void getGiftList(String page) {
        ApiStore.createApi(ApiService.class)
                .getGiftList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<List<Gift>>>() {
                    @Override
                    public void onNext(BaseResp<List<Gift>> listBaseResp) {
                        if (listBaseResp.getStatus() == Constant.REQUEST_SUCCESS) {
                            view.getGiftListOk(listBaseResp.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getGiftListErr("获取娃娃盒列表失败");
                    }
                });
    }
}
