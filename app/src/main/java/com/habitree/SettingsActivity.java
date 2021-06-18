package com.habitree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.habitree.accounts.LoginActivity;
import com.habitree.notifications.AlarmNotificationReceiver;
import com.habitree.notifications.MyService;

import org.jetbrains.annotations.NotNull;

import java.text.BreakIterator;
import java.util.Calendar;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");

    private TimePicker mTimePicker;

    Button signout;
    Button btn;

    int i, i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        signout = findViewById(R.id.signout_bttn);
        btn = findViewById(R.id.settime_bttn);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.bringToFront();
        bottomNavigationView.setSelectedItemId(R.id.settings);
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
                        startActivity(new Intent(getApplicationContext(),
                                HabitActivity.class));
                        overridePendingTransition(0, 0);
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
                        return true;
                }
                return false;
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null && firebaseAuth != null) {
                    firebaseAuth.signOut();

                    startActivity(new Intent(getApplicationContext(),
                            MainActivity.class));
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlarm(true, true, i, i1);
            }
        });

        mTimePicker = findViewById(R.id.timePicker);

        // Set a time changed listener to time picker
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(
                    TimePicker timePicker, // TimePicker view
                    int ci, // hourOfDay
                    int ci1 // Minute
            ) {
                i=ci;
                i1=ci1;
                // Make the notif at certain time
            }
        });


    }
    private void startAlarm(boolean isNotification, boolean isRepeat, int i, int i1) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;
        // SET TIME HERE
        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,i);
        calendar.set(Calendar.MINUTE,i1);

        myIntent = new Intent(SettingsActivity.this, AlarmNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);


        if(!isRepeat)
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+300, pendingIntent);
        else
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

}