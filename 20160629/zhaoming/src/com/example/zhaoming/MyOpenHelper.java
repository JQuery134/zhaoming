package com.example.zhaoming;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
/**
 * Created by Administrator on 2016/3/14/014.
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context, String name, CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    //鏁版嵁搴撳垱寤烘椂锛屾鏂规硶浼氳皟鐢�
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id integer primary key autoincrement,name char(10), password char(20))");

    }

    //鏁版嵁搴撳崌绾ф椂锛屾鏂规硶浼氳皟鐢�
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("鏁版嵁搴撳崌绾т簡");
    }
}
