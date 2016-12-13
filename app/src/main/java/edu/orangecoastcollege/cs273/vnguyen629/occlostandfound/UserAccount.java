package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * UserAccount info class
 * Creates a <code>UserAccount</code> account with their Student info
 *
 * @author Vu Nguyen
 */
public class UserAccount implements Parcelable {
    // User must log in each time they open the app, but this may change
    // if the user successfully logs in
    public static boolean isLoggedIn = false;
    public static String singedInUserAccountName = "";

    String mStudentUserName;
    String mStudentPassword;
    String mStudentPhoneNum;
    String mStudentEmail;
    String mStudentID;
    Uri    mStudentProfilePic;
    boolean mIsAdmin;
    private boolean mAllowShake;
    private boolean mAllowSms;
    String mFeedBack;

    /**
     * Parcelable constructor that creates an <code>UserAccount</code> object from a parcel
     * object
     * @param source The parcel object to obtain data from
     */
    public UserAccount(Parcel source) {
        this.mStudentUserName   = source.readString();
        this.mStudentPassword   = source.readString();
        this.mStudentPhoneNum   = source.readString();
        this.mStudentEmail      = source.readString();
        this.mStudentID         = source.readString();
        this.mStudentProfilePic = Uri.parse(source.readString());
        this.mIsAdmin           = ((source.readInt()== 1)? true : false);
        this.mAllowShake        = ((source.readInt()== 1)? true : false);
        this.mAllowSms          = ((source.readInt()== 1)? true : false);
        this.mFeedBack          = source.readString();
    }

    /**
     * Constructor for UserAccount. Set anything to it new value and some to it defalut value
     * @param mStudentUserName
     * @param mStudentPassword
     * @param mStudentPhoneNum
     * @param mStudentEmail
     * @param mStudentID
     */
    public UserAccount(String mStudentUserName, String mStudentPassword, String mStudentPhoneNum,
                       String mStudentEmail, String mStudentID) {
        this.mStudentUserName = mStudentUserName;
        this.mStudentPassword = mStudentPassword;
        this.mStudentPhoneNum = mStudentPhoneNum;
        this.mStudentEmail = mStudentEmail;
        this.mStudentID = mStudentID;
        this.mStudentProfilePic = Uri.parse("android.resource://edu.orangecoastcollege.cs273.vnguyen629.occlostandfound/drawable/image_name");;
        this.mIsAdmin = false;
        this.mAllowShake = false;
        this.mAllowSms = false;
        this.mFeedBack = "";
    }

    /**
     * Constructor for UserAccount. Set anything to it new value
     * @param mStudentUserName The new student name
     * @param mStudentPassword The new student password
     * @param mStudentPhoneNum The new student phone number
     * @param mStudentEmail The new student email address
     * @param mStudentID The new student ID
     */
    public UserAccount(String mStudentUserName, String mStudentPassword, String mStudentPhoneNum,
                       String mStudentEmail, String mStudentID, boolean isAdmin) {
        this.mStudentUserName = mStudentUserName;
        this.mStudentPassword = mStudentPassword;
        this.mStudentPhoneNum = mStudentPhoneNum;
        this.mStudentEmail = mStudentEmail;
        this.mStudentID = mStudentID;
        this.mStudentProfilePic = Uri.parse("android.resource://edu.orangecoastcollege.cs273.vnguyen629.occlostandfound/drawable/image_name");;
        this.mIsAdmin = isAdmin;
        this.mAllowShake = false;
        this.mAllowSms = false;
        this.mFeedBack = "";
    }

    /**
     * Constructor for UserAccount. Set anything to it new value
     * @param mStudentUserName The new student name
     * @param mStudentPassword The new student password
     * @param mStudentPhoneNum The new student phone number
     * @param mStudentEmail The new student email address
     * @param mStudentID The new student ID
     * @param mStudentProfilePic The new student image URI
     */
    public UserAccount(String mStudentUserName, String mStudentPassword, String mStudentPhoneNum,
                       String mStudentEmail, String mStudentID, Uri mStudentProfilePic, boolean isAdmin, boolean allowShake, boolean allowSms, String feedback) {
        this.mStudentUserName = mStudentUserName;
        this.mStudentPassword = mStudentPassword;
        this.mStudentPhoneNum = mStudentPhoneNum;
        this.mStudentEmail = mStudentEmail;
        this.mStudentID = mStudentID;
        this.mStudentProfilePic = mStudentProfilePic;
        this.mIsAdmin = isAdmin;
        this.mAllowShake = allowShake;
        this.mAllowSms = allowSms;
        this.mFeedBack = feedback;
    }

    /**
     * Default constructor
     */
    public UserAccount() {
        this("", "", "", "", "",null, false, false, false, "");
    }

    /**
     * getStudentUserName function
     * @return mStudentUserName
     */
    public String getStudentUserName() {
        return mStudentUserName;
    }

