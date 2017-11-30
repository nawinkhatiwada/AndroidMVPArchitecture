package com.nawin.androidmvparchitecture.data

import com.nawin.androidmvparchitecture.R
import com.nawin.androidmvparchitecture.data.local.LocalRepo
import com.nawin.androidmvparchitecture.data.model.Tags
import com.nawin.androidmvparchitecture.data.model.UserInfo
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse
import com.nawin.androidmvparchitecture.data.remote.RemoteRepo
import com.nawin.androidmvparchitecture.utils.NonNullMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created on 11/30/17.
 */
@Singleton
class Data @Inject
internal constructor(private val localRepo: LocalRepo, private val remoteRepo: RemoteRepo) {

    val isLoggedIn: Boolean
        get() = localRepo.getUserInfo() != null

    fun logout() {
        localRepo.logout()
    }


    fun requestLogin(username: String, password: String): Single<UserInfo> {
        val params = HashMap<String, Any>(2)
        params.put("username", username)
        params.put("password", password)
        return remoteRepo.requestLogin(params)
                .flatMap(NonNullMapper())
                .doOnSuccess { userInfo ->
                    localRepo.setUserInfo(userInfo)

                }.observeOn(AndroidSchedulers.mainThread())
    }

    fun requestTags(offset: Int, limit: Int): Single<Tags> {
        return remoteRepo.getTags(mapOf("offset" to offset, "limit" to limit))
                .map({
                    if (it.response != null)
                        it.response!!
                    else
                        throw NullPointerException()
                }).observeOn(AndroidSchedulers.mainThread())
    }
}
