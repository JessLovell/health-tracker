package com.example.healthtracker;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class Journal extends AppCompatActivity {

    private ExerciseDatabase exerciseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        exerciseDatabase = Room.databaseBuilder(getApplicationContext(),
                ExerciseDatabase.class, "exercise_journal").allowMainThreadQueries().build();


        //Help from Nick Crain here
        if (exerciseDatabase.exerciseDao().getById(0) == null){
            Exercise exercise = new Exercise("Breathing Exercises", 512, "Deep breathing for 3 mins", "1/10/2019 14:04");
            exerciseDatabase.exerciseDao().add(exercise);
        }

        TextView textView = findViewById(R.id.textView5);
        Exercise output = exerciseDatabase.exerciseDao().getById(0);
        textView.setText(output.timestamp + ": " + output.title);
    }
}
