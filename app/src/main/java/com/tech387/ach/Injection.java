package com.tech387.ach;

import android.content.Context;

import com.tech387.ach.data.source.ContentRepository;
import com.tech387.ach.data.source.ExerciseRepository;
import com.tech387.ach.data.source.local.AppDatabase;
import com.tech387.ach.data.source.local.exercise.ExerciseLocalDataSource;
import com.tech387.ach.data.source.remote.content.ContentRemoteDataSource;
import com.tech387.ach.util.AppExecutors;

public class Injection {

    public static AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    public static AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getInstance(context);
    }

    public static ContentRemoteDataSource provideContentRemoteDataSource() {
        return ContentRemoteDataSource.getInstance();
    }

    public static ExerciseLocalDataSource provideExerciseLocalDataSource(Context context) {
        return ExerciseLocalDataSource.getInstance(
                provideAppDatabase(context).getExerciseDao(),
                provideAppExecutors()
        );
    }

    public static ContentRepository provideContentRepository(Context context) {
        return ContentRepository.getInstance(
                provideAppExecutors(),
                provideContentRemoteDataSource(),
                provideExerciseLocalDataSource(context)
        );
    }

    public static ExerciseRepository provideExerciseRepository(Context context) {
        return ExerciseRepository.getInstance(provideExerciseLocalDataSource(context));
    }

}
