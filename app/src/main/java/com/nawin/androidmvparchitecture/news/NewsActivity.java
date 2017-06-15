package com.nawin.androidmvparchitecture.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nawin.androidmvparchitecture.R;

/**
 * Created by brainovation on 6/14/17.
 */

public class NewsActivity extends AppCompatActivity implements NewsContract.View {
    private NewsContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new NewsPresenter(this, this);
        presenter.start();
    }

    @Override
    protected void onPause() {
        presenter.stop();
        super.onPause();
    }

    @Override
    public void onSessionTimeout(String message) {

    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
