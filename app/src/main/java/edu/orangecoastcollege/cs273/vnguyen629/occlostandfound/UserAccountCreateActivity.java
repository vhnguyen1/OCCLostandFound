package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UserAccountCreateActivity extends AppCompatActivity {

    private EditText userEditText;
    private EditText passwordEditText;
    private EditText reenterPasswordEditText;
    private EditText emailEditText;

    private DBHelper dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_create);



    }
}
