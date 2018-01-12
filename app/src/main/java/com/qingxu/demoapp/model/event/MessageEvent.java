package com.qingxu.demoapp.model.event;

/**
 * Created by jxy on 2018/1/10.
 */

public class MessageEvent {
    public int code;
    public String mess;

    public MessageEvent(int code, String mess) {
        this.code = code;
        this.mess = mess;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
