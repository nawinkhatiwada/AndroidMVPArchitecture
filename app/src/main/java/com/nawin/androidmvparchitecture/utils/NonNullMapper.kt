package com.nawin.androidmvparchitecture.utils

import com.nawin.androidmvparchitecture.data.error.FailedResponseException
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function

/**
 * Created on 11/29/17.
 */
class NonNullMapper<T> : Function<BaseResponse<T>, Single<T>> {
    @Throws(Exception::class)
    override fun apply(@NonNull baseResponse: BaseResponse<T>): Single<T> {
        if (baseResponse.statusCode != 1)
            return Single.error(FailedResponseException(baseResponse.statusCode, baseResponse.statusMessage))
        val item = baseResponse.response
        return if (item == null)
            Single.error(NullPointerException("BaseResponse.Response == null"))
        else
            Single.just(item)
    }
}