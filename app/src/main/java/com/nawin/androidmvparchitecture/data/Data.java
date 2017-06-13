package com.nawin.androidmvparchitecture.data;

import com.nawin.androidmvparchitecture.data.local.LocalRepo;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.LoginRequest;
import com.nawin.androidmvparchitecture.data.remote.DataModule;
import com.nawin.androidmvparchitecture.data.remote.RemoteRepo;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by brainovation on 6/13/17.
 */

public class Data {
    private final LocalRepo localRepo;
    private final RemoteRepo remoteRepo;
    private static Data data;


    public static Data getInstance() {
        if (data == null) {
            RemoteRepo remoteRepo = new Retrofit.Builder()
                    .baseUrl(DataModule.BASE_URL)
                    .client(DataModule.getHttpClient()) //client is for logging the request and response, must be removed in release build
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build().create(RemoteRepo.class);

            data = new Data(new LocalRepo(), remoteRepo);
        }
        return data;
    }

    private Data(LocalRepo localRepo, RemoteRepo remoteRepo) {
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;
    }

    public Call<UserInfo> getLogin(LoginRequest loginRequest, Callback<UserInfo> callback) {
        Call<UserInfo> call = remoteRepo.getLogin(loginRequest);
        call.enqueue(callback);
        return call;
    }
}
