package edu.orangecoastcollege.cs273.vnguyen629.occlostandfound;

import android.net.Uri;
import android.os.Parcel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * JUnit Testing for the <code>Item</code> class. Checks for
 * the validity of the member methods.
 *
 * @author Vincent Nguyen
 */
public class ItemTest {
    private Item mItem;
    private Item mParcelItem;

    /**
     * Initializes the regular and parcelable <code>Item</code> objects for testing.
     * @throws Exception If the <code>Item</code> class does not exist.
     */
    @Before
    public void setUp() throws Exception {
        mItem = new Item();
        mParcelItem = new Item();
    }

    /**
     * Unused
     * @throws Exception Unused
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * Tests the parcelable features of the <code>Item</code> class.
     * @throws Exception If the parcelable or <code>Item</code> objects do not exist.
     */
    @Test
    public void parcelTest() throws Exception {
        final Parcel TESTER = Parcel.obtain();
        mParcelItem.writeToParcel(TESTER, 0);
        TESTER.setDataPosition(0);

        final Item ITEM_FROM_PARCEL = Item.CREATOR.createFromParcel(TESTER);
        assertEquals(TESTER, ITEM_FROM_PARCEL);
    }

    /**
     * Tests the retrieval of the <code>Item</code> name.
     * @throws Exception If the name field doesn't exist.
     */
    @Test
    public void getName() throws Exception {
        mItem.setName("Test Name");
        assertEquals("Error Found!", "Test Title", mItem.getName());
    }

    /**
     * Tests the setting/changing of the <code>Item</code> name.
     * @throws Exception If the name field doesn't exist
     */
    @Test
    public void setName() throws Exception {
        mItem.setName("Test Name");
        assertEquals("Error Found!", "Test Name", mItem.getName());
    }

    /**
     * Tests the retrieval of the <code>Item</code> description.
     * @throws Exception If the description field doesn't exist.
     */
    @Test
    public void getDescription() throws Exception {
        mItem.setDescription("Test Name");
        assertEquals("Error Found!", "Test Description", mItem.getDescription());
    }

    /**
     * Tests the setting/changing of the <code>Item</code> description.
     * @throws Exception If the description field doesn't exist
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
        mItem.setDateLost("Test Date Lost");
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
    public void getLastLocation() throws Exception {
        mItem.setLastLocation("Test Last Known Location");
        assertEquals("Error Found!", "Test Last Known Location", mItem.getDateLost());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setLastLocation() throws Exception {
        mItem.setLastLocation("Test Last Known Location");
        assertEquals("Error Found!", "Test Last Known Location", mItem.getDateLost());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getStatus() throws Exception {
        mItem.setStatus(true);
        assertEquals("Error Found!", "Found!", (mItem.getStatus())? "Found!" : "Not Found.");
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testStatus() throws Exception {
        mItem.setStatus(true);
        assertEquals("Error Found!", "Found!", (mItem.getStatus())? "Found!" : "Not Found.");
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void getImageUri() throws Exception {
        mItem.setImageURI(Uri.parse("Test Image Uri"));
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

    /**
     *
     * @throws Exception
     */
    @Test
    public void getReportedUsername() throws Exception {
        mItem.setLastLocation("Test Reported Username");
        assertEquals("Error Found!", "Test Reported Username", mItem.getReportedUsername());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void setReportedUsername() throws Exception {
        mItem.setLastLocation("Test Reported Username");
        assertEquals("Error Found!", "Test Reported Username", mItem.getReportedUsername());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void itemToString() throws Exception {

    }
}