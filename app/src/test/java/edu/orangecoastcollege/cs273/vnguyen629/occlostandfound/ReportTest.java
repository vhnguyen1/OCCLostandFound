
package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * JUnit Testing for the <code>Report</code> class
 *
 * @author Benjamin Nguyen
 */
public class ReportTest {
    private Report mReport;
    UserAccount account;
    Item newItem;

/*    private int mID;
    private UserAccount mAccount;
    private Item mItem;
    private int mSmsCheck;*/

    /**
     * Starts before everything else does, setting up the testing
     * @throws Exception If report doesn't exist
     */
    @Before
    public void setUp() throws Exception {
        mReport = new Report();

        account = new UserAccount();
        newItem = new Item();

        mReport.setAccount(account);
        mReport.setItem(newItem);
        mReport.setSmsCheck(1);
    }

    /**
     * Unused
     * @throws Exception Unused
     */
    @After
    public void tearDown() throws Exception {}

    /**
     *
     * @throws Exception
     */
    @Test
    public void getAccount() throws Exception {
        mReport.setAccount(account);
        assertEquals("Error Found!", "", mReport.getAccount().getStudentUserName());
    }

    /**
     * Test set and get mAccount
     * @throws Exception
     */
    @Test
    public void setAccount() throws Exception {
        mReport.setAccount(account);
        assertEquals("Error Found!", "", mReport.getAccount().getStudentUserName());
    }

    /**
     * Test set and get mItem
     * @throws Exception
     */
    @Test
    public void setItem() throws Exception {
        mReport.setItem(newItem);
        assertEquals("Error Found!", "", mReport.getItem().getName());
    }

    /**
     *T est set and get smsCheck
     * @throws Exception
     */
    @Test
    public void setSMSCheck() throws Exception {
        mReport.setSmsCheck(1);
        assertEquals("Error Found!", 1, mReport.getSmsCheck());
    }
}