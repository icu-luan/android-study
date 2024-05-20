package com.example.chapter03;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyApplication extends Application {

    private static final String TAG = "cai";

    //在app启动时
    @Override
    public void onCreate() {
        super.onCreate();
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
