package com.nawin.androidmvparchitecture.di;

import android.content.Context;

import com.nawin.androidmvparchitecture.MVPApp;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ApplicationModule {

    @Binds
    abstract Context bindsContext(MVPApp application);
}
