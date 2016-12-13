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
     * Starts up before testing all the other functions, initializing a testing
     * <code>Item</code> object to default testing values
     * @throws Exception If mItem or member functions/variables don't exist
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
    }

    /**
     * Tests the return method for retrieving the name
     * @throws Exception If getName doesn't exist
     */
    @Test
    public void getName() throws Exception {
        mItem.setName("1");
        assertEquals("Error Found!", "1", mItem.getDateLost());
    }

    /**
     * Tests the mutator method for setting the name
     * @throws Exception If setName doesn't exist
     */
    @Test
    public void setName() throws Exception {
        mItem.setName("1");
        assertEquals("Error Found!", "1", mItem.getDateLost());
    }

    /**
     * Tests the return method for retrieving the description
     * @throws Exception If getDescription doesn't exist
     */
    @Test
    public void getDescription() throws Exception {
        assertEquals("Error Found!", "2", mItem.getDescription());
    }

    /**
     * Tests the mutator method for setting the description
     * @throws Exception If setDescription doesn't exist
     */
    @Test
    public void setDescription() throws Exception {
        mItem.setDescription("2");
        assertEquals("Error Found!", "2", mItem.getDescription());
    }

    /**
     * Tests the return method for retrieving the date the <code>Item</code> was lost
     * @throws Exception If getDateLost doesn't exist
     */
    @Test
    public void getDateLost() throws Exception {
        assertEquals("Error Found!", "3", mItem.getDateLost());
    }

    /**
     * Tests the mutator method for setting the date the <code>Item</code> was lost
     * @throws Exception If setDescription doesn't exist setDateLost
     */
    @Test
    public void setDateLost() throws Exception {
        mItem.setDateLost("3");
        assertEquals("Error Found!", "3", mItem.getDateLost());
    }

    /**
     * Tests the return method for retrieving the last known the <code>Item</code> was at
     * @throws Exception If getLastLocation doesn't exist
     */
    @Test
    public void getLastLocation() throws Exception {
        assertEquals("Error Found!", "4", mItem.getLastLocation());
    }

    /**
     * Tests the mutator method for setting the last known the <code>Item</code> was at
     * @throws Exception If setLastLocation doesn't exist setDateLost
     */
    @Test
    public void setLastLocation() throws Exception {
        mItem.setLastLocation("4");
        assertEquals("Error Found!", "4", mItem.getLastLocation());
    }

    /**
     * Tests the return method for retrieving the <code>Item</code> object's status
     * @throws Exception If getStatus doesn't exist
     */
    @Test
    public void getStatus() throws Exception {
        assertFalse("Error Found!", mItem.getStatus());
    }

    /**
     * Tests the mutator method for setting the the <code>Item</code> object's status
     * @throws Exception If setStatus doesn't exist setDateLost
     */
    @Test
    public void setStatus() throws Exception {
        mItem.setStatus(false);
        assertFalse("Error Found!", mItem.getStatus());
    }

    /**
     * Tests the return method for retrieving the <code>Item</code> object's image uri
     * @throws Exception If getImageURI doesn't exist
     */
    @Test
    public void getImageURI() throws Exception {
        assertEquals("Error Found!", "5", mItem.getImageUri().toString());
    }

    /**
     * Tests the mutator method for setting the <code>Item</code> object's image uri
     * @throws Exception If setImageURI doesn't exist
     */
    @Test
    public void setImageURI() throws Exception {
        mItem.setImageURI(Uri.parse("5"));
        assertEquals("Error Found!", "5", mItem.getImageUri().toString());
    }
}