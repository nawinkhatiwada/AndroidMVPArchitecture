package com.nawin.androidmvparchitecture.data;

import com.nawin.androidmvparchitecture.data.local.LocalRepo;
import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;
import com.nawin.androidmvparchitecture.data.remote.RemoteRepo;
import com.nawin.androidmvparchitecture.utils.NonNullMapper;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by nawin on 6/13/17.
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

    public boolean isLoggedIn() {
        return localRepo.getUserInfo() != null;
    }

    public void logout() {
        localRepo.logout();
    }


    public Single<UserInfo> requestLogin(String username, String password) {
        HashMap<String, Object> params = new HashMap<>(2);
        params.put("username", username);
        params.put("password", password);
        return remoteRepo.requestLogin(params)
                .flatMap(new NonNullMapper<>())
                .doOnSuccess(userInfo -> {
                    if (userInfo != null)
                        localRepo.setUserInfo(userInfo);

                }).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Tags> requestTags(int offset, int limit) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("offset",offset);
        params.put("limit",limit);
        return remoteRepo.getTags(params)
                .map(BaseResponse::getResponse)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
