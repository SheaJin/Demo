package com.qingxu.demoapp.model.api;

import android.util.ArrayMap;

import com.qingxu.demoapp.model.data.mine.ESUser;
import com.qingxu.demoapp.model.data.match.MatchListBean;
import com.qingxu.demoapp.model.data.mine.Register;
import com.qingxu.demoapp.model.data.mine.TestModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by JinXinYi on 2018/1/7.
 */

public interface ApiService {
    /**
     * 测试接口
     * */
    @FormUrlEncoded
    @POST(AppConfig.TEST_URL)
    Observable<TestModel> testPost(@FieldMap ArrayMap<String, String> params);

    /**
     * 注册
     * */
    @FormUrlEncoded
    @POST(AppConfig.REGISTER)
    Observable<Register> register(@FieldMap ArrayMap<String, String> params);

    /**
     * 上传图片
     */
    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part List<MultipartBody.Part> partList);

    /**
     * 赛事列表
     */
    @Headers({"baseUrl:match"})
    @GET("/match/listByDate")
    Observable<MatchListBean> getMatchsByData(@Query("date") String date);

    /**
     * 我的
     */
//    @Headers({"baseUrl",AppConfig.RAYMALL})
    @GET("/player/statsRank")
    Observable<ResponseBody> getStatsRank(@Query("statType") String statType, @Query("num") int num, @Query("tabType") String tabType, @Query("seasonId") String seasonId);

    /**
     * 球队排名
     */
    @GET("/team/rank")
    Observable<ResponseBody> getTeamsRank();

    /**
     * 获取用户信息
     */
    @Headers({"baseUrl:info"})
    @FormUrlEncoded
    @POST("easysport/user/get_user_info.do")
    Observable<ESUser> getUserInfo(@Field("username") String username,
                                   @Field("password") String password);
}
