package com.nawin.androidmvparchitecture.data.model.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.nawin.androidmvparchitecture.data.model.TagItems;

public class TaggedQuestionViewModel extends BaseObservable {

    private TagItems taggedItems;

    public TaggedQuestionViewModel(TagItems taggedItems){
        this.taggedItems = taggedItems;
    }

    @Bindable
    public String getTitle(){
        return taggedItems.getTitle();
    }

    @Bindable
    public String getDescription(){
        return taggedItems.getDescription();
    }
}
