package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static edu.orangecoastcollege.cs273.vnguyen629.occlostandfound.UserAccount.singedInUserAccountName;

/**
 * The activity where the user submits a report of any lost
 * item(s).
 *
 * @author Vincent Nguyen
 */
public class ReportItemActivity extends AppCompatActivity {
    private Uri imageUri;
    private ImageView reportItemImageView;
    private EditText reportItemNameEditText;
    private EditText reportItemLastLocationEditText;
    private EditText reportItemDescriptionEditText;
    private Spinner monthSpinner;
    private Spinner dayNumberSpinner;
    private CheckBox smsCheckBox;

    private DBHelper database;

    private static String month;
    private static String day;
    private static final String YEAR = "2016";

    private static final int REPORT_ITEM_REQUEST_CODE = 13;

    /**
     * Sets up the page where the user may input various information about their lost item to
     * submit to the database.
     * @param savedInstanceState The state of the application saved into a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_item);

        month = getString(R.string.month_text);
        day = getString(R.string.day_text);

        database = new DBHelper(this);

        reportItemImageView = (ImageView) findViewById(R.id.reportItemImageView);
        reportItemNameEditText = (EditText) findViewById(R.id.reportItemNameEditText);
        reportItemLastLocationEditText = (EditText) findViewById(R.id.reportItemLastLocationEditText);
        reportItemDescriptionEditText = (EditText) findViewById(R.id.reportItemDescriptionEditText);

        smsCheckBox = (CheckBox) findViewById(R.id.smsCheckBox);

        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        ArrayAdapter<String> monthSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getMonthNames());
        monthSpinner.setAdapter(monthSpinnerAdapter);
        monthSpinner.setOnItemSelectedListener(monthSpinnerListener);

        dayNumberSpinner = (Spinner) findViewById(R.id.dayNumberSpinner);
        ArrayAdapter<String> dayNumberSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getDayNumbers());
        dayNumberSpinner.setAdapter(dayNumberSpinnerAdapter);
        dayNumberSpinner.setOnItemSelectedListener(dayNumberSpinnerListener);

        imageUri = getUriToResource(this, R.drawable.default_image);
        reportItemImageView.setImageURI(imageUri);
    }

    /**
     * Retrieves all the names of the months in the U.S. calender
     */
    private String[] getMonthNames() {
        String monthNames[] = new String[13];

        monthNames[0] = getString(R.string.month_text);
        monthNames[1] = getString(R.string.january_text);
        monthNames[2] = getString(R.string.february_text);
        monthNames[3] = getString(R.string.march_text);
        monthNames[4] = getString(R.string.april_text);
        monthNames[5] = getString(R.string.may_text);
        monthNames[6] = getString(R.string.june_text);
        monthNames[7] = getString(R.string.july_text);
        monthNames[8] = getString(R.string.august_text);
        monthNames[9] = getString(R.string.september_text);
        monthNames[10] = getString(R.string.october_text);
        monthNames[11] = getString(R.string.november_text);
        monthNames[12] = getString(R.string.december_text);

        return monthNames;
    }

    /**
     * Retrieves all the names of the months in the U.S. calender
     */
    private String[] getDayNumbers() {
        String dayNumbers[] = new String[32];

        dayNumbers[0] = getString(R.string.day_text);
        for (int day = 1; day < dayNumbers.length; day++) {
            if (day < 10)
                dayNumbers[day] = "0" + String.valueOf(day);
            else
                dayNumbers[day] = String.valueOf(day);
        }

        return dayNumbers;
    }

    /**
     *
     */
    public AdapterView.OnItemSelectedListener monthSpinnerListener =
            new AdapterView.OnItemSelectedListener() {
                /**
                 *
                 * @param parent
                 * @param view
                 * @param position
                 * @param l
                 */
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                    String selectedMonth = String.valueOf(parent.getItemAtPosition(position));

                    if (selectedMonth.equals(getString(R.string.month_text)))
                        month = "N/A";
                    else
                        month = selectedMonth;
                }

