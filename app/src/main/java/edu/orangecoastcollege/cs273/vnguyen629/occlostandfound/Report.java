package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The <code>Report</code> class maintains record of filed reports,
 * each entry includes an unique id number, account, name of lost item, and recorded date lost.
 *
 * @author Benjamin Nguyen
 */
public class Report implements Parcelable {
    private int mID;
    private UserAccount mAccount;
    private Item mItem;
    private int mSmsCheck;

    /**
     * Creates a new <code>Report</code> from its ID, related account, name of lost item,
     * and date lost.
     * @param newID The new reports id
     * @param newAccount The account associated with the user that filed the report
     * @param newItem The name of the lost item
     * @param smsCheck Check if user allow sms notification for this item
     */
    public Report(int newID, UserAccount newAccount, Item newItem, int smsCheck) {
        this.mID = newID;
        this.mAccount = newAccount;
        this.mItem = newItem;
        this.mSmsCheck = smsCheck;
    }

    /**
     * Creates a new <code>Report</code> from user input, ID is irrelevant
     * and assigned by the database
     * @param newAccount The account associated with the user that filed the report
     * @param newItem The name of the lost item
     * @param smsCheck The date in which the item was lost
     */
    public Report(UserAccount newAccount, Item newItem, int smsCheck) {
        this(-1, newAccount, newItem, smsCheck);
    }

    /**
     * Creates a default <code>Report</code> with an id of -1, empty account,
     * empty item, unchecked sms notification
     */
    public Report() {
        this(-1, new UserAccount(), new Item(), 0);
    }

    /**
     * Creates a new <code>Report</code> object, copying the information from
     * each part of the parameter item into the calling item
     * @param other <code>Report</code> to be copied
     */
    public Report(Report other) {
        this(-1, other.mAccount, other.mItem, other.mSmsCheck);
    }

    /**
     * Creates a parcelable <code>Report</code> object
     * @param in Parcelable object
     */
    protected Report(Parcel in) {
        mID = in.readInt();
        mAccount = in.readParcelable(UserAccount.class.getClassLoader());
        mItem = in.readParcelable(Item.class.getClassLoader());
        mSmsCheck = in.readInt();
    }

    /**
     * Writes the member variable data to a parcelable object
     * @param dest The parcelable object
     * @param flags Unused
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mID);
        dest.writeParcelable(mAccount, flags);
        dest.writeParcelable(mItem, flags);
        dest.writeInt(mSmsCheck);
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
     * Creates a parcelable <code>Report</code> object
     */
    public static final Creator<Report> CREATOR = new Creator<Report>() {
        /**
         * Creates a Parcelable <code>Report</code> object
         * @param in Parcelable object
         * @return A parcelable <code>Report</code> object
         */
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        /**
         * Returns an array of parcelable <code>Report</code> objects
         * @param size Size of the array
         * @return an array of <code>Report</code> objects
         */
        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    /**
     * Gets the unique id of the <code>Report</code>.
     * @return The unique id
     */
    public int getID() {
        return this.mID;
    }

    /**
     * Gets the name of the <code>Report</code>.
     * @return The account associated with the user that filed the report
     */
    public UserAccount getAccount() {
        return this.mAccount;
    }

    /**
     * Gets the name of the <code>Report</code>.
     * @return The name of the missing item
     */
    public Item getItem() {
        return this.mItem;
    }

    /**
     * Gets the name of the <code>Report</code>.
     * @return The date lost of the missing item
     */
    public int getSmsCheck() {
        return this.mSmsCheck;
    }

    /**
     * Sets the associated account of the <code>Report</code>.
     * @param newAccount The reports new account
     */
    public void setAccount(UserAccount newAccount) {
        this.mAccount = newAccount;
    }

    /**
     * Sets the name of the item in the <code>Report</code>.
     * @param newItem The items new name
     */
    public void setItem(Item newItem) {
        this.mItem = newItem;
    }

    /**
     * Sets the date lost of the <code>Report</code>.
     * @param smsCheck C
     */
    public void setSmsCheck(int smsCheck) {
        this.mSmsCheck = smsCheck;
    }

    /**
     * Creates a String representation of a given <code>Report</code>,
     * with all member variables displayed.
     * @return The string representation of the object
     */
    public String toString() {
        return "Item{" +
                "Id=" + this.mID +
                ", Account='" + this.mAccount.getStudentUserName() + '\'' +
                ", Item='" + this.mItem.getName() + '\'' +
                ", SmsCheck=" + ((this.mSmsCheck == 1)? "Allowed" : "Not Allowed") +
                '}';
    }
}