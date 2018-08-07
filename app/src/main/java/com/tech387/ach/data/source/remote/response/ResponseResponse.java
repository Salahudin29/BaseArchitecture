package com.tech387.ach.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseResponse implements Serializable {

    @SerializedName("exercises")
    @Expose
    private List<ExerciseResponse> mExercise;

    public List<ExerciseResponse> getExercise() {
        return mExercise;
    }

    public void setExercise(List<ExerciseResponse> exercise) {
        mExercise = exercise;
    }

}
