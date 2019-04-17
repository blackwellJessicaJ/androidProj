package com.example.fitnessapplication;


import android.webkit.WebView;

class Workout {
    String name;
    String reps;


    String videoUrl;

    Workout (String name, String reps, String video){
        this.name = name;
        this.reps = reps;
        this.videoUrl = video;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}


