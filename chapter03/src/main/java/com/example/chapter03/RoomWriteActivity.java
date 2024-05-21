package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.chapter03.dao.BookDao;
import com.example.chapter03.entity.BookInfo;
import com.example.chapter03.entity.User;
import com.example.chapter03.utils.ToastUtil;

import java.util.List;

public class RoomWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "cai";
    private EditText et_name;
    private EditText et_author;
    private EditText et_press;
    private EditText et_price;
    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_write);
        et_name = findViewById(R.id.et_name);
        et_author = findViewById(R.id.et_author);
        et_press = findViewById(R.id.et_press);
        et_price = findViewById(R.id.et_price);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_select).setOnClickListener(this);

        bookDao = MyApplication.getInstance().getBookDataBase().bookDao();
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String author = et_author.getText().toString();
        String press = et_press.getText().toString();
        String price = et_price.getText().toString();
        switch (v.getId()){
            case R.id.btn_save:
                BookInfo b1 = new BookInfo();
                b1.setName(name);
                b1.setAuthor(author);
                b1.setPress(press);
                b1.setPrice(Double.parseDouble(price));
                bookDao.insert(b1);
                ToastUtil.show(this,"保存成功");
                break;
            case R.id.btn_delete:
                BookInfo b2 = new BookInfo();
                b2.setId(1);
                bookDao.delete(b2);
                break;
            case R.id.btn_update:
                BookInfo b3 = new BookInfo();
                BookInfo b4 = bookDao.queryByName(name);
                b3.setId(b4.getId());
                b3.setName(name);
                b3.setAuthor(author);
                b3.setPress(press);
                b3.setPrice(Double.parseDouble(price));
                bookDao.update(b3);
                break;
            case R.id.btn_select:
                List<BookInfo> list = bookDao.queryAll();
                for (BookInfo info : list) {
                    Log.d(TAG, info.toString());
                }
                break;
        }

    }
}