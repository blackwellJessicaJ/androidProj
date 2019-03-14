package com.example.fitnessapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.applandeo.materialcalendarview.*;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;


public class CalendarPage extends AppCompatActivity {



    Calendar day = Calendar.getInstance();


    private CalendarView mCalendarView;






    private List<EventDay> mEventDays = new ArrayList<>();
    public static final String EVENT ="event";
    public static final String RESULT = "result";
    private static final int ADD_NOTE = 44;
    public static final String ADD = "ADD";




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


            BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
            bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            bottomNavigation.getMenu().getItem(2).setChecked(true);





            mCalendarView =  (CalendarView) findViewById(R.id.calendarView);

            mCalendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    previewNote(eventDay);
                }
            });



            Intent workout = getIntent();

            if (workout.getStringExtra("ADD") != null ){
                Object event = workout.getParcelableExtra("event");
                MyEventDay myEventDay = (MyEventDay) event;


                /*MyEventDay myEventDay = workout.getParcelableExtra("event");
                try {
                    mCalendarView.setDate(day);
                } catch (OutOfDateRangeException e) {
                    e.printStackTrace();
                }
                mEventDays.add(myEventDay);
                mCalendarView.setEvents(mEventDays);*/
                Intent addNote = new Intent(CalendarPage.this, AddNote.class);
                //Bundle bundle = new Bundle();
                //bundle.putParcelable("event", myEventDay);
                addNote.putExtra("event", myEventDay);

                startActivityForResult(addNote, 1);

            }















        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mCalendarView =  (CalendarView) findViewById(R.id.calendarView);
            MyEventDay completedWorkout = data.getParcelableExtra(RESULT);

            mCalendarView.setDate(day);
            mEventDays.add(completedWorkout);
            mCalendarView.setEvents(mEventDays);

            /*SharedPreferences prefs = getSharedPreferences("CompletedWorkouts", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            try{
                editor.putString("CompletedWorkouts", ObjectSerializer.serialize(mEventDays));

            }
            catch (IOException e){
                e.printStackTrace();
            }
            editor.commit();*/
        }
    }


    private void previewNote(EventDay eventDay) {
        Intent intent = new Intent(CalendarPage.this, NotePreview.class);
        if(eventDay instanceof MyEventDay){
            intent.putExtra(EVENT, (MyEventDay) eventDay);
        }
        startActivity(intent);
    }


}

