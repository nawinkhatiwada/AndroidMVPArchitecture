package com.nawin.androidmvparchitecture.di;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class CommonsModule {

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    //provide bus
}
