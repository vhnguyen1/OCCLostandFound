package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Allows the user to sign into their account (whether it be an administrator or
 * a regular account). If they have no current account, they may click on the TextView
 * to go to the page that allows them to.
 *
 * @author Vu Nguyen
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameLoginEditText;
    private EditText passwordLoginEditText;
    private ImageView loginButtonImageView;

    private DBHelper database;
    private List<UserAccount> accountList;
    private Animation shakeAnim;

    private boolean userFound = false;

    /**
     * Loads up the databases and links up the view widgets.
     * @param savedInstanceState The state of the application saved into a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLoginEditText = (EditText) findViewById(R.id.usernameLoginEditText);
        passwordLoginEditText = (EditText) findViewById(R.id.passwordLoginEditText);
        loginButtonImageView = (ImageView) findViewById(R.id.loginButtonImageView);

        database = new DBHelper(this);
        accountList = database.getAllUserAccount();
    }

    @Override
    public void onRestart() {
        super.onResume();
        setContentView(R.layout.activity_login);

        recreate();
    }
    /**
     * Allows the user to login to their account if the information they
     * provided is appropriate.
     * @param view The login button ImageView
     */
    public void login(View view)
    {
        shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_anim);

        String username = usernameLoginEditText.getText().toString().trim();
        String password = passwordLoginEditText.getText().toString().trim();

        for (UserAccount account : accountList) {
            if (account.getStudentUserName().equals(username) && account.getStudentPassword().equals(password)) {
                Toast.makeText(this, getString(R.string.account_found_text),
                        Toast.LENGTH_SHORT).show();
                UserAccount.isLoggedIn = true;
                UserAccount.singedInUserAccountName = username;

                if (account.getIsAdmin()) {
                    startActivity(new Intent(this, AdminMainMenuActivity.class).putExtra("Account", account));
                    this.finish();
                }
                else {
                    startActivity(new Intent(this, UserMenuActivity.class).putExtra("Account", account));
                    this.finish();
                }

                userFound = true;
            }
        }

        if (!userFound) {
            loginButtonImageView.startAnimation(shakeAnim);
            Toast.makeText(this, getString(R.string.invalid_username_password_text),
                    Toast.LENGTH_SHORT).show();
        }
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