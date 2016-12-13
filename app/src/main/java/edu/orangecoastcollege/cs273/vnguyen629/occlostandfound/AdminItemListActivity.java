package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays the item list of all the <code>Item</code> objects, but
 * also displays multiple functions that an administrator could do
 * but a regular user can't.
 *
 * @author Benjamin Nguyen
 */
public class AdminItemListActivity extends AppCompatActivity {

    private DBHelper database;
    public static List<Report> allReportList;
    public static List<Report> filteredReportList;
    public static ReportListAdapter reportListAdapter;
    private ListView reportListView;

    private EditText adminSearchItemEditText;
    private EditText adminSearchUserEditText;

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;

    /**
     * Loads up the database and ListView of all the reported items. Also sets up the
     * shake detector so if there's an appropriate shake, the ReportItemActivity
     * will be loaded.
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_item_list);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            /**
             * When a 3D motion that the sensors constitute as a shake has been detected,
             * the ItemListActivity is loaded
             */
            @Override
            public void onShake() {
                startActivity(new Intent(AdminItemListActivity.this, ReportItemActivity.class));
            }
        });

        database = new DBHelper(this);
        allReportList = database.getAllReports();
        filteredReportList = new ArrayList<>(allReportList);

        reportListView = (ListView) findViewById(R.id.reportListView);
        reportListAdapter = new ReportListAdapter(this, R.layout.list_report, allReportList);
        reportListView.setAdapter(reportListAdapter);
    }

    /**
     * When the user re-enters the app, the sensors start back up and begin
     * monitoring device movements/g-forces in a 3D (x-y-z) span. Only loads up the
     * ReportActivity if the user is logged in.
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (UserAccount.isLoggedIn) {
            sensorManager.registerListener(shakeDetector, accelerometer,
                    SensorManager.SENSOR_DELAY_UI);
        }
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

    public void adminViewItem(View view) {
        if (view instanceof LinearLayout) {
            final Item item = (Item) view.getTag();

            UserAccount account = getIntent().getExtras().getParcelable("Account");

            Intent intent = new Intent(this, UserItemDetailsActivity.class);
            intent.putExtra("Item", item);
            intent.putExtra("Account", account);
            startActivity(intent);
        }
    }
}