package com.nawin.androidmvparchitecture;

import android.app.Application;
import android.content.Context;

/**
 * Created by brainovation on 6/15/17.
 */

public class MVPApp extends Application {
    private MvpComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        this.component = DaggerMvpComponent.builder().mvpModule(new MvpModule(this)).build();
        component.buildType().buildTypeConfig().set();
    }

    public static MvpComponent component(Context context) {
        return ((MVPApp) context.getApplicationContext()).component;
    }
}
