package com.example.android.thenews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * {@link NewsLoader} for async task loader
 */
public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /** String resource log tag */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** String resource url */
    private String mUrl;

    /**
     * Create NewsLoader object
     *
     * @param context is the context
     * @param url is the string resource Id for url
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<News> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }
}
