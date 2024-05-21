package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DataStorageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
        findViewById(R.id.btn_share_write).setOnClickListener(this);
        findViewById(R.id.btn_database).setOnClickListener(this);
        findViewById(R.id.btn_database_operate).setOnClickListener(this);
        findViewById(R.id.btn_outfile).setOnClickListener(this);
        findViewById(R.id.btn_image_write).setOnClickListener(this);
        findViewById(R.id.btn_app_write).setOnClickListener(this);
        findViewById(R.id.btn_room_write).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_share_write:
                intent.setClass(this,ShareWriteActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_database:
                intent.setClass(this,DataBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_database_operate:
                intent.setClass(this,SQLiteHelperActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_outfile:
                intent.setClass(this,FileWriteActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_image_write:
                intent.setClass(this,ImageWriteActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_app_write:
                intent.setClass(this,AppWriteActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_room_write:
                intent.setClass(this,RoomWriteActivity.class);
                startActivity(intent);
                break;
        }
    }
}