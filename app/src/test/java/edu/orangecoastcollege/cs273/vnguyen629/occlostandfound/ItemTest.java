package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.net.Uri;

import org.junit.After;
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
        mItem.setName("Test Name");
        mItem.setDescription("Test Description");
        mItem.setDateLost("Test Date Lost");
        mItem.setLastLocation("Test Last Known Location");
        mItem.setStatus(true);
        mItem.setImageURI(Uri.parse("Test Image URI"));
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
    public void getName() throws Exception {
        //mItem.setName("Test Name");
        assertEquals("Error Found!", "Test Title", mItem.getName());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setName() throws Exception {
        mItem.setName("Test Name");
        assertEquals("Error Found!", "Test Name", mItem.getName());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getDescription() throws Exception {
        //mItem.setDescription("Test Name");
        assertEquals("Error Found!", "Test Description", mItem.getDescription());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setDescription() throws Exception {
        mItem.setDescription("Test Description");
        assertEquals("Error Found!", "Test Name", mItem.getDescription());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getDateLost() throws Exception {
        //mItem.setDateLost("Test Date Lost");
        assertEquals("Error Found!", "Test Date", mItem.getDateLost());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setDateLost() throws Exception {
        mItem.setDateLost("Test Date Lost");
        assertEquals("Error Found!", "Test Date Lost", mItem.getDateLost());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getLocation() throws Exception {

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setLocation() throws Exception {

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getImageUri() throws Exception {
        //mItem.setImageURI(Uri.parse("Test Image Uri"));
        assertEquals("Error Found!", "Test Image Uri", mItem.getImageUri().toString());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setImageUri() throws Exception {
        mItem.setImageURI(Uri.parse("Test Image Uri"));
        assertEquals("Error Found!", "Test Image Uri", mItem.getImageUri().toString());
    }
}