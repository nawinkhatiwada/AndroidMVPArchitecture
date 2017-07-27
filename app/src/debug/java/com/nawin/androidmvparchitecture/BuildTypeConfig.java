package com.nawin.androidmvparchitecture;

import android.content.Context;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

/**
 * Created by nawin on 6/15/17.
 */

public class BuildTypeConfig {
    private final Context context;

    @Inject
    BuildTypeConfig(Context context) {
        this.context = context;
    }

    void set() {
        Stetho.initializeWithDefaults(context);
    }

}
