package com.nawin.androidmvparchitecture.data.remote

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created on 11/29/17.
 */
@Module
class DataModule {
    companion object {
        val BASE_URL = "https://androidragger.000webhostapp.com/mvp_android/api.php/"
    }

    @Provides
    @Singleton
    internal fun getHttpClient(apiInterceptor: ApiInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(apiInterceptor)
                .build()
    }

    @Provides
    @Singleton
    internal fun remoteRepo(gson: Gson, client: OkHttpClient): RemoteRepo {
        return Retrofit.Builder()
                .baseUrl(DataModule.BASE_URL)
                .client(client) //client is for logging the request and response
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build().create(RemoteRepo::class.java)
    }
}