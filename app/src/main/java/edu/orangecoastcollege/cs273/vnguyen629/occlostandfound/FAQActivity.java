package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *
 */
public class FAQActivity extends AppCompatActivity {

    /**
     * Starts up the activity and prepares the ShakeDetector to monitor any
     * movements that constitute as shakes where the ListItemActivity may load
     * up if found
     * @param savedInstanceState The state of the application saved into a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
    }
}
