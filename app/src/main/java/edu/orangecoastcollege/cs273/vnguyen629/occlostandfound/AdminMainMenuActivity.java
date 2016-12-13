package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * The main menu where only administrators can get access to. Allows special
 * permissions and features that regular users cannot see or do.
 *
 * @author Benjamin Nguyen
 */
public class AdminMainMenuActivity extends AppCompatActivity {
    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    UserAccount account;

    /**
     * Starts up the activity and prepares the ShakeDetector to monitor any
     * movements that constitute as shakes where the AdminItemListActivity may load
     * up if found.
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_menu);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * When a 3D motion that the sensors constitute as a shake has been detected,
             * the ItemListActivity is loaded
             */
            @Override
            public void onShake() {
                startActivity(new Intent(AdminMainMenuActivity.this, AdminItemListActivity.class));
            }
        });

        account = getIntent().getExtras().getParcelable("Account");
    }

    /**
     * Allows the admin to view the list of all the <code>Item</code> objects but
     * with administrator permissions
     * @param view The view all items button
     */
    public void viewAllItems(View view) {
        startActivity(new Intent(this, AdminItemListActivity.class).putExtra("Account", account));
    }

    /**
     * Allows the admin to view the list of all the <code>UserAccount</code> objects
     * @param view The view all users button
     */
    public void viewAllUsers(View view) {
        startActivity(new Intent(this, AdminUserListActivity.class).putExtra("Account", account));
    }

    /**
     * Allows the admin to view the list of all the <code>UserAccount</code> objects
     * @param view The view all users button
     */
    public void viewAdminSettings(View view) {
        startActivity(new Intent(this, AdminSettingsActivity.class).putExtra("Account", account));
    }

    /**
     * Allows the admin to view the list of all the <code>UserAccount</code> objects
     * @param view The view all users button
     */
    public void adminSignOut(View view) {
        startActivity(new Intent(this, MainMenuActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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