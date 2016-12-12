package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 *
 */
public class UserMenuActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
    }

    /**
     *
     * @param view
     */
    public void userViewItems(View view) {
        startActivity(new Intent(UserMenuActivity.this, UserItemsListActivity.class));
    }

    /**
     *
     * @param view
     */
    public void userReportItem(View view) {
        startActivity(new Intent(UserMenuActivity.this, ReportItemActivity.class));
    }

    /**
     *
     * @param view
     */
    public void userViewSettings(View view) {
        startActivity(new Intent(UserMenuActivity.this, UserSettingsActivity.class));
    }

    /**
     *
     * @param view
     */
    public void userViewFeedback(View view) {
        startActivity(new Intent(UserMenuActivity.this, FeedbackActivity.class));
    }
}
