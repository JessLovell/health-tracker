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

    public int totalClicks;
    public int visitCount;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setResources();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        setResources();
    }

    //Add username at the top of the page
    public void addLoggedInUser(View v){

        EditText loggedInUser = findViewById(R.id.editText3);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.logged_in_user),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.logged_in_user), loggedInUser.getText().toString());
        editor.commit();

        finish();
        startActivity(getIntent());
    }

    //Redirect to Login activity
    public void onLoginClick(View v){
        Intent loginClick = new Intent(this, Login.class);
        startActivity(loginClick);
    }

    //show next image in carousel
    public void imageNextClicked(View v){

        imageIndex++;
        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(sampleImages[imageIndex % sampleImages.length]);

        TextView text = findViewById(R.id.textView);
        text.setText(imageText[imageIndex % sampleImages.length]);

        TextView counter = findViewById(R.id.textView7);
        counter.setText(((imageIndex % sampleImages.length) + 1) + " of " + sampleImages.length);
    }

    //show previous button in image carousel
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

    //redirect to finger exercise activity
    public void onFingerExerciseClick(View v) {
        Intent fingerExerciseIntent = new Intent(this, FingerExercises.class);
        startActivity(fingerExerciseIntent);
    }

    //redirect to the notificaitons activity
    public void onRemindersClick(View v) {
        Intent remindersIntent = new Intent(this, Notifications.class);
        startActivity(remindersIntent);
    }

    //redirect to journal activity
    public void onJournalClick(View v) {
        Intent journalIntent = new Intent(this, Journal.class);
        startActivity(journalIntent);
    }

    public void setResources(){

        sharedPref = this.getSharedPreferences(getString(R.string.logged_in_user), Context.MODE_PRIVATE);

        //update visit count
        visitCount = sharedPref.getInt(getString(R.string.visit_count_stat),0);
        visitCount++;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.visit_count_stat), visitCount);
        editor.commit();

        //Get Stats
        totalClicks = sharedPref.getInt(getString(R.string.finger_exercise_stat),0);

        //display user at top
        String user = sharedPref.getString(getString(R.string.logged_in_user), "Welcome");
        TextView loggedInUser = findViewById(R.id.textView11);
        loggedInUser.setText("Welcome " + user + ", Finger Strength: " + totalClicks + ", Visits: " + visitCount);

        // Image carousel
        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(sampleImages[imageIndex % sampleImages.length]);

        TextView text = findViewById(R.id.textView);
        text.setText(imageText[imageIndex % sampleImages.length]);

        TextView counter = findViewById(R.id.textView7);
        counter.setText(((imageIndex % sampleImages.length) + 1) + " of " + sampleImages.length);
    }
}

