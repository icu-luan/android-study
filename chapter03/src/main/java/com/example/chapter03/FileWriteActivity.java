package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter03.utils.FileUtil;
import com.example.chapter03.utils.ToastUtil;

import java.io.File;
import java.net.PasswordAuthentication;

public class FileWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "cai";
    private EditText et_name;
    private EditText et_age;
    private EditText et_high;
    private EditText et_weight;
    private CheckBox ck_married;
    private String path;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_write);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_high = findViewById(R.id.et_high);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);
        tv_text = findViewById(R.id.tv_text);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                String name = et_name.getText().toString();
                String age = et_age.getText().toString();
                String high = et_high.getText().toString();
                String weight = et_weight.getText().toString();

                StringBuilder sb = new StringBuilder();
                sb.append("姓名:").append(name);
                sb.append("\n年龄:").append(age);
                sb.append("\n身高:").append(high);
                sb.append("\n体重:").append(weight);
                sb.append("\n婚否:").append(ck_married.isChecked() ? "是":"否");

                String fileName = System.currentTimeMillis() + ".txt";
                String directory = null;
                //外部存储的私有空间,会随着应用卸载删除
                directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();

                //外部存储的公共空间,不会随应用卸载删除
                directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

                //内部存储空间
                directory = getFilesDir().toString();

                //File.separator等于斜杠"\"
                path = directory + File.separator + fileName;
                Log.d(TAG, path);
                FileUtil.saveText(path,sb.toString());
                ToastUtil.show(this,"保存成功");
                break;
            case R.id.btn_read:
                tv_text.setText(FileUtil.readText(path));

                break;
        }
    }
}