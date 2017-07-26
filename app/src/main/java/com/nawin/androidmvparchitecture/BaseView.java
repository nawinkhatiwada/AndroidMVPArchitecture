package com.nawin.androidmvparchitecture;

/**
 * Created by brain on 1/6/17.
 */

public interface BaseView<T extends BasePresenter> {
//    void onSessionTimeout(String message);

    void setPresenter(T presenter);
}
