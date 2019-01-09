package com.example.healthtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FingerExercises extends AppCompatActivity {

    private int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_exercises);
    }

    public void fingerExercise(View v){

        TextView message = findViewById(R.id.textView2);
        this.click++;
        String text = "Get those fingers clicking! ";

        if (this.click % 50 == 0 && click != 0){
            text = "Look at those slim fingers! ";
        } else if (click % 10 == 0){
            text = "The fat is melting away! ";
        }
        message.setText(text + click);
    }
}
