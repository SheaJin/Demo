package app.presenter;

import android.util.ArrayMap;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.constant.Constant;
import app.model.contract.GameContract;
import app.model.data.MachineInfo;
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

                    }
                });
    }
}
