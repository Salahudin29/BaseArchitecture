package com.tech387.ach.data.source.remote.content;

import com.tech387.ach.data.source.remote.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContentService {

    @GET("mobile")
    Call<BaseResponse> getContent(
            @Query("app") String appIdentifier,
            @Query("version") long version,
            @Query("type") String type,
            @Query("lang") String language
    );

}
