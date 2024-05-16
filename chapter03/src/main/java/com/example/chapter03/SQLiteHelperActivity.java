package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chapter03.database.UserDBHelper;
import com.example.chapter03.entity.User;
import com.example.chapter03.utils.ToastUtil;

public class SQLiteHelperActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "cai";
    private EditText et_name;
    private EditText et_age;
    private EditText et_high;
    private EditText et_weight;
    private CheckBox ck_married;
    private UserDBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_high = findViewById(R.id.et_high);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_select).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //获得数据库连接
        mHelper = UserDBHelper.getInstance(this);
        //打开读写
        mHelper.openReadLink();
        mHelper.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String height = et_high.getText().toString();
        String weight = et_weight.getText().toString();
        User user = null;
        switch (v.getId()){
            case R.id.btn_save:
                user = new User(name,Integer.parseInt(age),Long.parseLong(height),Float.parseFloat(weight),ck_married.isChecked());
                Log.d(TAG, user.toString());
                if (mHelper.insert(user) > 0){
                    ToastUtil.show(this,"添加成功");
                }else {
                    ToastUtil.show(this,"添加失败");
                }
                break;
//            case R.id.btn_delete:
//
//                break;
//            case R.id.btn_update:
//
//                break;
//            case R.id.btn_select:
//
//                break;
        }
    }
}