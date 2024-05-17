package com.example.chapter03.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chapter03.entity.LoginInfo;
import com.example.chapter03.entity.User;
import com.example.chapter03.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;


public class LoginDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "login.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "login_info";
    private static final String TAG = "cai";
    private static LoginDBHelper mHelper = null;
    private SQLiteDatabase mRDB = null;
    private SQLiteDatabase mWDB = null;

    public LoginDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //利用单例模式获取数据库帮助器的唯一实例
    public static LoginDBHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new LoginDBHelper(context);
        }
        return mHelper;
    }

    //打开数据库的读连接
    public SQLiteDatabase openReadLink() {
        if (mRDB == null || !mRDB.isOpen()) {
            mRDB = mHelper.getReadableDatabase();
        }
        return mRDB;
    }

    //打开数据库的写连接
    public SQLiteDatabase openWriteLink() {
        if (mWDB == null || !mWDB.isOpen()) {
            mWDB = mHelper.getWritableDatabase();
        }
        return mWDB;
    }

    //关闭数据库连接
    public void closeLink() {
        if (mRDB != null && mRDB.isOpen()) {
            mRDB.close();
            mRDB = null;
        }
        if (mWDB != null && mWDB.isOpen()) {
            mWDB.close();
            mWDB = null;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "phone VARCHAR NOT NULL," +
                "password VARCHAR NOT NULL," +
                "remember INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    //版本更新后,需要添加字段时使用
    //修改版本号DB_VERSION后,会自动执行方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void save(LoginInfo info){
        //如果存在则先删除,再添加
        try{
            mWDB.beginTransaction();
            delete(info);
            insert(info);
            mWDB.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mWDB.endTransaction();
        }

    }

    //删除
    public long delete(LoginInfo info){
        return mWDB.delete(TABLE_NAME, "phone=?", new String[]{info.phone});
    }

    //添加
    public long insert(LoginInfo info) {
        ContentValues values = new ContentValues();
        values.put("phone", info.phone);
        values.put("password", info.password);
        values.put("remember", info.remember);
        return mWDB.insert(TABLE_NAME, null, values);
    }

    //删除
    public long deleteByName(String name, String age) {
        //删除所有
//        mWDB.delete(TABLE_NAME,"1=1",null);
        return mWDB.delete(TABLE_NAME, "name=? and age=?", new String[]{name, age});
    }

    //查询所有
    public List<User> queryAll() {
        List<User> list = new ArrayList<>();
        Cursor cursor = mRDB.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.age = cursor.getInt(2);
            user.height = cursor.getLong(3);
            user.weight = cursor.getFloat(4);
            //SQLite没有布尔型,用0代表false,1代表true
            user.married = (cursor.getInt(5) == 0) ? false : true;
            list.add(user);
        }
        return list;
    }

    //条件查询
    public List<User> queryByName(String name) {
        List<User> list = new ArrayList<>();
        Cursor cursor = mRDB.query(TABLE_NAME, null, "name=?", new String[]{name}, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.age = cursor.getInt(2);
            user.height = cursor.getLong(3);
            user.weight = cursor.getFloat(4);
            //SQLite没有布尔型,用0代表false,1代表true
            user.married = (cursor.getInt(5) == 0) ? false : true;
            list.add(user);
        }
        return list;
    }

    public LoginInfo queryTop() {
        LoginInfo info = null;
        String sql = "select * from " + TABLE_NAME + " where remember = 1 ORDER BY _id DESC limit 1";
        Cursor cursor = mRDB.rawQuery(sql,null);
        if (cursor.moveToNext()) {
            info = new LoginInfo();
            info.id = cursor.getInt(0);
            info.phone = cursor.getString(1);
            info.password = cursor.getString(2);
            //SQLite没有布尔型,用0代表false,1代表true
            info.remember = (cursor.getInt(3) == 0) ? false : true;
        }
        return info;
    }

    public LoginInfo queryByPhone(String phone){
        LoginInfo info = null;
        String sql = "select * from " + TABLE_NAME + " where phone = "+ phone;
        Cursor cursor = mRDB.rawQuery(sql,null);
        if (cursor.moveToNext()) {
            info = new LoginInfo();
            info.id = cursor.getInt(0);
            info.phone = cursor.getString(1);
            info.password = cursor.getString(2);
            //SQLite没有布尔型,用0代表false,1代表true
            info.remember = (cursor.getInt(3) == 0) ? false : true;
        }
        return info;
    }
}
