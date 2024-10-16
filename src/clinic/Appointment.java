package clinic;

import util.Date;

/**
 * This class represents an appointment object, containing the date of the appointment,
 * the user's profile, the user's provider, and the time of the appointment.
 * @author Christopher Lee, modified Sept. 24, 2024
 */
public class Appointment implements Comparable<Appointment>{
        protected Date date;
        protected Timeslot timeslot;
        protected Person patient;
        protected Person provider;

    /**
     * Default constructor creating new objects for date, timeslot, profile, and provider
     */
    public Appointment() {
        this.date = new Date();
        //this.timeslot = new Timeslot();
        this.patient = new Person();
        //this.provider = new Provider();
    }

    /**
     * Constructor setting instance variables to parameter objects
     * @param date date object
     * @param timeslot timeslot object
     * @param profile profile object
     * @param doc provider object
     */
    public Appointment(Date date, Timeslot timeslot, Person profile, Doctor doc){
        this.date = date;
        this.timeslot = timeslot;
        this.patient = profile;
        this.provider = doc;
    }

    public Appointment(String date, String timeslot, String firstName, String lastName, String dob, Doctor doc){
        this.date = new Date(date);
        this.timeslot = new Timeslot(timeslot);
        this.patient = new Patient(firstName, lastName, dob);
        this.provider = doc;
    }

    public Appointment(String date, String timeslot, String firstName, String lastName, String dob, Technician technician){
        this.date = new Date(date);
        this.timeslot = new Timeslot(timeslot);
        this.patient = new Patient(firstName, lastName, dob);
        this.provider = technician;
    }
    public Appointment(Date date, Timeslot timeslot, Person profile){
        this.date = date;
        this.timeslot = timeslot;
        this.patient = profile;
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
                    &&this.patient.equals(appointment.patient)
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
     * @return this appointment's textual representation
     */
    @Override
    public String toString(){
        return this.date + " " + this.timeslot + " " +
                this.patient + " " + this.provider;
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
    public Person getPatient(){
        return this.patient;
    }

    /**
     * Returns provider object
     * @return return provider object
     */
    public Person getProvider(){
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
     * @param patient profile to set appointment to
     */
    public void setPatient(Person patient) {
        this.patient = patient;
    }

    /**
     * Sets this appointments provider to parameter
     * @param provider provider to set appointment to
     */
    public void setProvider(Person provider) {
        this.provider = provider;
    }

}

