package com.example.healthtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fingerExercise(View v){

        TextView textview = findViewById(R.id.textView);
        TextView message = findViewById(R.id.textView2);


        int click = Integer.parseInt(textview.getText().toString());
        click++;

        if (click % 50 == 0 && click != 0){
            message.setText("Look at those slim fingers!");
            textview.setText(click);
        } else if (click % 10 == 0){
            message.setText("The fat is melting away!");
            textview.setText(click);
        } else{
            message.setText("Get those thumbs clicking!");
            textview.setText(click);
        }

        System.out.println("click");

    }
}
