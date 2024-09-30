package clinic;

/**
 * This class includes the last name of the providers of the clinic
 * with the location and specialty of those providers.
 * It is an enum class and so far have 8 providers.
 * @author Gordon Lin,Christopher Lee modified 9/30/2024
 */
public enum Provider {
    CERAVOLO(Location.EDISON, Specialty.PEDIATRICIAN),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY);


    private final Location location;
    private final Specialty specialty;

    private Provider(Location location, Specialty specialty){
        this.location = location;
        this.specialty = specialty;
    }

    /**
     * Get the provider given the input string.
     * @param provider the string input.
     * @return return provider enum given the string input.
     * return null if it doesn't match any providers listed.
     */
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

    /**
     * Get the Specialty enum.
     * @return return the Specialty enum.
     */
    public Specialty getSpecialty(){
        return specialty;
    }

    /**
     * Get the Location enum.
     * @return return the Location enum.
     */
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
