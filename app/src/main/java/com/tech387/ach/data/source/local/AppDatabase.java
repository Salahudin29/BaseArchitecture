package com.tech387.ach.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tech387.ach.data.Exercise;
import com.tech387.ach.data.source.local.exercise.ExerciseDao;

@Database(entities = {Exercise.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance = null;

    public abstract ExerciseDao getExerciseDao();

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "spartan.db").build();
        }
        return sInstance;
    }

}
