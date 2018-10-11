package com.example.android.thenews;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link QueryUtils}
 */

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }

    //Set up HTTP request
    public static List<News> fetchNewsData(String requestUrl) {

        URL url = createUrl(requestUrl);
        String jsonResponse = null;

        //Try/catch for making HTTP request
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            //Log error fulfilling HTTP request
            Log.e(LOG_TAG, "Problem fulfilling the HTTP request.", e);
        }
        List<News> news = extractResponseFromJson(jsonResponse);

        //Return news list
        return news;
    }

    //Create URL
    private static URL createUrl(String stringUrl) {
        URL url = null;

        //Try/catch for creating URL
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            //Log error creating the url
            Log.e(LOG_TAG, "Error creating the URL ", e);
        }
        return url;
    }

    //Make HTTP request
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        //Try/catch for making HTTP request
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                //Log the specific error response code
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            //Log error retrieving results
            Log.e(LOG_TAG, "Problem retrieving The News App JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //Read and parse string stream
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    //Assign and parsing extracted response
    private static List<News> extractResponseFromJson(String newsappJSON) {
        if (TextUtils.isEmpty(newsappJSON)) {

            return null;
        }

        List<News> newsapp = new ArrayList<>();

        //Try/catch response
        try {

            JSONObject baseJsonResponse = new JSONObject(newsappJSON);

            JSONObject newsJSONObject = baseJsonResponse.getJSONObject("response");

            JSONArray newsappArray = newsJSONObject.getJSONArray("results");

            for (int i = 0; i < newsappArray.length(); i++) {

                JSONObject currentNews = newsappArray.getJSONObject(i);

                String section = currentNews.getString("sectionId");

                String title = currentNews.getString("webTitle");

                String date = currentNews.getString("webPublicationDate");

                String url = currentNews.getString("webUrl");

                JSONArray authorArray = currentNews.getJSONArray("tags");

                JSONObject currentAuthor = authorArray.getJSONObject(0);

                String author = currentAuthor.getString("webTitle");

                News newsapps = new News(title, section, date, author, url);

                newsapp.add(newsapps);
            }

        } catch (JSONException e) {

            //Log error for problems parsing API requests
            Log.e("QueryUtils", "Error parsing The News App JSON results", e);
        }

        return newsapp;
    }

}
