package com.example.android.thenews;

import java.text.SimpleDateFormat;

/**
 * {@link News} represents a category, article title, author, and date for news articles
 */
public class News {
    /** String resource ID for the category title */
    private String mCategory;

    /** String resource ID for the article title */
    private String mTitle;

    /** String resource ID for the date */
    private String mDate;

    /** String resource ID for the author */
    private String mAuthor;

    /** String resource ID for the article url */
    private String mUrl;

    /**
     * Create a new News object.
     *
     * @param category is the string resource ID for the category title
     * @param title is the string resource Id for the article title
     * @param date is the string resource Id for the date
     * @param date is the string resource Id for the author
     * @param date is the string resource Id for the article url
     */
    public News(String category, String title, String date,  String author, String url) {
        mCategory = category;
        mTitle = title;
        mDate = date;
        mAuthor = author;
        mUrl = url;
    }

    /**
     * Get the string resource ID for the category title.
     */
    public String getSection() {
        return mCategory;
    }

    /**
     * Get the string resource ID for the article title.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the string resource ID for the author.
     */
    public String getAuthor() { return mAuthor; }

    /**
     * Get the string resource ID for the date.
     */
    public String getDate() { return mDate; }

    /**
     * Get the string resource ID for the url.
     */
    public String getUrl() {
        return mUrl;
    }

}


