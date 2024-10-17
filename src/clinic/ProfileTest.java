package clinic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class test the implementation of the Profile class.
 */
public class ProfileTest {

    @Test
    public void test_LastName() {
        Profile profile = new Profile("Carl", "Brook", "2/20/2000");
        Profile compare = new Profile("Roy", "Brown", "2/20/2000");
        int expectedOutput = -1;
        int actualOutput = profile.compareTo(compare);
        assertEquals(expectedOutput,actualOutput);
        compare = new Profile("Carl", "Brook", "10/06/2004");
        profile = new Profile("Clark", "Brook", "1/1/2000");
        expectedOutput = 1;
        actualOutput = profile.compareTo(compare);
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void test_FirstName(){
        Profile profile = new Profile("Carl", "Brook", "10/06/2004");
        Profile compare = new Profile("Clark", "Brook", "1/1/2000");
        int expectedOutput = -1;
        int actualOutput = profile.compareTo(compare);
        assertEquals(expectedOutput,actualOutput);
        compare = new Profile("Carl", "Brook", "10/06/2004");
        profile = new Profile("Clark", "Brook", "1/1/2000");
        expectedOutput = 1;
        actualOutput = profile.compareTo(compare);
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void test_dob(){
        Profile profile = new Profile("Carl", "Brook", "10/06/2004");
        Profile compare = new Profile("Carl", "Brook", "1/1/2008");
        int expectedOutput = -1;
        int actualOutput = profile.compareTo(compare);
        assertEquals(expectedOutput,actualOutput);
        compare = new Profile("Carl", "Brook", "10/06/2004");
        profile = new Profile("Carl", "Brook", "1/1/2008");
        expectedOutput = 1;
        actualOutput = profile.compareTo(compare);
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void test_same(){
        Profile profile = new Profile("Carl", "Brook", "9/8/2022");
        Profile compare = new Profile("Carl", "Brook", "9/8/2022");
        int expectedOutput = 0;
        int actualOutput = profile.compareTo(compare);
        assertEquals(expectedOutput,actualOutput);
    }
}