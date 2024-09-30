package clinic;

/**
 * @author Christopher Lee, modified Sept. 24, 2024
 */
public class Appointment implements Comparable<Appointment>{
        private Date date;
        private Timeslot timeslot;
        private Profile profile;
        private Provider provider;

    /**
     * default constructor creating new objects for date, timeslot, profile, and provider
     */
    public Appointment() {
        this.date = new Date();
        //this.timeslot = new Timeslot();
        this.profile = new Profile();
        //this.provider = new Provider();
    }

    /**
     * Constructor setting instance variables to parameter objects
     * @param date date object
     * @param timeslot timeslot object
     * @param profile profile object
     * @param provider provider object
     */
    public Appointment(Date date, Timeslot timeslot, Profile profile, Provider provider){
        this.date = date;
        this.timeslot = timeslot;
        this.profile = profile;
        this.provider = provider;
    }

    public Appointment(String date, String timeslot, String patient){

    }
    /**
     * Compares obj to this appointment. returns true if equal, false otherwise.
     * @param obj object to be compared to
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Appointment){
            Appointment appointment = (Appointment) obj;
            return this.date == appointment.date
                    &&this.timeslot == appointment.timeslot
                    &&this.profile == appointment.profile
                    &&this.provider == appointment.provider;
        }
        return false;
    }

    /**
     * Compares this appointment to another appointment object.
     * @param appointment object to be compared to
     * @return return 1 ff this appointment is after input "appointment;
     * return -1 if this appointment is before input "appointment";
     * return 0 if equal.
     */
    @Override
    public int compareTo(Appointment appointment){
        if(this.date.compareTo(appointment.date)>0){
            return 1;
        }
        else if(this.date.compareTo(appointment.date)<0){
          return -1;
        }
        else{
            return 0;
        }
    }

    /**
     * creates a textual representation of the appointment object
     * @return this appointments textual representation
     */
    public String toString(){
        return this.date + " " + this.timeslot + " " +
                this.profile + " " + this.provider;
    }

    /**
     * returns date object
     * @return date object
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * returns timeslot object
     * @return timeslot object
     */
    public Timeslot getTimeslot(){
        return this.timeslot;
    }

    /**
     * returns profile object
     * @return profile object
     */
    public Profile getProfile(){
        return this.profile;
    }

    /**
     * returns provider object
     * @return provider object
     */
    public Provider getProvider(){
        return this.provider;
    }

    /**
     * sets this appointment's date to parameter
     * @param date date to set appointment to
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * sets this appointment's timeslot to parameter
     * @param timeslot timeslot to set appointment to
     */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * sets this appointment's profile to parameter
     * @param profile profile to set appointment to
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * sets this appointments provider to parameter
     * @param provider provider to set appointment to
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
