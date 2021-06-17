package com.habitree.util;

import android.app.Application;

public class HabitApi extends Application {
    private String username;
    private String userId;
    private static HabitApi instance;

    public static HabitApi getInstance() {
        if (instance == null)
            instance = new HabitApi();
        return instance;

    }

    public HabitApi(){}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
