package com.nawin.androidmvparchitecture.di;

import android.content.Context;

import com.google.gson.Gson;
import com.nawin.androidmvparchitecture.BuildTypeComponent;
import com.nawin.androidmvparchitecture.MVPApp;
import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.remote.DataModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by nawin on 7/20/17.
 */
@Singleton
@Component(modules = {
        CommonsModule.class,
        DataModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface MvpComponent extends AndroidInjector<MVPApp> {

@Component.Builder
interface Builder {
    @BindsInstance
     Builder application(MVPApp application);

    MvpComponent build();
}

    Context context();

    Gson gson();

    BuildTypeComponent buildType();

    Data data();
}
