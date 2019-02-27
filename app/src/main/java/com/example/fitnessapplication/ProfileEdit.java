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
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;

public class ProfileEdit extends AppCompatActivity {

    private TextView mTextMessage;



    public static final String PersonalInfo = "personalInfo";
    public static final String Name = "nameKey";
    public static final String DateOfBirth = "birthdateKey";
    public static final String Height = "heightKey";
    public static final String Weight = "weightKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        mTextMessage = (TextView) findViewById(R.id.message);


        sharedpreferences = getSharedPreferences("personalInfo", Context.MODE_PRIVATE);


        //Initializes EditText Fields for PersonalInfo
        EditText namefield = findViewById(R.id.editName);
        EditText agefield = findViewById(R.id.editAge);
        EditText heightfield = findViewById(R.id.editHeight);
        EditText weightfield = findViewById(R.id.editWeight);

        //Gets PersonalInfo on Profile
        Intent intent = getIntent();
        namefield.setText(sharedpreferences.getString(Name, null));
        agefield.setText(Integer.toString(sharedpreferences.getInt(DateOfBirth, 0)));
        heightfield.setText(sharedpreferences.getString(Height, null));
        weightfield.setText(sharedpreferences.getString(Weight, null));


        //Saves Edited ProfileInfo to SharedPreferences and returns Back to Profile
        Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //Gets location of EditText Fields
                EditText namefield = (EditText)findViewById(R.id.editName);
                EditText agefield = (EditText)findViewById(R.id.editAge);
                EditText heightfield = (EditText)findViewById(R.id.editHeight);
                EditText weightfield = (EditText)findViewById(R.id.editWeight);

                //Gets Values of EditText Fields
                String name = namefield.getText().toString();
                int age = Integer.parseInt(agefield.getText().toString());
                String height = (heightfield.getText().toString());
                String weight = (weightfield.getText().toString());

                //Sets SharedPreference values for personal info to new values
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, name);
                editor.putInt(DateOfBirth, age);
                editor.putString(Height, height);
                editor.putString(Weight, weight);
                editor.commit();


                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button cancelButton = findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();
                finish();
            }
        });
    }


}
