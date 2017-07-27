package com.nawin.androidmvparchitecture.data.remote;

import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by nawin on 6/13/17.
 */

public interface RemoteRepo {

    @POST("?login")
    Single<BaseResponse<UserInfo>> requestLogin(@Body Map<String,Object> params);

    @POST("?recentTags")
    Single<BaseResponse<List<Tags>>> getTags();
}
