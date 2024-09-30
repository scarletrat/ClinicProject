package clinic;

/**
 * Manages array of appointments.
 * @author Christopher Lee, modified Sept. 28, 2024.
 */
public class List {
    private Appointment[] appointments = new Appointment[0]; //array of appointment objects
    private int size = 0; //number of appointments

    /**
     * traverses array until it finds an element equal to the appointment parameter
     *
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
     * increases the size of appointments by 4
     */
    private void grow() {
        Appointment[] updatedSize = new Appointment[size + 4 - 1]; //creates array to update appointments size
        for (int i = 0; i < size; i++) {
            updatedSize[i] = appointments[i];
        }
        appointments = updatedSize;
    }

    /**
     * checks to see if array contains the appointment parameter
     *
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
     * adds appointment to end of array if not already in array
     *
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
     * remove appointment from array and replace with null
     *
     * @param appointment element to be removed
     */
    public void remove(Appointment appointment) {
        if (contains(appointment)) {
            Appointment[] updatedArray = new Appointment[appointments.length];
            for (int i = 0; i < size; i++) {
                if (i == find(appointment)) {
                   appointments[i] = null;
                }
                else if(i>find(appointment)){
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
     * checks to see if provider is already booked for another profile
     *
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
     * sorts array by profile, date, then timeslot and prints the array
     */
    public void printByPatient() {
        if(size > 0) {
            System.out.println("** Appointments ordered by patient/date/time **");
            sortPatient();
            printAppointments();
        }
        else{
            System.out.println("The schedule calendar is empty");
        }
    }

    /**
     * sorts array by profile, date, then timeslot and prints the array
     */
    public void printByLocation() {
        if(size > 0) {
            System.out.println("** Appointments ordered by county/date/time **");
            sortLocation();
            printAppointments();
        }
        else{
            System.out.println("The schedule calendar is empty");
        }
    }

    /**
     * sorts array by profile, date, then timeslot and prints the array
     */
    public void printByAppointment() {
        if(size > 0) {
            System.out.println("** Appointments ordered by date/time/provider **");
            sortAppointment();
            printAppointments();
        }
        else{
            System.out.println("The schedule calendar is empty");
        }
    }


    /**
     * Uses selection sort to sort the appointments by profile, date, then timeslot.
     */
    public void sortPatient(){
        for (int i = 0; i < size-1; i++) {
            int min = i;
            for(int j = i+1; j<size; j++) {
                if(appointments[j].getProfile().compareTo(appointments[i].getProfile()) < 0) {
                    min = j;
                }
                else if(appointments[j].compareTo(appointments[i])<0 &&
                        appointments[j].getProfile().compareTo(appointments[i].getProfile()) == 0){
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
                if(appointments[j].getProvider().getLocation().getCounty().compareTo(appointments[i].getProvider().getLocation().getCounty()) < 0) {
                    min = j;
                }
                else if(appointments[j].compareTo(appointments[i])<0 &&
                        appointments[j].getProvider().getLocation().getCounty().compareTo(appointments[i].getProvider().getLocation().getCounty()) == 0){
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

    public void printAppointments(){
            for(int i = 0; i<size; i++){
                System.out.println(appointments[i].toString());
            }
    }
    /**
     * gives the number of appointments in appointments array
     * @return size instance variable
     */
    public int getSize() {
        return size;
    }

    /**
     * returns appointments array
     * @return appointments instance variable
     */
    public Appointment[] getAppointments(){
        return appointments;
    }
}