package com.nawin.androidmvparchitecture.taggedquestion;

import com.nawin.androidmvparchitecture.di.MvpComponent;
import com.nawin.androidmvparchitecture.di.scope.FragementScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TaggedQuestionsFragmentModule {

    @FragementScope
    @Provides
    TaggedQuestionsContract.View provideFragment(TaggedQuestionsFragment fragment){
        return fragment;
    }

    @FragementScope
    @Provides
    TaggedQuestionsContract.Presenter providePresenter(MvpComponent component, TaggedQuestionsContract.View view){
        return new TaggedQuestionsPresenter(component,view);
    }
}
