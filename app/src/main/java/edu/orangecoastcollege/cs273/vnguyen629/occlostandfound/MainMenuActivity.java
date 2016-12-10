package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * The main menu of the application. It allows the user to select and
 * load up any activity from the application.
 *
 * @author Vincent Nguyen
 */
public class MainMenuActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main_menu);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * When a 3D motion that the sensors constitute as a shake has been detected,
             * the ItemListActivity is loaded.
             */
        @Override
        public void onShake() {
            startActivity(new Intent(MainMenuActivity.this, ItemsListActivity.class));
        }
    });
    }

    /**
     * Opens up ItemListActivity where the user may report a lost item or view
     * the list of all the reported items saved in the database.
     * @param view The ImageView or TextViews that open up the ItemListActivity.
     */
    public void openReportedItemsList(View view) {
        startActivity(new Intent(MainMenuActivity.this, ItemsListActivity.class));
    }

    /**
     * Opens up the the MapActivity where the user may view the location of
     * the Lost and Found, reported areas, or OCC the campus map.
     * @param view The ImageView or TextViews that open up the MapActivity.
     */
    public void openMap(View view) {
        startActivity(new Intent(MainMenuActivity.this, MapActivity.class));
    }

    /**
     * Opens up the SettingsActivity where the user may change various settings in regards
     * to their account, or settings about the application itself.
     * @param view The ImageView or TextViews that open up the SettingsActivity.
     */
    public void openSettings(View view) {
        startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
    }

    /**
     * Opens up the LoginActivity so the user can either sign in to their account,
     * or create a new account, or sign off.
     * @param view The clickable ImageView or TextView that opens up the LoginActivity.
     */
    public void signOut(View view) {
        UserAccount.isLoggedIn = false;
        UserAccount.singedInUserAccountName = "";
        startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
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