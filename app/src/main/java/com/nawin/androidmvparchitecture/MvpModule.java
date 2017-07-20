package com.nawin.androidmvparchitecture;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by brainovation on 7/20/17.
 */
@Module
public class MvpModule {
    private final Context context;

    MvpModule(MVPApp app) {
        this.context = app;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    Gson gson() {
        return new GsonBuilder().create();
    }
}
