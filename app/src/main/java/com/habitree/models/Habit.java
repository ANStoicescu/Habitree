package com.habitree.models;

public class Habit {
    private String userId;
    private String userName;
    private String habitName;
    private String habitDesc;

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getHabitDesc() {
        return habitDesc;
    }

    public void setHabitDesc(String habitDesc) {
        this.habitDesc = habitDesc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Habit() { //for firestore
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Habit(String habitName, String habitDesc, String userId, String userName) {
        this.habitName = habitName;
        this.habitDesc = habitDesc;
        this.userId = userId;
        this.userName = userName;
    }
}

