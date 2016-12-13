package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Allows the user to change their account password if needed
 *
 * @author Vu Nguyen
 */
public class PasswordChangeActivity extends AppCompatActivity {

    private EditText oldPasswordEditText;
    private EditText newPasswordEditText;
    private EditText reNewPasswordEditText;

    private DBHelper database;

    /**
     * Links the EditTexts together and also starts up the database.
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        oldPasswordEditText = (EditText) findViewById(R.id.oldPasswordEditText);
        newPasswordEditText = (EditText) findViewById(R.id.newPasswordEditText);
        reNewPasswordEditText = (EditText) findViewById(R.id.reNewPasswordEditText);

        database = new DBHelper(this);
    }

    /**
     * Saves the new password, overwriting the previous account password
     * @param view The save new password button
     */
    public void saveNewPassword(View view)
    {
        //UserAccount account = database.getUserAccount(singedInUserAccountName);
        UserAccount account = getIntent().getExtras().getParcelable("Account");

        if(account.getStudentPassword().equals(oldPasswordEditText.getText().toString()))
        {
            if(newPasswordEditText.getText().toString().equals(reNewPasswordEditText.getText().toString()))
            {
               account.setStudentPassword(newPasswordEditText.getText().toString());
                database.updateAccount(account);
                startActivity(new Intent(this, UserMenuActivity.class)
                        .putExtra("Account", account));
                this.finish();
            }
            else
                Toast.makeText(this, getString(R.string.passwords_do_not_match),
                        Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, getString(R.string.old_password_incorrect),
                    Toast.LENGTH_SHORT).show();
    }
}