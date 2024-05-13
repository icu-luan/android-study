package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter03.utils.DateUtil;
import com.example.chapter03.utils.ViewUtil;

import java.util.Calendar;

public class DrawableActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView sw_result;
    private TextView ck_sw_result;
    private TextView rb_result;
    private EditText et_phone;
    private TextView tv_alert;
    private DatePicker dp_date;
    private TextView tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        //checkbox
        CheckBox ck_system = findViewById(R.id.ck_system);
        CheckBox ck_custom = findViewById(R.id.ck_custom);
        ck_system.setOnCheckedChangeListener(this);
        ck_custom.setOnCheckedChangeListener(this);
        //switch
        Switch sw_status = findViewById(R.id.sw_status);
        sw_result = findViewById(R.id.sw_result);
        sw_status.setOnCheckedChangeListener(this);
        //通过checkbox实现switch
        CheckBox ck_switch = findViewById(R.id.ck_switch);
        ck_switch.setOnCheckedChangeListener(this);
        ck_sw_result = findViewById(R.id.ck_sw_result);
        //单选按钮
        RadioGroup rb_gender = findViewById(R.id.rb_gender);
        rb_gender.setOnCheckedChangeListener(this);
        rb_result = findViewById(R.id.rb_result);
        //焦点变更监听器
        et_phone = findViewById(R.id.et_phone);
        EditText et_password = findViewById(R.id.et_password);
        et_password.setOnFocusChangeListener(this);
        //文本变化监听器
        EditText et_phone1 = findViewById(R.id.et_phone1);
        EditText et_password1 = findViewById(R.id.et_password1);
        et_phone1.addTextChangedListener(new HideTextWatcher(et_phone1,11));
        et_password1.addTextChangedListener(new HideTextWatcher(et_password1,6));
        //提醒对话框
        findViewById(R.id.btn_alert).setOnClickListener(this);
        tv_alert = findViewById(R.id.tv_alert);
        //日期对话框
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_date).setOnClickListener(this);
        dp_date = findViewById(R.id.dp_date);
        tv_date = findViewById(R.id.tv_date);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.ck_system:
            case R.id.ck_custom:
                String desc = String.format("您%s了这个checkbox",isChecked? "勾选":"取消勾选");
                buttonView.setText(desc);
                break;
            case R.id.sw_status:
                String desc2 = String.format("您%s了这个switch开关",isChecked? "打开":"关闭");
                sw_result.setText(desc2);
                break;
            case R.id.ck_switch:
                String desc3 = String.format("您%s了这个checkbox改成的switch开关",isChecked? "打开":"关闭");
                ck_sw_result.setText(desc3);
                break;
        }


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_male:
                rb_result.setText("您的性别是男");
                break;
            case R.id.rb_female:
                rb_result.setText("您的性别是女");
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            String phone = et_phone.getText().toString();
            if (TextUtils.isEmpty(phone) || phone.length()<11){
                et_phone.requestFocus();
                Toast.makeText(this,"请输入11位手机号码",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_alert:
                //创建提醒对话框构造器
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //设置对话框的标题文本
                builder.setTitle("尊敬的用户");
                //设置对话框的文本内容
                builder.setMessage("确认卸载?");
                //设置对话框的肯定按钮文本及其点击监听器
                builder.setPositiveButton("残忍卸载", (dialog, which) -> {
                    tv_alert.setText("只能离开了");
                });
                //设置对话框的否定按钮文本及其点击监听器
                builder.setNegativeButton("我在想想", (dialog, which) -> {
                    tv_alert.setText("再陪一会儿");
                });
                //根据建造器构建提醒对话框对象
                AlertDialog alertDialog = builder.create();
                //显示提醒对话框
                alertDialog.show();
                break;
            case R.id.btn_ok:
                String desc =String.format("您选择的日期是%d年%d月%d日",dp_date.getYear(),dp_date.getMonth()+1,dp_date.getDayOfMonth());
                tv_date.setText(desc);
                break;
            case R.id.btn_date:
                //获取日历的一个实例
//                Calendar calendar = Calendar.getInstance();
//                calendar.get(Calendar.YEAR);
//                calendar.get(Calendar.MONTH);
//                calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(this, this, 2090, 5, 11);
                dialog.show();
                break;


        }


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc =String.format("您选择的日期是%d年%d月%d日",year,month+1,dayOfMonth);
        tv_date.setText(desc);
    }

    private class HideTextWatcher implements TextWatcher {
        //声明一个编辑框对象
        private EditText mView;
        //声明一个最大长度变量
        private int mMaxLength;
        public HideTextWatcher(EditText v, int maxLength) {
            this.mView = v;
            this.mMaxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        //在编辑框的输入文本变化后触发
        @Override
        public void afterTextChanged(Editable s) {
            //获得已输入的文本字符串
            String str = s.toString();
            //输入文本达到11位,或者达到6位时关闭输入法
            if (str.length() == mMaxLength){
                //隐藏输入法软键盘
                ViewUtil.hideOneInputMethod(DrawableActivity.this,mView);
            }

        }
    }
}