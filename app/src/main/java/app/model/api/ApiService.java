package app.model.api;

import android.util.ArrayMap;

import java.util.List;

import app.model.data.FastEntrance;
import app.model.data.Gift;
import app.model.data.IPaddress;
import app.model.data.Machine;
import app.model.data.MachineInfo;
import app.model.data.PlayHistory;
import app.model.data.UserInfo;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

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

    /**
     * 获取娃娃盒列表
     */
    @FormUrlEncoded
    @POST(AppConfig.GETGIFTLIST)
    Observable<BaseResp<List<Gift>>> getGiftList(@Field("page") String page);

    /**
     * 获取同款列表
     */
    @FormUrlEncoded
    @POST(AppConfig.GETFASTSELECTLIST)
    Observable<BaseResp<Machine>> getFastSelectList(@FieldMap ArrayMap<String, String> params);

    /**
     * 抓取记录
     */
    @FormUrlEncoded
    @POST(AppConfig.GETPLAYHISTORY)
    Observable<BaseResp<PlayHistory>> getHistoryList(@Field("page") String page);

    /**
     * socket地址
     */
    @POST(AppConfig.GETLINKURL)
    Observable<BaseResp<IPaddress>> getSocketUrl();

}
