package com.tech387.ach.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tech387.ach.Injection;
import com.tech387.ach.R;
import com.tech387.ach.data.Exercise;
import com.tech387.ach.data.source.ContentRepository;
import com.tech387.ach.data.source.ExerciseRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ContentRepository mContentRepository;

    private ExerciseRepository mExerciseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        mContentRepository = Injection.provideContentRepository(this);
        mExerciseRepository = Injection.provideExerciseRepository(this);

        mContentRepository.getContentAndInsertContent(new ContentRepository.GetContentCallback() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "Success");
                mExerciseRepository.getAllExercises(
                        new ExerciseRepository.GetAllExercisesCallback() {
                            @Override
                            public void onSuccess(List<Exercise> exercises) {
                                Log.e(TAG, "Exercise: Success Count: "+exercises.size());
                            }

                            @Override
                            public void onError() {
                                Log.e(TAG, "Exercise: Error");
                            }
                        });

            }

            @Override
            public void onError() {
                Log.e(TAG, "Error");
            }
        });

    }
}
