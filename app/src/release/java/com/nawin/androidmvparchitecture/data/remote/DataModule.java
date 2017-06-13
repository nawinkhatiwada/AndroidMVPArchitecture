package com.nawin.androidmvparchitecture.data.remote;

import okhttp3.OkHttpClient;

/**
 * Created by brainovation on 6/13/17.
 */

public class DataModule {
    public static final String BASE_URL = "balasdf";

    public static OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder().build();
    }
}
