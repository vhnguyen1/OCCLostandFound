package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UserItemDetailsActivity extends AppCompatActivity {

    private Spinner userItemStatusSpinner;

    /**
     * Starts up the activity and loads up the intent data from the ItemListActivity
     * that the user selects from the ListView, displaying the data. It also
     * prepares the ShakeDetector to monitor any movements that constitute as shakes
     * where the ListItemActivity may load up if found.
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_item_details);

        userItemStatusSpinner = (Spinner) findViewById(R.id.userItemStatusSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status_choices, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        userItemStatusSpinner.setAdapter(adapter);

    }


}