                /**
                 *
                 * @param parent
                 */
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    parent.setSelection(0);
                }
            };

    /**
     *
     */
    public AdapterView.OnItemSelectedListener dayNumberSpinnerListener =
            new AdapterView.OnItemSelectedListener() {
                /**
                 * Monitors the day spinner and changes the values accordingly to what day
                 * is chosen
                 * @param parent The adapter
                 * @param view The spinner
                 * @param position Specific item from the spinner's position
                 * @param l Unused
                 */
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                    String selectedDay = String.valueOf(parent.getItemAtPosition(position));

                    if (selectedDay.equals(getString(R.string.day_text)))
                        day = "N/A";
                    else {
                        day = selectedDay;
                    }
                }

                /**
                 * Resets the spinner selection to the original/default value
                 * @param parent The adapter
                 */
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    parent.setSelection(0);
                }
            };

    /**
     * If the necessary fields are not empty, then the data is then submitted and entered
     * into the databases.
     * @param view The submit button ImageView that submits and adds the item to the database.
     */
    public void submitReport(View view) {
        final String NAME = reportItemNameEditText.getText().toString().replaceAll("\\s+","");
        final String LAST_LOCATION = reportItemLastLocationEditText.getText().toString().replaceAll("\\s+","");

        if (NAME.equals("") || LAST_LOCATION.equals("") || day.equals(R.string.day_text)
                || month.equals(getString(R.string.month_text)))
            Toast.makeText(this, getString(R.string.all_fields_mandatory_text),
                    Toast.LENGTH_SHORT).show();
        else {
            if ((singedInUserAccountName.equals(""))) {
                final String NEW_ITEM_NAME = reportItemNameEditText.getText().toString().trim();
                final String NEW_ITEM_DATE_LOST = month + " " + day + ", " + YEAR;
                final String NEW_ITEM_LAST_LOCATION = reportItemLastLocationEditText.getText().toString().trim();
                final String NEW_ITEM_DESCRIPTION = reportItemDescriptionEditText.getText().toString().trim();

                final int SMS_NOTIFICATIONS = ((smsCheckBox.isChecked())? 1 : 0);

                if (imageUri == null)
                    imageUri = getUriToResource(this, R.drawable.default_image);

                final UserAccount ACCOUNT = getIntent().getExtras().getParcelable("Account");
                //UserAccount account = database.getUserAccount(singedInUserAccountName);

                Item newItem = new Item(NEW_ITEM_NAME, NEW_ITEM_DESCRIPTION, NEW_ITEM_DATE_LOST,
                        NEW_ITEM_LAST_LOCATION, false, imageUri, singedInUserAccountName);

                Report newReport = new Report(ACCOUNT, newItem, SMS_NOTIFICATIONS);

                database.addItem(newItem);
                //ItemsListActivity.allItemsList.add(newItem);
                //ItemsListActivity.itemsListAdapter.add(newItem);
                //ItemsListActivity.itemsListAdapter.notifyDataSetChanged();
                database.addReport(newReport);
                this.finish();
            }
        }
    }

    /**
     * Closes the activity and brings up the item list
     * @param view The cancel button
     */
    public void cancelReport(View view) {
        this.finish();
    }

    /**
     * After finished with the gallery, it onActivityResult checks to see if a valid action/image
     * was successfully chosen and changes the report item image if it is.
     * @param requestCode A predefined code to compare to the result code.
     * @param resultCode The actual response from selecting an image, if it is 13 then it is allowed.
     * @param data The selected image uri to set the item's image to.
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REPORT_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            reportItemImageView.setImageURI(imageUri);
        }
    }

    /**
     * Asks the user for camera and writing/reading external storage
     * permissions. If the user allows both of these permissions, then
     * it opens up the gallery allowing the user to select an image of the item
     * that was lost.
     * @param view The ImageView in the ReportActivity.
     */
    public void selectItemImage(View view) {
        ArrayList<String> permList =  new ArrayList<>();

        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.CAMERA);

        int writePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writePermission != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int readPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readPermission != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permList.size() > 0) {
            String[] perms = new String[permList.size()];
            ActivityCompat.requestPermissions(this, permList.toArray(perms), REPORT_ITEM_REQUEST_CODE);
        }

        if (cameraPermission == PackageManager.PERMISSION_GRANTED
                && writePermission == PackageManager.PERMISSION_GRANTED
                && readPermission == PackageManager.PERMISSION_GRANTED) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REPORT_ITEM_REQUEST_CODE);
        }
        else {
            if (cameraPermission != PackageManager.PERMISSION_GRANTED
                    && writePermission != PackageManager.PERMISSION_GRANTED
                    && readPermission != PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, getString(R.string.camera_and_external_permissions),
                        Toast.LENGTH_SHORT).show();
            else if (cameraPermission != PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, getString(R.string.camera_permissions),
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, getString(R.string.external_permissions),
                        Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Get uri to any resource type within an Android Studio project. Method is public static to
     * allow other classes to use it as a helper function.
     * @param context The current context.
     * @param resID The resource identifier for drawable.
     * @return Uri to resource by given id.
     * @throws Resources.NotFoundException If the given resource id does not exist.
     */
    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resID)
            throws Resources.NotFoundException {
        /** Return a Resources instance for your application's package. */
        Resources res = context.getResources();
        /** return URI. */
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resID)
                + '/' + res.getResourceTypeName(resID)
                + '/' + res.getResourceEntryName(resID));
    }
}