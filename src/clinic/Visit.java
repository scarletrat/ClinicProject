package clinic;

/**
 * This class defines a node in a singly linked list that maintains the list of visits.
 * Each visit stores an appointment, and a pointer to the next appointment.
 * @author Gordon Lin, modified 9/28/2024
 */
public class Visit {
    private Appointment appointment;
    private Visit next;

    Visit(Appointment appointment){
        this.appointment = appointment;
        this.next = null;
    }
}
