package clinic;

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


}
