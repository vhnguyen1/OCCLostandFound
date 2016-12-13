package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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

    UserAccount account;

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

        account  = getIntent().getExtras().getParcelable("Account");

        accountInfoEmailEditText = (EditText) findViewById(R.id.settingEmailEditText);
        accountInfoPhoneNumberEditText = (EditText)findViewById(R.id.settingPhoneNumberEditText);
        accountSettingShakeCheckBox = (CheckBox) findViewById(R.id.shakeCheckBox);
        accountSettingSmsCheckBox = (CheckBox) findViewById(R.id.smsCheckBox);

        accountSettingSmsCheckBox.setChecked(account.getAllowSms());
        accountSettingShakeCheckBox.setChecked(account.getAllowShake());
        accountInfoEmailEditText.setText(account.getStudentEmail());
        accountInfoPhoneNumberEditText.setText(account.getStudentPhoneNum());
    }

    /**
     * Saves the user input changes for their account.
     * @param view The save button.
     */
    public void saveInfo(View view)
    {
        account.setStudentEmail(accountInfoEmailEditText.getText().toString());
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
        Toast.makeText(this, getString(R.string.saved_successfully_text),
                Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this,
                UserMenuActivity.class).putExtra("Account", account));
        this.finish();
    }

    /**
     * Allows the user to change their password by opening up the
     * change password page.
     * @param view The change password button.
     */
    public void changePassword(View view)
    {
        Intent intent = new Intent(this, PasswordChangeActivity.class).putExtra("Account", account);
        startActivity(intent);
    }
}