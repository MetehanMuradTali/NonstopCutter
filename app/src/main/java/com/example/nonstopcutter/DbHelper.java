package com.example.nonstopcutter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context,"Userdata.db",null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="create Table UserDetails(id int primary key,maxscore int,gold int)";
        db.execSQL(sql);
        insertfirstdata(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql ="drop Table if exists UserDetails";
        db.execSQL(sql);
    }

    public void insertfirstdata(SQLiteDatabase db){
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",1);
        contentValues.put("maxscore",0);
        contentValues.put("gold",0);
        db.insert("UserDetails",null,contentValues);
    }
    public void updatemaxscore(SQLiteDatabase DB,int score){
        Cursor cursor =DB.rawQuery("Select * from UserDetails",null);
        cursor.moveToFirst();
        int data = cursor.getInt(cursor.getColumnIndexOrThrow("maxscore"));
        if(data<score){
            ContentValues contentValues=new ContentValues();
            contentValues.put("maxscore",score);
            DB.update("UserDetails",contentValues,"id=?",new String[]{"1"});
        }
    }

    public int getmaxscore(SQLiteDatabase DB) {
        Cursor cursor =DB.rawQuery("Select * from UserDetails",null);
        cursor.moveToFirst();
        int data = cursor.getInt(cursor.getColumnIndexOrThrow("maxscore"));
        return data;
    }
}
