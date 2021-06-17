package com.habitree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.habitree.models.Habit;

public class Habit_formular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_formular);
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