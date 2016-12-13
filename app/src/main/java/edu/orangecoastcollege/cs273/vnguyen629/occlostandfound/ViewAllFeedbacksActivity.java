package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Allows administrators to view all the feedback provided by the users
 *
 * @author Benjamin Nguyen
 */
public class ViewAllFeedbacksActivity extends AppCompatActivity {

    /**
     * Create and init variable
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_feedbacks);
    }
}
