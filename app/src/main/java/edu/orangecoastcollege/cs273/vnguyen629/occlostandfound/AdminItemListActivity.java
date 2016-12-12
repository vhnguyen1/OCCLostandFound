package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Displays the item list of all the <code>Item</code> objects, but
 * also displays multiple functions that an administrator could do
 * but a regular user can't.
 *
 * @author Benjamin Nguyen
 */
public class AdminItemListActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_item_list);
    }
}
