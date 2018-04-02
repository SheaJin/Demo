package com.xy.doll.rx;

import android.os.Bundle;

import com.xy.doll.R;
import com.xy.libs.util.app.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import app.model.api.ApiService;
import app.model.api.ApiStore;
import app.model.api.BaseResp;
import app.model.api.HttpObserver;
import app.model.data.TestLogin;
import app.model.data.TestRegister;
import app.ui.base.BaseActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
    }

    @Override
    protected void initUI() {
//        test1();

//        test2();

//        test3();

//        test4();

//        test5();

        test6();
    }

    private void test6() {
        /**
         * Buffer
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .buffer(3, 2)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        LogUtil.e(" 缓存区里的事件数量 = " + integers.size());
                        for (Integer integer : integers) {
                            LogUtil.e(" 事件 " + integer);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("err");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e("onComplete");
                    }
                });
    }

    private void test5() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtil.e(s);
            }
        });
    }

    private void test4() {
        /**
         * 实现注册完成自动登录功能
         */
        ApiStore.createApi(ApiService.class)
                .register()
                .subscribeOn(Schedulers.io())      //iox线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //在主线程处理注册结果
                .doOnNext(new Consumer<BaseResp<TestRegister>>() {
                    @Override
                    public void accept(BaseResp<TestRegister> testRegisterBaseResp) throws Exception {
                        //注册结果

                    }
                })
                .observeOn(Schedulers.io())     //线程回到io线程进行登录请求
                .flatMap(new Function<BaseResp<TestRegister>, ObservableSource<TestLogin>>() {
                    @Override
                    public ObservableSource<TestLogin> apply(BaseResp<TestRegister> testRegisterBaseResp) throws Exception {
//                        return ApiStore.createApi(ApiService.class).login();
//                        return new ObservableSource<TestLogin>;
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())      //回到主线程处理登录结果
                .subscribe(new HttpObserver<TestLogin>() {
                    @Override
                    public void onNext(TestLogin testLogin) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    private void test3() {
        /**
         * map和flatMap的区别
         * 前者是数据类型或者数据结构的改变 而后者是将源Observable转换为新建Observable
         *
         * flatMap能转化发射源，即“Observable<RegisterResponse>” -> "Observable<LoginResponse>",
         * 配合Retrofit就能在完成注册事件后继续完成登录事件。
         *
         * map操作符只能把“Observable<RegisterResponse>”里面的“RegisterResponse”转化成“LoginResponse”，
         * 而“LoginResponse”只是一个model对象，不能作为发射源完成登录操作。
         *
         */

        /**
         * flatMap 操作符
         * 无序的将被观察者发送的整个事件序列进行变换
         * 经过flatMap 变换输出
         * flatMap 输出是无序的
         * 如果想有序输出，使用concatMap
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
                e.onNext(6);
                e.onNext(7);
            }
        }).flatMap((Function<Integer, ObservableSource<String>>) integer -> {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("getMsg" + integer);
            }
            return Observable.fromIterable(list).delay(1000, TimeUnit.MILLISECONDS);
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                LogUtil.e("收到:" + o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void test2() {
        /**
         * map 函数
         * 将上游传递下来的事件经过map()函数在传递到下游
         *
         */
        Observable.create(e -> e.onNext("1"))
                .map((Function<Object, Object>) o -> {
                    String str = "map函数" + o;
                    return str;
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        LogUtil.e("收到:" + o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void test1() {
        /**
         * SubscribeOn() 指定的是上游 发送事件的线程
         * ObserveOn() 指定的是上游 发送事件的线程
         *
         * 多次指定上游线程，只有第一个有效
         * 多次指定下游线程是ok的
         */

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                //emitter为发射器
                emitter.onNext(1);
                LogUtil.e("上游Thread:" + Thread.currentThread().getName());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtil.e("onNext" + integer);
                        LogUtil.e("下游Thread:" + Thread.currentThread().getName());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void initData() {

    }
}
