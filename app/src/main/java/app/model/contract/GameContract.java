package app.model.contract;

import app.model.data.MachineInfo;
import app.model.data.UserInfo;

/**
 * Created by jxy on 2018/2/27.
 */

public class GameContract {

    public interface View{
        //娃娃机信息
        void getMachineInfoOk(MachineInfo machineInfo);

        void getMachineInfoErr(String info);

        //个人信息
        void getUserInfoOk(UserInfo userInfo);

        void getUserInfoErr(String info);

    }

    public interface Presenter {

        void getMachineInfo(String machineId,String toyId);

        void getUserInfo();
    }
}
