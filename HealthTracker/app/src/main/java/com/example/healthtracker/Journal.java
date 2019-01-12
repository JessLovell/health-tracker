package com.example.healthtracker;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Journal extends AppCompatActivity {

    private ExerciseDatabase exerciseDatabase;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

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


        recyclerView = (RecyclerView) findViewById(R.id.journalRecycler);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }
        // define an adapter
        mAdapter = new MyAdapter(exerciseDatabase.exerciseDao().getAll());
        recyclerView.setAdapter(mAdapter);

    }

    public void addJournalEntry(View v){

    }
}
