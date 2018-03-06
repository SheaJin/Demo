package app.model.contract;

import app.model.data.UserInfo;

/**
 * Created by jxy on 2018/2/1.
 */

public class LoginContract {

    public interface View{

        void loginOk(UserInfo userInfo);

        void loginErr(String errInfo);

        void showMess(String info);

//        void wechatLoginOk();

//        void wechatLoginErr();

    }

    public interface Presenter{

        void login(String username,String password);

//        void weChatLogin();

    }
}
