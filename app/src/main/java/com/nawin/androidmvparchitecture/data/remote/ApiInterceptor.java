package com.nawin.androidmvparchitecture.data.remote;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.nawin.androidmvparchitecture.utils.Commons.isNetworkAvailable;

/**
 * Created on 9/15/17.
 */
@Singleton
public class ApiInterceptor implements Interceptor {

    private Context context;

    @Inject
    public ApiInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isNetworkAvailable(context)) {
            throw new NetworkNotAvailableException();
        }
        Request.Builder requestBuilder = chain.request().newBuilder();
        return chain.proceed(requestBuilder.build());
    }
}
