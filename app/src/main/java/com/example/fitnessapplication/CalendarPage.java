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
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import com.applandeo.materialcalendarview.*;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
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
                json = dates.getString(workoutReps,null);
                Type type = new TypeToken<ArrayList<MyEventDay>>(){}.getType();
                ArrayList<MyEventDay> eventNote= gson.fromJson(json, type);

                List<EventDay> mEventDays = new ArrayList<>();

                //Uses for loop to turn each MyEventDay object into an EventDay and applies it after the loop
                for(int counter = 0; counter < eventNote.size(); counter++) {
                    mCalendarView =  (CalendarView) findViewById(R.id.calendarView);

                    MyEventDay completedWorkout = eventNote.get(counter);
                    mCalendarView.setMinimumDate(null);
                    mCalendarView.setMaximumDate(null);
                    try {
                        mCalendarView.setDate(completedWorkout.getCalendar());
                    } catch (OutOfDateRangeException e) {
                        e.printStackTrace();
                    }
                    mEventDays.add(completedWorkout);
                }
                mCalendarView.setEvents(mEventDays);

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

            WebView videoView = findViewById(R.id.videoWebView);

            if (workout.getStringExtra("ADD") != null )
            {
                Object event = workout.getParcelableExtra("event");

                Object vidEvent = workout.getParcelableExtra("video");
                MyEventDay myVideoDay= (MyEventDay) vidEvent;

                MyEventDay myEventDay = (MyEventDay) event;

                Intent addNote = new Intent(CalendarPage.this, AddNote.class);
                addNote.putExtra("video", myVideoDay);
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
            try {
                mCalendarView.setDate(completedWorkout.getCalendar());
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }
            mEventDays.add(completedWorkout);
            mCalendarView.setEvents(mEventDays);

            //Adds MyEventDay to an array to be added to SharedPreferences
            List<MyEventDay> eventsNote = new ArrayList<>();
            SharedPreferences dates = getSharedPreferences("CompletedWorkouts", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = dates.edit();
            String json = dates.getString(workoutReps,null);
            Type type = new TypeToken<ArrayList<MyEventDay>>(){}.getType();

            if (json == null){
                eventsNote = new ArrayList<>();
            } else {
                eventsNote = new Gson().fromJson(json, type);
            }

            //Checks if Events Already Exist in the Shared Preferences
            if(eventsNote.size() != 0){

                int lastElement = eventsNote.size()-1;
                MyEventDay temp = eventsNote.get(lastElement);
                //Checks if Event for Current day Exists and replaces it if it does, else just adds
                if(temp.getCalendar().equals(completedWorkout.getCalendar())) {

                    eventsNote.set(eventsNote.size()-1, completedWorkout);
                }else {

                    eventsNote.add(completedWorkout);
                }
            }else{
                eventsNote.add(completedWorkout);
            }

            String newJson = new Gson().toJson(eventsNote);
            editor.putString(workoutReps, newJson);
            editor.commit();





        }
        //Refreshes Calendar
        Intent refresh = new Intent(CalendarPage.this, CalendarPage.class);
        startActivity(refresh);
        this.finish();

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

