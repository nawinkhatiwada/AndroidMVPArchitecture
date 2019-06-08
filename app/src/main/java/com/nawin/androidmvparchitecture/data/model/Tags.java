package com.nawin.androidmvparchitecture.data.model;

import com.nawin.androidmvparchitecture.data.model.viewmodel.TaggedQuestionViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nawin on 7/19/17.
 */

public class Tags {

    private int itemCount;
    private List<TagItems> items;

    public List<TagItems> getItems() {
        return items;
    }

    public void setItems(List<TagItems> items) {
        this.items = items;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }


    public static List<TaggedQuestionViewModel> toViewModels(List<TagItems> tagItems) {
        final int count = tagItems.size();
        ArrayList<TaggedQuestionViewModel> viewModels = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            viewModels.add(new TaggedQuestionViewModel(tagItems.get(i)));
        }
        return viewModels;
    }
}
