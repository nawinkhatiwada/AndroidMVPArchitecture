package com.nawin.androidmvparchitecture;

import android.content.Context;

import com.google.gson.Gson;
import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.remote.DataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nawin on 7/20/17.
 */
@Component(modules = {MvpModule.class, DataModule.class})
@Singleton
public interface MvpComponent {
    Context context();

    BuildTypeComponent buildType();

    Data data();

    Gson gson();

    void inject(BaseActivity baseActivity);
}
