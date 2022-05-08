package com.example.upvoluntaryo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.net.ConnectException;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(user_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE NOT NULL, password TEXT)");
        MyDB.execSQL("create Table org(org_id INTEGER PRIMARY KEY AUTOINCREMENT, org_name TEXT UNIQUE NOT NULL, org_details TEXT)");
        MyDB.execSQL("create Table membership(membership_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "org_id INTEGER NOT NULL, user_id INTEGER NOT NULL," +
                "FOREIGN KEY (org_id) REFERENCES org (org_id)," +
                "FOREIGN KEY (user_id) REFERENCES users (user_id))");
        MyDB.execSQL("create Table events(event_id INTEGER PRIMARY KEY AUTOINCREMENT, event_name TEXT, event_address TEXT, event_details TEXT," +
                "org_id INTEGER NOT NULL," +
                "FOREIGN KEY (org_id) REFERENCES org (org_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion){
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists org");
        MyDB.execSQL("drop Table if exists membership");
        MyDB.execSQL("drop Table if exists events");
        onCreate(MyDB);
    }

    public Boolean insertUserData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select count(*) from users", null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        if (cursor.getCount() <= 0) contentValues.put("user_id", 0);

        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[] {username,password});

        if (cursor.getCount() > 0) {
            //debug
            cursor.moveToFirst();
            String log = cursor.getString(0);
            Log.d("DBHelper", "user_id:" + log);
            return true;
        }
        else
            return false;
    }
}
