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
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.data;

/**
 *
 */
public class ReportItemActivity extends AppCompatActivity {
    private Uri imageUri;
    private ImageView reportItemImageView;

    private static final int REQUEST_CODE = 13;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_item);

        imageUri = getUriToResource(this, R.drawable.defaultImage);
        reportItemImageView.setImageURI(imageUri);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            reportItemImageView.setImageURI(imageUri);
        }
    }

    /**
     *
     * @param view
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
