package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to provide a custom adapter for the lost <code>Item</code> list.
 *
 * @author Vincent Nguyen
 */
public class ItemListAdapter extends ArrayAdapter<Item> {
    private int mResourceId;
    private Context mContext;
    private List<Item> mLostItemsList = new ArrayList<>();

    private LinearLayout itemListLinearLayout;
    private ImageView itemListImageView;
    private TextView itemListNameTextView;
    private TextView itemListStatusTextView;

    /**
     * Creates a new <code>ItemListAdapter</code> given a mContext, resource id
     * and list of lost items.
     * @param c The mContext for which the adapter is being used (typically an activity).
     * @param rId The resource id (typically the layout file name).
     * @param lostItems The list of items to display.
     */
    public ItemListAdapter(Context c, int rId, List<Item> lostItems) {
        super(c, rId, lostItems);
        this.mContext = c;
        this.mResourceId = rId;
        this.mLostItemsList = lostItems;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the <code>Item</code> selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter.
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) this.mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(this.mResourceId, null);

        final Item ITEM = this.mLostItemsList.get(pos);
        itemListLinearLayout.setTag(ITEM);

        final Uri IMAGE_URI = ITEM.getImageUri();
        final String NAME = ITEM.getName();
        // go back and re-use the string in strings.xml
        final String STATUS = ((ITEM.getStatus())? mContext.getString(R.string.found_text)
                : mContext.getString(R.string.not_found_text));

        itemListLinearLayout = (LinearLayout) view.findViewById(R.id.itemListLinearLayout);
        itemListImageView = (ImageView) view.findViewById(R.id.itemListImageView);
        itemListNameTextView = (TextView) view.findViewById(R.id.itemListNameTextView);
        itemListStatusTextView = (TextView) view.findViewById(R.id.itemListStatusTextView);

        itemListImageView.setImageURI(IMAGE_URI);
        itemListNameTextView.setText(NAME);
        itemListStatusTextView.setText(STATUS);

        return view;
    }
}