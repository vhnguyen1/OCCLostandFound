package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.net.Uri;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * JUnit Testing for the <code>Item</code> class
 *
 * @author Vincent Nguyen
 */
public class ItemTest {
    private Item mItem;

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        mItem = new Item();
        mItem.setName("1");
        mItem.setDescription("2");
        mItem.setDateLost("3");
        mItem.setLastLocation("4");
        mItem.setStatus(false);
        mItem.setImageURI(Uri.parse("5"));
        mItem.setReportedUsername("6");
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getName() throws Exception {
        mItem.setName("1");
        assertEquals("Error Found!", "1", mItem.getDateLost());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setName() throws Exception {
        mItem.setName("1");
        assertEquals("Error Found!", "1", mItem.getDateLost());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getDescription() throws Exception {
        assertEquals("Error Found!", "2", mItem.getDescription());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setDescription() throws Exception {
        mItem.setDescription("2");
        assertEquals("Error Found!", "2", mItem.getDescription());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getDateLost() throws Exception {
        assertEquals("Error Found!", "3", mItem.getDateLost());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setDateLost() throws Exception {
        mItem.setDateLost("3");
        assertEquals("Error Found!", "3", mItem.getDateLost());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getLastLocation() throws Exception {
        assertEquals("Error Found!", "4", mItem.getLastLocation());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setLastLocation() throws Exception {
        mItem.setLastLocation("4");
        assertEquals("Error Found!", "4", mItem.getLastLocation());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getStatus() throws Exception {
        assertFalse("Error Found!", mItem.getStatus());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setStatus() throws Exception {
        mItem.setStatus(false);
        assertFalse("Error Found!", mItem.getStatus());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getImageURI() throws Exception {
        assertEquals("Error Found!", "5", mItem.getImageUri().toString());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setImageURI() throws Exception {
        mItem.setImageURI(Uri.parse("5"));
        assertEquals("Error Found!", "5", mItem.getImageUri().toString());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getReportingUser() throws Exception {
        assertEquals("Error Found!", "6", mItem.getReportedUsername());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setReportingUser() throws Exception {
        mItem.setReportedUsername("6");
        assertEquals("Error Found!", "6", mItem.getReportedUsername());
    }
}