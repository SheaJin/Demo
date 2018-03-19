package app.model.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xy.libs.util.app.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by jxy on 2018/3/16.
 */

public class WebSocketService extends Service {
    private static final String TAG = "WebSocketService";
    private OkHttpClient okHttpClient;
    private String socketUrl;
    private Request request;
    private WebSocket mWebSocket;
    private Disposable timer;
    private final int CONNECT = 1;
    private final int AUTHOR = 2;
    private final int SEND = 3;
    private final int CLOSE = 4;
    private int status = CLOSE;

    /**
     * OkHttp构建Socket
     */
    public WebSocketService() {
        LogUtil.e("WebSocketService start");
        status = CONNECT;
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        /**
         * 连接失败重试3次
         */
        timer = Observable.timer(2, TimeUnit.SECONDS)
                .repeat(1)
                .subscribe(aLong -> {
                    switch (status) {
                        case CONNECT:   //连接
                            LogUtil.e(TAG + "开始连接");
                            okHttpClient.newWebSocket(request, listener);
//                            okHttpClient.dispatcher().executorService().shutdown();
                            break;
                        case AUTHOR:
                            LogUtil.e(TAG + "发送token");

                            break;
                        case SEND:      //发消息
                            String data = "{\"data_type\":\"3\"}";
                            sendMessage(data);
                            break;
                        case CLOSE:     //关闭连接

                            break;
                    }
                });
    }

    private WebSocketListener listener = new WebSocketListener() {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            mWebSocket = webSocket;
            sendToken();
            LogUtil.e("WebSocketService 连接成功");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            LogUtil.e("WebSocketService 收到消息" + text);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            LogUtil.e("WebSocketService 连接失败" + t.toString());

            LogUtil.e(TAG + "重新连接");
            okHttpClient.newWebSocket(request, listener);

        }
    };

    /**
     * 设置socket连接地址
     */
    public void setSocketUrl(String url) {
        this.socketUrl = url;
        request = new Request.Builder().url(socketUrl).build();
    }

    /**
     * 发送消息
     *
     * @param data
     */
    public void sendMessage(String data) {
        if (mWebSocket != null) {
            mWebSocket.send(data);
        }
    }

    /**
     * 发送token
     */
    public void sendToken() {
//        String data = "{\"data_type\":\"1\",\"user_token\":\"" + SPs.get(this, Constant.TOKEN, "") + "\"}";
        String data = "{\"data_type\":\"1\",\"user_token\":\"" + "644d9180e47b1bcf592c39d7c84c3fae" + "\"}";
        sendMessage(data);
    }

    public void close() {
        if (okHttpClient != null) {
            okHttpClient.dispatcher().executorService().shutdown();
            okHttpClient = null;
        }
        if (mWebSocket != null) {
            mWebSocket.close(1002, "退出");
        }
        if (timer != null && !timer.isDisposed()) {
            timer.dispose();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null && !timer.isDisposed()) {
            timer.dispose();
        }
    }
}
