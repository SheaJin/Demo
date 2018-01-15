package com.qingxu.demoapp.model.api;

import android.util.ArrayMap;

import com.qingxu.demoapp.model.data.Register;
import com.qingxu.demoapp.model.data.TestModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

}
