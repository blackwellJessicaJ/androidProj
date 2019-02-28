package com.example.fitnessapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;




public class CalendarPage extends AppCompatActivity {

    private TextView mTextMessage;

        CalendarView calendarView;

        private static final String TAG = CalendarPage.class.getSimpleName();

        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.navigation_videos:
                        Intent intentWorkouts = new Intent(CalendarPage.this, Workouts.class);
                        startActivity(intentWorkouts);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;


                    case R.id.navigation_home:
                        Intent intentHome = new Intent(CalendarPage.this, MainActivity.class);
                        startActivity(intentHome);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;

                    case R.id.navigation_calendar:
                        break;

                    case R.id.navigation_profile:
                        Intent intentProfile = new Intent(CalendarPage.this, Profile.class);
                        startActivity(intentProfile);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                }

                return false;
            }
        };

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendar);

            mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
            bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            bottomNavigation.getMenu().getItem(2).setChecked(true);

            calendarView = findViewById(R.id.calendarView);

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

                }
            });

            //CalendarCustomView mView = (CalendarCustomView)findViewById(R.id.custom_calendar);


        }

}
