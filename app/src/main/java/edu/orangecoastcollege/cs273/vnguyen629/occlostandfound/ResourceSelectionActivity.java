package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Allows the user to select and view whichever resource page they want,
 * (all of which are open to public listings).
 *
 * @author Vincent Nguyen
 */
public class ResourceSelectionActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_selection);
    }
}
