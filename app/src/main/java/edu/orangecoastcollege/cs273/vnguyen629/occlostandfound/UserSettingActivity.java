package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserSettingActivity extends AppCompatActivity {

    private AccontInfo accontInfo;
    private EditText accountInfoNameEditText;
    private EditText accountInfoLastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        accountInfoNameEditText = (EditText) findViewById(R.id.accountInfoNameEditTExt);
        accountInfoLastNameEditText = (EditText) findViewById(R.id.accountInfoLastNameEditText);

        accountInfoNameEditText.setText(accontInfo.getName());
        accountInfoLastNameEditText.setText(accontInfo.getLastName());
    }

    public void saveInfoButton(View view)
    {
        accontInfo.setName(accountInfoNameEditText.getText().toString());
        accontInfo.setLastName(accountInfoLastNameEditText.getText().toString());
        ///tiung
    }

}
