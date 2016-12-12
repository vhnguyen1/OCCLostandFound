
package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import org.junit.After;
import org.junit.Before;

import static junit.framework.Assert.assertEquals;

/**
 * JUnit Testing for the <code>Report</code> class
 *
 * @author Vu Nguyen
 */
public class UserAccountTest {
    private UserAccount account;

    /**
     * Sets up the account before testing everything else
     * @throws Exception If the UserAccount doesn't exist
     */
    @Before
    public void setUp() throws Exception {
        account = new UserAccount();
        account.setStudentUserName("John");
        account.setStudentPassword("Hello");
        account.setStudentPhoneNum("(714) 200 0000");
        account.setStudentEmail("stuff@stuff.com");
        account.setStudentID("C787423456");
        account.setmIsAdmin(true);
        account.setmAllowShake(true);
        account.setmAllowSms(true);
        account.setFeedBack("Hello my name is Somethings");
    }

    /**
     * Unused
     * @throws Exception Unused
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * Test set and get mStudentUserName
     * @throws Exception
     */
    @Test
    public void UserName() throws Exception
    {
        account.setStudentUserName("John");
        assertEquals("Error Found!", "John", account.getStudentUserName());
    }

    /**
     *Test set and get mStudentPassword
     * @throws Exception
     */
    @Test
    public void password() throws Exception
    {
        account.setStudentPassword("Hello");
        assertEquals("Error Found!", "John", account.getStudentPassword());

    }

    /**
     *Test set and get mStudentPhoneNum
     * @throws Exception
     */
    @Test
    public void phoneNumber() throws Exception
    {
        account.setStudentPhoneNum("(714) 200 0000");
        assertEquals("Error Found!", "(714) 200 0000", account.getStudentPhoneNum());

    }

    /**
     *Test set and get mStudentEmail
     * @throws Exception
     */
    @Test
    public void email() throws Exception
    {
        account.setStudentEmail("stuff@stuff.com");
        assertEquals("Error Found!", "stuff@stuff.com", account.getStudentEmail());
    }

    /**
     *Test set and get mStudentID
     * @throws Exception
     */
    @Test
    public void ID() throws Exception
    {
        account.setStudentID("C787423456");
        assertEquals("Error Found!", "stuff@C787423456.com", account.getStudentID());
    }

    /**
     *Test set and get mIsAdmin
     * @throws Exception
     */
    @Test
    public void isAdmin() throws Exception
    {
        account.setmIsAdmin(true);
        assertEquals("Error Found!", true, account.getIsAdmin());
    }


    /**
     *Test set and get mAllowShake
     * @throws Exception
     */
    @Test
    public void allowShake() throws Exception
    {
        account.setmAllowShake(true);
        assertEquals("Error Found!",true, account.getAllowShake());
    }

    /**
     *Test set and get mAllowSMS
     * @throws Exception
     */
    @Test
    public void allowSMS() throws Exception
    {
        account.setmAllowSms(true);
        assertEquals("Error Found!",true, account.getAllowSms());
    }

    /**
     *Test set and get mFeedBack
     * @throws Exception
     */
    @Test
    public void feedBack() throws Exception
    {
        account.setFeedBack("Hello my name is Somethings");
        assertEquals("Error Found!","Hello my name is Somethings", account.getFeedBack());
    }


}