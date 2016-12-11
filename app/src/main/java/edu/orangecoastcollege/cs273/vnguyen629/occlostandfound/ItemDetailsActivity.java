package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Loads intent data from the ItemListActivity, displaying the selected
 * item's data more specifically
 *
 * @author Vu Nguyen
 */
public class ItemDetailsActivity extends AppCompatActivity {
    private ImageView itemDetailsImageView;
    private TextView itemDetailsNameTextView;
    private TextView itemDetailsDateLostTextView;
    private TextView itemDetailsLocationTextView;
    private TextView itemDetailsDescriptionTextView;
    private TextView itemDetailsStatusTextView;

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

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
        setContentView(R.layout.activity_item_details);

        final Item ITEM = getIntent().getExtras().getParcelable("Selected");

        itemDetailsImageView = (ImageView) findViewById(R.id.itemDetailsImageView);
        itemDetailsNameTextView = (TextView) findViewById(R.id.itemDetailsNameTextView);
        itemDetailsDateLostTextView = (TextView) findViewById(R.id.itemDetailsDateLostTextView);
        itemDetailsLocationTextView = (TextView) findViewById(R.id.itemDetailsLocationTextView);
        itemDetailsDescriptionTextView = (TextView) findViewById(R.id.itemDetailsDescriptionTextView);
        itemDetailsStatusTextView = (TextView) findViewById(R.id.itemDetailsStatusTextView);

        itemDetailsImageView.setImageURI(ITEM.getImageUri());
        itemDetailsNameTextView.setText(ITEM.getName());
        itemDetailsDescriptionTextView.setText(ITEM.getDescription());
        itemDetailsDateLostTextView.setText(ITEM.getDateLost());
        itemDetailsLocationTextView.setText(ITEM.getLastLocation());
        itemDetailsStatusTextView.setText((ITEM.getStatus())? getString(R.string.found_text)
                : getString(R.string.not_found_text));

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * When a 3D motion that the sensors constitute as a shake has been detected,
             * the ItemListActivity is loaded
             */
            @Override
            public void onShake() {
                startActivity(new Intent(ItemDetailsActivity.this, ItemsListActivity.class));
            }
        });
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