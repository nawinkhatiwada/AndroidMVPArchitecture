package com.nawin.androidmvparchitecture.data.remote

import com.nawin.androidmvparchitecture.data.model.Tags
import com.nawin.androidmvparchitecture.data.model.UserInfo
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created on 11/30/17.
 */
interface RemoteRepo {
    @POST("?login")
    fun requestLogin(@Body params: Map<String, String>): Single<BaseResponse<UserInfo>>

    @POST("?recentTags")
    fun getTags(@Body params: Map<String, Int>): Single<BaseResponse<Tags>>
}