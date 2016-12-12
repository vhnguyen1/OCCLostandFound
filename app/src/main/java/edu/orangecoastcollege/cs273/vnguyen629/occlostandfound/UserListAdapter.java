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
 * Helper class to provide a custom adapter for the <code>UserAccount</code> list.
 *
 * @author Benjamin Nguyen
 */
public class UserListAdapter extends ArrayAdapter<UserAccount> {
    private int mResourceId;
    private Context mContext;
    private List<UserAccount> mUserList = new ArrayList<>();

    private ImageView userListImageView;
    private TextView userListNameTextView;
    private TextView userListPhoneTextView;
    private LinearLayout userListLinearLayout;

    /**
     * Creates a new <code>UserListAdapter</code> given a mContext, resource id
     * and list of lost items.
     * @param c The mContext for which the adapter is being used (typically an activity).
     * @param rId The resource id (typically the layout file name).
     * @param userList The list of items to display.
     */
    public UserListAdapter(Context c, int rId, List<UserAccount> userList) {
        super(c, rId, userList);
        this.mContext = c;
        this.mResourceId = rId;
        this.mUserList = userList;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the <code>UserAccount</code> selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter.
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) this.mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(this.mResourceId, null);

        final UserAccount user = mUserList.get(pos);

        userListImageView = (ImageView) view.findViewById(R.id.userListImageView);
        userListNameTextView = (TextView) view.findViewById(R.id.userListNameTextView);
        userListPhoneTextView = (TextView) view.findViewById(R.id.userListPhoneTextView);
        userListLinearLayout = (LinearLayout) view.findViewById(R.id.userListLinearLayout);

        userListImageView.setImageURI(Uri.parse("android.resource://edu.orangecoastcollege.cs273.vnguyen629.occlostandfound/"
                + R.drawable.default_image));
        userListNameTextView.setText(user.getStudentUserName());
        userListPhoneTextView.setText(user.getStudentPhoneNum());

        userListLinearLayout.setTag(user);

        return view;
    }
}