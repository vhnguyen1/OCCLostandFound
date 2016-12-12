package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewUserFeedbackActivity extends AppCompatActivity {

    private TextView userFeedbackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_feedback);

        userFeedbackTextView = (TextView) findViewById(R.id.userFeedbackTextView);

        final UserAccount user = getIntent().getExtras().getParcelable("UserFeedback");

        userFeedbackTextView.setText(user.getFeedBack());
    }
}
