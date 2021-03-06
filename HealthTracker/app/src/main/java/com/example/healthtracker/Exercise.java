package com.example.healthtracker;

import android.location.Location;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String title;
    public String quantity;
    public String description;
    public String timestamp;
    public String location;

    public Exercise(){
    }

    public Exercise(String title, String quantity, String description, String timestamp, String location){
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.timestamp = timestamp;
        this.location = location;
    }

    public String toString(){
        return this.title + ", " + this.quantity + ": " + this.description;
    }
}
