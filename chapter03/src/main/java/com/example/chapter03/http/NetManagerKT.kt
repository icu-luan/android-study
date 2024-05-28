package com.example.chapter03.http

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @author: LiFan
 * @date: 2022/4/13
 * @Description:
 */
internal object NetManagerKT {
    lateinit var imp: RetrofitImpKT

    fun init() {
        val builder =
            OkHttpClient.Builder()
                .readTimeout(600, TimeUnit.SECONDS) //上传文件限制
                .writeTimeout(600, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .connectTimeout(5, TimeUnit.SECONDS)
        val client = builder.build()


        val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://119.45.189.43:8182") // 设置网络请求的公共Url地址
            .addConverterFactory(GsonConverterFactory.create(createGson())) // 设置数据解析器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
            .build()
        imp = retrofit.create(RetrofitImpKT::class.java)
    }

    fun createGson(): Gson {
        val builder = GsonBuilder()
//        builder.registerTypeAdapter(String::class.java, StringDefaultAdapter())
        return builder.create()
    }


}