package com.example.upvoluntaryo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.upvoluntaryo.objects.Users;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences usersSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "IsLoggedIn";

    private static final String KEY_USERID = "userId";
    private static final String KEY_FULLNAME = "fullName";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PRONOUN = "pronoun";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_ABOUT = "about";

    public SessionManager(Context context){
        this.context = context;
        usersSession = context.getSharedPreferences("userLoginSession",Context.MODE_PRIVATE);
        editor = usersSession.edit();
    }

    public void createLoginSession(int userId, String fullName, String username, String password, int pronoun, String birthday, String about){
        editor.putBoolean(IS_LOGIN, true);

        editor.putInt(KEY_USERID, userId);
        editor.putString(KEY_FULLNAME, fullName);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putInt(KEY_PRONOUN, pronoun);
        editor.putString(KEY_BIRTHDAY, birthday);
        editor.putString(KEY_ABOUT, about);

        editor.commit();
    }

    public Users getUsersDataFromSession(){
        return (new Users(
                usersSession.getInt(KEY_USERID,0),
                usersSession.getString(KEY_FULLNAME, null),
                usersSession.getString( KEY_USERNAME, null),
                usersSession.getString(KEY_PASSWORD, null),
                usersSession.getInt(KEY_PRONOUN,0),
                usersSession.getString(KEY_BIRTHDAY, null),
                usersSession.getString(KEY_ABOUT, null)
        ));
    }

    public boolean checkLogin(){
        if (usersSession.getString(KEY_USERNAME,null) == null) {
            return false;
        }
        else
            return true;
    }

    public void logoutUserFromSession(){
        editor.clear();
        editor.commit();
    }
}
