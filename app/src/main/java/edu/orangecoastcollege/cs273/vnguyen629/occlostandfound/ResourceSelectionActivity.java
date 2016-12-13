package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Allows the user to select and view whichever resource page they want,
 * (all of which are open to public listings).
 *
 * @author Vincent Nguyen
 */
public class ResourceSelectionActivity extends AppCompatActivity {

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    /**
     * Starts up the activity and prepares the ShakeDetector to monitor any
     * movements that constitute as shakes where the ListItemActivity may load
     * up if found.
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_selection);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * When a 3D motion that the sensors constitute as a shake has been detected,
             * the ItemListActivity is loaded
             */
            @Override
            public void onShake() {
                startActivity(new Intent(ResourceSelectionActivity.this, ItemsListActivity.class));
            }
        });
    }

    /**
     * Opens up the About page
     * @param view The open About button
     */
    public void openAbout(View view) {
        startActivity(new Intent(ResourceSelectionActivity.this, AboutActivity.class));
    }

    /**
     * Opens up the FAQ page
     * @param view The open FAQ button
     */
    public void openFAQ(View view) {
        startActivity(new Intent(this, FAQActivity.class));
    }

    /**
     * Opens up the Map page
     * @param view The open Map button
     */
    public void openMap(View view) {
        startActivity(new Intent(ResourceSelectionActivity.this, MapActivity.class));
    }

    /**
     * Opens up the ContactInfo page
     * @param view The open Contact Info button
     */
    public void openContactInfo(View view) {
        startActivity(new Intent(ResourceSelectionActivity.this, ContactInfoActivity.class));
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