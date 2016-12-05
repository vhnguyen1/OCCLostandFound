package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 *
 */
public class MainMenuActivity extends AppCompatActivity {
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    /**
     *
     * @param view
     */
    public void openReportedItemsList(View view) {
        startActivity(new Intent(MainMenuActivity.this, ItemsListActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openMap(View view) {
        startActivity(new Intent(MainMenuActivity.this, MapActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openFAQ(View view) {
        startActivity(new Intent(MainMenuActivity.this, FAQActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openSettings(View view) {
        startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openContactInfo(View view) {
        startActivity(new Intent(MainMenuActivity.this, ContactInfoActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openAbout(View view) {
        startActivity(new Intent(MainMenuActivity.this, AboutActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openLogin(View view) {
        startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
    }
}