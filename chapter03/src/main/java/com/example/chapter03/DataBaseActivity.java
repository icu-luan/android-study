package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DataBaseActivity extends AppCompatActivity implements View.OnClickListener {

    private String mDatabaseName;
    private TextView tv_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        findViewById(R.id.btn_create_database).setOnClickListener(this);
        findViewById(R.id.btn_delete_database).setOnClickListener(this);
        tv_database = findViewById(R.id.tv_database);
        mDatabaseName = getFilesDir() + "/test.db";
    }

    @Override
    public void onClick(View v) {
        String desc = null;
        switch (v.getId()){
            case R.id.btn_create_database:
                SQLiteDatabase db = openOrCreateDatabase(mDatabaseName, Context.MODE_PRIVATE, null);
                desc = String.format("数据库%s创建%s",db.getPath(),(db!=null)?"成功":"失败");
                tv_database.setText(desc);
                break;
            case R.id.btn_delete_database:
                boolean result = deleteDatabase(mDatabaseName);
                desc = String.format("数据库%s删除%s",mDatabaseName,result?"成功":"失败");
                tv_database.setText(desc);
                break;
        }
    }
}