package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 *  UserListActicity
 *  Get UserAccount and put it into a list
 */
public class AdminUserListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<UserAccount> allUserList;
    private List<UserAccount> filteredUserList;
    private UserListAdapter userListAdapter;

    private ListView userListView;
    private EditText searchUsernameEditText;

    private UserAccount loggedInAccount;

    /**
     * Create and init varible
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        db = new DBHelper(this);
        loggedInAccount = getIntent().getExtras().getParcelable("Account");

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
         *  Check on Text Change for search function
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

    public void adminViewUser(View view) {
        if (view instanceof LinearLayout) {
            UserAccount selectedUser = (UserAccount) view.getTag();
            UserAccount account = getIntent().getExtras().getParcelable("Account");

            Intent intent = new Intent(this, AdminViewUserActivity.class);
            intent.putExtra("SelectedUser", selectedUser);
            intent.putExtra("Account", account);
            startActivity(intent);
        }
    }
}
