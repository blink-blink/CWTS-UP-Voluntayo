package com.example.upvoluntaryo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.upvoluntaryo.objects.Event;
import com.example.upvoluntaryo.objects.Orgs;
import com.example.upvoluntaryo.objects.Users;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ORGS = "org";
    private static final String TABLE_MEMBERSHIP = "membership";
    private static final String TABLE_FOLLOWING = "following";
    private static final String TABLE_EVENTS = "events";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table " + TABLE_USERS +
                "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "full_name TEXT, " +
                "username TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL, " +
                "pronoun INTEGER, " +
                "birthday DATETIME, " +
                "about TEXT)");
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
        MyDB.execSQL("create Table " + TABLE_FOLLOWING +
                "(following_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "event_id INTEGER NOT NULL, " +
                "user_id INTEGER NOT NULL," +
                "FOREIGN KEY (event_id) REFERENCES " + TABLE_EVENTS + " (event_id)," +
                "FOREIGN KEY (user_id) REFERENCES " + TABLE_USERS + " (user_id))");
        MyDB.execSQL("create Table " + TABLE_EVENTS +
                "(event_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "event_type INTEGER NOT NULL, " +
                "event_name TEXT, " +
                "event_address TEXT, " +
                "event_details TEXT," +
                "event_date DATETIME," +
                "target INTEGER, " +
                "org_id INTEGER NOT NULL," +
                "FOREIGN KEY (org_id) REFERENCES " + TABLE_ORGS + " (org_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion){
        MyDB.execSQL("drop Table if exists " + TABLE_USERS);
        MyDB.execSQL("drop Table if exists " + TABLE_ORGS);
        MyDB.execSQL("drop Table if exists " + TABLE_MEMBERSHIP);
        MyDB.execSQL("drop Table if exists " + TABLE_FOLLOWING);
        MyDB.execSQL("drop Table if exists " + TABLE_EVENTS);
        onCreate(MyDB);
    }

    public Boolean insertUserData(String username, String password){ // Get other details (full_name, pronoun, birthday)
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select count(*) from " + TABLE_USERS, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        // Get other details (full_name, pronoun, birthday)
        if (cursor.getCount() <= 0) contentValues.put("user_id", 0);

        long result = MyDB.insert(TABLE_USERS, null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public String getUserData(String username, int columnNumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE_USERS + " where username = ?", new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(columnNumber);
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE_USERS + " where username = ?", new String[] {username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Users checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from " + TABLE_USERS + " where username = ? and password = ?", new String[] {username,password});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int user_id = Integer.parseInt(cursor.getString(0));
            String fullName = cursor.getString(1);
            int pronoun = 0;
            //int pronoun = Integer.parseInt(cursor.getString(4));
            String birthday = cursor.getString(5);
            String about = cursor.getString(6);
            return (new Users(user_id, fullName, username, password, pronoun,birthday,about));
        }
        return null;
    }

    public ArrayList<Event> listEvents(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ArrayList<Event> eventList = new ArrayList<>();
        Cursor cursor = MyDB.rawQuery("select * from " + TABLE_EVENTS, null);
        if (cursor.moveToFirst()){
            do {
                int eventID = Integer.parseInt(cursor.getString(0));
                int eventType = Integer.parseInt(cursor.getString(1));
                String eventName = cursor.getString(2);
                String eventAddress = cursor.getString(3);
                String eventDetails = cursor.getString(4);
                String eventDate = cursor.getString(5);
                int target = Integer.parseInt(cursor.getString(6));
                int orgID = Integer.parseInt(cursor.getString(7));
                eventList.add(new Event(eventID, eventType, eventName, eventAddress, eventDetails, eventDate, orgID,0,0));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return eventList;
    }

    public Boolean addEvent(Event event){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("event_type", event.getEventType());
        contentValues.put("event_name", event.getEventName());
        contentValues.put("event_address", event.getEventAddress());
        contentValues.put("event_details", event.getEventDetails());
        contentValues.put("event_date", event.getEventDate());
        contentValues.put("target", event.getEventTarget());
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
                "event_type INTEGER NOT NULL, " +
                "event_name TEXT, " +
                "event_address TEXT, " +
                "event_details TEXT," +
                "event_date DATETIME NOT NULL," +
                "target INTEGER, " +
                "org_id INTEGER NOT NULL," +
                "FOREIGN KEY (org_id) REFERENCES " + TABLE_ORGS + " (org_id))");
    }

    public ArrayList<Orgs> listOrgs(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ArrayList<Orgs> orgList = new ArrayList<>();
        Cursor cursor = MyDB.rawQuery("select * from " + TABLE_ORGS, null);
        if (cursor.moveToFirst()){
            do {
                int orgID = Integer.parseInt(cursor.getString(0));
                String orgName = cursor.getString(1);
                String orgDetails = cursor.getString(2);
                orgList.add(new Orgs(orgID,orgName,orgDetails));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return orgList;
    }

    public Boolean addOrg(Orgs org){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("org_name", org.getOrgName());
        contentValues.put("org_details", org.getOrgDetails());

        long result = MyDB.insert(TABLE_ORGS, null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean updateOrg(Orgs org){
        return false;
    }

    public Boolean followEvent(int userID, int eventID){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("event_id", eventID);
        contentValues.put("user_id", userID);

        long result = MyDB.insert(TABLE_FOLLOWING, null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public ArrayList<Event> listFollowedEvents(int userId){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ArrayList<Event> eventList = new ArrayList<>();
        Cursor cursor = MyDB.rawQuery("select * from " + TABLE_EVENTS+ " left join "+TABLE_FOLLOWING+
                " on "+TABLE_EVENTS+".event_id = "+TABLE_FOLLOWING+".event_id where "+TABLE_FOLLOWING+".user_id = ?", new String[] {Integer.toString(userId)});
        if (cursor.moveToFirst()){
            do {
                int eventID = Integer.parseInt(cursor.getString(0));
                int eventType = Integer.parseInt(cursor.getString(1));
                String eventName = cursor.getString(2);
                String eventAddress = cursor.getString(3);
                String eventDetails = cursor.getString(4);
                String eventDate = cursor.getString(5);
                int target = Integer.parseInt(cursor.getString(6));
                int orgID = Integer.parseInt(cursor.getString(7));
                eventList.add(new Event(eventID, eventType, eventName, eventAddress, eventDetails, eventDate, orgID,0,0));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return eventList;
    }
}