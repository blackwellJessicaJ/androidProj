package com.example.fitnessapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileEdit extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_videos:
                    break;

                case R.id.navigation_home:
                    break;

                case R.id.navigation_calendar:
                    break;

                case R.id.navigation_profile:
                    Intent intent = new Intent(ProfileEdit.this, Profile.class);
                    startActivity(intent);
                    break;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Initializes EditText Fields for PersonalInfo
        EditText namefield = findViewById(R.id.editName);
        EditText agefield = findViewById(R.id.editAge);
        EditText heightfield = findViewById(R.id.editHeight);
        EditText weightfield = findViewById(R.id.editWeight);

        //Gets PersonalInfo Sent from Profile
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        namefield.setText(name);
        int age = intent.getIntExtra("age", 0);
        agefield.setText(Integer.toString(age));
        double height = intent.getDoubleExtra("height", 0);
        heightfield.setText(Double.toString(height));
        double weight = intent.getDoubleExtra("weight", 0);
        weightfield.setText(Double.toString(weight));


        //Sends Edited ProfileInfo Back to Profile
        Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                EditText namefield = (EditText)findViewById(R.id.editName);
                EditText agefield = (EditText)findViewById(R.id.editAge);
                EditText heightfield = (EditText)findViewById(R.id.editHeight);
                EditText weightfield = (EditText)findViewById(R.id.editWeight);

                String name = namefield.getText().toString();
                int age = Integer.parseInt(agefield.getText().toString());
                Double height = Double.parseDouble(heightfield.getText().toString());
                Double weight = Double.parseDouble(weightfield.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}
