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
    public void setDateLost() throws Exception {
        mItem.setDateLost("3");
        assertEquals("Error Found!", "3", mItem.getDateLost());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getDateLost() throws Exception {
        assertEquals("Error Found!", "3", mItem.getDateLost());
    }
}