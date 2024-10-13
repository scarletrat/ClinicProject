package clinic;

public class Doctor extends Provider{
    private Specialty specialty;
    private String npi;

    public Doctor(Profile profile, Location location, Specialty specialty, String npi){
        super(profile,location);
        this.specialty = specialty;
        this.npi = npi;
    }

    public String getnpi(){
        return npi;
    }

    @Override
    public int rate() {
        return specialty.getCharge();
    }

    @Override
    public boolean equals(Object obj){
        if(!super.equals(obj)) return false;
        if(obj instanceof Doctor){
            Doctor doctor = (Doctor) obj;
            return this.npi.equals(doctor.npi);
        }
        return false;
    }

    @Override
    public String toString(){
        return "[" + super.toString() + "] [" + this.specialty + ", #" + this.npi +"]";
    }
}
