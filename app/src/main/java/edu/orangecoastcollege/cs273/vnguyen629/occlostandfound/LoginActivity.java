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

    /**
     * Allows the user to login to their account if the information they
     * provided is appropriate.
     * @param view The login button ImageView
     */
    public void login(View view)
    {
        boolean found = false;
        //Intent intent = new Intent(this, MainMenuActivity.class);
        //Intent adminIntent = new Intent(this, AdminMainMenuActivity.class);
        shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_anim);
        int pos = 0;

        String username = usernameLoginEditText.getText().toString().trim();
        String password = passwordLoginEditText.getText().toString().trim();

        for (UserAccount account : accountList) {
            if (account.getStudentUserName().equals(username) && account.getStudentPassword().equals(password)) {
                UserAccount.isLoggedIn = true;
                UserAccount.singedInUserAccountName = account.getStudentUserName();

                if (account.getIsAdmin())
                    startActivity(new Intent(this, AdminMainMenuActivity.class).putExtra("Account", account));
                else
                    startActivity(new Intent(this, UserMenuActivity.class).putExtra("Account", account));
            }
        }

        loginButtonImageView.startAnimation(shakeAnim);
        Toast.makeText(this, getString(R.string.invalid_username_password_text),
                Toast.LENGTH_SHORT).show();

        /*for(UserAccount userAccount : accountList)
        {
            if(userAccount.getStudentUserName().equals(usernameLoginEditText.getText()
                    .toString().trim())) {
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
                if(accountList.get(pos).getIsAdmin())
                    startActivity(adminIntent);
                else
                    startActivity(intent);
            }
            else {
                loginButtonImageView.startAnimation(shakeAnim);
                Toast.makeText(this, getString(R.string.invalid_username_password_text),
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            loginButtonImageView.startAnimation(shakeAnim);
            Toast.makeText(this, getString(R.string.invalid_username_password_text),
                    Toast.LENGTH_SHORT).show();
        }*/
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