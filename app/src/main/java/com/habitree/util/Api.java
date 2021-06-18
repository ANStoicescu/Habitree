package com.habitree.util;

import android.app.Application;

public class Api extends Application {
    private String username;
    private String userId;
    private static Api instance;

    public static Api getInstance() {
        if (instance == null)
            instance = new Api();
        return instance;
    }

    public Api(){}


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
