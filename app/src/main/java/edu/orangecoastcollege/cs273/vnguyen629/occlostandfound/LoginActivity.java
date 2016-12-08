package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import static edu.orangecoastcollege.cs273.vnguyen629.occlostandfound.UserAccount.isLoggedIn;
import static edu.orangecoastcollege.cs273.vnguyen629.occlostandfound.UserAccount.singedInUserAccountName;

/**
 *
 * @author Vu Nguyen
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameLoginEditText;
    private EditText passwordLoginEditText;

    private DBHelper database;
    private List<UserAccount> accountList;

    /**
     *
     * @param savedInstanceState The state of the application saved into a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLoginEditText = (EditText) findViewById(R.id.usernameLoginEditText);
        passwordLoginEditText = (EditText) findViewById(R.id.passwordLoginEditText);

        accountList = database.getAllUserAccount();
    }

    /**
     * Allows the user to login to their account if the information they
     * provided is appropriate.
     * @param view The login button ImageView
     */
    public void login(View view)
    {
        boolean found = false;
        Intent intent = new Intent(this, ItemsListActivity.class);
        int pos = 0;

        // TODO: loop thought list to check if user exist
        for(UserAccount userAccount : accountList)
        {
            if(userAccount.getStudentUserName() == usernameLoginEditText.getText().toString().trim()) {
                found = true;
                break;
            }
            else
                pos++;
        }

        if(found)
        {
            if(passwordLoginEditText.getText().toString().equals(accountList.get(pos).getStudentPassword())) {
                isLoggedIn = true;
                singedInUserAccountName = usernameLoginEditText.getText().toString();
                startActivity(intent);
            }
            else
                Toast.makeText(this, R.string.invalid_username_password_text, Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, R.string.invalid_username_password_text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Loads up the UserAccountCreateActivity so the user may create a new account
     * @param view The clickable TextView that loads up the UserAccountCreateActivity
     */
    public void createAccount(View view) {
        Intent intent = new Intent(this, UserAccountCreateActivity.class);
        startActivity(intent);
    }
}