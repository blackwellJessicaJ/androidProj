package com.example.fitnessapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNote extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Intent intent = getIntent();

        TextView note = (TextView) findViewById(R.id.note);
        WebView videoView = findViewById(R.id.videoWebView);

        //Gets myEventDay and Displays note on Screen
        if (intent != null) {
            Object event = intent.getParcelableExtra("event");
            Object videoEvent = intent.getParcelableExtra("video");

            if(videoEvent instanceof Video)
            {
                Video myVidDay = (Video) videoEvent;
                myVidDay.getVidURL();
                videoView.getSettings().setJavaScriptEnabled(true);
                videoView.setWebChromeClient(new WebChromeClient());
                videoView.loadData(myVidDay.getVidURL(),"text/html","utf-8" );

            }

            if(event instanceof MyEventDay){


                MyEventDay myEventDay = (MyEventDay)event;
                getSupportActionBar().setTitle(getFormattedDate(myEventDay.getCalendar().getTime()));


                note.setText(myEventDay.getNote());


                Button button = (Button) findViewById(R.id.finishButton);

                //On Button Click, gets existing note (if any) for current date and appends the new note to it
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        Calendar day = Calendar.getInstance();

                        SharedPreferences workoutNotes = v.getContext().getSharedPreferences("WorkoutNotes", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = workoutNotes.edit();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String currentNote = workoutNotes.getString(sdf.format(day.getTime()), "") + "\n" + myEventDay.getNote();


                        editor.putString(sdf.format(day.getTime()),currentNote);
                        editor.commit();


                        //Returns edited Event to CalendarPage
                        MyEventDay newMyEventDay = new MyEventDay(day, R.drawable.ic_message_black_48dp, currentNote);
                        returnIntent.putExtra(CalendarPage.RESULT, newMyEventDay);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                });

                return;
            }

            if(event instanceof EventDay){
                EventDay eventDay = (EventDay)event;
                getSupportActionBar().setTitle(getFormattedDate(eventDay.getCalendar().getTime()));


            }
        }

    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
