package com.nawin.androidmvparchitecture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nawin.androidmvparchitecture.data.Data;

import javax.inject.Inject;

/**
 * Created by brainovation on 7/20/17.
 */

public class BaseActivity extends AppCompatActivity{

    protected MvpComponent component;

    @Inject
    Data data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.component = MVPApp.component(this);
        this.component.inject(this);
    }
}
