package com.nawin.androidmvparchitecture.data.remote;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nawin on 6/13/17.
 */
@Module
public class DataModule {
    public static final String BASE_URL = "https://androidragger.000webhostapp.com/mvp_android/api.php/";

    @Provides
    @Singleton
    OkHttpClient getHttpClient(ApiInterceptor apiInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(apiInterceptor)
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
