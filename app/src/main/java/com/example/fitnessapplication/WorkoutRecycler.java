package com.example.fitnessapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Vector;

public class WorkoutRecycler extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<YouTubeVideos> youtubeVideos = new Vector<YouTubeVideos>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_workout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Calisthenics
        youtubeVideos.add(new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/YdB1HMCldJY\" frameborder=\"0\" " +
                 "allowfullscreen></iframe>"));
        //Cardio
        youtubeVideos.add(new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/50kH47ZztHs\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        //Yoga
        youtubeVideos.add(new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/kBZgtvy8gKs\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        //Boxing
        youtubeVideos.add(new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/HW7lqDLwG_Y\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));
        //lower-body
        youtubeVideos.add(new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/X3TcVT-g1-w\" frameborder=\"0\" " +
                "allowfullscreen></iframe>"));

        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
    }
}
