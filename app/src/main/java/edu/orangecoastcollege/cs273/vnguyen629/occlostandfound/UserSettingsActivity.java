package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import static edu.orangecoastcollege.cs273.vnguyen629.occlostandfound.UserAccount.singedInUserAccountName;

/**
 * Allows the user to change his/her settings and preferences.
 *
 * @author Vu Nguyen
 */
public class UserSettingsActivity extends AppCompatActivity {

    private DBHelper database;

    private EditText accountInfoEmailEditText;
    private EditText accountInfoPhoneNumberEditText;

    private CheckBox accountSettingShakeCheckBox;
    private CheckBox accountSettingSmsCheckBox;

    /**
     * Loads up the database while linking up and initializing the fields of the
     * user input EditTexts to the information associated with their account.
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        database = new DBHelper(this);

        accountInfoEmailEditText = (EditText) findViewById(R.id.settingEmailEditText);
        accountInfoPhoneNumberEditText = (EditText)findViewById(R.id.settingPhoneNumberEditText);
        accountSettingShakeCheckBox = (CheckBox) findViewById(R.id.shakeCheckBox);
        accountSettingSmsCheckBox = (CheckBox) findViewById(R.id.smsCheckBox);

        accountInfoEmailEditText.setText(database.getUserAccount(singedInUserAccountName).getStudentEmail());
        accountInfoPhoneNumberEditText.setText(database.getUserAccount(singedInUserAccountName).getStudentPhoneNum());
        //accountSettingShakeCheckBox.setChecked(database.getUserAccount(singedInUserAccountName).getAllowShake());
        //accountSettingSmsCheckBox.setChecked(database.getUserAccount(singedInUserAccountName).getAllowSms());
    }

    /**
     * Saves the user input changes for their account.
     * @param view The save button.
     */
    public void saveInfo(View view)
    {
        UserAccount account;

        account = database.getUserAccount(singedInUserAccountName);
        account.setStudentPhoneNum(accountInfoPhoneNumberEditText.getText().toString());

        if(accountSettingShakeCheckBox.isChecked())
            account.setmAllowShake(true);
        else
            account.setmAllowShake(false);

        if(accountSettingSmsCheckBox.isChecked())
            account.setmAllowSms(true);
        else
            account.setmAllowSms(false);

        database.updateAccount(account);
    }

    /**
     * Allpws the user to change their password by opening up the
     * change password page.
     * @param view The change password button.
     */
    public void changePassword(View view)
    {
        Intent intent = new Intent(this, PasswordChangeActivity.class);
        startActivity(intent);
    }
}