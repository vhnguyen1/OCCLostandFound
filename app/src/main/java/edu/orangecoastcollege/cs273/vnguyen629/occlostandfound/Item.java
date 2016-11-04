package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.net.Uri;

/**
 * The <code>Item</code> class maintains information about a lost item,
 * including its id number, name, description, recorded date lost,
 * last location known location, current status, and image. It also keeps
 * track of how many recorded lost items are in the database.
 *
 * @author Vincent Nguyen
 */
public class Item {
    private int mID;
    private String mName;
    private String mDescription;
    private String mDateLost;
    private String mLastLocation;
    private boolean mFound;
    private Uri mItemImage;

    // Number of lost items
    public static int numLostItems = 0;

    /**
     * Creates a new <code>Item</code> from its ID, description, date lost,
     * last known location, status, and image.
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
        numLostItems++;
    }

    /**
     * Creates a default <code>Item</code> with an id of -1, empty name,
     * empty description, empty date in which it was lost, empty last known location,
     * status and image set to a default value
     */
    public Item() {
        this(-1, "", "", "", "", false, null);
    }

    /**
     * Creates a new <code>Item</code> from user input, ID is irrelevant
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
        numLostItems++;
    }

    /**
     * Creates a new <code>Item</code> object, copying the information from
     * each part of the parameter item into the calling item
     * @param other <code>Item</code> to be copied
     */
    public Item(Item other) {
        this(-1, other.mName, other.mDescription, other.mDateLost,
                other.mLastLocation, other.mFound, other.mItemImage);
        numLostItems++;
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
     * Gets the name of the <code>Pet</code>.
     * @return The description of the missing item
     */
    public String getDescription() {
        return this.mDescription;
    }

    /**
     * Gets the name of the <code>Pet</code>.
     * @return The date lost of the missing item
     */
    public String getDateLost() {
        return this.mDateLost;
    }

    /**
     * Gets the name of the <code>Pet</code>.
     * @return The last known location of the missing item
     */
    public String getLastLocation() {
        return this.mLastLocation;
    }

    /**
     * Gets the name of the <code>Pet</code>.
     * @return The current status of the missing item
     */
    public boolean getStatus() {
        return this.mFound;
    }

    /**
     * Gets the name of the <code>Pet</code>.
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
        if (this.mFound)
            numLostItems--;
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
                "Id=" + mID +
                ", Name='" + mName + '\'' +
                ", Description='" + mDescription + '\'' +
                ", Date=" + mDateLost +
                ", Location=" + mLastLocation +
                "," + ((mFound)? "Found" : "Missing") +
                '}';
    }
}