package util;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the implementation of the Date class.
 */
public class DateTest {

    @Test
    public void testDaysInFeb() {
        Date date = new Date("2/29/2018");
        assertFalse(date.isValid());
        date = new Date("2/30/2020");
        assertFalse(date.isValid());
        date = new Date("2/29/2020");
        assertTrue(date.isValid());
    }

    @Test
    public void testMonth(){
        Date date = new Date("4/31/2020");
        assertFalse(date.isValid());
        date = new Date("0/20/2010");
        assertFalse(date.isValid());
        date = new Date("12/31/2000");
        assertTrue(date.isValid());
    }
}