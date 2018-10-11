package com.example.android.thenews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yahir on 9/19/2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Create a new {@link NewsAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param news is the list of {@link News}s to be displayed.
     */
    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Get the {@link News} object located at this position in the list
        News currentNews = getItem(position);

        // Find the TextView in the list_item.xml layout with the category_textview.
        TextView titleView = listItemView.findViewById(R.id.category_textview);
        // Get the address from the currentNews object and set this text on
        // the category TextView.
        titleView.setText(currentNews.getTitle());

        // Find the TextView in the list_item.xml layout with the title_textview.
        TextView sectionView = listItemView.findViewById(R.id.title_textview);
        // Get the address from the currentNews object and set this text on
        // the title TextView.
        sectionView.setText(currentNews.getSection());

        // Find the TextView in the list_item.xml layout with the date_textview.
        TextView dateView = listItemView.findViewById(R.id.date_textview);
        // Get the address from the currentNews object and set this text on
        // the date TextView.
        dateView.setText(currentNews.getDate());

        // Find the TextView in the list_item.xml layout with the author_textview.
        TextView authorView = listItemView.findViewById(R.id.author_textview);
        // Get the address from the currentNews object and set this text on
        // the author TextView.
        authorView.setText("by " + currentNews.getAuthor());

        // Return the whole list item layout (containing 4 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

}
