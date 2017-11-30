package com.nawin.androidmvparchitecture.data.model.api

/**
 * Created on 11/30/17.
 */
class BaseResponse<T> {

    var statusMessage: String? = null
    var statusCode: Int = 0
    var response: T? = null
}
