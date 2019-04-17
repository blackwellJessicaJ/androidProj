package com.example.fitnessapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotePreview extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_preview);

        Intent intent = getIntent();

        TextView note = (TextView) findViewById(R.id.note);

        //Gets note from event and displays on Screen
        if (intent != null) {
            Object event = intent.getParcelableExtra(CalendarPage.EVENT);

            if(event instanceof MyEventDay){
                MyEventDay myEventDay = (MyEventDay)event;

                getSupportActionBar().setTitle(getFormattedDate(myEventDay.getCalendar().getTime()));
                note.setText(myEventDay.getNote());

            }

            if(event instanceof EventDay){
                EventDay eventDay = (EventDay)event;
                getSupportActionBar().setTitle(getFormattedDate(eventDay.getCalendar().getTime()));
            }
        }

        Button edit = (Button) findViewById(R.id.editButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            //Sends Intent to NoteEdit Activity
            public void onClick(View view) {
                Intent  editNote = new Intent(NotePreview.this, EditNote.class);
                MyEventDay myEventDay = intent.getParcelableExtra(CalendarPage.EVENT);
                editNote.putExtra("event", myEventDay);
                startActivityForResult(editNote, 1 );
            }
        });

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent editNote){
        super.onActivityResult(requestCode, resultCode, editNote);

        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){

                    WebView videoView = findViewById(R.id.videoWebView);
                    MyEventDay newVideo = editNote.getParcelableExtra("video");
                    String video = newVideo.getVidURL();
                    videoView.getSettings().setJavaScriptEnabled(true);
                    videoView.setWebChromeClient(new WebChromeClient(){

                    });
                    videoView.loadData(video,"text/html","utf-8");

                    TextView note = (TextView) findViewById(R.id.note);
                    MyEventDay newNote = editNote.getParcelableExtra("New Note");
                    note.setText(newNote.getNote());

                }

            }

    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}