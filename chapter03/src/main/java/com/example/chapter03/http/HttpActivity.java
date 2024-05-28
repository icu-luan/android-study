package com.example.chapter03.http;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter03.utils.ToastUtil;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HttpActivity extends AppCompatActivity {
//    private NetManagerKT net = NetManager.;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NetManagerKT.INSTANCE.init();

        Disposable d = NetManagerKT.imp.login("90:E8:68:B5:EF:F3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userInfoApiResult -> {
                    ToastUtil.show(this,userInfoApiResult.getData().getOrgName());
                }, throwable -> {
                    throwable.printStackTrace();
                });

//        new Thread(() -> {
//            NetManager.init();
//
//            Object login = NetManager.imp.login("90:E8:68:B5:EF:F3");
//
//            login.toString();
//        }).start();



    }




}
