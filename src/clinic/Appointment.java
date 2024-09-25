package clinic;

/**
 * This class represents appointments made by users.
 * It implements Java Interface Comparable.
 * @author Christopher Lee, modified Sept. 24, 2024
 */
public class Appointment implements Comparable<Appointment>{
    private Date date;
    private Timeslot timeslot;
    private Profile profile;
    private Provider provider;

    /**
     * default constructor
     * Creates new objects for date, timeslot, profile, and provider
     */
    public Appointment{
        this.date = new Date();
        this.timeslot = new Timeslot();
        this.profile = new Profile();
        this.provider = new Provider();
    }

    /**
     * Constructor with 4 parameters
     * @param date day appointment was scheduled for
     * @param timeslot timeslot appointment was scheduled for
     * @param profile personal info
     * @param provider provider's info
     */
    public Appointment (Date date, Timeslot timeslot, Profile profile, Provider provider){
        this.date = date;
        this.timeslot = timeslot;
        this.profile = profile;
        this.provider = provider;
    }

    /**
     *Checks to see if two appointments are equal
     * @param obj check to see obj is equal to this appointment
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Appointment){
            Appointment appointment = (Appointment) obj;
            return this.date == appointment.date
                    &&this.timeslot == appointment.timeslot;
        }
        return false;
    }

    /**
     * Compares two appointments
     * @param appointment appointment being compared to this appointment
     * @return 1 if this appointment date is greater, -1 if this appointment is lesser,
     * 0 if this appointment date is equal
     */
    @Override
    public int compareTo(Appointment appointment){
        if(this.date.compareTo(appointment.date) > 0){
            return 1;
        }
        else if(this.date.compareTo(appointment.date) < 0){
            return -1;
        }
        else{
            return 0;
        }
    }

    /**
     * Textual representation of appointments
     * @return String containing date, timeslot, profile, and provider
     */
    @Override
    public String toString(){
        return date.toString() + " " + timeslot.toString() + " " + profile.toString() + " " + provider.toString();
    }

    /**
     * gets date
     * @return date
     */
    public getDate(){
        return this.date;
    }

    /**
     * gets timeslot
     * @return timeslot
     */
    public getTimeslot(){
        return this.timeslot;
    }

    /**
     * gets profile
     * @return profile
     */
    public getProfile(){
        return this.profile;
    }

    /**
     * gets provider
     * @return provider
     */
    public getProvider(){
        return this.provider;
    }

    /**
     * updates this date with date parameter
     * @param date date to be updated tp
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * updates this timeslot with the timeslot parameter
     * @param timeslot timeslot to be updated to
     */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * updates this profile with the profile parameter
     * @param profile profile to be updated to
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * updates this provider with the provider parameter
     * @param provider provider to be updated to
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}

