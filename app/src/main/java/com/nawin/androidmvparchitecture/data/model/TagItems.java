package com.nawin.androidmvparchitecture.data.model;

/**
 * Created on 9/8/17.
 */

public class TagItems {

    /**
     * id : 1
     * title : Android
     * description : Description is the pattern of
     * development that presents a
     * word picture of a thing, a
     * person, a situation, or a
     * series of events.
     */

    private int id;
    private String title;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
