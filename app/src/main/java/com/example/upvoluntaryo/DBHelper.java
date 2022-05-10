package com.example.upvoluntaryo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.upvoluntaryo.objects.Event;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ORGS = "org";
    private static final String TABLE_MEMBERSHIP = "membership";
    private static final String TABLE_EVENTS = "events";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table " + TABLE_USERS +
                "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE NOT NULL, " +
                "password TEXT)");
        MyDB.execSQL("create Table " + TABLE_ORGS +
                "(org_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "org_name TEXT UNIQUE NOT NULL, " +
                "org_details TEXT)");
        MyDB.execSQL("create Table " + TABLE_MEMBERSHIP +
                "(membership_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "org_id INTEGER NOT NULL, " +
                "user_id INTEGER NOT NULL," +
                "FOREIGN KEY (org_id) REFERENCES " + TABLE_ORGS + " (org_id)," +
                "FOREIGN KEY (user_id) REFERENCES " + TABLE_USERS + " (user_id))");
        MyDB.execSQL("create Table " + TABLE_EVENTS +
                "(event_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "event_name TEXT, " +
                "event_address TEXT, " +
                "event_details TEXT," +
                "org_id INTEGER NOT NULL," +
                "FOREIGN KEY (org_id) REFERENCES " + TABLE_ORGS + " (org_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion){
        MyDB.execSQL("drop Table if exists " + TABLE_USERS);
        MyDB.execSQL("drop Table if exists " + TABLE_ORGS);
        MyDB.execSQL("drop Table if exists " + TABLE_MEMBERSHIP);
        MyDB.execSQL("drop Table if exists " + TABLE_EVENTS);
        onCreate(MyDB);
    }

    public Boolean insertUserData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select count(*) from " + TABLE_USERS, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        if (cursor.getCount() <= 0) contentValues.put("user_id", 0);

        long result = MyDB.insert(TABLE_USERS, null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE_USERS + " where username = ?", new String[] {username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from " + TABLE_USERS + " where username = ? and password = ?", new String[] {username,password});

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

    public ArrayList<Event> listEvents(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ArrayList<Event> eventList = new ArrayList<>();
        Cursor cursor = MyDB.rawQuery("select * from " + TABLE_EVENTS, null);
        if (cursor.moveToFirst()){
            do {
                int eventID = Integer.parseInt(cursor.getString(0));
                String eventName = cursor.getString(1);
                String eventAddress = cursor.getString(2);
                String eventDetails = cursor.getString(3);
                int orgID = Integer.parseInt(cursor.getString(4));
                eventList.add(new Event(eventID, eventName, eventAddress, eventDetails,orgID,0));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return eventList;
    }

    public Boolean addEvent(Event event){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("event_name", event.getEventName());
        contentValues.put("event_address", event.getEventAddress());
        contentValues.put("event_details", event.getEventDetails());
        contentValues.put("org_id", event.getOrgId());
        //contentValues.put("image_id", event.getImageId());

        long result = MyDB.insert(TABLE_EVENTS, null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean updateEvent(Event event){
        return false;
    }

    public void clearEvents(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("drop Table if exists " + TABLE_EVENTS);
        MyDB.execSQL("create Table " + TABLE_EVENTS +
                "(event_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "event_name TEXT, " +
                "event_address TEXT, " +
                "event_details TEXT," +
                "org_id INTEGER NOT NULL," +
                "FOREIGN KEY (org_id) REFERENCES " + TABLE_ORGS + " (org_id))");
    }
}
