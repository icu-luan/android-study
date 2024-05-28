package com.example.chapter03.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author: LiFan
 * @date: 2022/4/13
 * @Description:
 */
public interface RetrofitImpJava {
    @POST("/device/login")
    @FormUrlEncoded
    Object login( @Field("mac") String mac );

    @POST("/device/login")
    @FormUrlEncoded
    Observable<Object> login2( @Field("mac") String mac );
}
