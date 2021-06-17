package com.habitree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.habitree.models.Habit;

import org.jetbrains.annotations.NotNull;

public class PostHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_habit);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.bringToFront();
        bottomNavigationView.setSelectedItemId(R.id.add_habit);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@androidx.annotation.NonNull @NotNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.habits:
                        return true;
                    case R.id.add_habit:
                        startActivity(new Intent(getApplicationContext(),
                                PostHabitActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.add_journal:
                        startActivity(new Intent(getApplicationContext(),
                                PostJournalActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),
                                SettingsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        createHabit();
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