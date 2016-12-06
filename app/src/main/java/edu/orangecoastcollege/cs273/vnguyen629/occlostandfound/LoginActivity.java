package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 *
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameLoginEditText;
    private EditText passwordLoginEditText;

    private ImageView loginButton;
    private TextView createAccountTextView;

    private DBHelper database;
    private List<UserAccount> accountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (EditText) findViewById(R.id.usernameLoginEditText);
        createAccountTextView = (EditText) findViewById(R.id.passwordLoginEditText);

        usernameLoginEditText = (ImageView) findViewById(R.id.loginButtonImageView);
        passwordLoginEditText = (EditText) findViewById(R.id.createAccountTextView);

        accountList = database.getAllUserAccount();
    }

    /**
     * login button
     * @param view
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
    public void createAccount(View view) {
        Intent intent = new Intent(this, UserAccountCreateActivity.class);
        startActivity(intent);
    }
}