package nl.kevinberendsen.throne;

import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;

/**
 * This TestSuite is primarily created to test the basic functions
 * of our moquette broker.
 *
 * Created by nedermail on 29/07/16.
 */
public class MqttClientTest extends TestSuite {

    /**
     * Setup the broker with a couple of clients.
     */
    @Before
    public void setUp() {

    }

    /**
     * Close down each client and shutdown the broker.
     */
    @After
    public void tearDown() {

    }
}
