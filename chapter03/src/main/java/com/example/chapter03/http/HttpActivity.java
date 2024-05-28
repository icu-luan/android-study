package com.example.chapter03.http;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

import io.reactivex.Observable;

public class HttpActivity extends AppCompatActivity {
//    private NetManagerKT net = NetManager.;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        NetManager.INSTANCE.init();

        new Thread(() -> {
            NetManager.init();

            Object login = NetManager.imp.login("90:E8:68:B5:EF:F3");

            login.toString();
        }).start();



    }




}
