package clinic;

import util.Date;

/**
 * This class represents an appointment object, containing the date of the appointment,
 * the user's profile, the user's provider, and the time of the appointment.
 * @author Christopher Lee, modified Sept. 24, 2024
 */
public class Appointment implements Comparable<Appointment>{
        private Date date;
        private Timeslot timeslot;
        private Profile profile;
        private Provider provider;

    /**
     * Default constructor creating new objects for date, timeslot, profile, and provider
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

    public Appointment(String date, String timeslot, String firstName, String lastName, String dob, String provider){
        this.date = new Date(date);
        this.timeslot = Timeslot.getTime(timeslot);
        this.profile = new Profile(firstName, lastName, dob);
        this.provider = Provider.getProvider(provider);
    }
    /**
     * Compares obj to this appointment. returns true if equal, false otherwise.
     * @param obj object to be compared to
     * @return return true if the appointment is equal;
     * return false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Appointment){
            Appointment appointment = (Appointment) obj;
            return this.date.equals(appointment.date)
                    &&this.timeslot == appointment.timeslot
                    &&this.profile.equals(appointment.profile)
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
            if(this.timeslot.compareTo(appointment.timeslot)>0){
                return 1;
            }
            else if(this.timeslot.compareTo(appointment.timeslot)<0){
                return -1;
            }
            else return 0;
        }
    }

    /**
     * Creates a textual representation of the appointment object
     * @return this appointments textual representation
     */
    public String toString(){
        return this.date + " " + this.timeslot + " " +
                this.profile + " " + this.provider;
    }

    /**
     * Returns date object
     * @return return date object
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * Returns timeslot object
     * @return return timeslot object
     */
    public Timeslot getTimeslot(){
        return this.timeslot;
    }

    /**
     * Returns profile object
     * @return return profile object
     */
    public Profile getProfile(){
        return this.profile;
    }

    /**
     * Returns provider object
     * @return return provider object
     */
    public Provider getProvider(){
        return this.provider;
    }

    /**
     * Sets this appointment's date to parameter
     * @param date date to set appointment to
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets this appointment's timeslot to parameter
     * @param timeslot timeslot to set appointment to
     */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Sets this appointment's profile to parameter
     * @param profile profile to set appointment to
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Sets this appointments provider to parameter
     * @param provider provider to set appointment to
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}

