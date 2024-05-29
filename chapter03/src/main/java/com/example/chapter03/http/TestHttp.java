package com.example.chapter03.http;

import android.os.Handler;
import android.os.Looper;

public class TestHttp {
    public static void main(String[] args) {
        String a = "1";
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+"主线程");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"进入自定义线程");
                Looper mainLooper = Looper.getMainLooper();
                Handler handler = new Handler(mainLooper);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+"切换到主线程");
                    }
                });
            }
        },"自定义线程1").start();
    }
}
