package com.example.chapter03;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.room.Room;

import com.example.chapter03.database.BookDataBase;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;

public class MyApplication extends Application {

    private static final String TAG = "cai";

    private static MyApplication mApp;

    //声明一个公共的信息映射对象,当作全局变量使用
    public HashMap<String,String> infoMap = new HashMap<>();

    //声明一个书籍数据库对象
    private BookDataBase bookDataBase;

    public static MyApplication getInstance(){
        return mApp;
    }

    //在app启动时
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Log.d(TAG, "MyApplication onCreate");

        //构建书籍数据库的实例
//        bookDataBase = Room.databaseBuilder(this,BookDataBase.class,"book")
//                //允许迁移数据库(发生数据库变更时,Room默认删除原数据库再创建新数据库,如此一来原来的记录会丢失
//                .addMigrations()
//                //允许在主线程中操作数据库(ROOM默认不能在主线程中操作数据库
//                .allowMainThreadQueries().build();
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

    public BookDataBase getBookDataBase(){
        return bookDataBase;
    }
}
