package com.nawin.androidmvparchitecture;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.di.MvpComponent;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class BaseFragment extends DaggerFragment {

    protected MvpComponent component;
    @Inject
    protected Data data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        if (activity != null){
            this.component = MVPApp.component(activity);
        }
    }
}
