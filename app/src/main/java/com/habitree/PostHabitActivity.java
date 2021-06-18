package com.habitree;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.habitree.models.Habit;
import com.habitree.util.HabitApi;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PostHabitActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "PostHabitActivity";
    private EditText HabitName;
    private EditText HabitDescription;
    private Button btnDone;

    private String currentUserId;
    private String currentUserName;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;

    //Connection to Firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference;

    private CollectionReference collectionReference = db.collection("Habit");
    private Uri imageUri;

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
                        startActivity(new Intent(getApplicationContext(),
                                HabitActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.add_habit:
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

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        storageReference = FirebaseStorage.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        HabitName = findViewById(R.id.HabitName);
        HabitDescription = findViewById(R.id.HabitDescription);

        btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(this);

        if (HabitApi.getInstance() != null) {
            currentUserId = HabitApi.getInstance().getUserId();
            currentUserName = HabitApi.getInstance().getUsername();
        }

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {

                }

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDone:
                //saveHabit
                saveHabit();
                break;
        }
    }

    private void saveHabit() {
        final String habittitle = HabitName.getText().toString().trim();
        final String habitdesc = HabitDescription.getText().toString().trim();

        if (!TextUtils.isEmpty(habittitle) && !TextUtils.isEmpty(habitdesc)) {
            //Todo: create a Habit Object - model
            Habit habit = new Habit();
            habit.setHabitName(habittitle);
            habit.setHabitDesc(habitdesc);
            habit.setUserName(currentUserName);
            habit.setUserId(currentUserId);

            //Todo:invoke our collectionReference
            collectionReference.add(habit)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            startActivity(new Intent(PostHabitActivity.this,
                                    HabitActivity.class));
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.getMessage());

                        }
                    });
            //Todo: and save a Journal instance.
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuth != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

}