package util;

import clinic.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class test the implementation of the List class.
 */
public class ListTest {
    Doctor doctor;
    Technician technician;
    List<Provider> providerList;

    @Before
    public void setUp() throws Exception {
        Profile profile = new Profile("Gordon", "Lin", "10/06/2004");
        doctor = new Doctor(profile,Location.BRIDGEWATER,Specialty.FAMILY,"120");
        technician = new Technician(profile,Location.BRIDGEWATER,125);
        providerList = new List<>();
    }

    @Test
    public void add() {
    assertFalse(providerList.contains(doctor));
    assertFalse(providerList.contains(technician));
    providerList.add(technician);
    assertTrue(providerList.contains(technician));
    providerList.add(doctor);
    assertTrue(providerList.contains(doctor));
    }

    @Test
    public void remove() {
        providerList.add(doctor);
        assertTrue(providerList.contains(doctor));
        providerList.add(technician);
        assertTrue(providerList.contains(technician));
        providerList.remove(doctor);
        assertFalse(providerList.contains(doctor));
        providerList.remove(technician);
        assertFalse(providerList.contains(technician));
    }
}