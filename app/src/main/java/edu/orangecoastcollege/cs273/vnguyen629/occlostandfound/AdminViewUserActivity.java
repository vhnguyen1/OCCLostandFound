package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Allows an administrator to view any the details of a user account
 *
 * @author Benjamin Nguyen
 */
public class AdminViewUserActivity extends AppCompatActivity {

    private TextView adminViewUsernameTextView;
    private TextView adminViewStudentIDTextView;
    private TextView adminViewEmailTextView;
    private TextView adminViewPhoneTextView;

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    UserAccount account, selectedUser;


    /**
     * Starts up the activity and links up the TextViews and sets them up with
     * the user's information. Also prepares the ShakeDetector to monitor any
     * movements that constitute as shakes where the AdminItemListActivity may load
     * up if found.
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);

        account = getIntent().getExtras().getParcelable("Account");
        selectedUser = getIntent().getExtras().getParcelable("SelectedUser");

        adminViewUsernameTextView = (TextView) findViewById(R.id.adminViewUsernameTextView);
        adminViewStudentIDTextView = (TextView) findViewById(R.id.adminViewStudentIDTextView);
        adminViewEmailTextView = (TextView) findViewById(R.id.adminViewEmailTextView);
        adminViewPhoneTextView = (TextView) findViewById(R.id.adminViewPhoneTextView);

        adminViewUsernameTextView.setText(selectedUser.getStudentUserName());
        adminViewPhoneTextView.setText(selectedUser.getStudentPhoneNum());
        adminViewStudentIDTextView.setText(selectedUser.getStudentID());
        adminViewEmailTextView.setText(selectedUser.getStudentEmail());

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * When a 3D motion that the sensors constitute as a shake has been detected,
             * the ItemListActivity is loaded
             */
            @Override
            public void onShake() {
                startActivity(new Intent(AdminViewUserActivity.this, AdminItemListActivity.class));
            }
        });
    }

    /**
     * Allows the admin to view the feedback the specific user made
     * @param view The view feedback button
     */
    public void viewFeedback(View view) {
        selectedUser = getIntent().getExtras().getParcelable("SelectedUser");
        Log.i("selecteduser", selectedUser.toString());
        Toast.makeText(this, selectedUser.toString(), Toast.LENGTH_SHORT);
        startActivity(new Intent(this, ViewUserFeedbackActivity.class).putExtra("Account", account).putExtra("SelectedUser", selectedUser));
    }

    public void viewSendSMS(View view) {
        startActivity(new Intent(this, SendSMSActivity.class).putExtra("Account", account).putExtra("SelectedUser", selectedUser));
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