package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SEND_SMS = 101;

    private DBHelper db;
    private List<UserAccount> allUserList;
    private List<UserAccount> filteredUserList;
    private UserListAdapter userListAdapter;

    private ListView userListView;
    private EditText searchUsernameEditText;

    /**
     *
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        db = new DBHelper(this);

        searchUsernameEditText = (EditText) findViewById(R.id.searchUsernameEditText);

        allUserList = db.getAllUserAccount();
        filteredUserList = new ArrayList<>(allUserList);

        userListView = (ListView) findViewById(R.id.userListView);
        userListAdapter = new UserListAdapter(this, R.layout.list_user, filteredUserList);
        userListView.setAdapter(userListAdapter);

        searchUsernameEditText.addTextChangedListener(usernameSearchTextWatcher);

    }

    /**
     *
     */
    public TextWatcher usernameSearchTextWatcher = new TextWatcher() {
        /**
         * Unused
         * @param s Unused
         * @param start Unused
         * @param count Unused
         * @param after Unused
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //Do nothing
        }

        /**
         *
         * @param s
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String input = s.toString().toLowerCase();
            userListAdapter.clear();

            if (input == "")
                for (UserAccount user : allUserList)
                    userListAdapter.add(user);
            else {
                String username;
                for (UserAccount user : allUserList) {
                    username = user.getStudentUserName();
                    if (username.toLowerCase().contains(input))
                        userListAdapter.add(user);
                }
            }
        }

        /**
         * Unused
         * @param s Unused
         */
        @Override
        public void afterTextChanged(Editable s) {
            //Do nothing
        }
    };

    /**
     *
     * @param view
     */
    public void selectUserSMS(View view) {
        String message = getIntent().getExtras().getString("MESSAGE");

        if (view instanceof LinearLayout) {
            final UserAccount user = (UserAccount) view.getTag();
            Log.i("OCC Lost and Found", user.toString());
            //Ask for permission to send text message
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_CODE_SEND_SMS);
            } else {
                //Define a reference to SmsManager (manages text messages)
                SmsManager smsManager = SmsManager.getDefault();

                smsManager.sendTextMessage(user.getStudentPhoneNum(), "ASOCC ", message, null, null);

                Toast.makeText(this, "Message sent to: " + user.getStudentUserName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
