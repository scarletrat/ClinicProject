package clinic;
import util.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import static util.Sort.provider;


/**
 * This class is the user interface of the clinic project.
 * The user inputs the commands to create, cancel, reschedule, and print appointments.
 * @author Gordon Lin, modified 9/30/2024.
 */
public class ClinicManager {
    private List<Appointment> appointments;
    private List<Provider> providers;
    private List<Technician> technicians;

    /**
     * Checks if the appointment date is valid.
     * @param date the appointment date to be checked.
     * @return return a string of whether it's valid or why it's not valid.
     */
    public String isValidAppointmentDate(Date date){
        boolean validDate = date.isValid();
        if(!validDate){
            return ("Appointment date: " + date + " is not a valid calendar date");
        }
        if(date.isPast() || date.isToday()){
            return ("Appointment date: " + date + " is today or a date before today.");
        }
        if(!date.isWeekDay()){
            return("Appointment date: " + date + " is Saturday or Sunday.");
        }
        if(!date.within6MonthFromToday()){
            return("Appointment date: " + date + " is not within six months.");
        }
        return "valid";
    }

    /**
     * Check if the date of birth is valid.
     * @param appointment the appointment to be checked.
     * @return return a string of whether it's valid or why it's not valid.
     */
    public String isValidDob(Appointment appointment){
        boolean validDate = appointment.getPatient().getProfile().getDob_inDate().isValid();
        if(!validDate){
            return ("Patient dob: " + appointment.getPatient().getProfile().getDob_inString() + " is not a valid calendar date");
        }
        if(appointment.getPatient().getProfile().getDob_inDate().isFuture() || appointment.getPatient().getProfile().getDob_inDate().isToday()){
            return("Patient dob: " + appointment.getPatient().getProfile().getDob_inString() + " is today or a date after today.");
        }
        return "valid";
    }

    public String dCommand(String[] inputPart){
        Date date = new Date(inputPart[1]);
        String validAppointmentDate = isValidAppointmentDate(date);
        if(!validAppointmentDate.equalsIgnoreCase("valid")){
            return validAppointmentDate;
        }
        Timeslot timeslot = new Timeslot(inputPart[2]);
        if(timeslot.getMinute() == 0 && timeslot.getHour() ==0){
            return(inputPart[2] + " is not a valid time slot.");
        }
        Person patient = new Person(inputPart[3],inputPart[4],inputPart[5]);
        return null;
    }

    public String tCommand(String[] inputPart){
        return null;
    }

    /**
     * This method does the C command. Cancel an existing appointment.
     * @param inputPart the input of the command line.
     * @param clinic the clinic.
     * @return return a string representation of whether the appointment have or haven't been cancelled.
     */
  /**  public String cCommand(String[] inputPart, List clinic){
        Date date = new Date(inputPart[1]);
        Timeslot tempSlot = new Timeslot(inputPart[2]);
        Profile profile = new Profile(inputPart[3],inputPart[4],inputPart[5]);
        Appointment target = new Appointment()
        Appointment appointment = appointments.find(date,tempSlot,profile);
        if(appointment ==null){
            return(date + " " + tempSlot+ " "
                    + profile + " does not exist.");
        }
        appointments.remove(appointment);
        return(appointment.getDate() + " " + appointment.getTimeslot().toString() + " "
                + appointment.getProfile() + " has been canceled.");
    }

    /**
     * This method does the R command. Reschedule an existing appointment.
     * @param inputPart the input of the command line.
     * @param clinic the clinic.
     * @return return a string representation of the result.
     */
  /**  public String rCommand(String[] inputPart, List clinic){
        Date date = new Date(inputPart[1]);
        Timeslot tempSlot = Timeslot.getTime(inputPart[2]);
        Profile profile = new Profile(inputPart[3],inputPart[4],inputPart[5]);
        Appointment appointment = clinic.getAppointment(date,tempSlot,profile);
        Timeslot newTimeSlot = Timeslot.getTime(inputPart[6]);
        if(appointment ==null){
            return(date + " " + tempSlot+ " "
                    + profile + " does not exist.");
        }
        if(newTimeSlot == null){
            return(inputPart[6] + " is not a valid time slot.");
        }
        String provider = appointment.getProvider().name();
        Appointment newAppointment = new Appointment(inputPart[1],inputPart[6],inputPart[3],
                inputPart[4],inputPart[5],provider);
        if(!clinic.isProviderFree(newAppointment)){
            return(appointment.getProvider() + " is not available at slot " + inputPart[6] + ".");
        }
        clinic.remove(appointment);
        clinic.add(newAppointment);
        return("Rescheduled to " + newAppointment);
    }

    /**
     * This method does the PS command. Print the bill of the patients.
     * @param clinic the clinic.
     * @param medicalRecord the medicalRecord/bill.
     */
  /**  public void PS_Command(List clinic, MedicalRecord medicalRecord){
        clinic.sortPatient();
        medicalRecord.add(clinic);
        int charge;
        Patient[] record = medicalRecord.getPatients();
        System.out.println(("** Billing statement ordered by patient **"));
        clinic.sortPatient();
        for (int i = 0;i<medicalRecord.getSize(); i++){
            Patient current = record[i];
            charge = current.charge();
            DecimalFormat format = new DecimalFormat("#,###.00");
            String formatCharge = format.format(charge);
            System.out.println("(" + (i+1) +") " + current.getProfile() +" [amount due: $" + formatCharge + "]");
        }
        System.out.println("** end of list **");
        clinic.removeAll();
    }

    /**
     * This method does the command of the command line input.
     * @param input the input command line.
     * @return return true if the command to keep running.
     * return false if terminated.
     */
   public boolean command(String input) {
        String[] inputPart = input.split(",");
        String command = inputPart[0];
        switch (command) {
            case "D":
                //Create a schedule
                System.out.println(dCommand(inputPart));
                return true;
            case "T":
                System.out.println(tCommand(inputPart));
                return true;
            case "C":
                //System.out.println(rCommand(inputPart));
                return true;
            case "R":
                //clinic.printByAppointment();
                return true;
            case "PA":
                //clinic.printByPatient();
                return true;
            case "PP":
                //clinic.printByLocation();
                return true;
            case "PL":
                //PS_Command(clinic,medicalRecord);
                return true;
            case "PS":
                return true;
            case "PO":
                return true;
            case "PI":
                return true;
            case "PC":
                return true;
            case "Q":
                System.out.println("Scheduler is terminated.");
                return false;
            default:
                System.out.println("Invalid command!");
                return true;
        }
    }


