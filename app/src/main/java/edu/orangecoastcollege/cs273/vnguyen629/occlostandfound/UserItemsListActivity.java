package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserItemsListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Item> itemList;
    private List<Report> reportList;
    private ItemListAdapter itemListAdapter;
    private ListView itemListView;

    private UserAccount loggedInAccount;

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    /**
     *
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_items_list);

        db = new DBHelper(this);

        loggedInAccount = getIntent().getExtras().getParcelable("Account");

        itemList = new ArrayList<Item>();

        //db.addItem(new Item("name", "des", "date", "loc", false, Uri.parse("android.resource://edu.orangecoastcollege.cs273.vnguyen629.occlostandfound/" + R.drawable.default_image), "user"));
        //db.addItem(new Item("name", "des", "date", "loc", false,
        // Uri.parse("android.resource://edu.orangecoastcollege.cs273.vnguyen629.occlostandfound/"
        // + R.drawable.default_image), "user"));

        reportList = db.getAllReportsFromUser(loggedInAccount);

        for (Report report : reportList) {
            itemList.add(report.getItem());
        }

        itemListView = (ListView) findViewById(R.id.userItemsListView);
        itemListAdapter = new ItemListAdapter(this, R.layout.list_item, itemList);
        itemListView.setAdapter(itemListAdapter);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * When a 3D motion that the sensors constitute as a shake has been detected,
             * the ItemListActivity is loaded
             */
            @Override
            public void onShake() {
                startActivity(new Intent(UserItemsListActivity.this, ItemsListActivity.class));
            }
        });
    }

    /**
     *
     * @param view
     */
    public void viewUserItemDetails(View view) {
        if (view instanceof LinearLayout) {
            final Item SELECTED_ITEM = (Item) view.getTag();
            Log.i("OCC Lost and Found", SELECTED_ITEM.toString());

            Intent detailsIntent = new Intent(this, UserItemDetailsActivity.class);
            detailsIntent.putExtra("Selected", SELECTED_ITEM);
            startActivity(detailsIntent);
        } else
            Toast.makeText(this, "Error selecting item.", Toast.LENGTH_SHORT).show();
    }

    /**
     * When the user re-enters the app, the sensors start back up and begin
     * monitoring device movements/g-forces in a 3D (x-y-z) span.
     */
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeDetector, accelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * When the user switches apps or clicks on the home button without closing the app,
     * all the sensors that monitor device movements and g-forces are then paused
     * to preserve battery life.
     */
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shakeDetector);
    }
}
