package app.presenter;

import android.util.ArrayMap;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.constant.Constant;
import app.model.contract.GameContract;
import app.model.data.Machine;
import app.model.data.MachineInfo;
import app.model.data.UserInfo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jxy on 2018/2/27.
 */

public class GamePresenter implements GameContract.Presenter{

    private GameContract.View view;

    public GamePresenter(GameContract.View view) {
        this.view = view;
    }

    /**
     * 获取娃娃机信息
     * */
    @Override
    public void getMachineInfo(String machineId,String toyId) {
        ArrayMap<String, String> params = new ArrayMap<>();
        params.put("device_id", machineId);
        params.put("toy_id", toyId);
        params.put("skt_id", "ac13d73e0a8f000296e6");
        ApiStore.createApi(ApiService.class)
                .getMachineInfo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<MachineInfo>>() {
                    @Override
                    public void onNext(BaseResp<MachineInfo> machineInfoBaseResp) {
                        if (machineInfoBaseResp.getStatus() == Constant.REQUEST_SUCCESS){
                            view.getMachineInfoOk(machineInfoBaseResp.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getMachineInfoErr("获取娃娃机信息失败");
                    }
                });
    }

    /**
     * 获取个人信息
     */
    @Override
    public void getUserInfo() {
        ApiStore.createApi(ApiService.class)
                .getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<UserInfo>>() {
                    @Override
                    public void onNext(BaseResp<UserInfo> userInfoBaseResp) {
                        if (userInfoBaseResp.getStatus() == Constant.REQUEST_SUCCESS) {
                            view.getUserInfoOk(userInfoBaseResp.getData());
                        } else {
                            view.getUserInfoErr(userInfoBaseResp.getInfo());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getUserInfoErr("获取个人信息失败");
                    }
                });
    }

    /**
     * 获取同款列表
     */
    @Override
    public void getFastSelectList(ArrayMap<String, String> params) {
        ApiStore.createApi(ApiService.class)
                .getFastSelectList(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<Machine>>() {
                    @Override
                    public void onNext(BaseResp<Machine> machineBaseResp) {
                        if (machineBaseResp.getStatus() == Constant.REQUEST_SUCCESS) {
                            view.getFastSelectListOk(machineBaseResp.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getUserInfoErr("获取同款列表失败");
                    }
                });
    }
}
