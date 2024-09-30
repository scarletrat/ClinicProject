package clinic;

/**
 * This class contains the patient's information.
 * It includes the profile object and visits.
 * @author Gordon Lin, modified 9/29/2024
 */

public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; //a linked list of visits

    /**
     * Default constructor.
     */
    public Patient() {
        this.profile = null;
    }

    /**
     * Two argument constructor.
     * @param profile the patient's profile.
     * @param appointment the appointment of the patient.
     */
    public Patient(Profile profile, Appointment appointment){
        this.profile = profile;
        this.visits = new Visit(appointment);
    }

    /**
     * Four argument constructor
     * @param fname       the first name of the patient.
     * @param lname       the last name of the patient.
     * @param dob         the date of birth of the patient.
     * @param appointment the appointment of the patient.
     */
    public Patient(String fname, String lname, String dob, Appointment appointment) {
        this.profile = new Profile(fname, lname, dob);
        this.visits = new Visit(appointment);
    }

    /**
     * Compare two Patient objects
     *
     * @param patient the object to be compared.
     * @return return 1 if the profile is "bigger" than the input "profile".
     * return -1 is the profile is "smaller" than the input "profile".
     * return 0 if equal.
     */
    @Override
    public int compareTo(Patient patient) {
        return this.profile.compareTo(patient.profile);
    }

    /**
     * Compare to see if two patient are equal.
     *
     * @param obj the obj being compared to.
     * @return true if two patients' profiles are equal.
     * return false if two patients' profiles are not equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
            Patient patient = (Patient) obj;
            return this.profile.equals(patient.profile);
        }
        return false;
    }

    /**
     * A textual representation of teh Patient object.
     *
     * @return return the Profile object textual representation.
     */
    @Override
    public String toString() {
        return this.profile.toString();
    }

    /**
     * Get the profile of the patient.
     * @return return the patient's profile.
     */
    public Profile getProfile(){
        return this.profile;
    }

    /**
     * Add an appointment visit to the Patient's visits.
     * @param appointment the appointment.
     */
    public void addVisit(Appointment appointment) {
        if (this.visits.getAppointment() == null) {
            this.visits.setAppointment(appointment);
            return;
        }
        Visit newVisit = new Visit(appointment);
        if (this.visits.getNext() == null) {
            this.visits.setNext(newVisit);
            return;
        }
        Visit ptr = this.visits.getNext();
        while (ptr.getNext() != null) {
            ptr = ptr.getNext();
        }
        ptr.setNext(newVisit);
    }

    /**
     * Remove the given appointment.
     * @param appointment the appointment to be removed.
     */
    public void removeVisit(Appointment appointment){
        Visit ptr = this.visits;
        Visit prev = ptr;
        boolean found = false;
        //appointment at the first node
        if(ptr.getAppointment().equals(appointment)){
            ptr.setAppointment(null);
            return;
        }
        //searching for appointment
        while(ptr.getNext()!=null){
            prev = ptr;
            ptr = ptr.getNext();
            if(ptr.getAppointment().equals(appointment)) {
                found = true;
                break;
            }
        }
        //ptr is now the appointment to be removed if found is true
        if (found) {
            if(ptr.getNext() == null){
                prev.setNext(null);
            }else {
                prev.setNext(ptr.getNext());
            }
        }
    }

    /**
     * Traverse the linked list to compute the charge.
     * @return the charge of the patient.
     */
    public int charge(){
        int charge = 0;
        Visit ptr = this.visits;
        if(ptr.getAppointment() == null){
            return 0;
        }
        while(ptr.getAppointment() != null) {
            int ptrCost = ptr.getAppointment().getProvider().getSpecialty().getCharge();
            charge += ptrCost;
            ptr = ptr.getNext();
        }
        return charge;
    }

}