    public void loadProviderList(){
        try {
            File file = new File("src/clinic/providers.txt");
            Scanner fileRead = new Scanner(file);
            while (fileRead.hasNextLine()) {
                String provider = fileRead.nextLine();
                String[] providerData = provider.split("\\s+");
                Provider temp;
                if(providerData[0].equals("D")){
                    Profile profile = new Profile(providerData[1], providerData[2], providerData[3]);
                    Location location = Location.getLocation(providerData[4]);
                    Specialty specialty = Specialty.getSpecialty(providerData[5]);
                    temp = new Doctor(profile, location, specialty, providerData[6]);
                }
                else{
                    Profile profile = new Profile(providerData[1], providerData[2], providerData[3]);
                    Location location = Location.getLocation(providerData[4]);
                    int rate = Integer.parseInt(providerData[5]);
                    temp = new Technician(profile, location, rate);
                    technicians.add((Technician) temp);
                }
                providers.add(temp);
                provider(providers);
            }
            fileRead.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public void displayProviderList(){
        Sort.provider(providers);
        for (int i = 0; i<providers.size(); i++){
            System.out.println(providers.get(i).toString());
        }
        System.out.println();
    }

    /**
     * This method creates a circular linked list in order by location and prints out the rotation list.
     */
    public void createRotation(){
        CircularLinkedList rotation = new CircularLinkedList();
        int size = technicians.size();
        for(int i = size-1; i >= 1; i--){
            rotation.insert(technicians.get(i));
            System.out.print(technicians.get(i).getProfile().getFname() + " " +
                    technicians.get(i).getProfile().getLname() + " (" +
                    technicians.get(i).getLocation().getName() + ") --> ");
        }
        System.out.print(technicians.get(0).getProfile().getFname() + " " +
                        technicians.get(0).getProfile().getLname() + " (" +
                        technicians.get(0).getLocation().getName() + ")");
        System.out.println();
    }

    /**
     * This method runs the user interface.
     */
    public void run() {
        providers = new List<>();
        technicians = new List<>();
        loadProviderList();
        displayProviderList();
        System.out.println("Rotation list for technicians.");
        createRotation();
        System.out.println("Clinic Manager is running...");
        Scanner commandLine = new Scanner(System.in);
        String input;
        while (true) {
            input = commandLine.nextLine();
            //If the user enters an empty line, continue the while loop
            if (input.isEmpty()) {
                continue;
            }
            //input commandm
            boolean terminate = command(input);
            if(!terminate){
                return;
            }
        }
    }

}
