package com.nawin.androidmvparchitecture.data.remote;

import okhttp3.OkHttpClient;

/**
 * Created by brainovation on 6/13/17.
 */
@Module
public class DataModule {
    public static final String BASE_URL = "http://api.stackexchange.com/2.2/";

    @Provides
    @SingleTon
    OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    RemoteRepo remoteRepo(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(DataModule.BASE_URL)
                .client(client) //client is for logging the request and response
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RemoteRepo.class);
    }
}
