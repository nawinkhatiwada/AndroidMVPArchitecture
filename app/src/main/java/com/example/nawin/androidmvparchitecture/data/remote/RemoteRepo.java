package com.example.nawin.androidmvparchitecture.data.remote;

import com.example.nawin.androidmvparchitecture.data.model.UserInfo;
import com.example.nawin.androidmvparchitecture.data.model.api.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by brainovation on 6/13/17.
 */

public interface RemoteRepo {

    @POST("/login")
    Call<UserInfo> getLogin(@Body LoginRequest loginRequest);
}
