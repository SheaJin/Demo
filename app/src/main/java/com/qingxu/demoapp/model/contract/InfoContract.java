package com.qingxu.demoapp.model.contract;

/**
 * Created by jxy on 2018/1/25.
 */

public class InfoContract  {

    public interface View {

        void getRankListOk();

        void getRankListErr();

    }

    public interface Presenter {

        void getRankList(String statusType, int rows, String tagType, String seasonId);

        void getTeamRank();
    }
}
