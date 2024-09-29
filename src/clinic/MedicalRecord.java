package clinic;

/**
 * This is an array-based implementation to hold a list of Patient objects.
 * It models a clinic's medical record with the the Patient object.
 * @author Gordon Lin, modified 9/29/2024
 */
public class MedicalRecord {
    private Patient[] patients;
    private int size;

    /**
     * One argument constructor.
     * @param patient tne patient to put inside the record.
     */
    public MedicalRecord(Patient patient){
        patients = new Patient[1];
        patients[0] = patient;
        size = size + 1;
    }

    /**
     * Add a patient to the medical record.
     * @param patient the patient to be added.
     */
    public void addPatient(Patient patient){
        Patient[] temp = new Patient[size+1];
        for(int i=0; i< size; i++){
            temp[i] = patients[i];
        }
        patients = temp;
        size += 1;
    }

    /**
     * Updates the patient's profile.
     * @param patient the patient to be updated.
     */
    public void updatePatient(Patient patient){
        for(int i = 0; i<size; i++){
            if (patients[i].getProfile().equals(patient.getProfile())){
                patients[i] = patient;
            }
        }
    }

}
