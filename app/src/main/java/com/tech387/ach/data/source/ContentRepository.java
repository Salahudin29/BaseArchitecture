package com.tech387.ach.data.source;

import com.tech387.ach.data.source.local.exercise.ExerciseLocalDataSource;
import com.tech387.ach.data.source.remote.content.ContentRemoteDataSource;
import com.tech387.ach.data.source.remote.response.BaseResponse;
import com.tech387.ach.util.AppExecutors;

/**
 * This repository will get all the data from the server and save it to the local database
 */
public class ContentRepository {

    private static ContentRepository sInstance = null;

    private final AppExecutors mAppExecutors;

    private final ContentRemoteDataSource mContentRemoteDataSource;

    private final ExerciseLocalDataSource mExerciseLocalDataSource;

    private ContentRepository(
            AppExecutors appExecutors, ContentRemoteDataSource contentRemoteDataSource,
            ExerciseLocalDataSource exerciseLocalDataSource) {
        mAppExecutors = appExecutors;
        mContentRemoteDataSource = contentRemoteDataSource;
        mExerciseLocalDataSource = exerciseLocalDataSource;
    }

    public static ContentRepository getInstance(
            AppExecutors appExecutors,
            ContentRemoteDataSource contentRemoteDataSource,
            ExerciseLocalDataSource exerciseLocalDataSource) {
        if (sInstance == null) {
            sInstance = new ContentRepository(appExecutors, contentRemoteDataSource, exerciseLocalDataSource);
        }
        return sInstance;
    }

    /**
     * 1.) Getting the content from server
     * 2.) Insert the data to local database
     *
     * @param callback if the content is successfully added to the database
     */
    public void getContentAndInsertContent(final GetContentCallback callback) {
        mContentRemoteDataSource.getContent(
                new ContentRemoteDataSource.GetContentCallback() {
                    @Override
                    public void onSuccess(final BaseResponse baseResponse) {
                        mAppExecutors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                mExerciseLocalDataSource.insertExercises(baseResponse.getResponseResponse().getExercise());

                                mAppExecutors.mainThread().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        callback.onSuccess();
                                    }
                                });
                            }
                        });

                    }

                    @Override
                    public void onError() {
                        callback.onError();
                    }
                });
    }

    public interface GetContentCallback {
        void onSuccess();

        void onError();
    }

}
