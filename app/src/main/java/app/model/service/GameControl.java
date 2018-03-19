package app.model.service;

import com.google.gson.Gson;

import app.model.data.DollMachineSocket;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by jxy on 2018/3/16.
 */

public class GameControl {
    public static GameControl control;
    private WebSocketService webSocketService;
    private GameControlHelper gameControlHelper;
    private Gson gson;
    private SingleObserver<DollMachineSocket> observer;

    public GameControl() {
        webSocketService = new WebSocketService();
        gson = new Gson();

        observer = new SingleObserver<DollMachineSocket>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(DollMachineSocket dollMachineSocket) {
                switch (dollMachineSocket.getData_type()){
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        gameControlHelper.gameStatusChange(dollMachineSocket.getGame_status(),dollMachineSocket.getLog_id());
                        break;
                    case 7:

                        break;
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        };

    }

    public static GameControl getInstance() {
        if (control == null) {
            control = new GameControl();
        }
        return control;
    }

    public void setSocketUrl(String socketUrl) {
        webSocketService.setSocketUrl(socketUrl);
    }

    public void close() {
        webSocketService.close();
    }

}
