package com.nawin.androidmvparchitecture.data.remote;

import com.nawin.androidmvparchitecture.data.model.News;
import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by brainovation on 6/13/17.
 */

public interface RemoteRepo {

    @POST("?login")
    Call<BaseResponse<UserInfo>> requestLogin(@Body Map<String, Object> params);

    @GET("/search?pagesize=25&order=desc&sort=activity&tagged=android-annotations&site=stackoverflow")
    Call<BaseResponse<List<Tags>>> getTags();

}
