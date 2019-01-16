package com.example.healthtracker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //FINGER EXERCISE
    private int imageIndex = 0;
    private int[] sampleImages = {R.drawable.exercise, R.drawable.sleep, R.drawable.vegggies, R.drawable.water};
    private String[] imageText = {"Exercise is important!",
            "Get Lots of sleep so you can be the most productive.",
            "Eat fruits and veggies to get more energy.", "Stay hydrated!" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView counter = findViewById(R.id.textView7);
        counter.setText(imageIndex % sampleImages.length + " of " + sampleImages.length);
    }

    public void imageNextClicked(View v){

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(sampleImages[imageIndex++ % sampleImages.length]);

        TextView text = findViewById(R.id.textView);
        text.setText(imageText[imageIndex % sampleImages.length]);

        TextView counter = findViewById(R.id.textView7);
        counter.setText(imageIndex % sampleImages.length + " of " + sampleImages.length);
    }

    public void imageBackClicked(View v){

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(sampleImages[imageIndex-- % sampleImages.length]);

        TextView text = findViewById(R.id.textView);
        text.setText(imageText[imageIndex % sampleImages.length]);

        TextView counter = findViewById(R.id.textView7);
        counter.setText(imageIndex % sampleImages.length + " of " + sampleImages.length);
    }

    public void stopWatchClicked(View v) {
        Intent stopWatch = new Intent(this, StopWatch.class);
        startActivity(stopWatch);
    }

    public void onFingerExerciseClick(View v) {
        Intent fingerExerciseIntent = new Intent(this, FingerExercises.class);
        startActivity(fingerExerciseIntent);
    }

    public void onRemindersClick(View v) {
        Intent remindersIntent = new Intent(this, Notifications.class);
        startActivity(remindersIntent);
    }

    public void onJournalClick(View v) {
        Intent journalIntent = new Intent(this, Journal.class);
        startActivity(journalIntent);
    }
}

