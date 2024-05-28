package com.example.chapter03.http


import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author: LiFan
 * @date: 2022/4/13
 * @Description:
 */
internal interface RetrofitImpKT {
    @POST("/device/login")
    @FormUrlEncoded
    fun login(
        @Field("mac") mac: String,
    ): Observable<ApiResult<UserInfo>>

    @POST("/device/login")
    @FormUrlEncoded
    fun login2(
        @Field("mac") mac: String,
    ): ApiResult<UserInfo>
}