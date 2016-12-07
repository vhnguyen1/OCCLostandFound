package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

/**
 * Created by Vu Nguyen on 12/4/2016.
 */

public class AccountInfo
{


    private String mName;
    private String mLastName;

    public AccountInfo(String mName, String mLastName)
    {
        this.mName = mName;
        this.mLastName = mLastName;
    }

    public AccountInfo() {
       this("","");
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getName() {
        return mName;
    }

    public String getLastName() {
        return mLastName;
    }
}