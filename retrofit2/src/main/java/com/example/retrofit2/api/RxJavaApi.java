package com.example.retrofit2.api;

import com.example.retrofit2.bean.Bean2;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by xxsj on 2017/11/2.
 */

public interface RxJavaApi {

    @FormUrlEncoded
    @POST("region")
    Observable<Bean2> getData(@Field("city") String city, @Field("key")String key);
}
