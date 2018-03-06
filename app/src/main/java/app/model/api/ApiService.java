package app.model.api;

import android.util.ArrayMap;

import app.model.data.FastEntrance;
import app.model.data.Machine;
import app.model.data.MachineInfo;
import app.model.data.UserInfo;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by JinXinYi on 2018/1/7.
 */

public interface ApiService {
    /**
     * 登录
     * */
//    @Headers({"urlname:mdffx"})
    @FormUrlEncoded
    @POST(AppConfig.LOGIN)
    Observable<BaseResp<UserInfo>> login(@FieldMap ArrayMap<String, String> params);

    /**
     * banner + 快速入口
     * */
    @POST(AppConfig.FASTENTRANCE)
    Observable<BaseResp<FastEntrance>> getFastEntrance();

    /**
     * 娃娃列表
     */
    @FormUrlEncoded
    @POST(AppConfig.MACHINELIST)
    Observable<BaseResp<Machine>> getMachineList(@FieldMap ArrayMap<String, String> params);

    /**
     * 个人信息
     */
    @POST(AppConfig.GETUSERINFO)
    Observable<BaseResp<UserInfo>> getUserInfo();

    /**
     * 获取娃娃机信息
     */
    @FormUrlEncoded
    @POST(AppConfig.GETMACHINEINFO)
    Observable<BaseResp<MachineInfo>> getMachineInfo(@FieldMap ArrayMap<String, String> params);
}
