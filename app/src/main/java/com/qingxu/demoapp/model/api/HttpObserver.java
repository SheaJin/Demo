package com.qingxu.demoapp.model.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class HttpObserver<T> implements Observer<T> {

//    @Override
//    public abstract void onError(Throwable e) {
//        if (e != null) {
////            if (e instanceof ApiStore.ApiError) {
////                if (((ApiStore.ApiError) e).getErrorCode() == 100) {
////                    EventBusMode ebm = new EventBusMode();
////                    ebm.setType(EventBusMode.ONLogoutSuccess);
////                    EventBusManager.getInstance().getGlobalEventBus().post(ebm);
////                    SharedPreferenceUtil.getInstance().logout();
////                }
////                onErrorInfo((ApiStore.ApiError) e);
////            }
//            e.printStackTrace();
//        }
//
//    }

    @Override
    public abstract void onNext(T t);

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onError(Throwable e);
}
