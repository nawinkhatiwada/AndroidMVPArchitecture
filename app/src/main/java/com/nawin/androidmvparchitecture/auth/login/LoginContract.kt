package com.nawin.androidmvparchitecture.auth.login

import com.nawin.androidmvparchitecture.BasePresenter
import com.nawin.androidmvparchitecture.BaseView

/**
 * Created on 12/13/17.
 */
interface LoginContract {

    interface View : BaseView<Presenter> {
        fun showLoginProgress()

        fun showLoginSuccess()

        fun showLoginError(message: String)

        fun showNetworkNotAvailableError()
    }

    interface Presenter : BasePresenter {
        fun onLogin(username: String, password: String)
    }
}
