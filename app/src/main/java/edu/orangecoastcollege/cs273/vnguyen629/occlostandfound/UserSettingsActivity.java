package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

import static edu.orangecoastcollege.cs273.vnguyen629.occlostandfound.UserAccount.singedInUserAccountName;

//TODO checkBoxOfShake, checkBoxforSMS, allow to phone number

/**
 *
 */
public class UserSettingsActivity extends AppCompatActivity {

    private AccountInfo accountInfo;
    private DBHelper database;
    private ArrayList<UserAccount> accountList;
    private UserAccount account;

    private EditText accountInfoNameEditText;
    private EditText accountInfoLastNameEditText;
    private EditText accountInfoEmailEditText;
    private EditText accountInfoPhoneNumberEditText;

    private CheckBox accountSettingShakeCheckBox;
    private CheckBox accountSettingSmsCheckBox;



    /**
     *
     * @param savedInstanceState The state of the application saved into a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);


        accountInfoNameEditText = (EditText) findViewById(R.id.accountInfoNameEditTExt);
        accountInfoLastNameEditText = (EditText) findViewById(R.id.accountInfoLastNameEditText);
        accountInfoEmailEditText = (EditText) findViewById(R.id.settingEmailEditText);
        accountInfoPhoneNumberEditText = (EditText)findViewById(R.id.settingPhoneNumberEditText);
        accountSettingShakeCheckBox = (CheckBox) findViewById(R.id.shakeCheckBox);
        accountSettingSmsCheckBox = (CheckBox) findViewById(R.id.smsCheckBox);

        accountInfoNameEditText.setText(accountInfo.getName());
        accountInfoLastNameEditText.setText(accountInfo.getLastName());
        accountInfoEmailEditText.setText(database.getUserAccount(singedInUserAccountName).getStudentEmail());
        accountInfoPhoneNumberEditText.setText(database.getUserAccount(singedInUserAccountName).getStudentPhoneNum());
    }

    /**
     *
     * @param view
     */
    public void saveInfoButton(View view)
    {
        UserAccount account;
        accountInfo.setName(accountInfoNameEditText.getText().toString());
        accountInfo.setLastName(accountInfoLastNameEditText.getText().toString());

        account = database.getUserAccount(singedInUserAccountName);
        account.setStudentPhoneNum(accountInfoPhoneNumberEditText.getText().toString());
        database.updateAccount(account);

        if(accountSettingShakeCheckBox.isChecked())
             accountInfo.setAllowShake(true);
        else
            accountInfo.setAllowShake(false);

        if(accountSettingSmsCheckBox.isChecked())
            accountInfo.setAllowSms(true);
        else
            accountInfo.setAllowSms(false);

    }

}
