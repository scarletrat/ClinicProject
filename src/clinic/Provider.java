package clinic;

/**
 * This class includes the last name of the providers of the clinic
 * with the location and specialty of those providers.
 * It is an enum class and so far have 8 providers.
 * @author Gordon Lin, modified 9/28/2024
 */
public enum Provider {
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO(Location.EDISON, Specialty.PEDIATRICIAN);

    private final Location location;
    private final Specialty specialty;

    private Provider(Location location, Specialty specialty){
        this.location = location;
        this.specialty = specialty;
    }

    public Specialty getSpecialty(){
        return specialty;
    }
    /**
     * Return a textual representation of Provider.
     * @return return [name, location, specialty]
     */
    @Override
    public String toString(){
        return "[" + name() + ", " + location + ", " + specialty + "]";
    }
}
