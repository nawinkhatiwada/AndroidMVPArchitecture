package com.nawin.androidmvparchitecture;

import android.app.Application;

/**
 * Created by brainovation on 6/15/17.
 */

public class MVPApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BuildTypeConfig config = new BuildTypeConfig(getApplicationContext());
        config.set();
    }
}
