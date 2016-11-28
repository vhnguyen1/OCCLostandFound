package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Displays a list of reported lost items to the user, where they can
 * either click on the item to view the specific details related to the item
 * or click on the report lost item button to report a lost item.
 *
 * @author Vincent Nguyen
 */
public class ItemsListActivity extends AppCompatActivity {
    private DBHelper database;
    private List<Item> itemsList;
    private ItemListAdapter itemsListAdapter;
    private ListView itemsListView;

    private Button reportLostItemButton;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        this.deleteDatabase(DBHelper.DATABASE_NAME);
        database = new DBHelper(this);

        itemsList = database.getAllItems();
        itemsListAdapter = new ItemListAdapter(this, R.layout.list_item, itemsList);

        itemsListView = (ListView) findViewById(R.id.itemsListView);
        itemsListView.setAdapter(itemsListAdapter);
    }

    /**
     *
     * @param view
     */
    public void viewItemDetails(View view) {
        if (view instanceof LinearLayout) {
            final Item SELECTED_GAME = (Item) view.getTag();
            Log.i("OCC Lost and Found", SELECTED_GAME.toString());

            Intent detailsIntent = new Intent(this, ItemDetailsActivity.class);
            detailsIntent.putExtra("Selected", SELECTED_GAME);
            startActivity(detailsIntent);
        }
    }

    /**
     *
     * @param view
     */
    public void addItem(View view) {

    }

    /**
     *
     * @param view
     */
    public void clearAllItems(View view) {
        if (!itemsList.isEmpty()) {
            database.deleteAllItems();
            itemsList.clear();
            itemsListAdapter.notifyDataSetChanged();
        }
        else
            Toast.makeText(this, "List is already empty.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Get uri to any resource type within an Android Studio project. Method is public static to
     * allow other classes to use it as a helper function
     * @param context The current context
     * @param resID The resource identifier for drawable
     * @return Uri to resource by given id
     * @throws Resources.NotFoundException If the given resource id does not exist.
     */
    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resID)
            throws Resources.NotFoundException {
        /** Return a Resources instance for your application's package */
        Resources res = context.getResources();
        /** return URI */
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resID)
                + '/' + res.getResourceTypeName(resID)
                + '/' + res.getResourceEntryName(resID));
    }
}