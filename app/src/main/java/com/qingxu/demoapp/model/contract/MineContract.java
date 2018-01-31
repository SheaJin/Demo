package com.qingxu.demoapp.model.contract;

/**
 * Created by jxy on 2018/1/25.
 */

public class MineContract {
    public interface View {

    }

    public interface Presenter {

        void login(String username, String password);
    }
}
