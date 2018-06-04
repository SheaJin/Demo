package wan.model.api;

import android.util.ArrayMap;

import java.util.List;

import app.model.data.FastEntrance;
import app.model.data.Gift;
import app.model.data.IPaddress;
import app.model.data.Machine;
import app.model.data.MachineInfo;
import app.model.data.PlayHistory;
import app.model.data.TestLogin;
import app.model.data.TestRegister;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import wan.model.data.UserInfo;

/**
 * Created by JinXinYi on 2018/1/7.
 */

public interface ApiService {
    /**
     * 登录
     */
//    @Headers({"urlname:mdffx"})
    @FormUrlEncoded
    @POST(AppConfig.LOGIN)
    Observable<BaseResp<UserInfo>> login(@FieldMap ArrayMap<String, String> params);
}
