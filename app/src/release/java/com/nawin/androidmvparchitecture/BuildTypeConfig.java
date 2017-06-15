package com.nawin.androidmvparchitecture;

import android.content.Context;

/**
 * Created by brainovation on 6/15/17.
 */

public class BuildTypeConfig {
        private Context context;
    public BuildTypeConfig(Context ctx) {
       this.context = ctx;
    }

    void set() {
    }

    static void onAttachBaseContext(Context context) {

    }
}
