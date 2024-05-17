package com.example.chapter03.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;

import com.example.chapter03.entity.User;
import com.example.chapter03.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;


public class UserDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "user_info";
    private static UserDBHelper mHelper = null;
    private SQLiteDatabase mRDB = null;
    private SQLiteDatabase mWDB = null;

    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //利用单例模式获取数据库帮助器的唯一实例
    public static UserDBHelper getInstance(Context context){
        if(mHelper == null){
            mHelper = new UserDBHelper(context);
        }
        return mHelper;
    }

    //打开数据库的读连接
    public SQLiteDatabase openReadLink(){
        if (mRDB == null || !mRDB.isOpen()){
            mRDB = mHelper.getReadableDatabase();
        }
        return mRDB;
    }

    //打开数据库的写连接
    public SQLiteDatabase openWriteLink(){
        if (mWDB == null || !mWDB.isOpen()){
            mWDB = mHelper.getWritableDatabase();
        }
        return mWDB;
    }

    //关闭数据库连接
    public void closeLink(){
        if (mRDB != null && mRDB.isOpen()){
            mRDB.close();
            mRDB = null;
        }
        if (mWDB != null && mWDB.isOpen()){
            mWDB.close();
            mWDB = null;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+ " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR NOT NULL," +
                "age INTEGER NOT NULL," +
                "height LONG NOT NULL," +
                "weight FLOAT NOT NULL," +
                "married INTEGER NOT NULL," +
                "update_time VARCHAR NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //添加
    public long insert(User user){
        ContentValues values = new ContentValues();
        values.put("name",user.name);
        values.put("age",user.age);
        values.put("height",user.height);
        values.put("weight",user.weight);
        values.put("married",user.married);
        values.put("update_time", DateUtil.getNowTime());
        return mWDB.insert(TABLE_NAME,null,values);
    }

    //删除
    public long deleteByName(String name , String age){
        //删除所有
//        mWDB.delete(TABLE_NAME,"1=1",null);
        return mWDB.delete(TABLE_NAME,"name=? and age=?",new String[]{name,age});
    }

    //修改
    public long update(User user){
        ContentValues values = new ContentValues();
        values.put("name",user.name);
        values.put("age",user.age);
        values.put("height",user.height);
        values.put("weight",user.weight);
        values.put("married",user.married);
        values.put("update_time", DateUtil.getNowTime());
        return mWDB.update(TABLE_NAME,values,"name=?",new String[]{user.name});
    }

    //查询所有
    public List<User> queryAll(){
        List<User> list = new ArrayList<>();
        Cursor cursor = mRDB.query(TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            User user = new User();
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.age = cursor.getInt(2);
            user.height = cursor.getLong(3);
            user.weight = cursor.getFloat(4);
            //SQLite没有布尔型,用0代表false,1代表true
            user.married = (cursor.getInt(5) == 0) ?false:true;
            list.add(user);
        }
        return list;
    }
    //条件查询
    public List<User> queryByName(String name){
        List<User> list = new ArrayList<>();
        Cursor cursor = mRDB.query(TABLE_NAME,null,"name=?",new String[]{name},null,null,null);
        while (cursor.moveToNext()){
            User user = new User();
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.age = cursor.getInt(2);
            user.height = cursor.getLong(3);
            user.weight = cursor.getFloat(4);
            //SQLite没有布尔型,用0代表false,1代表true
            user.married = (cursor.getInt(5) == 0) ?false:true;
            list.add(user);
        }
        return list;
    }
}
