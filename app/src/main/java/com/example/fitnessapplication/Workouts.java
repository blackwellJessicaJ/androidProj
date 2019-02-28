package com.example.fitnessapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Workouts extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.navigation_videos:
                    break;


                case R.id.navigation_home:
                    Intent intentHome = new Intent(Workouts.this, MainActivity.class);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;

                case R.id.navigation_calendar:
                    Intent intentCalendar = new Intent(Workouts.this, CalendarPage.class);
                    startActivity(intentCalendar);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;

                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Workouts.this, Profile.class);
                    startActivity(intentProfile);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.getMenu().getItem(0).setChecked(true);
    }

}
