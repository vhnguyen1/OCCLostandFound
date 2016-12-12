package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static edu.orangecoastcollege.cs273.vnguyen629.occlostandfound.UserAccount.singedInUserAccountName;

/**
 *
 */
public class PasswordChangeActivity extends AppCompatActivity {

    private EditText oldPasswordEditText;
    private EditText newPasswordEditText;
    private EditText reNewPasswordEditText;

    private DBHelper database;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        oldPasswordEditText = (EditText) findViewById(R.id.oldPasswordEditText);
        newPasswordEditText = (EditText) findViewById(R.id.newPasswordEditText);
        reNewPasswordEditText = (EditText) findViewById(R.id.reNewPasswordEditText);
    }

    /**
     *
     * @param view
     */
    public void saveNewPassword(View view)
    {
        UserAccount account = database.getUserAccount(singedInUserAccountName);

        if(account.getStudentPassword() == oldPasswordEditText.getText().toString())
        {
            if(newPasswordEditText.getText().toString() == reNewPasswordEditText.getText().toString())
            {
               account.setStudentPassword(newPasswordEditText.getText().toString());
                database.updateAccount(account);
            }
            else
                Toast.makeText(this, "Your new password does not macth...", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Your old password is incorrect...", Toast.LENGTH_SHORT).show();
    }

}
