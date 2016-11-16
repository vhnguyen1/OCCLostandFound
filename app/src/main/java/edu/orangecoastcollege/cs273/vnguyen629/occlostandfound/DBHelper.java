package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Provides the necessary functions and tools to put information
 * regarding reported missing items inside an SQLite database while
 * providing various different functions such as adding, upgrading
 * and modifications.
 *
 * @author Vincent Nguyen
 */
class DBHelper extends SQLiteOpenHelper {
    static final String ITEMS_NAME = "Items";
    private static final String ITEMS_TABLE = "LostItems";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_DATE_LOST = "date_lost";
    private static final String FIELD_LAST_LOCATION = "last_location";
    private static final String FIELD_STATUS = "last_location";
    private static final String FIELD_IMAGE_URI = "image_uri";

    /**
     * Creates a database
     * @param context
     */
    public DBHelper (Context context){
        super (context, ITEMS_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates a new <code>Item</code> database
     * @param db The <code>Item</code> database
     */
    @Override
    public void onCreate (SQLiteDatabase db){
        String petTable = "CREATE TABLE " + ITEMS_TABLE + "("
                + KEY_FIELD_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_NAME + " TEXT, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_DATE_LOST + " TEXT, "
                + FIELD_LAST_LOCATION + " TEXT, "
                + FIELD_STATUS + " INTEGER, "
                + FIELD_IMAGE_URI + " TEXT" + ")";
        db.execSQL(petTable);
    }

    /**
     * Upgrades the current <code>Item</code> database for newer versions
     * @param db The <code>Item</code> database
     * @param oldVersion The previous database version number
     * @param newVersion The new database version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        onCreate(db);
    }

    /************* Item database functions *************/

    /**
     * Adds a new <code>Item</code> into the current database
     * @param newItem New <code>Item</code> to be added too the database
     */
    public void addItem(Item newItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String name = newItem.getName();
        String description = newItem.getDescription();
        String dateLost = newItem.getDateLost();
        String lastLocation = newItem.getLastLocation();
        int status = ((newItem.getStatus())? 1 : 0);
        String imageURI = newItem.getImageUri().toString();

        values.put(FIELD_NAME, name);
        values.put(FIELD_DESCRIPTION, description);
        values.put(FIELD_LAST_LOCATION, lastLocation);
        values.put(FIELD_DATE_LOST, dateLost);
        values.put(FIELD_STATUS, status);
        values.put(FIELD_IMAGE_URI, imageURI);

        db.insert(ITEMS_TABLE, null, values);
        db.close();
    }

    /**
     * Returns a list of all the lost items inside the database
     * @return The list of all the lost items
     */
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> itemArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                ITEMS_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_NAME, FIELD_DESCRIPTION, FIELD_DATE_LOST,
                        FIELD_LAST_LOCATION, FIELD_STATUS, FIELD_IMAGE_URI},
                null, null, null, null, null, null );

        if (cursor.moveToFirst()){
            do {
                int itemID = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String dateLost = cursor.getString(3);
                String lastLocation = cursor.getString(4);
                boolean status = ((cursor.getInt(5) == 1)? true : false);
                Uri imageUri = Uri.parse(cursor.getString(6));

                Item item = new Item(itemID, name, description, dateLost, lastLocation,
                        status, imageUri);
                itemArrayList.add(item);

            } while (cursor.moveToNext());
        }

        db.close();
        return itemArrayList;
    }

    /**
     * Retrieves a specified <code>Item</code> from the database
     * @param id The unique id of the <code>Item</code>
     * @return The item with the matching unique ID
     */
    public Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                ITEMS_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_NAME, FIELD_DESCRIPTION, FIELD_DATE_LOST,
                        FIELD_LAST_LOCATION, FIELD_STATUS, FIELD_IMAGE_URI},
                KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null );

        if (cursor != null)
            cursor.moveToFirst();

        int itemID = cursor.getInt(0);
        String name = cursor.getString(1);
        String description = cursor.getString(2);
        String dateLost = cursor.getString(3);
        String lastLocation = cursor.getString(4);
        boolean status = ((cursor.getInt(5) == 1)? true : false);
        Uri imageUri = Uri.parse(cursor.getString(6));

        Item item = new Item(itemID, name, description, dateLost, lastLocation,
                status, imageUri);

        db.close();
        return item;
    }

    /**
     * Applies changes/updates to a <code>Item</code>
     * @param item <code>Item</code> to be updated in the database
     */
    public void updateItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String name = item.getName();
        String description = item.getDescription();
        String dateLost = item.getDateLost();
        String lastLocation = item.getLastLocation();
        int status = ((item.getStatus())? 1 : 0);
        String imageURI = item.getImageUri().toString();

        values.put(FIELD_NAME, name);
        values.put(FIELD_DESCRIPTION, description);
        values.put(FIELD_LAST_LOCATION, lastLocation);
        values.put(FIELD_DATE_LOST, dateLost);
        values.put(FIELD_STATUS, status);
        values.put(FIELD_IMAGE_URI, imageURI);

        db.update(ITEMS_TABLE, values, KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(item.getID())});
        db.close();
    }

    /**
     * Delete all the items in the current database
     */
    public void deleteAllItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEMS_TABLE, null, null);
        db.close();
    }
}