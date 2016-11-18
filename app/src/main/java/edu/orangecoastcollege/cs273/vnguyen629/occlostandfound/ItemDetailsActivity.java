package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 */
public class ItemDetailsActivity extends AppCompatActivity {

    private TextView itemDetailsNameTextView;
    private TextView itemDetailsDateLostTextView;
    private TextView itemDetailsLocationTextView;
    private TextView itemDetailsDescriptionTextView;
    private ImageView itemDetailsImageView;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        itemDetailsNameTextView = (TextView) findViewById(R.id.itemDetailsNameTextView);
        itemDetailsDateLostTextView = (TextView) findViewById(R.id.itemDetailsDateLostTextView);
        itemDetailsLocationTextView = (TextView) findViewById(R.id.itemDetailsLocationTextView);
        itemDetailsDescriptionTextView = (TextView) findViewById(R.id.itemDetailsDescriptionTextView);
        itemDetailsImageView = (ImageView) findViewById(R.id.itemDetailsImageView);

        Item item = getIntent().getExtras().getParcelable("Selected");

        itemDetailsNameTextView.setText(item.getName());
        itemDetailsDescriptionTextView.setText(item.getDescription());
        itemDetailsDateLostTextView.setText(item.getDateLost());
        itemDetailsLocationTextView.setText(item.getLastLocation());
        itemDetailsImageView.setImageURI(item.getImageUri());

        //this.finish();
    }
}