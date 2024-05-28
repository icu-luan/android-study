package com.example.chapter03.http;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: LiFan
 * @date: 2024/5/28
 * @Description:
 */

public class RxJavaTest {
    public static void run() {
        Disposable d = Observable
                .fromArray("1")
                //delay操作符
                .delay(1000, TimeUnit.MILLISECONDS)
                //切换到IO线程
                .observeOn(Schedulers.io())
                .doOnNext(
                        string -> printThread()
                )
                //切换到主线程
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        string -> printThread()
                )
                //转换数据类型
                .map(
                        string -> Integer.valueOf(string)
                )
                //订阅
                .subscribe(s -> {
                    //经过 map 操作符之后 s 是一个int
                    print(s);
                }, t -> {
                    //流程中的错误处理
                    t.printStackTrace();
                });
    }

    public static void printThread() {
        Log.i("cql", "当前线程" + Thread.currentThread().getName());
    }

    public static void print(Object obj) {
        Log.i("cql", obj.toString());
    }
}
