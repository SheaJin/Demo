package app.presenter;

import android.util.ArrayMap;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.constant.Constant;
import app.model.contract.MachineContract;
import app.model.data.Machine;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jxy on 2018/2/6.
 */

public class MachinePresenter implements MachineContract.Presenter {

    private MachineContract.View view;
    private ArrayMap<String, String> params;

    public MachinePresenter(MachineContract.View view) {
        this.view = view;
        params = new ArrayMap<>();
    }

    @Override
    public void getMachineList(String page, String genre, String series_id) {
        params.put("page", page);
        params.put("genre", genre);
        params.put("series_id", series_id);

        ApiStore.createApi(ApiService.class)
                .getMachineList(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<Machine>>() {
                    @Override
                    public void onNext(BaseResp<Machine> machineBaseResp) {
                        if (machineBaseResp.getStatus() == Constant.REQUEST_SUCCESS) {
                            view.getMachineListOk(machineBaseResp.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getMachineListErr(e.getMessage());
                    }
                });
    }
}
