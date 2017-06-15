package com.nawin.androidmvparchitecture.auth.news;


import android.content.Context;

import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.local.LocalRepo;
import com.nawin.androidmvparchitecture.data.model.News;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nawin.androidmvparchitecture.utils.Commons.cancel;

/**
 * Created by brainovation on 6/14/17.
 */

public class NewsPresenter implements NewsContract.Presenter {
    private Context context;
    private NewsContract.View view;
    private Call<BaseResponse<List<News>>> calls;

    public NewsPresenter(Context context, NewsContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        UserInfo userInfo = LocalRepo.getInstance(context).getUserInfo();
        calls = Data.getInstance(context).requestNews(userInfo.getUserId(), new Callback<BaseResponse<List<News>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<News>>> call, Response<BaseResponse<List<News>>> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse<List<News>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void stop() {
        cancel(calls);
    }
}
