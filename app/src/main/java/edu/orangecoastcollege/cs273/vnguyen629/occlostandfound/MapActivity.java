package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 *
 */
public class MapActivity extends AppCompatActivity {

    private ImageView mapImageView;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapImageView = (ImageView) findViewById(R.id.mapImageView);
    }

    /**
     *
     * @param view
     */
    public void mapChange(View view) {
        if (mapImageView.getDrawable() == null)
            mapImageView.setImageDrawable(getDrawable(R.drawable.occ_map_full));
         else
            mapImageView.setImageDrawable(null);

    }
}
