package com.qingxu.demoapp.model.contract;

import com.qingxu.demoapp.model.data.match.MatchListBean;

/**
 * Created by jxy on 2018/1/25.
 */

public class MatchContract {

    public interface View {

        void getMatchListOk(MatchListBean matchListBean);

        void getMatchListErr();

    }

    public interface Presenter {

        void getMatchList(String date);
    }

}
