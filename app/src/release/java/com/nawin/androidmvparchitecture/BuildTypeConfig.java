package com.nawin.androidmvparchitecture;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by nawin on 6/15/17.
 */

public class BuildTypeConfig {
    private Context context;

    @Inject
     BuildTypeConfig(Context ctx) {
        this.context = ctx;
    }

    void set() {
    }

}
