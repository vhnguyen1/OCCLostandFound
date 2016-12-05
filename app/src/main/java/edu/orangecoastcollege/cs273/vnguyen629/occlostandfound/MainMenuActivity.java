package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 *
 */
public class MainMenuActivity extends AppCompatActivity {
    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * When a 3D motion that the sensors constitute as a shake has been detected,
             * the ItemListActivity is loaded
             */
            @Override
            public void onShake() {
                startActivity(new Intent(MainMenuActivity.this, ItemsListActivity.class));
            }
        });
    }

    /**
     * When the user re-enters the app, the sensors start back up and begin
     * monitoring device movements/g-forces in a 3D (x-y-z) span
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
     * to preserve battery life
     */
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shakeDetector);
    }

    /**
     *
     * @param view
     */
    public void openReportedItemsList(View view) {
        startActivity(new Intent(MainMenuActivity.this, ItemsListActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openMap(View view) {
        startActivity(new Intent(MainMenuActivity.this, MapActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openFAQ(View view) {
        startActivity(new Intent(MainMenuActivity.this, FAQActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openSettings(View view) {
        startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openContactInfo(View view) {
        startActivity(new Intent(MainMenuActivity.this, ContactInfoActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openAbout(View view) {
        startActivity(new Intent(MainMenuActivity.this, AboutActivity.class));
    }

    /**
     *
     * @param view
     */
    public void openLogin(View view) {
        startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
    }
}