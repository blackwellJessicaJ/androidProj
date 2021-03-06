package com.example.fitnessapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Workouts extends AppCompatActivity {

    private TextView mTextMessage;
    private List<Workout> workouts;
    private RecyclerView rv;


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

        mTextMessage = (TextView) findViewById(R.id.WelcomeBack);
        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.getMenu().getItem(0).setChecked(true);


        rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeData();
        initializeAdapter();
    }

    //Initialize the data for the RecycleViewer CardViews
    private void initializeData(){
        workouts = new ArrayList<>();
        workouts.add(new Workout ("Push-Ups", "10 Push-Ups", "<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/YdB1HMCldJY\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        workouts.add(new Workout ("20 Minute Full Body", "30/10 Cardio", "<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/UBMk30rjy0o\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        workouts.add(new Workout ("Sit-Ups", "10 Sit-Ups", "<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/50kH47ZztHs\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        workouts.add(new Workout ("Flexibility and Mobility", "20 Minute stretch","<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/nFo5dOhlYUw\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        workouts.add(new Workout ("Core", "20/10 Abs","<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://https://www.youtube.com/embed/dJlFmxiL11s\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        workouts.add(new Workout ("30 Minute Lower Body", "30 Minute glutes","<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://https://www.youtube.com/embed/_ZqtZSuh5Rk\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        workouts.add(new Workout ("Squats", "10 Squats" ,"<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/50kH47ZztHs\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        workouts.add(new Workout ("30 Minute Cardio", "Cardio Boxing" ,"<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/HW7lqDLwG_Y\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
    }

    //Sets RecyclerView
    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(workouts);
        rv.setAdapter(adapter);
    }





}


