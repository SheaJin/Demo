package app.model.contract;

import app.model.data.FastEntrance;
import app.model.data.MachineInfo;

/**
 * Created by jxy on 2018/2/27.
 */

public class GameContract {

    public interface View{

        void getMachineInfoOk(MachineInfo machineInfo);

        void getMachineInfoErr(String info);

    }

    public interface Presenter {

        void getMachineInfo(String machineId,String toyId);
    }
}
