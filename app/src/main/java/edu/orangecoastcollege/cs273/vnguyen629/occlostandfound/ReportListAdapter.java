package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.app.Activity;
import android.content.Context;
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
public class ReportListAdapter extends ArrayAdapter<Report> {
    private int mResourceId;
    private Context mContext;
    private List<Report> mReportList = new ArrayList<>();

    private LinearLayout reportListLinearLayout;
    private ImageView reportListImageView;
    private TextView reportUserNameTextView;
    private TextView reportItemNameTextView;

    /**
     * Creates a new <code>ItemListAdapter</code> given a mContext, resource id
     * and list of lost items.
     * @param c The mContext for which the adapter is being used (typically an activity).
     * @param rId The resource id (typically the layout file name).
     * @param reportList The list of items to display.
     */
    public ReportListAdapter(Context c, int rId, List<Report> reportList) {
        super(c, rId, reportList);
        this.mContext = c;
        this.mResourceId = rId;
        this.mReportList = reportList;
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

        final Report report = this.mReportList.get(pos);

        Item item = report.getItem();

        reportListLinearLayout = (LinearLayout) view.findViewById(R.id.reportListLinearLayout);
        reportListImageView = (ImageView) view.findViewById(R.id.reportImageView);
        reportUserNameTextView = (TextView) view.findViewById(R.id.reportUserNameTextView);
        reportItemNameTextView = (TextView) view.findViewById(R.id.reportItemNameTextView);

        reportListImageView.setImageURI(item.getImageUri());
        reportUserNameTextView.setText(report.getAccount().getStudentUserName());
        reportItemNameTextView.setText(item.getName());

        reportListLinearLayout.setTag(item);

        return view;
    }
}