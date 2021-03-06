package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FingerExercises extends AppCompatActivity {

    private int totalClicks;
    private TextView reps;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_exercises);

        // Get username
        sharedPref = this.getSharedPreferences(getString(R.string.logged_in_user), Context.MODE_PRIVATE);
        String user = sharedPref.getString(getString(R.string.logged_in_user), "Welcome");
        TextView loggedInUser = findViewById(R.id.textView8);
        loggedInUser.setText("Welcome " + user);

        // Intitalize clicks counter
        totalClicks = sharedPref.getInt(getString(R.string.finger_exercise_stat),0);
        reps = findViewById(R.id.textView5);
        reps.setText(totalClicks + "");
    }

    public void fingerExercise(View v){

        TextView message = findViewById(R.id.textView2);
        reps = findViewById(R.id.textView5);

        totalClicks++;
        String text = "Get those fingers clicking! ";

        if (this.totalClicks % 50 == 0 && totalClicks != 0){
            text = "Look at those slim fingers! ";
        } else if (totalClicks % 10 == 0){
            text = "The fat is melting away! ";
        }

        message.setText(text);
        reps.setText(totalClicks + "");

        //save to shared preferences
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.finger_exercise_stat), totalClicks);
        editor.commit();
    }
}
