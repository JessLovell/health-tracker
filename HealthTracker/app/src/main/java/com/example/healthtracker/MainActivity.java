package com.example.healthtracker;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;


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

        //display user at top
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.logged_in_user), Context.MODE_PRIVATE);
        String user = sharedPref.getString(getString(R.string.logged_in_user), "Welcome");
        TextView loggedInUser = findViewById(R.id.textView11);
        loggedInUser.setText("Welcome " + user);

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(sampleImages[imageIndex % sampleImages.length]);

        TextView text = findViewById(R.id.textView);
        text.setText(imageText[imageIndex % sampleImages.length]);

        TextView counter = findViewById(R.id.textView7);
        counter.setText(((imageIndex % sampleImages.length) + 1) + " of " + sampleImages.length);
    }

    public void addLoggedInUser(View v){

        EditText loggedInUser = findViewById(R.id.editText3);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.logged_in_user),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.logged_in_user), loggedInUser.getText().toString());
        editor.commit();

        finish();
        startActivity(getIntent());
    }

    public void imageNextClicked(View v){

        imageIndex++;
        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(sampleImages[imageIndex % sampleImages.length]);

        TextView text = findViewById(R.id.textView);
        text.setText(imageText[imageIndex % sampleImages.length]);

        TextView counter = findViewById(R.id.textView7);
        counter.setText(((imageIndex % sampleImages.length) + 1) + " of " + sampleImages.length);
    }

    public void imageBackClicked(View v){

        imageIndex--;
        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(sampleImages[imageIndex % sampleImages.length]);

        TextView text = findViewById(R.id.textView);
        text.setText(imageText[imageIndex % sampleImages.length]);

        TextView counter = findViewById(R.id.textView7);
        counter.setText(((imageIndex % sampleImages.length) + 1) + " of " + sampleImages.length);
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

