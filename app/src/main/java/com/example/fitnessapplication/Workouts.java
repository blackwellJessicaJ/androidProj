package com.example.fitnessapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class Workouts extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {


    public static final String DEVELOPER_KEY = "AIzaSyBb4W4CpWZZXaQf-G8x02tOnSR51_b_q0k";
    private static final String VIDEO_ID = "srH-2pQdKhg";
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    YouTubePlayerFragment myYouTubePlayerFragment;

    private TextView mTextMessage;

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

        //youtube player
        myYouTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.youtubeplayerfragment);
        myYouTubePlayerFragment.initialize(DEVELOPER_KEY, this);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {

        if (youTubeInitializationResult.isUserRecoverableError()
        ) {

            youTubeInitializationResult.getErrorDialog(this,RECOVERY_DIALOG_REQUEST).show();
        }
        else {
            String errorMessage = String.format("There was an error initializing the YouTubePlayer", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        if (requestCode== RECOVERY_DIALOG_REQUEST){
            //retry initialization if user performed recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider(){
        return (YouTubePlayerView) findViewById(R.id.youtubeplayerfragment);
    }

}
