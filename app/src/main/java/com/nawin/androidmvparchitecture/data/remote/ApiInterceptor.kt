package com.nawin.androidmvparchitecture.data.remote

import android.content.Context
import com.nawin.androidmvparchitecture.data.error.NetworkNotAvailableException
import com.nawin.androidmvparchitecture.utils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created on 11/30/17.
 */

@Singleton
class ApiInterceptor @Inject
constructor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable(context)) {
            throw NetworkNotAvailableException()
        }
        val requestBuilder = chain.request().newBuilder()
        return chain.proceed(requestBuilder.build())
    }
}
