package com.example.healthtracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercise")
    List<Exercise> getAll();

    @Query("SELECT * FROM exercise WHERE id = :id")
    Exercise getById(long id);

    //This code from https://stackoverflow.com/questions/5191503/how-to-select-the-last-record-of-a-table-in-sql
    @Query("SELECT * FROM exercise ORDER BY id DESC LIMIT 1 ")
    Exercise getLast();

    @Insert
    void add(Exercise exercise);

    @Update
    void update(Exercise exercise);

    @Delete
    void delete(Exercise exercise);

}
