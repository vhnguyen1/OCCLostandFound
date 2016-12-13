package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Allows administrators to view all the various feedback information given
 * by users of the app
 *
 * @author Benjamin Nguyen
 */
public class ViewUserFeedbackActivity extends AppCompatActivity {

    private TextView userFeedbackTextView;

    /**
     * Create and initialize variables
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_feedback);

        //userFeedbackTextView = (TextView) findViewById(R.id.userFeedbackTextView);

        final UserAccount user = getIntent().getExtras().getParcelable("UserFeedback");

        //userFeedbackTextView.setText(user.getFeedBack());
    }
}