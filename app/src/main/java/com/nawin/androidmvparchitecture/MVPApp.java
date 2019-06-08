package com.nawin.androidmvparchitecture;

import android.content.Context;

import com.nawin.androidmvparchitecture.di.DaggerMvpComponent;
import com.nawin.androidmvparchitecture.di.MvpComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by nawin on 6/15/17.
 */

public class MVPApp extends DaggerApplication {
    private MvpComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MvpComponent component(Context context) {
        return ((MVPApp) context.getApplicationContext()).component;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        this.component = DaggerMvpComponent.builder().application(this).build();
        this.component.buildType().buildTypeConfig().set();
        return component;
    }
}
