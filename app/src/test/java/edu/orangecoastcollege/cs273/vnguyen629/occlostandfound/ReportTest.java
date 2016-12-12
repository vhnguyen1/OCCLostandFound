
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
public class ReportTest {
    private Report mReport;
    UserAccount account = new UserAccount();
    Item newItem = new Item();

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        //mReport = new Report();
        //mReport.setAccount("Test Report Account");
        //mReport.setName("Test Report Name");
        //mReport.setDateLost("Test Report Date Lost");
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getAccount() throws Exception {
        //mReport.setAccount("Test Report Account");
        //assertEquals("Error Found!", "Test Report Account", mReport.getAccount());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getName() throws Exception {
        //mReport.setName("Test Report Name");
        //assertEquals("Error Found!", "Test Report Name", mReport.getName());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getDateLost() throws Exception {
        //mReport.setDateLost("Test Report Date Lost");
        //assertEquals("Error Found!", "Test Report Date Lost", mReport.getDateLost());
    }


    /**
     *Test set and get mAccount
     * @throws Exception
     */
    @Test
    public void setAccount() throws Exception {
        mReport.setAccount(account);
        assertEquals("Error Found!", "", mReport.getAccount().getStudentUserName());
    }

    /**
     *Test set and get mItem
     * @throws Exception
     */
    @Test
    public void setItem() throws Exception {
        mReport.setItem(newItem);
        assertEquals("Error Found!", "", mReport.getItem().getName());
    }

    /**
     *Test set and get smsCheck
     * @throws Exception
     */
    @Test
    public void SMSCheck() throws Exception {
        mReport.setSmsCheck(1);
        assertEquals("Error Found!", 1, mReport.getSmsCheck());
    }
}