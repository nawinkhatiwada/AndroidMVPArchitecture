package com.nawin.androidmvparchitecture.auth.login;

import com.nawin.androidmvparchitecture.di.scope.ActivityScope;
import com.nawin.androidmvparchitecture.di.MvpComponent;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    @ActivityScope
    LoginContract.View provideLoginActivity(LoginActivity loginActivity){
        return loginActivity;
    }

    @Provides
    @ActivityScope
    LoginContract.Presenter providePresenter(MvpComponent component, LoginContract.View view){
        return new  LoginPresenter(component,view);
    }

}
