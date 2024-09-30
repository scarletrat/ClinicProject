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
        boolean validDate = appointment.getProfile().getDob_inDate().isValid();
        if(!validDate){
            return ("Patient dob: " + appointment.getProfile().getDob_inString() + " is not a valid calendar date.");
        }
        if(appointment.getProfile().getDob_inDate().isFuture() || appointment.getProfile().getDob_inDate().isToday()){
            return("Patient dob: " + appointment.getProfile().getDob_inString() + " is today or a date after today.");
        }
        return "valid";
    }

    /**
     * This method does the S command. Add the appointment to clinic.
     * @param inputPart the input of the command line.
     * @param clinic the clinic.
     * @return return a string representation of the command outcome.
     */
    public String sCommand(String[] inputPart,List clinic){
        Appointment appointment = new Appointment(inputPart[1],inputPart[2],inputPart[3],
                inputPart[4],inputPart[5],inputPart[6]);
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
        if(clinic.contains(appointment)){
            return(appointment.getProfile() + " has an existing appointment at the same time slot.");
        }
        Provider provider = appointment.getProvider();
        if(provider == null){
            return(inputPart[6] + " - provider doesn't exist.");
        }
        if(!clinic.isProviderFree(appointment)){
            return(appointment.getProvider() + " is not available at slot " + inputPart[2] + ".");
        }
        clinic.add(appointment);
        return(appointment + " booked.");
    }

    /**
     * This method does the C command. Cancel an existing appointment.
     * @param inputPart the input of the command line.
     * @param clinic the clinic.
     * @return return a string representation of whether the appointment have or haven't been cancelled.
     */
    public String cCommand(String[] inputPart, List clinic){
        Appointment appointment = new Appointment(inputPart[1],inputPart[2],inputPart[3],
                inputPart[4],inputPart[5],inputPart[6]);
        if(clinic.contains(appointment)){
            clinic.remove(appointment);
            return(appointment.getDate() + " " + appointment.getTimeslot().toString() + " "
                    + appointment.getProfile() + " has been canceled.");
        }else{
            return(appointment.getDate() + " " + appointment.getTimeslot().toString() + " "
                    + appointment.getProfile() + " does not exist.");
        }
    }

    /**
     * This method does the R command. Reschedule an existing appointment.
     * @param inputPart the input of the command line.
     * @param clinic the clinic.
     * @return return a string representation of the result.
     */
    public String rCommand(String[] inputPart, List clinic){
        Appointment appointment = new Appointment(inputPart[1],inputPart[2],inputPart[3],
                inputPart[4],inputPart[5],inputPart[6]);
        if(!clinic.contains(appointment)){
            return(appointment.getDate() + " " + appointment.getTimeslot().toString() + " "
                    + appointment.getProfile() + " does not exist.");
        }else{
            String newTime = inputPart[7];
            Timeslot newTimeSlot = Timeslot.getTime(newTime);
            if(newTimeSlot == null){
                return(newTime + " is not a valid timeslot.");
            }
            Appointment newAppointment = new Appointment(inputPart[1],newTime,inputPart[3],
                    inputPart[4],inputPart[5],inputPart[6]);
            if(!clinic.isProviderFree(appointment)){
                return(appointment.getProvider() + " is not available at slot " + inputPart[2] + ".");
            }
            clinic.remove(appointment);
            clinic.add(newAppointment);
        }
        return("Rescheduled to " + appointment);
    }

    public void PS_Command(List clinic, MedicalRecord medicalRecord){
        medicalRecord.add(clinic);
        int charge;
        Patient[] record = medicalRecord.getPatients();
        System.out.println(("** Billing statement ordered by patient **"));
        clinic.sortPatient();
        for (int i = 0;i<medicalRecord.getSize(); i++){
            Patient current = record[i];
            charge = current.charge();
            System.out.println("(" + i+1 +")" + current.getProfile() +" [amount due: $" + charge + ".00]");
        }
        System.out.println("** end of list **");
    }

    /**
     * This method does the command of the command line input.
     * @param input the input command line.
     * @param clinic the clinic.
     * @return return true if the command to keep running.
     * return false if terminated.
     */
    public boolean command(String input,List clinic, MedicalRecord medicalRecord) {
        String[] inputPart = input.split(",");
        String command = inputPart[0];
        switch (command) {
            case "S":
                //Create a schedule
                System.out.println(sCommand(inputPart,clinic));
                return true;
            case "C":
                System.out.println(cCommand(inputPart,clinic));
                return true;
            case "R":
                System.out.println(rCommand(inputPart,clinic));
                return true;
            case "PA":
                System.out.println(clinic.printByAppointment());
                return true;
            case "PP":
                System.out.println(clinic.printByPatient());
                return true;
            case "PL":
                System.out.println(clinic.printByLocation());
                return true;
            case "PS":
                PS_Command(clinic,medicalRecord);
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
        MedicalRecord medicalRecord = new MedicalRecord();
        while (true) {
            input = scanner.nextLine();
            //If the user enters an empty line, continue the while loop
            if (input.isEmpty()) {
                continue;
            }
            //input command
            boolean terminate = command(input,clinic,medicalRecord);
            if(!terminate){
                return;
            }
        }
    }
}
