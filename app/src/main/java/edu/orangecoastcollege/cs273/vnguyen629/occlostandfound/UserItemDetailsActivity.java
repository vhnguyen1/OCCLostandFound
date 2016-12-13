package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

public class UserItemDetailsActivity extends AppCompatActivity {

    private UserAccount loggedInAccount;

    private Spinner userItemStatusSpinner;

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    private CheckBox userItemSMSCheckBox;

    private ArrayList<Report> reportedItemList;
    private DBHelper database = new DBHelper(this);

    /**
     * Starts up the activity and loads up the intent data from the ItemListActivity
     * that the user selects from the ListView, displaying the data. It also
     * prepares the ShakeDetector to monitor any movements that constitute as shakes
     * where the ListItemActivity may load up if found.
     *
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_item_details);

        final String MESSAGE = getString(R.string.your_item_has_been_found_text);

        loggedInAccount = getIntent().getExtras().getParcelable("Account");

        Item selectedItem = getIntent().getExtras().getParcelable("Item");

        userItemSMSCheckBox = (CheckBox) findViewById(R.id.userItemSMSCheckBox);

        userItemStatusSpinner = (Spinner) findViewById(R.id.userItemStatusSpinner);
        final String[] strings = {getString(R.string.not_found), getString(R.string.found)};
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_choices, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        userItemStatusSpinner.setAdapter(adapter);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * When a 3D motion that the sensors constitute as a shake has been detected,
             * the ItemListActivity is loaded
             */
            @Override
            public void onShake() {
                startActivity(new Intent(UserItemDetailsActivity.this, UserItemsListActivity.class));
            }
        });

        userItemStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * If the user selects a valid category, it displays all the
             * objects that meet the specified criteria.
             * @param parent Unused
             * @param view Unused
             * @param position Unused
             * @param id Unused
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedStatus = String.valueOf(parent.getItemAtPosition(position));

                if (selectedStatus.equals(strings[1])) {
                    SmsManager manager = SmsManager.getDefault();
                    manager.sendTextMessage(loggedInAccount.getStudentPhoneNum(),
                            null, MESSAGE, null, null);
                }
            }

            /**
             * If the user clicks on the category filter spinner but ends up not choosing a
             * category, the default value is set.
             * @param parent The category filter spinner
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setSelection(0);
            }
        });

        reportedItemList = database.getAllReports();

        for (Report report : reportedItemList)
        {
            if(report.getItem().getName().equals(selectedItem.getName()))
            {
                if(userItemSMSCheckBox.isChecked())
                {
                    SmsManager manager = SmsManager.getDefault();
                    manager.sendTextMessage(loggedInAccount.getStudentPhoneNum(), null, MESSAGE, null, null);
                }
            }
        }
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