package app.model.contract;

import app.model.data.PlayHistory;
import app.model.data.UserInfo;

/**
 * Created by jxy on 2018/3/15.
 */

public class PlayHistoryContract {

    public interface View{

        void getHistoryListOk(PlayHistory playHistory);

        void getHistoryListErr(String errInfo);

    }

    public interface Presenter{

        void getHistoryList(String page);

    }
}
