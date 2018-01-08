package com.qingxu.demoapp.api;

import android.util.ArrayMap;

import com.qingxu.demoapp.model.Register;
import com.qingxu.demoapp.model.TestModel;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
