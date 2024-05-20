package com.example.chapter03;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class MyApplication extends Application {

    private static final String TAG = "cai";

    private static MyApplication mApp;

    //声明一个公共的信息映射对象,当作全局变量使用
    public HashMap<String,String> infoMap = new HashMap<>();

    public static MyApplication getInstance(){
        return mApp;
    }

    //在app启动时
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Log.d(TAG, "MyApplication onCreate");
    }

    //在app终止时调用
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate");
    }

    //在配置改变时调用,例如从竖屏变为横屏
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged");
    }
}
