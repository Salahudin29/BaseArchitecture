package com.tech387.ach.data.source.remote.content;

import com.tech387.ach.data.source.remote.ServiceGenerator;
import com.tech387.ach.data.source.remote.response.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class will get the data from the server
 */
public class ContentRemoteDataSource {

    private static ContentRemoteDataSource sInstance = null;

    private ContentRemoteDataSource() {

    }

    public static ContentRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new ContentRemoteDataSource();
        }
        return sInstance;
    }

    public void getContent(final GetContentCallback callback) {
        ContentService contentService = ServiceGenerator.createService(ContentService.class);
        contentService
                .getContent("spartan.system", 0, "pro", "en")
                .enqueue(
                        new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                if (response.isSuccessful()) {
                                    callback.onSuccess(response.body());
                                } else {
                                    callback.onError();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponse> call, Throwable t) {
                                callback.onError();
                            }
                        }
                );
    }

    public interface GetContentCallback {
        void onSuccess(BaseResponse baseResponse);

        void onError();
    }


}
