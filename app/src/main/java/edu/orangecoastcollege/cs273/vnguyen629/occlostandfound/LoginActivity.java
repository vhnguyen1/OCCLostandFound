package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameLoginEditText;
    private EditText passwordLoginEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        usernameLoginEditText = (EditText) findViewById(R.id.usernameLoginEditText);
        passwordLoginEditText= (EditText) findViewById(R.id.passwordLoginEditText);
    }

    /**
     *
     * @param view
     */
    public void loginButton(View view)
    {
        boolean found = false;
        Intent intent = new Intent(this, ItemsListActivity.class);

        // TODO: loop thought list to check if user exist


        if(found)
        {
            if(passwordLoginEditText.getText().toString() == "Password Place Holder")
                startActivity(intent);
            else
                Toast.makeText(this, "Username or password is invalid.", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Username or password is invalid.", Toast.LENGTH_SHORT).show();
    }
}