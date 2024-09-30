package clinic;

/**
 * @author Christopher Lee, modified Sept. 28, 2024.
 */
public class List {
    private Appointment[] appointments; //array of appointment objects
    private int size = 0; //number of appointments

    /**
     * traverses array until it finds an element equal to the appointment parameter
     * @param appointment element to find in array
     * @return -1 if element is not in array and the index if in the array
     */
    private int find(Appointment appointment) {
        for(int i = 0; i < size; i++){
            if(appointments[i].equals(appointment)){
                return i;
            }
        }
        return -1;
    }

    /**
     * increases the size of appointments by 4
     */
    private void grow(){
        Appointment[] updatedSize = new Appointment[size+4-1];
        for(int i = 0; i<size; i++){
            updatedSize[i] = appointments[i];
        }
         appointments = updatedSize;
    }

    /**
     * checks to see if array contains the appointment parameter
     * @param appointment element to look for
     * @return -1 if element is not in array and the index if in the array
     */
    private boolean contains(Appointment appointment) {
        for(int i = 0; i < size; i++){
            if(appointments[i].equals(appointment)){
                return true;
            }
        }
        return false;
    }

    /**
     * adds appointment to end of array if not already in array
     * @param appointment element to be added
     */
    public void add(Appointment appointment){
        if(!contains(appointment)) {
            if (size == appointments.length) {
                grow();

            }
            appointments[size] = appointment;
            size++;
        }
    }

    /**
     * remove appointment from array and replace with null
     * @param appointment element to be removed
     */
    public void remove(Appointment appointment){
        if(contains(appointment)){
            appointments[find(appointment)] = null;
        }
    }

    public void printByPatient(){

    }

}
