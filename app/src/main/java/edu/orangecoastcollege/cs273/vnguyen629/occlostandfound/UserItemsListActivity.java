package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class UserItemsListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Item> itemList;
    private ItemListAdapter2 itemListAdapter;
    private ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report_list);

        db = new DBHelper(this);

        db.addItem(new Item("name", "des", "date", "loc", false, Uri.parse("android.resource://edu.orangecoastcollege.cs273.vnguyen629.occlostandfound/" + R.drawable.default_image), "user"));

        itemList = db.getAllItems();

        itemListView = (ListView) findViewById(R.id.userItemsListView);
        itemListAdapter = new ItemListAdapter2(this, R.layout.list_item_2, itemList);
        itemListView.setAdapter(itemListAdapter);

    }

    public void viewUserItemDetails(View view) {
        if (view instanceof LinearLayout) {
            final Item SELECTED_ITEM = (Item) view.getTag();
            Log.i("OCC Lost and Found", SELECTED_ITEM.toString());

            Intent detailsIntent = new Intent(this, UserItemDetailsActivity.class);
            detailsIntent.putExtra("Selected", SELECTED_ITEM);
            startActivity(detailsIntent);
        } else
            Toast.makeText(this, "Error selecting item.", Toast.LENGTH_SHORT).show();
    }
}
