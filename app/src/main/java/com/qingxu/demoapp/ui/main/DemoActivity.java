package com.qingxu.demoapp.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qingxu.demoapp.R;
import com.qingxu.demoapp.ui.base.BaseActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class DemoActivity extends BaseActivity {


    private Button start;
    private TextView text;
    private WebSocket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    @Override
    protected void initUI() {
        start = findViewById(R.id.start);
        text = findViewById(R.id.text);
        setClick(R.id.start);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        connect();
    }

    public void showText(final String content) {
        activity.runOnUiThread(() -> showLog(activity, "receive:" + content + "\n"));
    }

    class EchoWebSocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            socket = webSocket;
//            webSocket.send("hello world");
//            webSocket.send("welcome");
//            webSocket.send(ByteString.decodeHex("adef"));
//            webSocket.close(1000, "再见");
            sendMessage("mess1");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            showText("onMessage: " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            showText("onMessage byteString: " + bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(1000, null);
            showText("onClosing: " + code + "/" + reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            showText("onClosed: " + code + "/" + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            showText("onFailure: " + t.getMessage());
        }
    }

    private void connect() {
        EchoWebSocketListener listener = new EchoWebSocketListener();
        Request request = new Request.Builder()
                .url("ws://echo.websocket.org")
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    void sendMessage(String msg) {
        if (socket != null) {
            socket.send(msg);
        }
    }

    void close(int code, String reason) {
        if (socket != null) {
            socket.close(code, reason);
        }
    }
}