    /**
     * getStudentPassword function
     * @return mStudentPassword
     */
    public String getStudentPassword() {
        return mStudentPassword;
    }

    /**
     * getStudentPhoneNum function
     * @return mStudentPhoneNum
     */
    public String getStudentPhoneNum() {
        return mStudentPhoneNum;
    }

    /**
     * getStudentEmail  function
     * @return mStudentEmail
     */
    public String getStudentEmail() {
        return mStudentEmail;
    }

    /**
     * Get Student ID Class
     * @return mStudentID
     */
    public String getStudentID() {
        return mStudentID;
    }

    /**
     * getStudentProfilePic function
     * @return mStudentProfilePic
     */
    public Uri getStudentProfilePic() {
        return mStudentProfilePic;
    }

    /**
     * setStudentUserName function
     * @param mStudentUserName
     */
    public void setStudentUserName(String mStudentUserName) {
        this.mStudentUserName = mStudentUserName;
    }

    /**
     *  get Admin
     * @return mIsAdmin
     */
    public boolean getIsAdmin() {
        return mIsAdmin;
    }

    /**
     * get Shake
     * @return mAllowShake
     */
    public boolean getAllowShake() {
        return mAllowShake;
    }

    /**
     *get SMS
     * @return mAllowSms
     */
    public boolean getAllowSms() {
        return mAllowSms;
    }

    /**
     * get Feedback
     * @return mFeedBack
     */
    public String getFeedBack() {
        return mFeedBack;
    }

    /**
     * setStudentPassword function
     * @param mStudentPassword The new password
     */
    public void setStudentPassword(String mStudentPassword) {
        this.mStudentPassword = mStudentPassword;
    }

    /**
     * setStudentPhoneNum function
     * @param mStudentPhoneNum The new phone number
     */
    public void setStudentPhoneNum(String mStudentPhoneNum) {
        this.mStudentPhoneNum = mStudentPhoneNum;
    }

    /**
     * setStudentEmail function
     * @param mStudentEmail The new email address
     */
    public void setStudentEmail(String mStudentEmail) {
        this.mStudentEmail = mStudentEmail;
    }

    /**
     * setStudentID function
     * @param mStudentID The new studnet ID
     */
    public void setStudentID(String mStudentID) {
        this.mStudentID = mStudentID;
    }

    /**
     * setStudentProfilePic function
     * @param mStudentProfilePic The new image URI
     */
    public void setStudentProfilePic(Uri mStudentProfilePic) {
        this.mStudentProfilePic = mStudentProfilePic;
    }

    /**
     * set admin
     * @param mIsAdmin New rights
     */
    public void setmIsAdmin(boolean mIsAdmin) {
        this.mIsAdmin = mIsAdmin;
    }

    /**
     * to shake or not to shake
     * @param mAllowShake New shake settings
     */
    public void setmAllowShake(boolean mAllowShake) {
        this.mAllowShake = mAllowShake;
    }

    /**
     *  set if allow sms
     * @param mAllowSms New SMS settings
     */
    public void setmAllowSms(boolean mAllowSms) {
        this.mAllowSms = mAllowSms;
    }

    public void setFeedBack(String feedBack) {
        this.mFeedBack = feedBack;
    }

    /**
     * toString function
     * @return string value of object
     */
    public String toString() {
        return "UserAccount{" +
                ", UserName='" + this.mStudentUserName + '\'' +
                ", Password='" + this.mStudentPassword + '\'' +
                ", PhoneNumber='" + this.mStudentPhoneNum + '\'' +
                ", Email=" + this.mStudentEmail +
                ", StudentID=" + this.mStudentID +
                '}';
    }

    /**
     * writeToParcel function
     * @param dest Parcelable object
     * @param flags Unused
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mStudentUserName);
        dest.writeString(mStudentPassword);
        dest.writeString(mStudentPhoneNum);
        dest.writeString(mStudentEmail);
        dest.writeString(mStudentID);
        dest.writeString(mStudentProfilePic.toString());
        dest.writeInt((mIsAdmin)? 1 : 0);
        dest.writeInt((mAllowShake)? 1 : 0);
        dest.writeInt((mAllowSms)? 1 : 0);
        dest.writeString(mFeedBack);
    }

    /**
     * describeContents
     * @return nothing
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Creates a parcelable UserAccounts
     */
    public static final Parcelable.Creator<UserAccount> CREATOR = new Parcelable.Creator<UserAccount>() {
        /**
         * Creates a parcelable object
         * @param source Parcelable object
         * @return
         */
        @Override
        public UserAccount createFromParcel(Parcel source) {
            return new UserAccount(source);
        }

        /**
         * Creates an array of UserAccounts
         * @param size size of the UserAccounts Array
         * @return Array of UserAccounts
         */
        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };
}