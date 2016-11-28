package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Provides the necessary functions and tools to put information
 * regarding reported missing items inside an SQLite database while
 * providing various different functions such as adding, upgrading
 * and modifications.
 *
 * @author Vincent Nguyen
 * @version 1.0
 */
class DBHelper extends SQLiteOpenHelper {
    private Context mContext;
    static final String DATABASE_NAME = "LostAndFound";
    private static final int DATABASE_VERSION = 1;

    // Item Database Start
    private static final String ITEMS_TABLE = "LostItems";
    private static final String ITEM_KEY_FIELD_ID = "id";
    private static final String FIELD_ITEM_NAME = "name";
    private static final String FIELD_ITEM_DESCRIPTION = "description";
    private static final String FIELD_ITEM_DATE_LOST = "date_lost";
    private static final String FIELD_ITEM_LAST_LOCATION = "last_location";
    private static final String FIELD_ITEM_STATUS = "last_location";
    private static final String FIELD_ITEM_IMAGE_URI = "image_uri";
    // Item Database End

    // Account Table Start
    private static final String ACCOUNT_TABLE = "Accounts";
    private static final String FIELD_ACCOUNT_USERNAME = "name";
    private static final String FIELD_ACCOUNT_PASSWORD = "password";
    private static final String FIELD_ACCOUNT_PHONE_NUMBER = "phone_number";
    private static final String FIELD_ACCOUNT_EMAIL = "email";
    private static final String FIELD_ACCOUNT_STUDENT_ID = "student_id";
    private static final String FIELD_ACCOUNT_PROFILE_PICTURE = "profile_pic";
    // Account Table End

    // Report Table Start
    private static final String REPORT_TABLE  = "Reports";


    // Report Table End

