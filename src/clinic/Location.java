package clinic;

/**
 * This class defines locations of the clinic with
 * the county name and zip code.
 * It is an enum class and so far have 6 locations offered.
 * @author Gordon Lin, modified 9/28/2024
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county;
    private final String zip;

    private Location(String county, String zip){
        this.county = county;
        this.zip = zip;
    }

    /**
     * Get the county of the location.
     * @return return the county.
     */
    public String getCounty(){
        return county;
    }

    /**
     * Get the zip code of the location.
     * @return return the zip code.
     */
    public String getZip(){
        return zip;
    }

    /**
     * Return a textual representation of the location.
     * @return return "name, county zip"
     */
    @Override
    public String toString(){
        return name() + ", " + county + " " + zip;
    }
}
