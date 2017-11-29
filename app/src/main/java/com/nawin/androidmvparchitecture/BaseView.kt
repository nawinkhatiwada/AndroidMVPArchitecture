package com.nawin.androidmvparchitecture

/**
 * Created on 11/29/17.
 */
interface BaseView<in T:BasePresenter> {
    fun setPresenter(presenter: T)

}