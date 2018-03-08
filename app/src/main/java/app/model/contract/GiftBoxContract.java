package app.model.contract;

import java.util.List;

import app.model.data.Gift;

/**
 * Created by jxy on 2018/3/7.
 */

public class GiftBoxContract {

    public interface View {

        void getGiftListOk(List<Gift> gifts);

        void getGiftListErr(String info);

    }

    public interface Presenter {

        void getGiftList(String page);
    }
}
