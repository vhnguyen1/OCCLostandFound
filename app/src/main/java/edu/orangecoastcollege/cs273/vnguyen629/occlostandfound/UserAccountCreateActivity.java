package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        dataBase = new DBHelper(this);

        userEditText = (EditText) findViewById(R.id.userEditText);
        userEditText.addTextChangedListener(userNameTextWatcher);
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
        UserAccount newAccount = new UserAccount();
        boolean noError = true;
        String name = "";
        String password = "";
        String email = "";
        String id = "";
        String tempName = userEditText.getText().toString().replace("admin", "");


        for(UserAccount account : accounts)
        {
            if(account.getStudentUserName().toLowerCase().startsWith(tempName))
            {
                Toast.makeText(this, "Sorry Admin your user name has already been taken...", Toast.LENGTH_SHORT).show();
                nameIsNotSame = true;
            }
        }

        if(!noError)
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
                renterPasswordTextView.setText(getString(R.string.password_same_text));
                renterPasswordTextView.setTextColor(getResources().getColor(R.color.red));

            }
            if(emailEditText.getText().toString().endsWith(getString(R.string.student_cccd_edu)))
                email = emailEditText.getText().toString();
            else
            {
                noError = false;
                emailTextView.setText(getString(R.string.must_be_email_text));
                emailTextView.setTextColor(getResources().getColor(R.color.red));
            }
            id = idEditText.getText().toString();

        }

        if(noError)
        {
            if(name.startsWith("admin") && name.endsWith("admin"))
            {
                newAccount.setmIsAdim(true);
                name.replace("admin", "");
            }
            newAccount.setStudentUserName(name);
            newAccount.setStudentPassword(password);
            newAccount.setStudentEmail(email);
            newAccount.setStudentID(id);

            dataBase.addAccount(newAccount);
        }

    }


    /**
     *
     */
    public TextWatcher userNameTextWatcher = new TextWatcher() {
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