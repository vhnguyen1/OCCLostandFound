package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UserMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
    }

    public void userViewItems(View view) {
        startActivity(new Intent(UserMenuActivity.this, UserItemsListActivity.class));
    }

    public void userReportItem(View view) {
        startActivity(new Intent(UserMenuActivity.this, ReportItemActivity.class));
    }

    public void userViewSettings(View view) {
        startActivity(new Intent(UserMenuActivity.this, UserSettingsActivity.class));
    }

    public void userViewFeedback(View view) {
        startActivity(new Intent(UserMenuActivity.this, FeedbackActivity.class));
    }
}
