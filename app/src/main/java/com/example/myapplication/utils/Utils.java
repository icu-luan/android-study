package com.example.myapplication.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {

    //根据手机的分辨率从dp单位转为px(像素)
    public static int dip2px(Context context,float dpValue){
        //获取当前手机的像素密度
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale+0.5f);
    }
}
