package com.nawin.androidmvparchitecture.taggedquestion;

import com.nawin.androidmvparchitecture.di.MvpComponent;
import com.nawin.androidmvparchitecture.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TaggedQuestionModule {

    @Provides
    @ActivityScope
    TaggedQuestionsContract.View provideActivity(TaggedQuestionsActivity activity){
        return activity;
    }

    @Provides
    @ActivityScope
    TaggedQuestionsContract.Presenter providePresenter(MvpComponent component, TaggedQuestionsContract.View view){
        return new TaggedQuestionsPresenter(component,view);
    }

}
