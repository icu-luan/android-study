package com.example.chapter03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter03.database.LoginDBHelper;
import com.example.chapter03.entity.LoginInfo;
import com.example.chapter03.utils.ViewUtil;

import java.util.Random;

public class LoginMainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, View.OnFocusChangeListener {

    private static final String TAG = "cai";
    private TextView tv_password;
    private EditText et_password;
    private Button btn_forget;
    private CheckBox ck_remember;
    private EditText et_phone;
    private RadioButton rb_code;
    private RadioButton rb_password;
    private ActivityResultLauncher<Intent> register;
    private Button btn_login;
    private String mPassword = "111111";
    private String mVerifyCode;
    private SharedPreferences preferences;
    private LoginDBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        //给登录类型设置单选监听器
        RadioGroup rg_type = findViewById(R.id.rg_type);
        rg_type.setOnCheckedChangeListener(this);
        tv_password = findViewById(R.id.tv_password);
        et_password = findViewById(R.id.et_password);
        et_phone = findViewById(R.id.et_phone);
        btn_forget = findViewById(R.id.btn_forget);
        ck_remember = findViewById(R.id.ck_remember);
        //给手机号输入设置监听器
        rb_password = findViewById(R.id.rb_password);
        rb_code = findViewById(R.id.rb_code);
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone,11));
        et_password.addTextChangedListener(new HideTextWatcher(et_password,6));
        btn_forget.setOnClickListener(this);
        //给登录按钮设置监听
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                if (intent != null && result.getResultCode() == Activity.RESULT_OK){
                    mPassword = intent.getStringExtra("new_password");
                }

            }
        });

        et_password.setOnFocusChangeListener(this);

        //保存手机密码
//        preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    private void reload() {
        //记住密码优化
        LoginInfo info = mHelper.queryTop();
        if (info != null && info.remember){
            et_phone.setText(info.phone);
            et_password.setText(info.password);
            ck_remember.setChecked(true);
        }
//        boolean isRemember = preferences.getBoolean("isRemember", false);
//        if (isRemember){
//            String phone = preferences.getString("phone", null);
//            et_phone.setText(phone);
//            String password = preferences.getString("password", "");
//            et_password.setText(password);
//            ck_remember.setChecked(true);
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHelper = LoginDBHelper.getInstance(this);
        mHelper.openReadLink();
        mHelper.openWriteLink();
        reload();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHelper.closeLink();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_password:
                tv_password.setText(getString(R.string.login_password));
                et_password.setHint(getString(R.string.input_password));
                btn_forget.setText(getString(R.string.forget_password));
                ck_remember.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_code:
                tv_password.setText(getString(R.string.verifycode));
                et_password.setHint(getString(R.string.input_verifycode));
                btn_forget.setText(getString(R.string.get_verifycode));
                ck_remember.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString();
        switch (v.getId()){
            case R.id.btn_forget:
                if (!(phone.length() == 11)){
                    Toast.makeText(this,"请输入正确的手机号" ,Toast.LENGTH_LONG).show();
                    return;
                }
                //密码校验方式
                if (rb_password.isChecked()){
                    //携带手机号跳转到密码找回页面
                    Intent intent = new Intent(LoginMainActivity.this,LoginForgetActivity.class);
                    intent.putExtra("phone",phone);
                    register.launch(intent);

                }else if (rb_code.isChecked()){
                    //生成6位随机数字验证码
                    mVerifyCode = String.format("%06d",new Random().nextInt(999999));
                    //以下弹出提醒对话框,提示用户记住验证码
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("验证码");
                    builder.setMessage("手机号" + phone +"本次的验证码是:" + mVerifyCode);
                    builder.setPositiveButton("确认",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;
            case R.id.btn_login:
                //密码校验方式
                if (rb_password.isChecked()){
                    if (!mPassword.equals(et_password.getText().toString())){
                        Toast.makeText(this,"密码错误",Toast.LENGTH_LONG).show();
                        return;
                    }
                    loginSuccess();
                }else if (rb_code.isChecked()){
                    if (!mVerifyCode.equals(et_password.getText().toString())){
                        Toast.makeText(this,"验证码错误",Toast.LENGTH_LONG).show();
                        return;
                    }
                    loginSuccess();
                }
                break;
        }
    }

    //登录成功
    private void loginSuccess() {
        String desc = String.format("您的手机号码%s登录成功,请点击确认返回上一个页面",et_phone.getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录成功");
        builder.setMessage(desc);
        builder.setPositiveButton("确定返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //结束当前的活动页面
                finish();
            }
        });
        builder.setNegativeButton("我再看看",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
//        if (ck_remember.isChecked()){
//            SharedPreferences.Editor edit = preferences.edit();
//            edit.putString("phone",et_phone.getText().toString());
//            edit.putString("password",et_password.getText().toString());
//            edit.putBoolean("isRemember",ck_remember.isChecked());
//            Log.d(TAG, et_phone.getText().toString());
//            edit.commit();
//        }else {
//            SharedPreferences.Editor edit = preferences.edit();
//            edit.putString("phone",null);
//            edit.putString("password",null);
//            edit.putBoolean("isRemember",false);
//            Log.d(TAG, et_phone.getText().toString());
//            edit.commit();
//        }
        //优化记住密码,通过SQLite数据库保存
        LoginInfo info = new LoginInfo();
        info.phone = et_phone.getText().toString();
        info.password = et_password.getText().toString();
        info.remember = ck_remember.isChecked();
        mHelper.save(info);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.et_password && hasFocus){
            LoginInfo info = mHelper.queryByPhone(et_phone.getText().toString());
            if (info != null){
                et_password.setText(info.password);
                ck_remember.setChecked(info.remember);
            }else {
                //没查到,清空密码
                et_password.setText("");
                ck_remember.setChecked(false);
            }
        }
    }

    private class HideTextWatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;
        public HideTextWatcher(EditText et, int maxLength) {
            this.mMaxLength = maxLength;
            this.mView = et;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() == mMaxLength){
                ViewUtil.hideOneInputMethod(LoginMainActivity.this,mView);
            }

        }
    }
}