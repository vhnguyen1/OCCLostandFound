package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserSettingsActivity extends AppCompatActivity {

    private AccountInfo accountInfo;
    private EditText accountInfoNameEditText;
    private EditText accountInfoLastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        accountInfoNameEditText = (EditText) findViewById(R.id.accountInfoNameEditTExt);
        accountInfoLastNameEditText = (EditText) findViewById(R.id.accountInfoLastNameEditText);

        accountInfoNameEditText.setText(accountInfo.getName());
        accountInfoLastNameEditText.setText(accountInfo.getLastName());
    }

    public void saveInfoButton(View view)
    {
        accountInfo.setName(accountInfoNameEditText.getText().toString());
        accountInfo.setLastName(accountInfoLastNameEditText.getText().toString());
        ///tiung
    }

}
