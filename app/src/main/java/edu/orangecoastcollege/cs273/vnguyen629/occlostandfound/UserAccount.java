package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Student Account info class
 * Create a student account with Student info
 *
 * @author Vu Nguyen
 */
public class UserAccount implements Parcelable {

    String mStudentUserName;
    String mStudentPassword;
    String mStudentPhoneNum;
    String mStudentEmail;
    int    mStudentID;
    Uri    mStudentProfilePic;

    /**
     *
     * @param source
     */
    public UserAccount(Parcel source) {
        this.mStudentUserName   = source.readString();
        this.mStudentPassword   = source.readString();
        this.mStudentPhoneNum   = source.readString();
        this.mStudentEmail      = source.readString();
        this.mStudentID         = source.readInt();
        this.mStudentProfilePic = Uri.parse(source.readString());
    }

    /**
     *
     * @param mStudentUserName
     * @param mStudentPassword
     * @param mStudentPhoneNum
     * @param mStudentEmail
     * @param mStudentID
     * @param mStudentProfilePic
     */
    public UserAccount(String mStudentUserName, String mStudentPassword, String mStudentPhoneNum, String mStudentEmail, int mStudentID, Uri mStudentProfilePic) {
        this.mStudentUserName = mStudentUserName;
        this.mStudentPassword = mStudentPassword;
        this.mStudentPhoneNum = mStudentPhoneNum;
        this.mStudentEmail = mStudentEmail;
        this.mStudentID = mStudentID;
        this.mStudentProfilePic = mStudentProfilePic;
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
    public int getStudentID() {
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
     * setStudentPassword function
     * @param mStudentPassword
     */
    public void setStudentPassword(String mStudentPassword) {
        this.mStudentPassword = mStudentPassword;
    }

    /**
     * setStudentPhoneNum function
     * @param mStudentPhoneNum
     */
    public void setStudentPhoneNum(String mStudentPhoneNum) {
        this.mStudentPhoneNum = mStudentPhoneNum;
    }

    /**
     * setStudentEmail function
     * @param mStudentEmail
     */
    public void setStudentEmail(String mStudentEmail) {
        this.mStudentEmail = mStudentEmail;
    }

    /**
     * setStudentID function
     * @param mStudentID
     */
    public void setStudentID(int mStudentID) {
        this.mStudentID = mStudentID;
    }

    /**
     * setStudentProfilePic function
     * @param mStudentProfilePic
     */
    public void setStudentProfilePic(Uri mStudentProfilePic) {
        this.mStudentProfilePic = mStudentProfilePic;
    }

    /**
     * toString function
     * @return string
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
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mStudentUserName);
        dest.writeString(mStudentPassword);
        dest.writeString(mStudentPhoneNum);
        dest.writeString(mStudentEmail);
        dest.writeInt(mStudentID);
        dest.writeString(mStudentProfilePic.toString());
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
     *
     */
    public static final Parcelable.Creator<UserAccount> CREATOR = new Parcelable.Creator<UserAccount>() {
        /**
         *
         * @param source
         * @return
         */
        @Override
        public UserAccount createFromParcel(Parcel source) {
            return new UserAccount(source);
        }

        /**
         *
         * @param size
         * @return
         */
        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };
}
