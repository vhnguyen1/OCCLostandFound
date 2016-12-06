package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 */
public class UserAccountCreateActivity extends AppCompatActivity {

    private EditText userEditText;
    private EditText passwordEditText;
    private EditText reenterPasswordEditText;
    private EditText emailEditText;
    private EditText idEditText;

    private TextView renterPasswordTextView;
    private TextView emailTextView;
    private DBHelper dataBase;
    private ArrayList<UserAccount> accounts;

    private boolean nameIsNotSame = false;

    /**
     *
     * @param savedInstanceState The state of the application saved into a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_create);

        userEditText = (EditText) findViewById(R.id.userEditText);
        userEditText.addTextChangedListener(userNameTextWather);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText) ;
        reenterPasswordEditText = (EditText) findViewById(R.id.reenterPasswordEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        idEditText = (EditText) findViewById(R.id.idEditText);
        renterPasswordTextView = (TextView) findViewById(R.id.renterPassEditText);
        emailTextView = (TextView) findViewById(R.id.emailTextView);

        accounts = dataBase.getAllUserAccount();



    }

    /**
     *
     * @param view
     */
    public void createAccount(View view)
    {
        boolean noError = true;
        String name = "";
        String password = "";
        String email = "";
        String id = "";
        while(!noError)
        {
            if(nameIsNotSame) {
                name = userEditText.getText().toString();
            }
            else
                noError = false;
            password = passwordEditText.getText().toString();
            if(password != reenterPasswordEditText.getText().toString())
            {
                noError = false;
                renterPasswordTextView.setText("Password must be the same");
                renterPasswordTextView.setTextColor(getResources().getColor(R.color.red));

            }
            if(emailEditText.getText().toString().contains("@student.cccd.edu"))
                email = emailEditText.getText().toString();
            else
            {
                noError = false;
                emailTextView.setText("Must be a student e-mail");
                emailTextView.setTextColor(getResources().getColor(R.color.red));
            }
            id = idEditText.getText().toString();

        }

        if(noError)
        {
            UserAccount newAccount = new UserAccount();
            newAccount.setStudentUserName(name);
            newAccount.setStudentPassword(password);
            newAccount.setStudentEmail(email);
            newAccount.setStudentID(id);
        }

    }


    /**
     *
     */
    public TextWatcher userNameTextWather = new TextWatcher() {
        /**
         * Unused
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        /**
         *
         * @param s
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            String input = s.toString().toLowerCase();
            for(UserAccount account : accounts)
            {
                if(account.getStudentUserName().toLowerCase().startsWith(input))
                {
                    nameIsNotSame = false;
                    userEditText.setTextColor(getResources().getColorStateList(R.color.red));
                }
                else
                {
                    nameIsNotSame = true;
                    userEditText.setTextColor(getResources().getColorStateList(R.color.green));
                }
            }


        }

        /**
         * Unused
         */
        @Override
        public void afterTextChanged(Editable s) {}
    };
}