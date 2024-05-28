package com.example.chapter03.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: LiFan
 * @date: 2022/4/13
 * @Description:
 */
public class NetManager {
//    public static NetManager instance;
    public static RetrofitImpJava imp;

    public static void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(600, TimeUnit.SECONDS)
                .writeTimeout(600, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .connectTimeout(5, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://119.45.189.43:8182")
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        imp = retrofit.create(RetrofitImpJava.class);
    }

    public static Gson createGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
}
