package com.example.fitnessapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstOpen extends AppCompatActivity {

    // Creates calls keys for necessary Sharedpreferences calls
    public static final String PersonalInfo = "personalInfo";
    public static final String Name = "nameKey";
    public static final String DateOfBirth = "birthdateKey";
    public static final String Height = "heightKey";
    public static final String Weight = "weightKey";
    public static final String FirstTimeApp = "firstOpen";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_open);


        sharedpreferences = getSharedPreferences("personalInfo", Context.MODE_PRIVATE);


        //Initializes EditText Fields for PersonalInfo
        EditText namefield = findViewById(R.id.editName);
        EditText agefield = findViewById(R.id.editAge);
        EditText heightfield = findViewById(R.id.editHeight);
        EditText weightfield = findViewById(R.id.editWeight);



        //Saves Edited ProfileInfo to SharedPreferences and returns Back to HomeScreen
        Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                EditText namefield = (EditText)findViewById(R.id.editName);
                EditText agefield = (EditText)findViewById(R.id.editAge);
                EditText heightfield = (EditText)findViewById(R.id.editHeight);
                EditText weightfield = (EditText)findViewById(R.id.editWeight);

                String name = namefield.getText().toString();
                int age = Integer.parseInt(agefield.getText().toString());
                String height = (heightfield.getText().toString());
                String weight = (weightfield.getText().toString());

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, name);
                editor.putInt(DateOfBirth, age);
                editor.putString(Height, height);
                editor.putString(Weight, weight);
                editor.putBoolean(FirstTimeApp, false);
                editor.commit();


                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
