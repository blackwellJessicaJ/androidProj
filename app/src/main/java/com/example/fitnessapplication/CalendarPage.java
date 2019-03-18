package com.example.fitnessapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.applandeo.materialcalendarview.*;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;


public class CalendarPage extends AppCompatActivity {



    Calendar day = Calendar.getInstance();


    private CalendarView mCalendarView;





    //private List<EventDay> mEventDays = new ArrayList<>();
    public static final String EVENT ="event";
    public static final String RESULT = "result";
    private static final int ADD_NOTE = 44;
    public static final String ADD = "ADD";
    public static final String workoutReps = "WorkoutReps";




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


            //Gets Workout EventDays from SharedPreferences and applies them to the Calendar View
            SharedPreferences dates = getSharedPreferences("CompletedWorkouts", Context.MODE_PRIVATE);

            Gson gson = new Gson();
            String json = dates.getString(workoutReps,null);

            if (json != null) {
                Type type = new TypeToken<ArrayList<MyEventDay>>(){}.getType();
                ArrayList<MyEventDay> eventNote= gson.fromJson(json, type);

                //Uses for loop to turn each MyEventDay object into an EventDay and applies it
                for(int counter = 0; counter < eventNote.size(); counter++) {
                    mCalendarView =  (CalendarView) findViewById(R.id.calendarView);
                    List<EventDay> mEventDays = new ArrayList<>();
                    MyEventDay completedWorkout = eventNote.get(counter);
                    //mCalendarView.setDate(completedWorkout.getCalendar());
                    mEventDays.add(completedWorkout);

                    mCalendarView.setEvents(mEventDays);
                }


            }

            //On Date click activates PreviewNote intent
            mCalendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    previewNote(eventDay);
                }
            });


            //When accessed through Workouts Page selects current date and sends data to add note Activity
            Intent workout = getIntent();

            if (workout.getStringExtra("ADD") != null ){
                Object event = workout.getParcelableExtra("event");
                MyEventDay myEventDay = (MyEventDay) event;
                Intent addNote = new Intent(CalendarPage.this, AddNote.class);
                addNote.putExtra("event", myEventDay);
                startActivityForResult(addNote, 1);

            }
















        }

        //Returns from AddNote Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {

            //Takes Workout and adds to CalendarView
            mCalendarView =  (CalendarView) findViewById(R.id.calendarView);
            MyEventDay completedWorkout = data.getParcelableExtra(RESULT);
            List<EventDay> mEventDays = new ArrayList<>();
            //mCalendarView.setDate(completedWorkout.getCalendar());
            mEventDays.add(completedWorkout);
            mCalendarView.setEvents(mEventDays);

            //Adds MyEventDay to an array to be added to SharedPreferences
            List<MyEventDay> eventNotes = new ArrayList<>();
            eventNotes.add(completedWorkout);
            SharedPreferences dates = getSharedPreferences("CompletedWorkouts", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = dates.edit();
            Gson gson = new Gson();
            String json = gson.toJson(eventNotes);
            editor.putString(workoutReps, json);
            editor.commit();

        }
    }

    //Starts PreviewNote Activity for selected date
    private void previewNote(EventDay eventDay) {
        Intent intent = new Intent(CalendarPage.this, NotePreview.class);
        if(eventDay instanceof MyEventDay){
            intent.putExtra(EVENT, (MyEventDay) eventDay);
        }
        startActivity(intent);
    }


}

