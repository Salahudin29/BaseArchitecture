package com.tech387.ach.data.source.converter;

import com.tech387.ach.data.Exercise;
import com.tech387.ach.data.source.remote.response.ExerciseResponse;

import java.util.ArrayList;
import java.util.List;

public class RemoteToLocal {

    public static List<Exercise> exerciseConverter(List<ExerciseResponse> exerciseResponses) {
        List<Exercise> exercises = new ArrayList<>();

        for (ExerciseResponse e : exerciseResponses) {
            exercises.add(
                    new Exercise(
                            e.getId(),
                            e.getName(),
                            e.getThumbnail()
                    )
            );
        }

        return exercises;
    }

}
