package com.example.fitnessapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditNote extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Intent intent = getIntent();

        EditText note = (EditText) findViewById(R.id.workoutNote);

        //Gets note from event and displays on Screen
        if (intent != null) {
            Object event = intent.getParcelableExtra(CalendarPage.EVENT);

            if(event instanceof MyEventDay){
                MyEventDay myEventDay = (MyEventDay)event;
                //getSupportActionBar().setTitle(getFormattedDate(myEventDay.getCalendar().getTime()));
                note.setText(myEventDay.getNote());


            }

            if(event instanceof EventDay){
                EventDay eventDay = (EventDay)event;
                //getSupportActionBar().setTitle(getFormattedDate(eventDay.getCalendar().getTime()));
            }
        }
        Button acceptEdit = (Button) findViewById(R.id.acceptButton);
        acceptEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Gets location of EditText Fields
                EditText noteField = (EditText)findViewById(R.id.workoutNote);


                //Gets Values of EditText Field
                String newNote = noteField.getText().toString();

                SharedPreferences dates = getSharedPreferences("CompletedWorkouts", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = dates.edit();
                MyEventDay myEventDay = (MyEventDay) intent.getParcelableExtra((CalendarPage.EVENT));

                Gson gson = new Gson();
                String json = dates.getString("WorkoutReps",null);

                if (json != null) {
                    json = dates.getString("WorkoutReps",null);
                    Type type = new TypeToken<ArrayList<MyEventDay>>(){}.getType();
                    ArrayList<MyEventDay> eventNote= gson.fromJson(json, type);

                    List<EventDay> mEventDays = new ArrayList<>();

                    //Uses for loop to turn search for EventDay that matches one being edited and replace it
                    for(int counter = 0; counter < eventNote.size(); counter++) {

                        MyEventDay completedWorkout = eventNote.get(counter);

                        if(completedWorkout.getCalendar().equals(myEventDay.getCalendar())){
                            myEventDay.setNote(newNote);
                            eventNote.set(counter, myEventDay);
                            Calendar day = myEventDay.getCalendar();

                            SharedPreferences workoutNotes = v.getContext().getSharedPreferences("WorkoutNotes", Context.MODE_PRIVATE);
                            SharedPreferences.Editor noteEditor = workoutNotes.edit();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            noteEditor.putString(sdf.format(day.getTime()),myEventDay.getNote() );
                            noteEditor.commit();
                            break;
                        }


                    }
                    String newJson = new Gson().toJson(eventNote);
                    editor.putString("WorkoutReps", newJson);
                    editor.commit();


                }



                Intent finishEdit = new Intent();
                finishEdit.putExtra("New Note", myEventDay);
                setResult(RESULT_OK, finishEdit);
                finish();
            }
        });



        Button cancelEdit = (Button) findViewById(R.id.cancelButton);
        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cancelEdit = new Intent();
                finish();
            }
        });
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}