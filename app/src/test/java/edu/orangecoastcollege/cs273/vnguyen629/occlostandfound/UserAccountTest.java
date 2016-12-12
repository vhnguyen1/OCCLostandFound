
package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * JUnit Testing for the <code>Report</code> class
 *
 * @author Benjamin Nguyen
 */
public class UserAccountTest {
    private UserAccount account;

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test set and get mStudentUserName
     * @throws Exception
     */
    public void UserName() throws Exception
    {
        account.setStudentUserName("John");
        assertEquals("Error Found!", "John", account.getStudentUserName());
    }

    /**
     *Test set and get mStudentPassword
     * @throws Exception
     */
    public void password() throws Exception
    {
        account.setStudentPassword("Hello");
        assertEquals("Error Found!", "John", account.getStudentPassword());

    }

    /**
     *Test set and get mStudentPhoneNum
     * @throws Exception
     */
    public void phoneNumber() throws Exception
    {
        account.setStudentPhoneNum("(714) 200 0000");
        assertEquals("Error Found!", "(714) 200 0000", account.getStudentPhoneNum());

    }

    /**
     *Test set and get mStudentEmail
     * @throws Exception
     */
    public void email() throws Exception
    {
        account.setStudentEmail("stuff@stuff.com");
        assertEquals("Error Found!", "stuff@stuff.com", account.getStudentEmail());
    }

    /**
     *Test set and get mStudentID
     * @throws Exception
     */
    public void ID() throws Exception
    {
        account.setStudentID("C787423456");
        assertEquals("Error Found!", "stuff@C787423456.com", account.getStudentID());
    }

    /**
     *Test set and get mIsAdmin
     * @throws Exception
     */
    public void isAdmin() throws Exception
    {
        account.setmIsAdmin(true);
        assertEquals("Error Found!", true, account.getIsAdmin());
    }


    /**
     *Test set and get mAllowShake
     * @throws Exception
     */
    public void allowShake() throws Exception
    {
        account.setmAllowShake(true);
        assertEquals("Error Found!",true, account.getAllowShake());
    }

    /**
     *Test set and get mAllowSMS
     * @throws Exception
     */
    public void allowSMS() throws Exception
    {
        account.setmAllowSms(true);
        assertEquals("Error Found!",true, account.getAllowSms());
    }

    /**
     *Test set and get mFeedBack
     * @throws Exception
     */
    public void feedBack() throws Exception
    {
        account.setFeedBack("Hello my name is Something");
        assertEquals("Error Found!","Hello my name is Something", account.getFeedBack());
    }


}