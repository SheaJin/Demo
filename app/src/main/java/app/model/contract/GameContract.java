package app.model.contract;

import android.util.ArrayMap;

import app.model.data.Machine;
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

        //同款列表
        void getFastSelectListOk(Machine machine);

        void getFastSelectListErr(String info);

    }

    public interface Presenter {

        void getMachineInfo(String machineId,String toyId);

        void getUserInfo();

        void getFastSelectList(ArrayMap<String, String> params);
    }
}
