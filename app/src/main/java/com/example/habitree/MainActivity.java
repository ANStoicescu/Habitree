package com.example.habitree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button show;

    public static String MY_PREFS_NAME= "nameOfSharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = findViewById(R.id.btnDone);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlarm(true,false);
                System.out.println("DONE");
            }
        });

    }

    private void startAlarm(boolean isNotification, boolean isRepeat) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

        // SET TIME HERE
        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,21);
        calendar.set(Calendar.MINUTE,32);


        myIntent = new Intent(MainActivity.this,AlarmNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);


        if(!isRepeat)
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+300, pendingIntent);
        else
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void createHabit(){
        TextView hName;
        TextView hDesc;
        TimePicker picker;
        Button btn;
        Habit habit = new Habit();
        String habitName;
        String habitDesc;

        hName = findViewById(R.id.HabitName);
        hDesc = findViewById(R.id.HabitDescription);
        picker = findViewById(R.id.timePicker);
        btn = findViewById(R.id.btnDone);
        habitName  = hName.getText().toString();
        habitDesc = hDesc.getText().toString();

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int hour, min;
                String AM_PM;
                hour = picker.getHour();
                min = picker.getMinute();

                if(hour > 12) {
                    AM_PM = "PM";
                    hour = hour - 12;
                }
                else
                {
                    AM_PM="AM";
                }
                Log.d("Hour is ", String.valueOf(hour) );
                Log.d( " minute is ", String.valueOf(min));

                habit.setHabitName(habitName);
                habit.setHabitDesc(habitDesc);
                habit.setHour(hour);
                habit.setMinute(min);
            }
        });
    }

}
