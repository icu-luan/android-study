package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    private String first_num="";
    private String operator="";
    private String second_num="";
    private String result="";
    private String show_text="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        tv_result = findViewById(R.id.tv_result);

        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.chu).setOnClickListener(this);
        findViewById(R.id.chen).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.jian).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.genhao).setOnClickListener(this);
        findViewById(R.id.daoshu).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.dian).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String inputText;
        if (v.getId() == R.id.genhao){
            inputText = "√";
        }else {
            inputText = ((TextView)v).getText().toString();
        }
        switch (v.getId()){
            case R.id.clear:
                clear();
                break;
            case R.id.btn_cancel:
                break;
            case R.id.plus:
            case R.id.jian:
            case R.id.chen:
            case R.id.chu:
                operator = inputText;
                refreshText(show_text + operator);
                break;
            case R.id.equal:
                double calculator_result = calculateFour();
                refreshOperator(String.valueOf(calculator_result));
                refreshText(show_text + "=" + result);
                break;
            case R.id.genhao:
                double genhao_result = Math.sqrt(Double.parseDouble(first_num));
                refreshOperator(String.valueOf(genhao_result));
                refreshText(show_text + "√=" + result);
                break;
            case R.id.daoshu:
                double daoshu_result = 1.0/Double.parseDouble(first_num);
                refreshOperator(String.valueOf(daoshu_result));
                refreshText(show_text + "/+" + result);
                break;
            default:
                if (result.length()>0 &&operator.equals("")){
                    clear();
                }
                if (operator.equals("")){
                    first_num = first_num + inputText;
                }else {
                    second_num = second_num + inputText;
                }
                //整数前面不需要0
                if (show_text.equals("0")&&!inputText.equals(".")){
                    refreshText(inputText);
                }else {
                    refreshText(show_text + inputText);
                }
        }
    }

    private double calculateFour() {
        switch (operator){
            case "+":
                return Double.parseDouble(first_num) + Double.parseDouble(second_num);
            case "-":
                return Double.parseDouble(first_num) - Double.parseDouble(second_num);
            case "×":
                return Double.parseDouble(first_num) * Double.parseDouble(second_num);
            default:
                return Double.parseDouble(first_num) / Double.parseDouble(second_num);
        }
    }

    private void clear() {
        refreshText("");
        refreshOperator("");
    }
    private void refreshOperator(String new_result){
        result = new_result;
        first_num = result;
        second_num = "";
        operator = "";
    }

    private void refreshText(String text){
        show_text = text;
        tv_result.setText(show_text);
    }
}