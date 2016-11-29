
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

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        mReport = new Report();
        mReport.setAccount("Test Account");
        mReport.setName("Test Name");
        mReport.setDateLost("Test Date Lost");
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
        //mReport.setAccount("Test Account");
        assertEquals("Error Found!", "Test Account", mReport.getAccount());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getName() throws Exception {
        //mItem.setName("Test Name");
        assertEquals("Error Found!", "Test Name", mReport.getName());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getDateLost() throws Exception {
        //mItem.setName("Test Name");
        assertEquals("Error Found!", "Test Date Lost", mReport.getDateLost());
    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void setAccount() throws Exception {
        mReport.setName("Test Account");
        assertEquals("Error Found!", "Test Account", mReport.getAccount());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setName() throws Exception {
        mReport.setName("Test Name");
        assertEquals("Error Found!", "Test Name", mReport.getName());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setDateLost() throws Exception {
        mReport.setDateLost("Test Date Lost");
        assertEquals("Error Found!", "Test Date Lost", mReport.getDateLost());
    }
}