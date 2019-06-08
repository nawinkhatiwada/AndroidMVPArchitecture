package com.nawin.androidmvparchitecture.auth.login;

import android.content.Context;

import com.nawin.androidmvparchitecture.di.MvpComponent;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.error.FailedResponseException;
import com.nawin.androidmvparchitecture.data.error.NetworkNotAvailableException;
import com.nawin.androidmvparchitecture.data.model.UserInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created on 9/15/17.
 */

public class LoginPresenterTest {

    @Mock
    Context context;

    @Mock
    LoginContract.View view;

    @Mock
    Data data;

    @Mock
    MvpComponent component;

    private LoginPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(component.data()).thenReturn(data);
        when(component.context()).thenReturn(context);
        this.presenter = new LoginPresenter(component, view);
    }

    @Test
    public void onLogin_successTest() {
        when(data.requestLogin(eq("nawin"), eq("khatiwada")))
                .thenReturn(Single.just(new UserInfo()));
        presenter.onLogin("nawin", "khatiwada");
        verify(view).showLoginProgress();
        verify(view).showLoginSuccess();
    }

    @Test
    public void onLogin_failedResponseExceptionTest() {
        when(data.requestLogin(eq("nawin"), eq("khatiwada")))
                .thenReturn(Single.error(new FailedResponseException(-1, "SE")));
        presenter.onLogin("nawin", "khatiwada");
        verify(view).showLoginProgress();
        verify(view).showLoginError(eq("SE"));
    }

    @Test
    public void onLogin_networkNotAvailableTest() {
        when(data.requestLogin(eq("nawin"), eq("khatiwada")))
                .thenReturn(Single.error(new NetworkNotAvailableException()));
        presenter.onLogin("nawin", "khatiwada");
        verify(view).showLoginProgress();
        verify(view).showNetworkNotAvailableError();
    }

    @Test
    public void onLogin_failureTest() {
        when(context.getString(R.string.server_error)).thenReturn("Server Error");
        when(data.requestLogin(eq("nawin"), eq("khatiwada")))
                .thenReturn(Single.error(new Exception()));
        presenter.onLogin("nawin", "khatiwada");
        verify(view).showLoginProgress();
        verify(view).showLoginError(eq("Server Error"));

    }
}
