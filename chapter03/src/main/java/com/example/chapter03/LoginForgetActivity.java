package com.example.chapter03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "cai";
    private String mVerifyCode;
    private String mPhone;
    private EditText et_new_password;
    private EditText et_new_password_again;
    private EditText et_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);
        et_new_password = findViewById(R.id.et_new_password);
        et_new_password_again = findViewById(R.id.et_new_password_again);
        et_code = findViewById(R.id.et_code);
        Button btn_getcode = findViewById(R.id.btn_getcode);
        Button btn_confirm = findViewById(R.id.btn_confirm);
        mPhone = getIntent().getStringExtra("phone");
        //给获取验证码设置监听
        btn_getcode.setOnClickListener(this);
        //给确认设置监听
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_getcode:
                mVerifyCode = String.format("%06d",new Random().nextInt(999999));
                String desc = String.format("手机号" + mPhone + "的验证码为:"+mVerifyCode);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("验证码");
                builder.setMessage(desc);
                builder.setPositiveButton("确认",null);
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.btn_confirm:
                String first_password = et_new_password.getText().toString();
                String second_password = et_new_password_again.getText().toString();
                String code = et_code.getText().toString();

                if (first_password.length() < 6||second_password.length() < 6){
                    Toast.makeText(this,"密码长度小于6,请重新输入密码",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!first_password.equals(second_password)){
                    Log.d(TAG, first_password + "," + second_password );
                    Toast.makeText(this,"两次密码不相等,请重新输入密码",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!mVerifyCode.equals(code)){
                    Toast.makeText(this,"验证码错误,请重新输入",Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(this,"密码修改成功",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("new_password",et_new_password.getText().toString());
                setResult(Activity.RESULT_OK,intent);
                finish();
                break;
        }

    }
}