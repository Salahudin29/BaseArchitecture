package com.tech387.ach.data.source.local.exercise;

import com.tech387.ach.data.Exercise;
import com.tech387.ach.data.source.converter.RemoteToLocal;
import com.tech387.ach.data.source.remote.response.ExerciseResponse;
import com.tech387.ach.util.AppExecutors;

import java.util.List;

/**
 * This class will inserting data to database and getting data from database
 */
public class ExerciseLocalDataSource {

    private static ExerciseLocalDataSource sInstance = null;

    private final ExerciseDao mExerciseDao;

    private final AppExecutors mAppExecutors;

    private ExerciseLocalDataSource(ExerciseDao exerciseDao, AppExecutors appExecutors) {

        mExerciseDao = exerciseDao;
        mAppExecutors = appExecutors;
    }

    public static ExerciseLocalDataSource getInstance(ExerciseDao exerciseDao, AppExecutors appExecutors) {
        if (sInstance == null) {
            sInstance = new ExerciseLocalDataSource(exerciseDao, appExecutors);
        }
        return sInstance;
    }

    /**
     * Insert data from server to local database
     *
     * @param exerciseResponses
     */
    public void insertExercises(List<ExerciseResponse> exerciseResponses) {
        mExerciseDao.insertExercises(
                RemoteToLocal.exerciseConverter(
                        exerciseResponses
                )
        );
    }

    /**
     * Get all exercise from local database
     */
    public void getAllExercises(final GetAllExerciseCallback callback) {
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Exercise> exercises = mExerciseDao.getExercises();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(exercises);
                    }
                });
            }
        });

    }

    public interface GetAllExerciseCallback {
        void onSuccess(List<Exercise> exercises);

        void onError();
    }


}
