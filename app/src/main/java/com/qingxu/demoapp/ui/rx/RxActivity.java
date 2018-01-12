package com.qingxu.demoapp.ui.rx;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;


/**
 * Created by jxy on 2018/1/12.
 */

/**
 * Observable 被观察者 (类)
 * Observer   观察者 （接口）
 * Subscribe  订阅
 * Subscriber 观察者 它与Observer没什么实质的区别，不同的是 Subscriber要与Flowable(也是一种被观察者)联合使用，
 * Obsesrver用于订阅Observable，而Subscriber用于订阅Flowable
 */
public class RxActivity extends BaseActivity {

    @BindView(R.id.but)
    Button but;
    private Activity activity;
    private Observer observer;
    private Observable observable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        activity = RxActivity.this;
    }

    @Override
    protected void initUI() {
        /**
         * Observable的创建
         */
//        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(ObservableEmitter<Object> e) throws Exception {
//                e.onNext("send msg");
//            }
//        });
        /**
         * Observer 创建
         */
        observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                //观察者收到通知，操作
                showLog(activity, "收到通知" + o.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
//        /**
//         * 订阅
//         * */
//        observable.subscribe(observer);

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.but)
    public void click(View view) {
        switch (view.getId()) {
            case R.id.but:
                showLog(activity, "click");
                //Observable 其他创建方式1
//              observable = Observable.just("hello");

                //Observable 其他创建方式2
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < 10; i++) {
                    list.add("12345" + i);
                }
//                observable = Observable.fromIterable((Iterable<String>) list);

                //定时器
//                observable = Observable.interval(2, TimeUnit.SECONDS);

                //延时器  一个给定的延迟后发射一个特殊的值，即表示延迟2秒
//                observable = Observable.timer(3,TimeUnit.SECONDS);

                //重复发送
//                observable = Observable.timer(2,TimeUnit.SECONDS).repeat();
                /**
                 * 订阅
                 * */
//                observable.subscribe(observer);

                /**
                 * Consumer
                 * */
//                Observable.just("hello").subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        showLog(activity,"get msg!");
//                    }
//                });
                /**
                 * map()操作符，就是把原来的Observable对象转换成另一个Observable对象，
                 * 同时将传输的数据进行一些灵活的操作，方便Observer获得想要的数据形式。
                 * (将数据格式转换并返回)
                 * */
//                Observable<Integer> observable = Observable.just("send").map(new Function<String, Integer>() {
//                    @Override
//                    public Integer apply(String s) throws Exception {
//                        return s.length();
//                    }
//                });

                /**
                 * flatMap()对于数据的转换比map()更加彻底，如果发送的数据是集合，
                 * flatMap()重新生成一个Observable对象，并把数据转换成Observer想要的数据形式。
                 * 它可以返回任何它想返回的Observable对象。
                 *
                 * */
//                Observable<Object> observable = Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(List<String> strings) throws Exception {
//                        return Observable.fromIterable(strings);
//                    }
//                });

                /**
                 * filter 过滤器
                 * filter()操作符根据test()方法中，根据自己想过滤的数据加入相应的逻辑判断，
                 * 返回true则表示数据满足条件，返回false则表示数据需要被过滤。
                 * 最后过滤出的数据将加入到新的Observable对象中，方便传递给Observer想要的数据形式。
                 * */
//                Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(List<String> strings) throws Exception {
//                        return Observable.fromIterable(strings);
//                    }
//                }).filter(new Predicate<Object>() {
//                    @Override
//                    public boolean test(Object o) throws Exception {
//                        String s = o.toString();
//                        if (Integer.parseInt(String.valueOf(s.charAt(5))) - 3 > 0) {
//                            return true;
//                        }
//                        return false;
//                    }
//                }).subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        showLog(activity, (String) o);
//                    }
//                });

                /**
                 * take 输出最多指定数量的结果。
                 *
                 * doOnNext()允许我们在每次输出一个元素之前做一些额外的事情。
                 * */
                Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(List<String> strings) throws Exception {
                        return Observable.fromIterable(strings);
                    }
                }).filter(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        String s = o.toString();
                        if (Integer.parseInt(String.valueOf(s.charAt(5))) - 3 > 0) {
                            return true;
                        }
                        return false;
                    }
                }).take(3).doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        showLog(activity,"prepare");
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        showLog(activity, (String) o);
                    }
                });

                /**
                 * 订阅
                 * */
//                observable.subscribe(observer);
                break;
        }
    }
}
