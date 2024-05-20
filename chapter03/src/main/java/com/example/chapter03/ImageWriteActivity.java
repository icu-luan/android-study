package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chapter03.utils.FileUtil;
import com.example.chapter03.utils.ToastUtil;

import java.io.File;

public class ImageWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "cai";
    private ImageView iv_content;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_write);
        iv_content = findViewById(R.id.iv_content);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                String fileName = System.currentTimeMillis() + ".jpeg";
                //获取当前app的私有下载目录
                path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar + fileName;
                Log.d(TAG, path);
                //从指定资源文件获取位图对象
                Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.checked);
                //吧位图对象保存为图片文件
                FileUtil.saveImage(path, b1);
                ToastUtil.show(this, "保存成功");

                break;
            case R.id.btn_read:
                Bitmap b2 = FileUtil.openImage(path);
                iv_content.setImageBitmap(b2);

//                Bitmap b2 = BitmapFactory.decodeFile(path);
//                iv_content.setImageBitmap(b2);
                //直接调用setImageURI方式,设置图像视图的路径对象
                iv_content.setImageURI(Uri.parse(path));

                break;
        }
    }
}