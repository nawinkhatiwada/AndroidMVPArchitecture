package com.nawin.androidmvparchitecture;

import android.content.Context;

/**
 * Created by brainovation on 6/15/17.
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
