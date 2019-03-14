package com.example.fitnessapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class WorkoutActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View v = findViewById(R.id.button1);
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0){
        if (arg0.getId() == R.id.button1){
            //define a new intent for the second activity
            Intent intent = new Intent(this, WorkoutRecycler.class);

            //start the second activity
            this.startActivity(intent);
        }
    }
}