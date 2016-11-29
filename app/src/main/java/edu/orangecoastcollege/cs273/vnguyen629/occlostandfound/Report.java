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
    private String mAccount;
    private String mName;
    private String mDateLost;

    /**
     *
     * @param source
     */
    private Report(Parcel source) {
        this.mID = source.readInt();
        this.mAccount = source.readString();
        this.mName = source.readString();
        this.mDateLost = source.readString();
    }

    /**
     * Creates a new <code>Report</code> from its ID, related account, name of lost item,
     * and date lost.
     * @param newID The new reports id
     * @param newAccount The account associated with the user that filed the report
     * @param newName The name of the lost item
     * @param newDateLost The date in which the item was lost
     */
    public Report(int newID, String newAccount, String newName, String newDateLost) {
        this.mID = newID;
        this.mAccount = newAccount;
        this.mName = newName;
        this.mDateLost = newDateLost;
    }

    /**
     * Creates a new <code>Report</code> from user input, ID is irrelevant
     * and assigned by the database
     * @param newAccount The account associated with the user that filed the report
     * @param newName The name of the lost item
     * @param newDateLost The date in which the item was lost
     */
    public Report(String newAccount, String newName, String newDateLost) {
        this(-1, newAccount, newName, newDateLost);
    }

    /**
     * Creates a default <code>Report</code> with an id of -1, empty account,
     * empty name, empty date in which it was lost
     */
    public Report() {
        this(-1, "", "", "");
    }

    /**
     * Creates a new <code>Report</code> object, copying the information from
     * each part of the parameter item into the calling item
     * @param other <code>Report</code> to be copied
     */
    public Report(Report other) {
        this(-1, other.mAccount, other.mName, other.mDateLost);
    }

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
    public String getAccount() {
        return this.mAccount;
    }

    /**
     * Gets the name of the <code>Report</code>.
     * @return The name of the missing item
     */
    public String getName() {
        return this.mName;
    }



    /**
     * Gets the name of the <code>Report</code>.
     * @return The date lost of the missing item
     */
    public String getDateLost() {
        return this.mDateLost;
    }


    /**
     * Sets the associated account of the <code>Report</code>.
     * @param newAccount The reports new account
     */
    public void setAccount(String newAccount) {
        this.mAccount = newAccount;
    }

    /**
     * Sets the name of the item in the <code>Report</code>.
     * @param newName The items new name
     */
    public void setName(String newName) {
        this.mName = newName;
    }


    /**
     * Sets the date lost of the <code>Report</code>.
     * @param newDateLost The reports new date lost
     */
    public void setDateLost(String newDateLost) {
        this.mDateLost = newDateLost;
    }

    /**
     * Creates a String representation of a given <code>Report</code>,
     * with all member variables displayed.
     * @return The string representation of the object
     */
    public String toString() {
        return "Item{" +
                "Id=" + this.mID +
                ", Account='" + this.mAccount + '\'' +
                ", ItemName='" + this.mName + '\'' +
                ", Date=" + this.mDateLost +
                '}';
    }

    /**
     *
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mID);
        parcel.writeString(this.mAccount);
        parcel.writeString(this.mName);
        parcel.writeString(this.mDateLost);
    }

    /**
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     */
    public static final Creator<Report> CREATOR = new Creator<Report>() {
        /**
         *
         * @param source
         * @return
         */
        @Override
        public Report createFromParcel(Parcel source) {
            return new Report(source);
        }

        /**
         *
         * @param size
         * @return
         */
        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };
}