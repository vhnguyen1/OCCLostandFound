package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Allows the user to send SMS messages
 *
 * @author Benjamin Nguyen
 */
public class SendSMSActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SEND_SMS = 101;

    private EditText smsSenderEditText;

    /**
     * Links up the EditText for the message
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        smsSenderEditText = (EditText) findViewById(R.id.smsSenderEditText);
    }

    /**
     * Opens up the UserList page for sending the intended message
     * @param view The send SMS button
     */
    public void sendSMS(View view) {
        String message = smsSenderEditText.getText().toString();
        if (message.isEmpty())
            Toast.makeText(this, "Message cannot be empty.", Toast.LENGTH_SHORT).show();
        else {
            Intent smsIntent = new Intent(SendSMSActivity.this, UserListActivity.class);
            smsIntent.putExtra("MESSAGE", message);
            startActivity(smsIntent);
        }
    }

    /**
     * Select User and send a SMS if item have been found
     * @param view
     */
    public void selectUserSMS(View view) {
        String message = getIntent().getExtras().getString("MESSAGE");

        if (view instanceof LinearLayout) {
            final UserAccount user = (UserAccount) view.getTag();
            Log.i("OCC Lost and Found", user.toString());
            //Ask for permission to send text message
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_CODE_SEND_SMS);
            } else {
                //Define a reference to SmsManager (manages text messages)
                SmsManager smsManager = SmsManager.getDefault();

                smsManager.sendTextMessage(user.getStudentPhoneNum(), "ASOCC ", message, null, null);

                Toast.makeText(this, "Message sent to: " + user.getStudentUserName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}