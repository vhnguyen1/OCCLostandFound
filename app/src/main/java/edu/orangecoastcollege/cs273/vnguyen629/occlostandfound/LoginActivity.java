package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 *
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameLoginEditText;
    private EditText passwordLoginEditText;

    private DBHelper database;
    private List<UserAccount> accountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLoginEditText = (EditText) findViewById(R.id.usernameLoginEditText);
        passwordLoginEditText= (EditText) findViewById(R.id.passwordLoginEditText);

        accountList = database.getAllUserAccount();
    }

    /**
     * login button
     * @param view
     */
    public void loginButton(View view)
    {
        boolean found = false;
        Intent intent = new Intent(this, ItemsListActivity.class);
        int pos = 0;

        // TODO: loop thought list to check if user exist
        for(UserAccount userAccount : accountList)
        {
            if(userAccount.getStudentUserName() == usernameLoginEditText.getText().toString()) {
                found = true;
                break;
            }
            else
                pos++;
        }


        if(found)
        {
            if(passwordLoginEditText.getText().toString() == accountList.get(pos).getStudentPassword())
                startActivity(intent);
            else
                Toast.makeText(this, "Username or password is invalid.", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Username or password is invalid.", Toast.LENGTH_SHORT).show();
    }


    /**
     * Create Account Button
     * @param view
     */
    public void createAccountButton(View view) {
        Intent intent = new Intent(this, UserAccountCreateActivity.class);
        startActivity(intent);
    }
}