package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AdminMainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_menu);
    }

    public void viewAllItems(View view) {
        startActivity(new Intent(AdminMainMenuActivity.this, AdminItemListActivity.class));
    }

    public void viewAllUsers(View view) {
        startActivity(new Intent(AdminMainMenuActivity.this, AdminViewUserActivity.class));
    }

}
