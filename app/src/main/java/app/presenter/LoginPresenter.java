package app.presenter;

import android.util.ArrayMap;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.constant.Constant;
import app.model.contract.LoginContract;
import app.model.data.UserInfo;
import app.util.AppUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jxy on 2018/2/1.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private ArrayMap<String,String> params;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        params = new ArrayMap<>();
    }

    @Override
    public void login(String username,String password) {
        params.put("mobile", username);
        params.put("code", password);
        params.put("imei", AppUtil.getImei());
        params.put("logintype", Constant.LOGINTYPE);

        if (username == null){
            view.loginErr(Constant.NAMENULL);
        } else if (password == null){
            view.loginErr(Constant.CODENULL);
        } else {
            ApiStore.createApi(ApiService.class)
                    .login(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new HttpObserver<BaseResp<UserInfo>>() {
                        @Override
                        public void onNext(BaseResp<UserInfo> response) {
                            if (response.getStatus() == Constant.REQUEST_SUCCESS){
                                view.loginOk(response.getData());
                            }
                            view.showMess(response.getInfo());
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showMess(Constant.LOGINFAIL);
                        }
                    });
        }
    }
}
