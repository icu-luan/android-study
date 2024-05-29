package com.example.chapter03;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.chapter03.databinding.ActivityMainBinding;
import com.example.chapter03.http.HttpActivity;
import com.example.chapter03.recycler_view_demo.RecyclerActivity;
import com.example.chapter03.utils.DateUtil;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "cai";
    private TextView tv_hello;
    private ActivityMainBinding v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //RxJavaTest
//        try {
//            String a = "1";
//            try {
//                Thread.sleep(1000L);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            Log.d(TAG, Thread.currentThread().getName()+"主线程");
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Log.d(TAG, Thread.currentThread().getName()+"进入自定义线程");
//                    Looper mainLooper = Looper.getMainLooper();
//                    Handler handler = new Handler(mainLooper);
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.d(TAG, Thread.currentThread().getName()+"切换到主线程");
//                            int i = Integer.valueOf(a);
//                            Log.d(TAG, "i="+i);
//                        }
//                    });
//                }
//            },"自定义线程1").start();
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }
        Log.d(TAG, "MainActivity onCreate");
//        setContentView(R.layout.activity_main);

        //1.
        v = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        TextView a = v.btnFont;
//        v.btnFont.setTextColor(0x1111111);
//        v.btnFont.setOnClickListener(v -> {
//
//        });

        //2.
        ActivityMainBinding bing = ActivityMainBinding.inflate(getLayoutInflater(),null,false);
        setContentView(bing.getRoot());






        findViewById(R.id.btn_font).setOnClickListener(this);
        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_my).setOnClickListener(this);
        findViewById(R.id.btn_mid_control).setOnClickListener(this);
        findViewById(R.id.btn_find_password).setOnClickListener(this);
        findViewById(R.id.btn_data_storage).setOnClickListener(this);
        findViewById(R.id.btn_recycler_view).setOnClickListener(this);
        findViewById(R.id.btn_http_view).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String phoneNo = "12345";
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.btn_font:
                intent.setClass(MainActivity.this, TextViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("request_time", DateUtil.getNowTime());
                bundle.putString("request_content", tv_hello.getText().toString());
                intent.putExtras(bundle);
//                startActivity(intent);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_dial:
                intent.setAction(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:" + phoneNo);
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.btn_sms:
                intent.setAction(Intent.ACTION_SENDTO);
                Uri uri2 = Uri.parse("smsto:" + phoneNo);
                intent.setData(uri2);
                startActivity(intent);
                break;
            case R.id.btn_mid_control:
                intent.setClass(MainActivity.this,DrawableActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_find_password:
                intent.setClass(MainActivity.this,LoginMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_data_storage:
                intent.setClass(MainActivity.this,DataStorageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_view:
                intent.setClass(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_http_view:
                intent.setClass(MainActivity.this, HttpActivity.class);
                startActivity(intent);
                break;
            default:


        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity onDestroy");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras(); // 从返回的意图中获取快递包裹
            // 从包裹中取出名叫response_time的字符串
            String response_time = bundle.getString("response_time");
            // 从包裹中取出名叫response_content的字符串
            String response_content = bundle.getString("response_content");
            String desc = String.format("收到返回消息：\n应答时间为：%s\n应答内容为：%s",
                    response_time, response_content);
            tv_hello.setText(desc); // 把返回消息的详情显示在文本视图上
        }
    }
}