package com.nawin.androidmvparchitecture.data;

import com.nawin.androidmvparchitecture.data.local.LocalRepo;
import com.nawin.androidmvparchitecture.data.model.News;
import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;
import com.nawin.androidmvparchitecture.data.model.api.LoginRequest;
import com.nawin.androidmvparchitecture.data.remote.RemoteRepo;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by brainovation on 6/13/17.
 */
@Singleton
public class Data {
    private final LocalRepo localRepo;
    private final RemoteRepo remoteRepo;

    @Inject
    Data(LocalRepo localRepo, RemoteRepo remoteRepo) {
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;
    }

    public Call<BaseResponse<List<News>>> requestNews(int userId, Callback<BaseResponse<List<News>>> callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Call<BaseResponse<List<News>>> call = remoteRepo.requestNews(params);
        call.enqueue(callback);
        return call;
    }

    public Call<BaseResponse<UserInfo>> requestLogin(final LoginRequest loginRequest, final Callback<BaseResponse<UserInfo>> callback) {

        Call<BaseResponse<UserInfo>> call = remoteRepo.requestLogin(loginRequest);
        call.enqueue(new Callback<BaseResponse<UserInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserInfo>> call, Response<BaseResponse<UserInfo>> response) {
                UserInfo userInfo = response.body() == null ? null : response.body().getResponse();
                if (userInfo != null) {
                    localRepo.setUserInfo(userInfo);
                    callback.onResponse(call, response);
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

    public Single<List<Tags>> requestTags() {
        return remoteRepo.getTags()
                .map(BaseResponse::getResponse)
                .observeOn(AndroidSchedulers.mainThread());

    }
}
