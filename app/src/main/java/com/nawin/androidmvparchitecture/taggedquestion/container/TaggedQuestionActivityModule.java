package com.nawin.androidmvparchitecture.taggedquestion.container;

import android.app.Activity;

import com.nawin.androidmvparchitecture.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TaggedQuestionActivityModule {

    @Provides
    @ActivityScope
    Activity provideActivity(TaggedQuestionsActivity activity){
        return activity;
    }

}
