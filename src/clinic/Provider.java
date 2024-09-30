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

    public static Provider getProvider(String provider){
        if(provider.equalsIgnoreCase("PATEL")){
            return PATEL;
        } else if(provider.equalsIgnoreCase("LIM")){
            return LIM;
        } else if (provider.equalsIgnoreCase("ZIMNES")) {
            return ZIMNES;
        } else if (provider.equalsIgnoreCase("HARPER")) {
            return HARPER;
        } else if (provider.equalsIgnoreCase("KAUR")){
            return KAUR;
        } else if (provider.equalsIgnoreCase("TAYLOR")){
            return TAYLOR;
        }else if (provider.equalsIgnoreCase("RAMESH")){
            return RAMESH;
        }else if (provider.equalsIgnoreCase("CERAVOLO")){
            return CERAVOLO;
        }else{
            return null;
        }
    }

    public Specialty getSpecialty(){
        return specialty;
    }

    public Location getLocation(){
        return location;
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