    /**
     * Creates a new database
     * @param context
     */
    public DBHelper (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    /**
     * Creates a new <code>Item</code> database
     * @param db The <code>Item</code> database
     */
    @Override
    public void onCreate (SQLiteDatabase db){
        String table = "CREATE TABLE " + ITEMS_TABLE + "("
                + ITEM_KEY_FIELD_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_ITEM_NAME + " TEXT, "
                + FIELD_ITEM_DESCRIPTION + " TEXT, "
                + FIELD_ITEM_DATE_LOST + " TEXT, "
                + FIELD_ITEM_LAST_LOCATION + " TEXT, "
                + FIELD_ITEM_STATUS + " INTEGER, "
                + FIELD_ITEM_IMAGE_URI + " TEXT" + ")";
        db.execSQL(table);


        table =  "CREATE TABLE " + ACCOUNT_TABLE + "("
                + FIELD_ACCOUNT_USERNAME + " TEXT, "
                + FIELD_ACCOUNT_PASSWORD + " TEXT, "
                + FIELD_ACCOUNT_PHONE_NUMBER + " TEXT, "
                + FIELD_ACCOUNT_EMAIL + " TEXT, "
                + FIELD_ACCOUNT_STUDENT_ID + " INTEGER, "
                + FIELD_ACCOUNT_PROFILE_PICTURE + " TEXT, "
                + ")";
        db.execSQL(table);

        table =  "CREATE TABLE " + REPORT_TABLE + "(";
        db.execSQL(table);
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
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);
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

        values.put(FIELD_ITEM_NAME, name);
        values.put(FIELD_ITEM_DESCRIPTION, description);
        values.put(FIELD_ITEM_LAST_LOCATION, lastLocation);
        values.put(FIELD_ITEM_DATE_LOST, dateLost);
        values.put(FIELD_ITEM_STATUS, status);
        values.put(FIELD_ITEM_IMAGE_URI, imageURI);

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
                new String[]{ITEM_KEY_FIELD_ID, FIELD_ITEM_NAME, FIELD_ITEM_DESCRIPTION,
                        FIELD_ITEM_DATE_LOST, FIELD_ITEM_LAST_LOCATION, FIELD_ITEM_STATUS,
                        FIELD_ITEM_IMAGE_URI},
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

                itemArrayList.add(new Item(itemID, name, description, dateLost, lastLocation,
                        status, imageUri));

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
                new String[]{ITEM_KEY_FIELD_ID, FIELD_ITEM_NAME, FIELD_ITEM_DESCRIPTION,
                        FIELD_ITEM_DATE_LOST, FIELD_ITEM_LAST_LOCATION, FIELD_ITEM_STATUS,
                        FIELD_ITEM_IMAGE_URI},
                ITEM_KEY_FIELD_ID + "=?",
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

        final Item ITEM = new Item(itemID, name, description, dateLost, lastLocation,
                status, imageUri);

        db.close();
        return ITEM;
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

        values.put(FIELD_ITEM_NAME, name);
        values.put(FIELD_ITEM_DESCRIPTION, description);
        values.put(FIELD_ITEM_LAST_LOCATION, lastLocation);
        values.put(FIELD_ITEM_DATE_LOST, dateLost);
        values.put(FIELD_ITEM_STATUS, status);
        values.put(FIELD_ITEM_IMAGE_URI, imageURI);

        db.update(ITEMS_TABLE, values, ITEM_KEY_FIELD_ID + " = ?",
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

    /**
     * Imports/reads lost item data from a CSV file to populate the database with.
     * @param csvFileName Name of the file to import data from
     * @return Whether or not the file data was open and imported successfully
     */
    public boolean importItemFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException err) {
            err.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 4) {
                    Log.d("OCC Lost and Found", "Skipping Bad CSV Row: "
                            + Arrays.toString(fields));
                    continue;
                }

                int id = Integer.parseInt(fields[0].replaceAll("\\s+",""));
                String name = fields[1].trim();
                String description = fields[2].trim();
                String dateLost = fields[3].trim();
                String lastLocation = fields[4].trim();
                boolean status = ((fields[5].replaceAll("\\s+","") == "Found")? true : false);
                Uri itemImageURI = Uri.parse(fields[5].trim());

                addItem(new Item(id, name, description, dateLost, lastLocation,
                        status, itemImageURI));
            }
        } catch (IOException err) {
            err.printStackTrace();
            return false;
        }
        return true;
    }

    /************* User Account database functions *************/

    /**
     *
     * @param account
     */
    public void addAccount(UserAccount account)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_ACCOUNT_USERNAME, account.getStudentUserName());
        values.put(FIELD_ACCOUNT_PASSWORD, account.getStudentPassword());
        values.put(FIELD_ACCOUNT_PHONE_NUMBER, account.getStudentPhoneNum());
        values.put(FIELD_ACCOUNT_EMAIL, account.getStudentEmail());
        values.put(FIELD_ACCOUNT_STUDENT_ID, account.getStudentID());
        values.put(FIELD_ACCOUNT_PROFILE_PICTURE, account.getStudentProfilePic().toString());

        db.insert(ACCOUNT_TABLE, null, values);
        db.close();
    }

    /**
     *
     * @return
     */
    public ArrayList<UserAccount> getAllUserAccount()
    {
        ArrayList<UserAccount> accountList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                ACCOUNT_TABLE,
                new String[]{FIELD_ACCOUNT_USERNAME,FIELD_ACCOUNT_PASSWORD, FIELD_ACCOUNT_PHONE_NUMBER,
                           FIELD_ACCOUNT_EMAIL, FIELD_ACCOUNT_STUDENT_ID, FIELD_ACCOUNT_PROFILE_PICTURE},
                null,null,null,null,null,null
        );
        if (cursor.moveToFirst())
        {
            do {
                UserAccount account =
                        new UserAccount(cursor.getString(0), cursor.getString(1),
                                cursor.getString(2), cursor.getString(3),
                                cursor.getInt(4), Uri.parse(cursor.getString(5).toString()));
                accountList.add(account);

            }while (cursor.moveToNext());
        }
        return accountList;
    }

    /**
     *
     * @param account
     */
    public void deleteUserAccount(UserAccount account) {
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(ACCOUNT_TABLE, FIELD_ACCOUNT_STUDENT_ID + " = ?",
                new String[]{String.valueOf(account.getStudentID())});
        db.close();
    }

    /**
     *
     */
    public void deleteAllUserAccounts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ACCOUNT_TABLE, null, null);
        db.close();
    }

    /**
     *
     * @param account
     */
    public void updateAccount(UserAccount account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_ACCOUNT_USERNAME, account.getStudentUserName());
        values.put(FIELD_ACCOUNT_PASSWORD, account.getStudentPassword());
        values.put(FIELD_ACCOUNT_PHONE_NUMBER, account.getStudentPhoneNum());
        values.put(FIELD_ACCOUNT_EMAIL, account.getStudentEmail());
        values.put(FIELD_ACCOUNT_STUDENT_ID, account.getStudentID());
        values.put(FIELD_ACCOUNT_PROFILE_PICTURE, account.getStudentProfilePic().toString());

        db.update(ACCOUNT_TABLE, values, FIELD_ACCOUNT_STUDENT_ID + " = ?",
                new String[]{String.valueOf(account.getStudentID())});
        db.close();
    }

    /**
     *
     * @param id
     * @return
     */
    public UserAccount getUserAccount(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                ACCOUNT_TABLE,
                new String[]{FIELD_ACCOUNT_USERNAME,FIELD_ACCOUNT_PASSWORD, FIELD_ACCOUNT_PHONE_NUMBER,
                        FIELD_ACCOUNT_EMAIL, FIELD_ACCOUNT_STUDENT_ID, FIELD_ACCOUNT_PROFILE_PICTURE},
                FIELD_ACCOUNT_STUDENT_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        UserAccount account =
                new UserAccount(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getInt(4), Uri.parse(cursor.getString(5).toString()));

        db.close();
        return account;
    }

    /************* Report database functions *******************/


}