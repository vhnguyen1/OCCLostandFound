package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

/**
 * Allows administrators to view all the feedback provided by the users
 *
 * @author Benjamin Nguyen
 */
public class ViewAllFeedbacksActivity extends AppCompatActivity {

    private DBHelper database;
    public static List<UserAccount> allFeedbackList;
    private ListView feedbackListView;
    private static FeedbackListAdapter feedbackListAdapter;

    /**
     * Create and init variable
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_feedbacks);

        //this.deleteDatabase(DBHelper.DATABASE_NAME);
        database = new DBHelper(this);

        feedbackListView = (ListView) findViewById(R.id.feedbackListView);
        //feedbackListView = new FeedbackListAdapter(this, R.layout.activity_view_user_feedback, allFeedbackList);
        feedbackListView.setAdapter(feedbackListAdapter);
    }
}