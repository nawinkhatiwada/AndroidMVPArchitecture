package com.nawin.androidmvparchitecture

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created on 11/29/17.
 */
@Module
class MvpModule(var app: MVPApp) {
    @Provides
    internal fun provideContext(): Context = app

    @Provides
    @Singleton
    internal fun gson(): Gson = GsonBuilder().create()
}