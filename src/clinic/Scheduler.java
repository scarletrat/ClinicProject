package clinic;
import java.util.Scanner;


public class Scheduler {

    /**
     * Checks if the appointment date is valid.
     * @param appointment the appointment to be checked.
     * @return return a string of whether it's valid or why it's not valid.
     */
    public String isValidAppointmentDate(Appointment appointment){
        boolean validDate = appointment.getDate().isValid();
        if(!validDate){
            return ("Appointment Date: " + appointment.getDate() + " is not a valid calendar date.");
        }
        if(appointment.getDate().isPast() || appointment.getDate().isToday()){
            return ("Appointment Date: " + appointment.getDate() + " is today or a date before today.");
        }
        if(!appointment.getDate().isWeekDay()){
            return("Appointment Date: " + appointment.getDate() + " is Saturday or Sunday.");
        }
        if(!appointment.getDate().within6MonthFromToday()){
            return("Appointment Date: " + appointment.getDate() + " is not within six months.");
        }
        return "valid";
    }

    /**
     * Check if the date of birth is valid.
     * @param appointment the appointment to be checked.
     * @return return a string of whether it's valid or why it's not valid.
     */
    public String isValidDob(Appointment appointment){
        boolean validDate = appointment.getDate().isValid();
        if(!validDate){
            return ("Patient dob: " + appointment.getDate() + " is not a valid calendar date.");
        }
        if(appointment.getDate().isFuture() || appointment.getDate().isToday()){
            return("Patient dob: " + appointment.getDate() + " is today or a date after today.");
        }
        return "valid";
    }
    public String sCommand(String[] inputPart,List clinic){
        Appointment appointment = new Appointment(inputPart[1],inputPart[2],inputPart[3],inputPart[4],inputPart[5],inputPart[6]);
        String validAppointmentDate = isValidAppointmentDate(appointment);
        if(!validAppointmentDate.equalsIgnoreCase("valid")){
            return validAppointmentDate;
        }
        Timeslot timeslot = appointment.getTimeslot();
        if(timeslot == null){
            return(inputPart[2] + " is not a valid timeslot.");
        }
        String validDob = isValidDob(appointment);
        if(!validDob.equalsIgnoreCase("valid")){
            return validDob;
        }
        Provider provider = appointment.getProvider();
        if(provider == null){
            return(inputPart[6] + " - provider doesn't exist.");
        }
        //check if provider is not avaiable
        if(clinic.contains(appointment){
            return(appointment.getProfile() + " has an existing appointment at the same timeslot.");
        }
        if(!clinic.isProviderFree(appointment)){
            return(appointment.getProvider() + " is not available at slot " + inputPart[2]);
        }
        clinic.add(appointment);
        return(appointment.getDate() + " " + appointment.getTimeslot().toString()
                + " " + appointment.getProfile() + " " + appointment.getProvider().toString() + " booked.");
    }

    public boolean command(String input,List clinic) {
        String[] inputPart = input.split(",");
        String command = inputPart[0];
        switch (command) {
            case "S":
                //Create a schedule
                System.out.println(sCommand(inputPart,clinic));
                return true;
            case "C":
                System.out.println("Cancel appointment");
                return true;
            case "R":
                System.out.println("Reschedule appointment");
                return true;
            case "PA":
                System.out.println("Display appointment sorted by dates");
                return true;
            case "PP":
                System.out.println("Display appointment by patient");
                return true;
            case "PL":
                System.out.println("Display appointment sorted by location");
                return true;
            case "PS":
                System.out.println("Display billing info and move all appointments to medicalRecord");
                return true;
            case "Q":
                System.out.println("Scheduler Terminated.");
                return false;
            default:
                System.out.println("Invalid command!");
                return true;
        }
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scheduler is running.");
        String input;
        List clinic = new List();
        while (true) {
            input = scanner.nextLine();
            //If the user enters an empty line, continue the while loop
            if (input.isEmpty()) {
                continue;
            }
            //input command
            boolean terminate = command(input,clinic);
            if(!terminate){
                return;
            }
        }
    }
    }
