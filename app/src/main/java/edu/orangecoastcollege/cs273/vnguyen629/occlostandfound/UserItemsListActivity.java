package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Benjamin Nguyen
 */
public class UserItemsListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Item> itemList;
    private List<Report> reportList;
    private ItemListAdapter itemListAdapter;
    private ListView itemListView;

    private UserAccount loggedInAccount;

    private EditText userItemSearchEditText;

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    /**
     * Create Init all variable
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_items_list);

        db = new DBHelper(this);

        loggedInAccount = getIntent().getExtras().getParcelable("Account");

        itemList = new ArrayList<Item>();

        reportList = db.getAllReportsFromUser(loggedInAccount);

        for (Report report : reportList) {
            itemList.add(report.getItem());
        }

        itemListView = (ListView) findViewById(R.id.userItemsListView);
        itemListAdapter = new ItemListAdapter(this, R.layout.list_item, itemList);
        itemListView.setAdapter(itemListAdapter);

        userItemSearchEditText = (EditText) findViewById(R.id.userItemSearchEditText);
        userItemSearchEditText.addTextChangedListener(itemNameSearchTextWatcher);

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
     * Select and launch selected item account
     * @param view
     */
    public void viewItemDetails(View view) {
        if (view instanceof LinearLayout) {
            UserAccount account = getIntent().getExtras().getParcelable("Account");
            final Item SELECTED_ITEM = (Item) view.getTag();
            Log.i("OCC Lost and Found", SELECTED_ITEM.toString());

            if (account != null) {
                Intent detailsIntent = new Intent(this, UserItemDetailsActivity.class);
                detailsIntent.putExtra("Item", SELECTED_ITEM);
                detailsIntent.putExtra("Account", account);
                startActivity(detailsIntent);
            } else {
                startActivity(new Intent(this, ItemDetailsActivity.class).putExtra("Item", SELECTED_ITEM));
            }
        } else
            Toast.makeText(this, getString(R.string.error_selecting_item_text),
                    Toast.LENGTH_SHORT).show();
    }

    /**
     * Monitors the EditText for searching item names in the database. It displays results
     * as the text is typed/deleted accordingly.
     */
    public TextWatcher itemNameSearchTextWatcher = new TextWatcher() {
        /**
         * Unused
         * @param charSequence Unused
         * @param i Unused
         * @param i1 Unused
         * @param i2 Unused
         */
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        /**
         * Monitors the EditText for searching item names in the database. It displays results
         * as the text is typed/deleted accordingly.
         * @param charSequence The input from the EditText.
         * @param i Unused
         * @param i1 Unused
         * @param i2 Unused
         */
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String input = charSequence.toString().toLowerCase();
            itemListAdapter.clear();

            if (input.equals("")) {
                for (Report report : reportList) {
                    itemList.add(report.getItem());
                }

                for (Item item : itemList)
                    itemListAdapter.add(item);
            }
            else {
                for (Report report : reportList) {
                    itemList.add(report.getItem());
                }

                for (Item item : itemList) {
                    if (item.getName().toLowerCase().contains(input))
                        itemListAdapter.add(item);
                }
            }
        }

        /**
         * Unused
         * @param editable Unused
         */
        @Override
        public void afterTextChanged(Editable editable) {}
    };

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
