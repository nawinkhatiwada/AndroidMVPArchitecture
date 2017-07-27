package com.nawin.androidmvparchitecture.data.remote;

import okhttp3.OkHttpClient;

/**
 * Created by nawin on 6/13/17.
 */

public class DataModule {
    public static final String BASE_URL = "https://androidragger.000webhostapp.com/mvp_android/api.php/";

    public static OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder().build();
    }
}
