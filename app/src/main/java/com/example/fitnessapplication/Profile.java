package com.example.fitnessapplication;

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

    //public abstract SharedPreferences.Editor putString(name) s ;

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
                    break;

                case R.id.navigation_home:
                    Intent intent = new Intent(Profile.this, MainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_calendar:
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


        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Sends Intent to ProfileEdit Activity
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ProfileEdit.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
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

                    name = dataIntent.getStringExtra("name");
                    age = dataIntent.getIntExtra("age", age);
                    height = dataIntent.getDoubleExtra("height", height);
                    weight = dataIntent.getDoubleExtra("weight", weight);
                    TextView nameDisplay = findViewById(R.id.name);
                    nameDisplay.setText(this.name);
                    TextView ageDisplay = findViewById(R.id.age);
                    ageDisplay.setText(Integer.toString(this.age));
                    TextView heightDisplay = findViewById(R.id.height);
                    heightDisplay.setText(Double.toString(this.height));
                    TextView weightDisplay = findViewById(R.id.weight);
                    weightDisplay.setText(Double.toString(this.weight));
                }

        }


    }






}
