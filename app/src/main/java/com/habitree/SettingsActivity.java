package com.habitree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.habitree.accounts.LoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    private FirebaseFirestore db =  FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");

    Button signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        signout = findViewById(R.id.signout_bttn);

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

    }


}