package com.example.fitnessapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.navigation_videos:
                    Intent intentWorkouts = new Intent(Calendar.this, Workouts.class);
                    startActivity(intentWorkouts);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;


                case R.id.navigation_home:
                    Intent intentHome = new Intent(Calendar.this, MainActivity.class);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;

                case R.id.navigation_calendar:
                    break;

                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Calendar.this, Profile.class);
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
        setContentView(R.layout.activity_calendar);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.getMenu().getItem(2).setChecked(true);
    }

}
