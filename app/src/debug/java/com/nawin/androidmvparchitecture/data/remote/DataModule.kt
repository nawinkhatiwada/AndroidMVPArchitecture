package com.nawin.androidmvparchitecture.data.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created on 11/29/17.
 */
@Module
class DataModule {

    companion object {
        private val BASE_URL = "https://androidragger.000webhostapp.com/mvp_android/api.php/"
    }

    @Provides
    @Singleton
    internal fun getHttpClient(apiInterceptor: ApiInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(apiInterceptor)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
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