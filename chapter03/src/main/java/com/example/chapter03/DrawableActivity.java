package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chapter03.utils.DateUtil;

public class DrawableActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView sw_result;
    private TextView ck_sw_result;

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
}