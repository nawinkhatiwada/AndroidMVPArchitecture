package com.nawin.androidmvparchitecture.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nawin on 6/13/17.
 */
@Module
public class DataModule {
    private static final String BASE_URL = "https://androidragger.000webhostapp.com/mvp_android/api.php/";

    @Provides
    @Singleton
    OkHttpClient getHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    @Provides
    @Singleton
    RemoteRepo remoteRepo(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(DataModule.BASE_URL)
                .client(client) //client is for logging the request and response
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build().create(RemoteRepo.class);
    }

}
