package com.example.healthtracker;

import androidx.room.Room;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
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

    public void getServerDb(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://stormy-bayou-86086.herokuapp.com/exercises";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Exercise>>(){}.getType();
                        List<Exercise> serverResponse = gson.fromJson(response, listType);
                        Log.i("Journal.getServer", "got response");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Journal.getServer", error.toString());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
