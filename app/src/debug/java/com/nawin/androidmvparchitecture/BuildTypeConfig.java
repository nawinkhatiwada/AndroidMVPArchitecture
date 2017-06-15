package com.nawin.androidmvparchitecture;

import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by brainovation on 6/15/17.
 */

public class BuildTypeConfig {
    private final Context context;

    public BuildTypeConfig(Context context) {
        this.context = context;
    }

    void set() {
        Stetho.initializeWithDefaults(context);
    }

}
