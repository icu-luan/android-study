package com.example.chapter03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter03.utils.DateUtil;
import com.example.chapter03.utils.Utils;

public class TextViewActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv_now_time;
    private TextView tv_test;
    private TextView tv_font;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acativity_text_view);
        tv_font = findViewById(R.id.tv_font);
        Button btn_change = findViewById(R.id.btn_change);
        tv_now_time = findViewById(R.id.now_time);
        Bundle bundle = getIntent().getExtras();
        String request_time = bundle.getString("request_time");
        String request_content = bundle.getString("request_content");
        String result = String.format("收到请求消息：\n请求时间为%s\n请求内容为%s", request_time, request_content);
        tv_font.setText(result);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                TextView tv_font = findViewById(R.id.tv_font);
                ViewGroup.LayoutParams layoutParams = tv_font.getLayoutParams();
                layoutParams.width = Utils.dip2px(TextViewActivity.this, 300);
                tv_font.setLayoutParams(layoutParams);
            }
        });
        Button btn_long_click = findViewById(R.id.btn_long_click);
        btn_long_click.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String desc = String.format("%s 您点击了长按按钮 %s", DateUtil.getNowTime(), ((Button) v).getText());
                tv_now_time.setText(desc);
                return true;
            }
        });
        Button btn_able = findViewById(R.id.btn_able);
        Button btn_disable = findViewById(R.id.btn_disable);
        Button btn_test = findViewById(R.id.btn_test);
        //全局点击
//        btn_able.setOnClickListener(this);
        tv_test = findViewById(R.id.test_text);
        btn_able.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_test.setEnabled(true);
                tv_test.setText("已激活按钮" + DateUtil.getNowTime());
            }
        });
        btn_disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_test.setEnabled(false);
                tv_test.setText("已禁用按钮" + DateUtil.getNowTime());
            }
        });
        Button btn_return = findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                Intent intent = new Intent();
                bundle1.putString("response_time", DateUtil.getNowTime());
                bundle1.putString("response_content", "返回内容");
                intent.putExtras(bundle1);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_able) {

        }
    }
}
