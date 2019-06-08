package com.nawin.androidmvparchitecture.di;

import com.nawin.androidmvparchitecture.di.scope.FragementScope;
import com.nawin.androidmvparchitecture.taggedquestion.TaggedQuestionsFragment;
import com.nawin.androidmvparchitecture.taggedquestion.TaggedQuestionsFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBindingModule {

    @FragementScope
    @ContributesAndroidInjector(modules = TaggedQuestionsFragmentModule.class)
    abstract TaggedQuestionsFragment taggedQuestionsFragment();
}
