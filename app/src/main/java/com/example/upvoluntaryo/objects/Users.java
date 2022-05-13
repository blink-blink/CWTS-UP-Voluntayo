package com.example.upvoluntaryo.objects;

public class Users {
    String fullName, username, password, birthday, about;
    int userId, pronoun;

    public Users(int userId, String fullName, String username, String password, int pronoun, String birthday, String about) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.pronoun = pronoun;
        this.birthday = birthday;
        this.about = about;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPronoun() {
        return pronoun;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAbout() {
        return about;
    }
}