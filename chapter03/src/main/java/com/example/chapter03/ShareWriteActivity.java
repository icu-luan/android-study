package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ShareWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_high;
    private EditText et_weight;
    private CheckBox ck_married;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_write);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_high = findViewById(R.id.et_high);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.btn_save).setOnClickListener(this);

        preferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        reload();
    }

    private void reload() {
        String name = preferences.getString("name", null);
        if (name != null){
            et_name.setText(name);
        }

        int age = preferences.getInt("age", 0);
        if (age != 0){
            et_age.setText(String.valueOf(age));
        }

        float high = preferences.getFloat("high", 0f);
        if (high != 0f){
            et_high.setText(String.valueOf(high));
        }

        float weight = preferences.getFloat("weight", 0f);
        if (weight != 0f){
            et_weight.setText(String.valueOf(weight));
        }

        boolean married = preferences.getBoolean("married", false);
        ck_married.setChecked(married);

    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String high = et_high.getText().toString();
        String weight = et_weight.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name",name);
        editor.putInt("age",Integer.parseInt(age));
        editor.putFloat("high",Float.parseFloat(high));
        editor.putFloat("weight",Float.parseFloat(weight));
        editor.putBoolean("married",ck_married.isChecked());
        editor.commit();
        Toast.makeText(this,"已保存到共享参数",Toast.LENGTH_LONG).show();
    }
}