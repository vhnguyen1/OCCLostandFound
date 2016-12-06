package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * The <code>Item</code> class maintains information about a lost item,
 * including its id number, name, description, recorded date lost,
 * last location known location, current status, and image.
 *
 * @author Vincent Nguyen
 */
public class Item implements Parcelable {
    private int mID;
    private String mName;
    private String mDescription;
    private String mDateLost;
    private String mLastLocation;
    private boolean mFound;
    private Uri mItemImage;

    /**
     * Parcelable constructor that creates an <code>Item</code> object from a parcel/cursor
     * object
     * @param source The parcel/cursor object to obtain data from
     */
    private Item(Parcel source) {
        this.mID = source.readInt();
        this.mName = source.readString();
        this.mDescription = source.readString();
        this.mDateLost = source.readString();
        this.mLastLocation = source.readString();
        this.mItemImage = Uri.parse(source.readString());
    }

    /**
     * Overloaded constructor that creates a new <code>Item</code> from its ID, description,
     * date lost, last known location, status, and image.
     * @param newID The new items id
     * @param newName The new items name
     * @param newDescription The new items description
     * @param newDateLost The new items date in which it was lost
     * @param newLastLocation The new items last known location
     * @param status The new items current status
     * @param newImage The new items image
     */
    public Item(int newID, String newName, String newDescription, String newDateLost,
                String newLastLocation, boolean status, Uri newImage) {
        this.mID = newID;
        this.mName = newName;
        this.mDescription = newDescription;
        this.mDateLost = newDateLost;
        this.mLastLocation = newLastLocation;
        this.mFound = status;
        this.mItemImage = newImage;
    }

    /**
     * Overloaded constructor that creates a new <code>Item</code> from user input, ID is irrelevant
     * and assigned by the database
     * @param newName The new items name
     * @param newDescription The new items description
     * @param newDateLost The new items date in which it was lost
     * @param newLastLocation The new items last known location
     * @param status The new items current status
     * @param newImage The new items image
     */
    public Item(String newName, String newDescription, String newDateLost,
                String newLastLocation, boolean status, Uri newImage) {
        this(-1, newName, newDescription, newDateLost, newLastLocation,
                status, newImage);
    }

    /**
     * Overloaded constructor that creates a new <code>Item</code> from user input, ID is irrelevant
     * and assigned by the database. Used if there is no image uri given
     * @param newName The new items name
     * @param newDescription The new items description
     * @param newDateLost The new items date in which it was lost
     * @param newLastLocation The new items last known location
     * @param status The new items current status
     */
    public Item(String newName, String newDescription, String newDateLost,
                String newLastLocation, boolean status) {
        this(-1, newName, newDescription, newDateLost, newLastLocation,
                status, null);
    }

    /**
     * Default Constructor that creates a default <code>Item</code> with an id of -1, empty name,
     * empty description, empty date in which it was lost, empty last known location,
     * status and image set to a default value
     */
    public Item() {
        this(-1, "", "", "", "", false, null);
    }

    /**
     * Copy Constructor that creates a new <code>Item</code> object, copying the information from
     * each part of the parameter item into the calling item
     * @param other <code>Item</code> to be copied
     */
    public Item(Item other) {
        this(-1, other.mName, other.mDescription, other.mDateLost,
                other.mLastLocation, other.mFound, other.mItemImage);
    }

    /**
     * Gets the unique id of the <code>Item</code>.
     * @return The unique id
     */
    public int getID() {
        return this.mID;
    }

    /**
     * Gets the name of the <code>Item</code>.
     * @return The name of the missing item
     */
    public String getName() {
        return this.mName;
    }

    /**
     * Gets the name of the <code>Item</code>.
     * @return The description of the missing item
     */
    public String getDescription() {
        return this.mDescription;
    }

    /**
     * Gets the name of the <code>Item</code>.
     * @return The date lost of the missing item
     */
    public String getDateLost() {
        return this.mDateLost;
    }

    /**
     * Gets the last known location of the <code>Item</code>.
     * @return The last known location of the missing item
     */
    public String getLastLocation() {
        return this.mLastLocation;
    }

    /**
     * Gets the current status of the <code>Item</code>.
     * @return The current status of the missing item
     */
    public boolean getStatus() {
        return this.mFound;
    }

    /**
     * Gets the image URI of the <code>Item</code>.
     * @return The image URI of the missing item
     */
    public Uri getImageUri() {
        return this.mItemImage;
    }

    /**
     * Sets the name of the <code>Item</code>.
     * @param newName The items new name
     */
    public void setName(String newName) {
        this.mName = newName;
    }

    /**
     * Sets the name of the <code>Item</code>.
     * @param newDescription The items new description
     */
    public void setDescription(String newDescription) {
        this.mDescription = newDescription;
    }

    /**
     * Sets the name of the <code>Item</code>.
     * @param newDateLost The items new recorded date lost
     */
    public void setDateLost(String newDateLost) {
        this.mDateLost = newDateLost;
    }

    /**
     * Sets the name of the <code>Item</code>.
     * @param newLastLocation The items new last known location
     */
    public void setLastLocation(String newLastLocation) {
        this.mLastLocation = newLastLocation;
    }

    /**
     * Sets the name of the <code>Item</code>.
     * @param newStatus The items new status
     */
    public void setStatus(boolean newStatus) {
        this.mFound = newStatus;
    }

    /**
     * Sets the name of the <code>Item</code>.
     * @param newImageURI The item new image URI
     */
    public void setImageURI(Uri newImageURI) {
        this.mItemImage = newImageURI;
    }

    /**
     * Creates a String representation of a given <code>Item</code>,
     * with all member variables displayed.
     * @return The string representation of the object
     */
    public String toString() {
        return "Item{" +
                "Id=" + this.mID +
                ", Name='" + this.mName + '\'' +
                ", Description='" + this.mDescription + '\'' +
                ", Date=" + this.mDateLost +
                ", Location=" + this.mLastLocation +
                "," + ((this.mFound)? "Found" : "Missing") +
                '}';
    }

    /**
     * Writes an <code>Item</code> objects data into a parcel/cursor object
     * @param parcel The parcelable/cursor object to write the data to
     * @param i Unused
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mID);
        parcel.writeString(this.mName);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mDateLost);
        parcel.writeString(this.mLastLocation);
        parcel.writeString(this.mItemImage.toString());
    }

    /**
     * Returns 0
     * @return An integer of 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Creates parcelable <code>Item</code> objects
     */
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        /**
         * Uses the Parcelable constructor to create an <code>Item/code> object from a
         * parcel/cursor object
         * @param source The parcel/cursor object to obtain data from
         * @return A new <code>Item</code> object with data copied from the parcel/cursor object
         */
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        /**
         * Returns an array of <code>Item</code> objects with a given size
         * @param size The size of the <code>Item</code> array
         * @return
         */
        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}