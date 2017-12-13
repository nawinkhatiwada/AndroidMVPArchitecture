package com.nawin.androidmvparchitecture.auth.login

import com.nawin.androidmvparchitecture.MvpComponent
import com.nawin.androidmvparchitecture.R
import com.nawin.androidmvparchitecture.data.error.FailedResponseException
import com.nawin.androidmvparchitecture.data.error.NetworkNotAvailableException
import io.reactivex.disposables.Disposable

/**
 * Created on 12/13/17.
 */
internal class LoginPresenter constructor(private val component: MvpComponent,
                              private val view: LoginContract.View)
    : LoginContract.Presenter {

    private var disposable: Disposable? = null

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun stop() {
        disposable?.dispose()
    }

    override fun onLogin(username: String, password: String) {
        view.showLoginProgress()
        disposable = component.data().requestLogin(username, password)
                .subscribe({
                    view.showLoginSuccess()

                }) { throwable ->
                    when (throwable) {
                        is FailedResponseException ->
                            view.showLoginError(throwable.message)
                        is NetworkNotAvailableException ->
                            view.showNetworkNotAvailableError()
                        else ->
                            view.showLoginError(component.context().getString(R.string.server_error))

                    }
                }

    }
}
