package com.nawin.androidmvparchitecture.auth.login

import android.content.Context
import com.nawin.androidmvparchitecture.MvpComponent
import com.nawin.androidmvparchitecture.R
import com.nawin.androidmvparchitecture.data.Data
import com.nawin.androidmvparchitecture.data.error.FailedResponseException
import com.nawin.androidmvparchitecture.data.error.NetworkNotAvailableException
import com.nawin.androidmvparchitecture.data.model.UserInfo
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created on 12/13/17.
 */
class LoginPresenterTest {

    private val context: Context = Mockito.mock(Context::class.java)
    private val component: MvpComponent = Mockito.mock(MvpComponent::class.java)
    private val data: Data = Mockito.mock(Data::class.java)
    private val view: LoginContract.View = Mockito.mock(LoginContract.View::class.java)
    private lateinit var presenter: LoginPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        given(component.data()).willReturn(data)
        given(component.context()).willReturn(context)
        this.presenter = LoginPresenter(component, view)
    }

    @Test
    fun onLogin_successTest() {
      given(data.requestLogin(eq("nawin"), eq("khatiwada")))
                .willReturn(Single.just(UserInfo()))
        presenter.onLogin("nawin", "khatiwada")
        verify(view).showLoginProgress()
        verify(view).showLoginSuccess()
    }

    @Test
    fun onLogin_failedResponseExceptionTest() {
        given(data.requestLogin(eq("nawin"),eq("khatiwada")))
                .willReturn(Single.error<UserInfo>(FailedResponseException(-1, "SE")))
        presenter.onLogin("nawin", "khatiwada")
        verify(view).showLoginProgress()
        verify(view).showLoginError(eq("SE"))
    }

    @Test
    fun onLogin_networkNotAvailableTest() {
        given(data.requestLogin(eq("nawin"), eq("khatiwada")))
                .willReturn(Single.error<UserInfo>(NetworkNotAvailableException()))
        presenter.onLogin("nawin", "khatiwada")
        verify(view).showLoginProgress()
        verify(view).showNetworkNotAvailableError()
    }

    @Test
    fun onLogin_failureTest() {
        given(context.getString(R.string.server_error)).willReturn("Server Error")
        given(data.requestLogin(eq("nawin"), eq("khatiwada")))
                .willReturn(Single.error<UserInfo>(Exception()))
        presenter.onLogin("nawin", "khatiwada")
        verify(view).showLoginProgress()
        verify(view).showLoginError(eq("Server Error"))
    }
}