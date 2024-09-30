package clinic;

/**
 * This is an array-based implementation to hold a list of Patient objects.
 * It models a clinic's medical record with the the Patient object.
 * @author Gordon Lin, modified 9/30/2024
 */
public class MedicalRecord {
    private Patient[] patients;
    private int size;

    public MedicalRecord(){
        patients = null;
        size = 0;
    }

    /**
     * Add a new patient to medical record.
     * @param appointment the patient's appointment.
     */
    public void addNewPatient(Appointment appointment){
        Patient newPatient = new Patient(appointment.getProfile(), appointment);
        Patient [] temp = new Patient[size+1];
        for(int i =0; i<size; i++){
            temp[i] = patients[i];
        }
        temp[size] = newPatient;
        patients = temp;
    }

    /**
     * Get the size of medical record.
     * @return return the size of medical record.
     */
    public int getSize(){
        return size;
    }

    /**
     * Add the clinic's appointment to the medical record.
     * @param clinic the clinic containing all the appointments.
     */
    public void add(List clinic){
        int clinicSize = clinic.getSize();
        Appointment[] appointments = clinic.getAppointments();
        for(int i = 0; i<clinicSize; i++){
            Profile profile = appointments[i].getProfile();
            if(contains(profile)){
                Patient patient = findPatient(profile);
                updatePatient(patient,appointments[i]);
            }else{
                addNewPatient(appointments[i]);
                size++;
            }
        }
    }

    /**
     * Find the patient in medical record.
     * @param profile the patient's profile
     * @return return the patient.
     */
    public Patient findPatient(Profile profile){
        for(int i = 0; i< size; i++){
            if(patients[i].getProfile().equals(profile)){
                return patients[i];
            }
        }
        return null;
    }

    /**
     * Check if the medical record contains the profile.
     * @param profile the profile to be checked.
     * @return return true if there's a record of the profile.
     * return false otherwise.
     */
    public boolean contains(Profile profile){
        for(int i = 0; i< size; i++){
            if(patients[i].getProfile().equals(profile)){
                return true;
            }
        }
        return false;
    }

    /**
     * Get the patients array of medical record.
     * @return return the patients array.
     */
    public Patient[] getPatients(){
        return patients;
    }

    /**
     * Updates the patient's profile.
     * @param appointment the patient's profile to be updated.
     */
    public void updatePatient(Patient patient,Appointment appointment){
        patient.addVisit(appointment);
    }

}
