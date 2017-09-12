package com.nawin.androidmvparchitecture.data.model;

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
}
