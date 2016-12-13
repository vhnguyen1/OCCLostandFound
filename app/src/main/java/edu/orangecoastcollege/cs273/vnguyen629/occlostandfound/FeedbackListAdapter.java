/*
package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * Helper class to provide a custom adapter for the ussr feedback.
 *
 * @author Benjamin Nguyen
 *//*

public class FeedbackListAdapter extends ArrayAdapter<UserAccount> {
    private int mResourceId;
    private Context mContext;
    private List<UserAccount> mFeedbackList = new ArrayList<>();

    private LinearLayout feedbackListLinearLayout;
    private TextView feedbackListTextView;

    */
/**
     * Creates a new <code>FeedbackListAdapter</code> given a mContext, resource id
     * and list of lost items.
     * @param c The mContext for which the adapter is being used (typically an activity).
     * @param rId The resource id (typically the layout file name).
     * @param userAccountList The list of items to display.
     *//*

    public FeedbackListAdapter(Context c, int rId, List<UserAccount> userAccountList) {
        super(c, rId, userAccountList);
        this.mContext = c;
        this.mResourceId = rId;
        this.mFeedbackList = userAccountList;
    }

    */
/**
     * Gets the view associated with the layout.
     * @param pos The position of the <code>UserAccount</code> selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter.
     * @return The new view with all content set.
     *//*

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) this.mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(this.mResourceId, null);

        feedbackListLinearLayout = (LinearLayout) view.findViewById(R.id.feedbackListLinearLayout);
        feedbackListTextView = (TextView) view.findViewById(R.id.feedbackListTextView);

        return view;
    }
}*/
