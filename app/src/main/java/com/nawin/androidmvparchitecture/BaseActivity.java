package com.nawin.androidmvparchitecture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.di.MvpComponent;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by nawin on 7/20/17.
 */

public class BaseActivity extends DaggerAppCompatActivity {

    protected MvpComponent component;

    @Inject
    protected Data data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.component = MVPApp.component(this);
    }
}
