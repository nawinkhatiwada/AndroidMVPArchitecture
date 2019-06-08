package com.nawin.androidmvparchitecture.di;

import com.nawin.androidmvparchitecture.auth.login.LoginActivity;
import com.nawin.androidmvparchitecture.auth.login.LoginActivityModule;
import com.nawin.androidmvparchitecture.di.scope.ActivityScope;
import com.nawin.androidmvparchitecture.taggedquestion.container.TaggedQuestionActivityModule;
import com.nawin.androidmvparchitecture.taggedquestion.container.TaggedQuestionsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity loginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {TaggedQuestionActivityModule.class, FragmentBindingModule.class})
    abstract TaggedQuestionsActivity taggedQuestionsActivity();
}
