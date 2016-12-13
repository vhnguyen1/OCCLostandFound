package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class UserItemDetailsActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_SEND_SMS = 101;

    private UserAccount selectedAccount;
    private Item selectedItem;

    private Spinner userItemStatusSpinner;
    private TextView userItemDetailsNameTextView;
    private TextView userItemDetailsDateTextView;
    private TextView userItemDetailsLocationTextView;
    private TextView userItemDetailsDescriptionTextView;
    private ImageView userItemDetailsImageView;

    private Animation colorChange;

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    private String MESSAGE;

    private ArrayList<Report> reportedItemList;
    private DBHelper database;


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

        colorChange = AnimationUtils.loadAnimation(this, R.anim.color_change);

        database = new DBHelper(this);

        MESSAGE = getString(R.string.your_item_has_been_found_text);

        selectedAccount = getIntent().getExtras().getParcelable("Account");

        selectedItem = getIntent().getExtras().getParcelable("Item");

        userItemStatusSpinner = (Spinner) findViewById(R.id.userItemStatusSpinner);
        String[] strings = {getString(R.string.not_found), getString(R.string.found)};
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userItemStatusSpinner.setAdapter(adapter);

        userItemDetailsNameTextView = (TextView) findViewById(R.id.userItemDetailsNameTextView);
        userItemDetailsDateTextView = (TextView) findViewById(R.id.userItemDetailsDateTextView);
        userItemDetailsLocationTextView = (TextView) findViewById(R.id.userItemDetailsLocationTextView);
        userItemDetailsDescriptionTextView = (TextView) findViewById(R.id.userItemDetailsDescriptionTextView);
        userItemDetailsImageView = (ImageView) findViewById(R.id.userItemDetailsImageView);

        userItemDetailsNameTextView.setText(selectedItem.getName());
        userItemDetailsDateTextView.setText(selectedItem.getDateLost());
        userItemDetailsLocationTextView.setText(selectedItem.getLastLocation());
        userItemDetailsDescriptionTextView.setText(selectedItem.getDescription());


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

        userItemStatusSpinner.setSelection((selectedItem.getStatus() ? 1 : 0));
        userItemStatusSpinner.setOnItemSelectedListener(statusSpinnerListener);
    }

    public AdapterView.OnItemSelectedListener statusSpinnerListener = new AdapterView.OnItemSelectedListener() {
        /**
         * @param parent
         * @param view
         * @param position
         * @param id
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedItem.setStatus(position == 1);
            String[] strings = {getString(R.string.not_found), getString(R.string.found)};
            String selectedStatus = String.valueOf(parent.getItemAtPosition(position));
            if (selectedStatus.equals(strings[1])) {
                userItemDetailsImageView.setAnimation(colorChange);
                if (ActivityCompat.checkSelfPermission(UserItemDetailsActivity.this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UserItemDetailsActivity.this, new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_CODE_SEND_SMS);
                } else {
                    SmsManager manager = SmsManager.getDefault();
                    manager.sendTextMessage(selectedAccount.getStudentPhoneNum(), null, MESSAGE, null, null);
                }
            }
            database.updateItem(selectedItem);
        }

        /**
         * @param parent
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            parent.setSelection(0);
        }
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
