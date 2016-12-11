package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

public class UserReportListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Item> itemList;
    private ItemListAdapter itemListAdapter;
    private ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report_list);

        db = new DBHelper(this);

        db.addItem(new Item("name", "des", "date", "loc", false, Uri.parse("android.resource://edu.orangecoastcollege.cs273.vnguyen629.occlostandfound/" + R.drawable.default_image), "user"));

        itemList = db.getAllItems();

        itemListView = (ListView) findViewById(R.id.userItemsListView);
        itemListAdapter = new ItemListAdapter(this, R.layout.list_item, itemList);
        itemListView.setAdapter(itemListAdapter);

    }
}
