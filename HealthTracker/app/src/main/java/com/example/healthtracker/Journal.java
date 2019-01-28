package com.example.healthtracker;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Journal extends AppCompatActivity {

    private ExerciseDatabase exerciseDatabase;
    private List<Exercise> serverDatabase;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FusedLocationProviderClient mFusedLocationClient;
    private String exerciseLocation;

    private final int MY_PERMISSIONS_REQUEST_LOCATIONS = 1896;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        //display user at top
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.logged_in_user), Context.MODE_PRIVATE);
        String user = sharedPref.getString(getString(R.string.logged_in_user), "Welcome");
        TextView loggedInUser = findViewById(R.id.textView6);
        loggedInUser.setText(user + "'s Journal");

        //getting the last location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getExerciseLocation();

        //call to server database
        getServerDb();
    }

    public void addJournalEntry(View v){

        EditText title = findViewById(R.id.editText4);
        EditText description = findViewById(R.id.editText6);
        EditText quantity = findViewById(R.id.editText5);
        String timestamp = new Date().toString();

        Exercise exercise = new Exercise(title.getText().toString(), quantity.getText().toString(), description.getText().toString(), timestamp, exerciseLocation);
        exerciseDatabase.exerciseDao().add(exercise);

        saveToServerDatabase(title.getText().toString(), quantity.getText().toString(), description.getText().toString());

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
                        serverDatabase = serverResponse;

                        //combine the two databases -- local and server
                        renderRecyclerView();
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

    public void renderRecyclerView(){

        exerciseDatabase = Room.databaseBuilder(getApplicationContext(),
                ExerciseDatabase.class, "exercise_journal").allowMainThreadQueries().build();

        //Add the external database to the local database
        serverDatabase.addAll(exerciseDatabase.exerciseDao().getAll());

        recyclerView = (RecyclerView) findViewById(R.id.journalRecycler);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new MyAdapter(serverDatabase);
        recyclerView.setAdapter(mAdapter);
    }

    public void saveToServerDatabase(final String title, final String quantity, final String description){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://stormy-bayou-86086.herokuapp.com/exercises";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Journal.getServer", "added to server db");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Journal.getServer", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();
                params.put("title", title);
                params.put("quantity", quantity);
                params.put("description", description);
                params.put("location", exerciseLocation);

                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void getExerciseLocation(){

        //Permission Granted
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {

                                //Turn the location to a String object
                                Geocoder geocoder = new Geocoder(Journal.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                exerciseLocation = addresses.get(0).getLocality();

                                Log.i("Journal.Location", "Got a location " + exerciseLocation);
                            }
                            else {
                                exerciseLocation = "Unknown";
                            }
                        }
                    });
        }
        // Permission denied, request permission
        else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                //TODO: Customize the alert message

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATIONS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    getExerciseLocation();

                } else {
                    // permission denied, boo! Disable the
                    exerciseLocation = "Unknown";
                    Log.i("Journal.Location", "Home");
                }
                return;
            }

            // TODO: ADD camera?
            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
