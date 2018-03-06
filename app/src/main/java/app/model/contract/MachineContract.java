package app.model.contract;

import app.model.data.Machine;

/**
 * Created by jxy on 2018/2/6.
 */

public class MachineContract {

    public interface View{

        void getMachineListOk(Machine machine);

        void getMachineListErr(String info);

    }

    public interface Presenter {

        void getMachineList(String page,String genre,String series_id);
    }
}
