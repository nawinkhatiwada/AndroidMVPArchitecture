package com.nawin.androidmvparchitecture.data.remote;

import com.nawin.androidmvparchitecture.data.model.News;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;
import com.nawin.androidmvparchitecture.data.model.api.LoginRequest;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by brainovation on 6/13/17.
 */

public interface RemoteRepo {

    @POST("/login")
    Call<BaseResponse<UserInfo>> getLogin(@Body LoginRequest loginRequest);

    @POST("/getNews")
    Call<BaseResponse<List<News>>> getNews(@Body HashMap<String, Object> params);
}
