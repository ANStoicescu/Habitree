package com.example.habitree;

public class Habit {
    private String habitName;
    private String habitDesc;
    private int hour;
    private int minute;

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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Habit() {
        this.habitName = "";
        this.habitDesc = "";
        this.hour = 0;
        this.minute = 0;
    }

    public Habit(String habitName, String habitDesc, int hour, int minute) {
        this.habitName = habitName;
        this.habitDesc = habitDesc;
        this.hour = hour;
        this.minute = minute;
    }
}

