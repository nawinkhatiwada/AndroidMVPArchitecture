package com.nawin.androidmvparchitecture.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by brainovation on 6/14/17.
 */

public class News {

    /**
     * newsId : 1
     * newsTitle : How do you cite a newspaper article?
     * newsDescription: : Schwartz, J. (1993, September 30). Obesity affects economic, social status. The Washington Post, pp. A1, A4.Precede page numbers for newspaper articles with p. or pp .If an article appears on discontinuous pages, give all page numbers, and separate the numbers with a comma (e.g., pp. B1, B3, B5–B7)
     * author : Nawin Khatiwada
     * authorUserName : nawin.khatiwada@gmail.com
     * postedDate : 2017/05/19
     */

    private int newsId;
    private String newsTitle;
    private String newsDescription; // FIXME check this code
    private String author;
    private String authorUserName;
    private String postedDate;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorUserName() {
        return authorUserName;
    }

    public void setAuthorUserName(String authorUserName) {
        this.authorUserName = authorUserName;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }
}
