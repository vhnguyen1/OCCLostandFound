package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * An activity that shows the users potential and frequently asked questions with a series
 * of answers.
 *
 * @author Vincent Nguyen
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
