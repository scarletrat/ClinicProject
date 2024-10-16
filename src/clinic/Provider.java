package clinic;

/**
 * This class includes the last name of the providers of the clinic
 * with the location and specialty of those providers.
 * It is an enum class and so far have 8 providers.
 * @author Gordon Lin,Christopher Lee modified 9/30/2024
 */
public abstract class Provider extends Person {
    private Location location;
    public abstract int rate();

    public Provider(String fname, String lname, String dob, Location location){
        super(fname,lname,dob);
        this.location = location;
    }
    public Provider(Profile profile,Location location){
        super(profile);
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }

    /**
     * Return a textual representation of Provider.
     * @return return [name, location, specialty]
     */
    @Override
    public String toString(){
        return "[" + super.getProfile() + ", " + location + "] ";

    }


}
