package clinic;

/**
 * Manages array of appointments by sorting, adding elements, removing elements,
 * and adjusting the size of the array.
 * @author Christopher Lee, Gordon Lin modified Sept. 30, 2024.
 */
public class List {
    private Appointment[] appointments = new Appointment[0]; //array of appointment objects
    private int size = 0; //number of appointments

    /**
     * Traverses array until it finds an element equal to the appointment parameter
     * @param appointment element to find in array
     * @return -1 if element is not in array and the index if in the array
     */
    private int find(Appointment appointment) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].equals(appointment)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Increases the size of appointments by 4
     */
    private void grow() {
        Appointment[] updatedSize = new Appointment[size + 4 - 1]; //creates array to update appointments size
        for (int i = 0; i < size; i++) {
            updatedSize[i] = appointments[i];
        }
        appointments = updatedSize;
    }

    /**
     * Checks to see if array contains the appointment parameter
     * @param appointment element to look for
     * @return -1 if element is not in array and the index if in the array
     */
    public boolean contains(Appointment appointment) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].equals(appointment)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds appointment to end of array if not already in array
     * @param appointment element to be added
     */
    public void add(Appointment appointment) {
        if (!contains(appointment)) {
            if (size == appointments.length) {
                grow();

            }
            appointments[size] = appointment;
            size++;
        }
    }

    /**
     * Remove appointment from array and shifts all elements by 1 index
     * @param appointment element to be removed
     */
    public void remove(Appointment appointment) {
        if (contains(appointment)) {
            Appointment[] updatedArray = new Appointment[appointments.length];
            int target = find(appointment);
            for (int i = 0; i < size; i++) {
                if (i == target) {
                   appointments[i] = null;
                }
                else if(i>target){
                    updatedArray[i-1] = appointments[i];
                }
                else{
                    updatedArray[i] = appointments[i];
                }
            }
            appointments = updatedArray;
            size--;
        }
    }

    /**
     * Checks to see if provider is already booked for another profile
     * @return false if date, timeslot, and provider are all equal, true otherwise
     */
    public boolean isProviderFree(Appointment appointment) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].getTimeslot() == appointment.getTimeslot() &&
                    appointments[i].getDate().equals(appointment.getDate())
                    && appointments[i].getProvider().equals(appointment.getProvider())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sorts array by profile, date, then timeslot and prints the array
     */
    public void printByPatient() {
        if(size > 0) {
            System.out.println("\n** Appointments ordered by patient/date/time **");
            sortPatient();
            printAppointments();

        }
        else{
            System.out.println("The schedule calendar is empty.");
        }
    }

    /**
     * Sorts array by profile, date, then timeslot and prints the array
     */
    public void printByLocation() {
        if(size > 0) {
            System.out.println("\n** Appointments ordered by county/date/time **");
            sortLocation();
            printAppointments();
        }
        else{
            System.out.println("The schedule calendar is empty.");
        }
    }

    /**
     * Sorts array by profile, date, then timeslot and prints the array
     */
    public void printByAppointment() {
        if(size > 0) {
            System.out.println("\n** Appointments ordered by date/time/provider **");
            sortAppointment();
            printAppointments();
        }
        else{
            System.out.println("The schedule calendar is empty.");
        }
    }

    /**
     * Uses selection sort to sort the appointments by profile, date, then timeslot.
     */
    public void sortPatient(){
        for (int i = 0; i < size-1; i++) {
            int min = i;
            for(int j = i+1; j<size; j++) {
                if(appointments[j].getProfile().compareTo(appointments[min].getProfile()) < 0) {
                    min = j;
                }
                else if(appointments[j].compareTo(appointments[min])<0 &&
                        appointments[j].getProfile().compareTo(appointments[min].getProfile()) == 0){
                    min =j;
                }
            }
            swap(i,min);
        }
    }

    /**
     * Uses selection sort to sort the appointments by county, date, then timeslot.
     */
    public void sortLocation(){
        for (int i = 0; i < size-1; i++) {
            int min = i;
            for(int j = i+1; j<size; j++) {
                if(appointments[j].getProvider().getLocation().getCounty().compareTo(appointments[min].getProvider().getLocation().getCounty()) < 0) {
                    min = j;
                }
                else if(appointments[j].compareTo(appointments[min])<0 &&
                        appointments[j].getProvider().getLocation().getCounty().compareTo(appointments[min].getProvider().getLocation().getCounty()) == 0){
                    min =j;
                }
            }
            swap(i,min);
        }
    }

    /**
     * Uses selection sort to sort the appointments by date, timeslot, then provider.
     */
    public void sortAppointment(){
        for (int i = 0; i < size-1; i++) {
            int min = i;
            for(int j = i+1; j<size; j++) {
                if(appointments[j].compareTo(appointments[min]) < 0) {
                    min = j;
                }
                else if(appointments[j].getProvider().compareTo(appointments[min].getProvider())<0 &&
                        appointments[j].compareTo(appointments[min]) == 0){
                    min =j;
                }
            }
            swap(i,min);
        }
    }

    /**
     * creates temporary appointment to swap two appointments next to each other
     * @param i i and j are indexes of elements being swapped
     */
    public void swap(int i, int j){
        Appointment temp = appointments[j];
        appointments[j] = appointments[i];
        appointments[i] = temp;
    }

    /**
     * helper method for the printBy... methods to list all the appointments in a list format
     */
    private void printAppointments(){
            for(int i = 0; i<size; i++){
                System.out.println(appointments[i].toString());
            }
            System.out.println("** end of list **");
    }

    /**
     * Gives the number of appointments in appointments array
     * @return size instance variable
     */
    public int getSize() {
        return size;
    }

    /**
     * Removes all appointments from the array and resetting the size to 0
     */
    public void removeAll(){
        this.appointments = new Appointment[0];
        this.size =0;
    }

    /**
     * Returns appointments array
     * @return appointments instance variable
     */
    public Appointment[] getAppointments(){
        return appointments;
    }

    /**
     * Get the appointments from the list without the provider information.
     * @param date the date of the appointment.
     * @param tempSlot the timeslot of the appointment.
     * @param profile the profile of the appointment.
     * @return return the appointment or null if not found.
     */
    public Appointment getAppointment(Date date, Timeslot tempSlot, Profile profile){
        for(int i = 0; i<size; i++){
            if(appointments[i].getProfile().equals(profile)){
                if(appointments[i].getDate().equals(date)){
                    if(appointments[i].getTimeslot().equals(tempSlot)){
                        return appointments[i];
                    }
                }
            }
        }
        return null;
    }
}