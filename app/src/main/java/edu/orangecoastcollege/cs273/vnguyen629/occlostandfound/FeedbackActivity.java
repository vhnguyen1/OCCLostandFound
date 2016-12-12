package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Allows users to provide feedback for the application.
 *
 * @author Benjamin Nguyen
 */
public class FeedbackActivity extends AppCompatActivity {

    private EditText feedbackEditText;
    private ImageView feedbackSubmitImageView;

    /**
     * Links up the view widgets
     * @param savedInstanceState The state of the application saved into a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackEditText = (EditText) findViewById(R.id.feedbackEditText);
        feedbackSubmitImageView = (ImageView) findViewById(R.id.feedbackSubmitImageView);
    }

    public void submitFeedback(View view) {
        if (feedbackEditText.getText().toString().isEmpty())
            Toast.makeText(this, "Feedback cannot be empty!", Toast.LENGTH_SHORT).show();
        else
            feedbackEditText.setText("asdkfjasd");
    }
}
