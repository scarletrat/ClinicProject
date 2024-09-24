package clinic;

/**
 * This class defines the time slots of a day in the format HH:MM
 *
 */
public enum Timeslot {
    SLOT1 (9, 0),
    SLOT2 (10, 45),
    SLOT3 (11, 15),
    SLOT4 (13, 30),
    SLOT5 (15, 0),
    SLOT6 (16, 15);

    private Timeslot(int hour, int minute) {
    }

}
