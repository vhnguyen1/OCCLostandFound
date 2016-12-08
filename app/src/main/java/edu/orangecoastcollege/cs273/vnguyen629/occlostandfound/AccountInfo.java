package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

/**
 * Created by Vu Nguyen on 12/4/2016.
 */

public class AccountInfo
{


    private String mName;
    private String mLastName;
    private boolean mAllowShake;
    private boolean mAllowSms;

    public AccountInfo(String mName, String mLastName, boolean allowShake, boolean allowSms)
    {
        this.mName = mName;
        this.mLastName = mLastName;
        this.mAllowShake = allowSms;
    }

    public AccountInfo() {
       this("","", false, false);
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public void setAllowShake(boolean mAllowShake) {
        this.mAllowShake = mAllowShake;
    }

    public void setAllowSms(boolean mAllowSms) {
        this.mAllowSms = mAllowSms;
    }

    public String getName() {
        return mName;
    }

    public String getLastName() {
        return mLastName;
    }

    public boolean isAllowSms() {
        return mAllowSms;
    }

    public boolean isAllowShake() {
        return mAllowShake;
    }
}