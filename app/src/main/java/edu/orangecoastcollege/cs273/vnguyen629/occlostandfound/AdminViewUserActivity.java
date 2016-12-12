package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AdminViewUserActivity extends AppCompatActivity {

    private TextView adminViewUsernameTextView;
    private TextView adminViewStudentIDTextView;
    private TextView adminViewEmailTextView;
    private TextView adminViewPhoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);

        final UserAccount user = getIntent().getExtras().getParcelable("SelectedUser");

        adminViewUsernameTextView = (TextView) findViewById(R.id.adminViewUsernameTextView);
        adminViewStudentIDTextView = (TextView) findViewById(R.id.adminViewStudentIDTextView);
        adminViewEmailTextView = (TextView) findViewById(R.id.adminViewEmailTextView);
        adminViewPhoneTextView = (TextView) findViewById(R.id.adminViewPhoneTextView);

        adminViewPhoneTextView.setText(user.getStudentUserName());
        adminViewStudentIDTextView.setText(user.getStudentID());
        adminViewEmailTextView.setText(user.getStudentEmail());
        adminViewPhoneTextView.setText(user.getStudentPhoneNum());
    }

    public void sendSMS(View view) {
        Intent smsIntent = new Intent(AdminViewUserActivity.this, SendSMSActivity.class);
        smsIntent.putExtra("UserPhoneNumber", adminViewPhoneTextView.getText().toString());
        startActivity(smsIntent);
    }

    public void viewFeedbacks(View view) {
        final UserAccount user = getIntent().getExtras().getParcelable("SelectedUser");
        Intent viewFeedbackIntent = new Intent(this, ViewUserFeedbackActivity.class);
        viewFeedbackIntent.putExtra("UserFeedback", user);
        startActivity(viewFeedbackIntent);
    }
}
