package com.tech387.ach.data.source;

import com.tech387.ach.data.Exercise;
import com.tech387.ach.data.source.local.exercise.ExerciseLocalDataSource;

import java.util.List;

/**
 * This class will handle all exercise data manipulation
 */
public class ExerciseRepository {

    private static ExerciseRepository sInstance = null;

    private final ExerciseLocalDataSource mExerciseLocalDataSource;

    private ExerciseRepository(ExerciseLocalDataSource exerciseLocalDataSource) {

        mExerciseLocalDataSource = exerciseLocalDataSource;
    }

    public static ExerciseRepository getInstance(ExerciseLocalDataSource exerciseLocalDataSource) {
        if (sInstance == null) {
            sInstance = new ExerciseRepository(exerciseLocalDataSource);
        }
        return sInstance;
    }

    public void getAllExercises(final GetAllExercisesCallback callback) {
        mExerciseLocalDataSource.getAllExercises(
                new ExerciseLocalDataSource.GetAllExerciseCallback() {
                    @Override
                    public void onSuccess(List<Exercise> exercises) {
                        callback.onSuccess(exercises);
                    }

                    @Override
                    public void onError() {
                        callback.onError();
                    }
                });
    }

    public interface GetAllExercisesCallback {
        void onSuccess(List<Exercise> exercises);

        void onError();
    }


}
