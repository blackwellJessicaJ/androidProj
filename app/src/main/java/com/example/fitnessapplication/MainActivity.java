package com.example.fitnessapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    public static final String PersonalInfo = "personalInfo";
    public static final String Name = "nameKey";
    public static final String DateOfBirth = "birthdateKey";
    public static final String Height = "heightKey";
    public static final String Weight = "weightKey";
    public static final String FirstTimeApp = "firstOpen";

    SharedPreferences sharedpreferences;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.navigation_videos:
                    Intent intentWorkouts = new Intent(MainActivity.this, Workouts.class);
                    startActivity(intentWorkouts);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;


                case R.id.navigation_home:
                    break;

                case R.id.navigation_calendar:
                    Intent intentCalendar = new Intent(MainActivity.this, CalendarPage.class);
                    startActivity(intentCalendar);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;

                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(MainActivity.this, Profile.class);
                    startActivity(intentProfile);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
            }

            return false;
        }
    };
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(PersonalInfo, Context.MODE_PRIVATE);

        Boolean firstOpen = sharedpreferences.getBoolean(FirstTimeApp, true);
        if (firstOpen){
            Intent intent = new Intent(MainActivity.this, FirstOpen.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG).show();
        }

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.getMenu().getItem(1).setChecked(true);


    }



}
