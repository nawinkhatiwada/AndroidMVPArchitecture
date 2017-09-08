package com.nawin.androidmvparchitecture.data;

import android.content.Context;

import com.google.gson.Gson;
import com.nawin.androidmvparchitecture.data.local.LocalRepo;
import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;
import com.nawin.androidmvparchitecture.data.remote.DataModule;
import com.nawin.androidmvparchitecture.data.remote.RemoteRepo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nawin on 6/13/17.
 */

public class Data {
    private final LocalRepo localRepo;
    private final RemoteRepo remoteRepo;
    private static Data data;

    public static Data getInstance(Context context) {
        if (data == null) {
            RemoteRepo remoteRepo = new Retrofit.Builder()
                    .baseUrl(DataModule.BASE_URL)
                    .client(DataModule.getHttpClient()) //client is for logging the request and response
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build().create(RemoteRepo.class);

            data = new Data(LocalRepo.getInstance(context), remoteRepo);
        }
        return data;
    }

    private Data(LocalRepo localRepo, RemoteRepo remoteRepo) {
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;
    }

    public boolean isLoggedIn() {
        return localRepo.getUserInfo() != null;
    }

    public void logout() {
        localRepo.logout();
    }

    public Call<BaseResponse<UserInfo>> requestLogin(String username, String password, final Callback<BaseResponse<UserInfo>> callback) {
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("username", username);
        params.put("password", password);
        Call<BaseResponse<UserInfo>> call = remoteRepo.requestLogin(params);
        call.enqueue(new Callback<BaseResponse<UserInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserInfo>> call, Response<BaseResponse<UserInfo>> response) {
                if (response != null && response.isSuccessful()) {
                    callback.onResponse(call, response);
                    UserInfo userInfo = response.body().getResponse() == null ? null : response.body().getResponse();
                    if (userInfo != null) {
                        localRepo.setUserInfo(userInfo);
                    }
                } else {
                    callback.onFailure(call, new Exception());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UserInfo>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
        return call;
    }

    public Call<BaseResponse<Tags>> requestTags(int offset, int limit, Callback<BaseResponse<Tags>> callback) {
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("offset", offset);
        params.put("limit", limit);
        Call<BaseResponse<Tags>> call = remoteRepo.getTags(params);
        call.enqueue(callback);
        return call;
    }
}
