package com.example.healthtracker;

import androidx.room.Room;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

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

        recyclerView = (RecyclerView) findViewById(R.id.journalRecycler);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new MyAdapter(exerciseDatabase.exerciseDao().getAll());
        recyclerView.setAdapter(mAdapter);

    }

    public void addJournalEntry(View v){

        EditText title = findViewById(R.id.editText4);
        EditText description = findViewById(R.id.editText6);
        EditText quantity = findViewById(R.id.editText5);
        String timestamp = new Date().toString();

        Exercise exercise = new Exercise(title.getText().toString(), quantity.getText().toString(), description.getText().toString(), timestamp);
        exerciseDatabase.exerciseDao().add(exercise);

        //got this from: https://stackoverflow.com/questions/3053761/reload-activity-in-android
        finish();
        startActivity(getIntent());
    }
}
