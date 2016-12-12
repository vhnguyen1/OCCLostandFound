package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMSActivity extends AppCompatActivity {

    private EditText smsSenderEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        smsSenderEditText = (EditText) findViewById(R.id.smsSenderEditText);

    }

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
}
