package com.example.fitnessapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private TextView mTextMessage;

    public static final String PersonalInfo = "personalInfo";
    public static final String Name = "nameKey";
    public static final String DateOfBirth = "birthdateKey";
    public static final String Height = "heightKey";
    public static final String Weight = "weightKey";

    SharedPreferences sharedpreferences;

    public String name;
    public int age;
    public double height;
    public double weight;
    public void setPersonalInfo(String name, int age, double height, double weight){
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_videos:
                    Intent intentWorkouts = new Intent(Profile.this, Workouts.class);
                    startActivity(intentWorkouts);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;

                case R.id.navigation_home:
                    Intent intentHome = new Intent(Profile.this, MainActivity.class);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;

                case R.id.navigation_calendar:
                    Intent intentCalendar = new Intent(Profile.this, Calendar.class);
                    startActivity(intentCalendar);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;

                case R.id.navigation_profile:
                    break;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        //Display Info as TextView
        /*TextView nameDisplay = findViewById(R.id.name);
        nameDisplay.setText(name);
        TextView ageDisplay = findViewById(R.id.age);
        ageDisplay.setText(Integer.toString(age));
        TextView heightDisplay = findViewById(R.id.height);
        heightDisplay.setText(Double.toString(height));
        TextView weightDisplay = findViewById(R.id.weight);
        weightDisplay.setText(Double.toString(weight));*/

        sharedpreferences = getSharedPreferences(PersonalInfo, Context.MODE_PRIVATE);

        TextView nameDisplay = findViewById(R.id.name);
        nameDisplay.setText(sharedpreferences.getString(Name, null));
        TextView ageDisplay = findViewById(R.id.age);
        ageDisplay.setText(Integer.toString(sharedpreferences.getInt(DateOfBirth, 0)));
        TextView heightDisplay = findViewById(R.id.height);
        heightDisplay.setText(sharedpreferences.getString(Height, null));
        TextView weightDisplay = findViewById(R.id.weight);
        weightDisplay.setText(sharedpreferences.getString(Weight, null));


        mTextMessage = findViewById(R.id.message);
        BottomNavigationView bottomNavigation = findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.getMenu().getItem(3).setChecked(true);

        Button editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Sends Intent to ProfileEdit Activity
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ProfileEdit.class);
                /*intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);*/
                startActivityForResult(intent, 1 );
            }
            });
    }
    //Return from ProfileEdit
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent){
        super.onActivityResult(requestCode, resultCode, dataIntent);

        switch(requestCode)
        {
            case 1:
                //Gets Changed PersonalInfo from ProfileEdit


                if(resultCode == RESULT_OK)
                {

                    TextView nameDisplay = findViewById(R.id.name);
                    nameDisplay.setText(sharedpreferences.getString(Name, null));
                    TextView ageDisplay = findViewById(R.id.age);
                    ageDisplay.setText(Integer.toString(sharedpreferences.getInt(DateOfBirth, 0)));
                    TextView heightDisplay = findViewById(R.id.height);
                    heightDisplay.setText(sharedpreferences.getString(Height, null));
                    TextView weightDisplay = findViewById(R.id.weight);
                    weightDisplay.setText(sharedpreferences.getString(Weight, null));

                }

        }


    }






}
