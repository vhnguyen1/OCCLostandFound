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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

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
    private EditText reportItemDateLostEditText;
    private EditText reportItemLastLocationEditText;
    private EditText reportItemDescriptionEditText;

    private ImageView submitButtonImageView;

    private static final int REQUEST_CODE = 13;

    /**
     *
     * @param savedInstanceState The state of the application saved into a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_item);

        reportItemImageView = (ImageView) findViewById(R.id.reportItemImageView);
        reportItemNameEditText = (EditText) findViewById(R.id.reportItemNameEditText);
        reportItemDateLostEditText = (EditText) findViewById(R.id.reportItemDateLostEditText);
        reportItemLastLocationEditText = (EditText) findViewById(R.id.reportItemLastLocationEditText);
        reportItemDescriptionEditText = (EditText) findViewById(R.id.reportItemDescriptionEditText);

        imageUri = getUriToResource(this, R.drawable.default_image);
        reportItemImageView.setImageURI(imageUri);

        submitButtonImageView = (ImageView) findViewById(R.id.submitButtonImageView);
    }

    /**
     * If the necessary fields are not empty, then the data is then submitted and entered
     * into the databases.
     * @param view The submit button ImageView that submits and adds the item to the database
     */
    public void submitReport(View view) {
        final String NAME = reportItemNameEditText.getText().toString().replaceAll("\\s+","");
        final String DATE_LOST = reportItemDateLostEditText.getText().toString().replaceAll("\\s+","");
        final String LAST_LOCATION = reportItemLastLocationEditText.getText().toString().replaceAll("\\s+","");
        //final String DESCRIPTION = reportItemDescriptionEditText.getText().toString().replaceAll("\\s+","");

        if (NAME.equals("") || DATE_LOST.equals("") || LAST_LOCATION.equals(""))
            Toast.makeText(this, "All fields except item image and description are mandatory.",
                    Toast.LENGTH_SHORT).show();
        else {
            final String NEW_ITEM_NAME = reportItemNameEditText.getText().toString().trim();
            final String NEW_ITEM_DATE_LOST = reportItemDateLostEditText.getText().toString().trim();
            final String NEW_ITEM_LAST_LOCATION = reportItemLastLocationEditText.getText().toString().trim();
            final String NEW_ITEM_DESCRIPTION = reportItemDescriptionEditText.getText().toString().trim();
        }
    }

    /**
     *
     * @param requestCode A predefined code to compare to the result code
     * @param resultCode The actual response from selecting an image, if it is 13 then it is allowed
     * @param data The selected image uri to set the item's image to
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            reportItemImageView.setImageURI(imageUri);
        }
    }

    /**
     * Asks the user for camera and writing/reading external storages
     * permissions. If the user allows both of these permissions, then
     * it opens up the gallery allowing the user to select an image of the item
     * that was lost.
     *
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
            ActivityCompat.requestPermissions(this, permList.toArray(perms), REQUEST_CODE);
        }

        if (cameraPermission == PackageManager.PERMISSION_GRANTED
                && writePermission == PackageManager.PERMISSION_GRANTED
                && readPermission == PackageManager.PERMISSION_GRANTED) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REQUEST_CODE);
        }
        else {
            if (cameraPermission != PackageManager.PERMISSION_GRANTED
                    && writePermission != PackageManager.PERMISSION_GRANTED
                    && readPermission != PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "OCC Lost and Found requires camera and " +
                        "external storage permissions.", Toast.LENGTH_SHORT).show();
            else if (cameraPermission != PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "OCC Lost and Found requires camera permissions.",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "OCC Lost and Found requires external storage permissions.",
                        Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Get uri to any resource type within an Android Studio project. Method is public static to
     * allow other classes to use it as a helper function
     * @param context The current context
     * @param resID The resource identifier for drawable
     * @return Uri to resource by given id
     * @throws Resources.NotFoundException If the given resource id does not exist.
     */
    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resID)
            throws Resources.NotFoundException {
        /** Return a Resources instance for your application's package */
        Resources res = context.getResources();
        /** return URI */
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resID)
                + '/' + res.getResourceTypeName(resID)
                + '/' + res.getResourceEntryName(resID));
    }
}
