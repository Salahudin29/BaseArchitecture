package com.tech387.ach.data.source.local.exercise;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tech387.ach.data.Exercise;

import java.util.List;

/**
 * Inserting and getting exercises from database
 */
@Dao
public interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExercises(List<Exercise> exercises);

    @Query("SELECT * FROM exercise_table")
    List<Exercise> getExercises();

}
