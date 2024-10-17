package clinic;
import util.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.FileNotFoundException;
import static util.Sort.provider;


/**
 * This class is the user interface of the clinic project.
 * The user inputs the commands to create, cancel, reschedule, and print appointments.
 * @author Gordon Lin, modified 9/30/2024.
 */
public class ClinicManager {
    final static int REQUIRED_INPUTS = 7;
    private List<Appointment> appointments;
    private List<Provider> providers;
    private List<Technician> technicians;
    private CircularLinkedList rotation;

    /**
     * Checks if the appointment date is valid.
     * @param date the appointment date to be checked.
     * @return return a string of whether it's valid or why it's not valid.
     */
    private String isValidAppointmentDate(Date date) {
        boolean validDate = date.isValid();
        if (!validDate) {
            return ("Appointment date: " + date + " is not a valid calendar date ");
        }
        if (date.isPast() || date.isToday()) {
            return ("Appointment date: " + date + " is today or a date before today.");
        }
        if (!date.isWeekDay()) {
            return ("Appointment date: " + date + " is Saturday or Sunday.");
        }
        if (!date.within6MonthFromToday()) {
            return ("Appointment date: " + date + " is not within six months.");
        }
        return "valid";
    }

    /**
     * Check if the date of birth is valid.
     * @param date the date of birth of patient to be checked.
     * @return return a string of whether it's valid or why it's not valid.
     */
    private String isValidDob(Date date) {
        boolean validDate = date.isValid();
        if (!validDate) {
            return ("Patient dob: " + date + " is not a valid calendar date ");
        }
        if (date.isFuture() || date.isToday()) {
            return ("Patient dob: " + date + " is today or a date after today.");
        }
        return "valid";
    }

    /**
     * Check if appointment with the same patient profile, date, and timeslot already exists.
     * @param profile the patient's profile.
     * @param date the appointment date.
     * @param timeslot the appointment timeslot.
     * @return return true if it doesn't exist
     * return false if the appointment already exist.
     */
    private boolean isValidAppointment(Profile profile, Date date, Timeslot timeslot) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getPatient().getProfile().equals(profile) && appointment.getDate().equals(date) && appointment.getTimeslot().equals(timeslot)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the String can be numeric.
     * @param npi the input String.
     * @return return true if it can be numeric,
     * return false otherwise.
     */
    private boolean isNumeric(String npi) {
        if (npi == null || npi.isEmpty()) {
            return false; // Return false for null or empty strings
        }

        try {
            Integer.parseInt(npi);
            return true;              // If successful, return true
        } catch (NumberFormatException e) {
            return false;             // If parsing fails, return false
        }
    }

    /**
     * Check if the npi is valid from the providers list.
     * @param npi the given npi.
     * @return return true if it's valid,
     * return false otherwise.
     */
    private boolean isValidNpi(String npi){
        for(int i = 0; i<providers.size(); i++){
            if(providers.get(i) instanceof Doctor){
                if(((Doctor) providers.get(i)).getnpi().equals(npi)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Find the doctor given their npi.
     * @param npi the given npi.
     * @return return the Doctor with the given npi.
     */
    private Doctor findDoctor(String npi){
        for(int i = 0; i<providers.size(); i++){
            if(providers.get(i) instanceof Doctor){
                if(((Doctor) providers.get(i)).getnpi().equals(npi)){
                    return (Doctor)providers.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Check if the doctor is free during an appointment time.
     * @param date the appointment date.
     * @param timeslot the appointment timeslot.
     * @param doc the doctor.
     * @return return true if the doctor is free at that time,
     * return false otherwise.
     */
    private boolean isDoctorFree(Date date, Timeslot timeslot,Doctor doc){
        for(int i = 0; i<appointments.size();i++){
            Appointment appointment = appointments.get(i);
            if (appointment.getDate().equals(date) && appointment.getTimeslot().equals(timeslot) && appointment.getProvider().equals(doc)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method does the D command. Schedule an appointment with a Doctor.
     * @param inputPart the input of the command line.
     * @return return a string representation of whether the appointment have been scheduled.
     */
    private String dCommand(String[] inputPart){
        if(inputPart.length<REQUIRED_INPUTS){
            return ("Missing data tokens.");
        }

        if(!isValidAppointmentDate(new Date(inputPart[1])).equalsIgnoreCase("valid")){
            return isValidAppointmentDate(new Date(inputPart[1]));
        }
        Timeslot timeslot = new Timeslot(inputPart[2]);
        if(timeslot.getMinute() == 0 && timeslot.getHour() ==0){
            return(inputPart[2] + " is not a valid time slot.");
        }
        if(!isValidDob(new Date(inputPart[5])).equalsIgnoreCase("valid")){
            return isValidDob(new Date(inputPart[5]));
        }
        String npi = inputPart[6];
        if(!isNumeric(npi) || !isValidNpi(npi)){
            return npi + (" - provider doesn't exist.");
        }
        Doctor doc = findDoctor(npi);
        Appointment appointment = new Appointment(inputPart[1],inputPart[2],inputPart[3],
                inputPart[4],inputPart[5],doc);
        if(appointments.contains(appointment)){
            return appointment.getPatient().getProfile() + (" has an existing appointment at the same time slot.");
        }
        if(isDoctorFree(appointment.getDate(),appointment.getTimeslot(),doc)){
            appointments.add(appointment);
            return( appointment + " booked.");
        }
        return doc + (" is not available at slot " + inputPart[2]+ ".");
    }

    public Boolean serviceExists(String service){
        if(service.equalsIgnoreCase("xray")
                ||service.equalsIgnoreCase("catscan")
                ||service.equalsIgnoreCase("ultrasound")) {
            return true;
        }
            return false;
    }
    private int isServiceAvailable(Timeslot timeslot, Date date, Radiology service){
        int count = 0;
        for (int i = 0; i < appointments.size(); i++){
            if(appointments.get(i) instanceof Imaging){
                if(appointments.get(i).getDate().equals(date)
                        &&appointments.get(i).getTimeslot().equals(timeslot)
                        &&service.getService().equals(((Imaging)appointments.get(i)).getRadiology().getService())){
                    count++;
                }
            }
        }
        return (count);
    }
    private Location findLocation(Date date, Timeslot timeslot, Radiology service) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getDate().equals(date) && appointment.getTimeslot().equals(timeslot)) {
                if(appointment instanceof Imaging){
                    Provider provider = (Provider) appointment.getProvider();
                    return provider.getLocation();
                }
            }
        }
        return null;
    }

    private boolean isTechnicianFree(Date date, Timeslot timeslot, Technician technician)  {
        for(int i = 0; i < appointments.size(); i++){
            if(appointments.get(i).getDate().equals(date)&&appointments.get(i).getDate().equals(timeslot)
            &&appointments.get(i).getProvider().equals(technician)){
                return false;
            }
        }
        return true;
    }
    private Technician findTechnician(Location location, Date date, Timeslot timeslot){
        for(int i = 0; i < rotation.getSize(); i++){
            if(!rotation.getLast().getNext().getData().getLocation().equals(location)
                    &&isTechnicianFree(date, timeslot, rotation.getLast().getNext().getData())){
                rotation.setLast(rotation.getLast().getNext());
                return rotation.getLast().getData();
            }
            rotation.shiftByOne();
        }
        return null;
    }
    private Technician findTechnician(Date date, Timeslot timeslot){
        for(int i = 0; i < rotation.getSize(); i++){
            if(isTechnicianFree(date, timeslot, rotation.getLast().getNext().getData())){
                rotation.setLast(rotation.getLast().getNext());
                return rotation.getLast().getData();
            }
            rotation.shiftByOne();
        }
        return null;
    }

    public String tCommand(String[] inputPart){
        Date date = new Date(inputPart[1]);
        if(inputPart.length<REQUIRED_INPUTS){
            return "Missing data tokens.";
        }
        if(!isValidAppointmentDate(date).equalsIgnoreCase("valid")){
            return isValidAppointmentDate(date);
        }
        Timeslot time = new Timeslot(inputPart[2]);
        if(time.getHour()==0&&time.getMinute()==0){
            return inputPart[2] + " is not a valid time slot.";
        }
        Date dob = new Date(inputPart[5]);
        if (!isValidDob(dob).equals("valid")) {
            return isValidDob(dob);
        }

        Person patient = new Person(inputPart[3],inputPart[4],inputPart[5]);
        if(!isValidAppointment(patient.getProfile(),date,time)){
            return patient.getProfile() + (" has an existing appointment at the same time slot.");
        }
        if(!serviceExists(inputPart[6])){
            return inputPart[6] + " - imaging service not provided.";
        }
        Radiology radiology = new Radiology(inputPart[6]);
        int currentServices = isServiceAvailable(time, date, radiology);
        if(currentServices>=2) {
            return "Cannot find an available technician at all locations for "
                    + radiology.getService() + " at slot " + inputPart[2];
        }
        Technician technician = null;
        if(currentServices == 1) {
            technician = findTechnician(findLocation(date, time, radiology), date, time);
        }
        if(currentServices == 0) {
            technician = findTechnician(date, time);
        }
        Appointment imaging = new Imaging(date, time, patient, inputPart[6], technician);
        appointments.add(imaging);
        return imaging.toString() + "["+((Imaging) imaging).getRadiology().getService()+"]" + " booked.";
    }

    /**
     * Remove the appointment from the list.
     * @param date the date of the appointment.
     * @param timeslot the timeslot of the appointment,
     * @param profile the patient's profile.
     */
    private void removeAppointment(Date date, Timeslot timeslot, Profile profile){
        for(int i =0; i< appointments.size(); i++){
            Appointment appointment = appointments.get(i);
            if(appointment.getDate().equals(date) && appointment.getTimeslot().equals(timeslot) && appointment.getPatient().getProfile().equals((profile))){
                appointments.remove(appointment);
            }
        }
    }

    /**
     * This method does the C command. Cancel an existing appointment.
     * @param inputPart the input of the command line.
     * @return return a string representation of whether the appointment have or haven't been cancelled.
     */
    private String cCommand(String[] inputPart){
        if(inputPart.length<REQUIRED_INPUTS-1) return "Missing data tokens.";
        Date date = new Date(inputPart[1]);
        Timeslot timeslot = new Timeslot(inputPart[2]);
        Profile profile = new Profile(inputPart[3],inputPart[4],inputPart[5]);
        if(isValidAppointment(profile,date,timeslot)){
            return date +" " + timeslot + " " + profile + " - appointment does not exist.";
        }
        removeAppointment(date,timeslot,profile);
        return date +" " + timeslot + " " + profile + " - appointment has been cancelled.";
    }

    /**
     * Find if the patient has the appointment at the time specified.
     * @param date the appointment date.
     * @param timeslot the appointment timeslot.
     * @param profile the patient's profile.
     * @return return true if the patient has the appointment at that time,
     * return false otherwise.
     */
    private boolean hasAppointmentAtTimeslot(Date date, Timeslot timeslot,Profile profile){
        for(int i =0; i< appointments.size(); i++){
            Appointment appointment = appointments.get(i);
            if(appointment.getTimeslot().equals(timeslot) && appointment.getPatient().getProfile().equals(profile) && appointment.getDate().equals(date)){
                return true;
            }
        }
        return false;
    }

    /**
     * Find the Doctor given the patient profile, appointment date and timeslot.
     * @param profile the patient's profile.
     * @param date the date of the appointment.
     * @param timeslot the timeslot of the appointment.
     * @return return the doctor at the appointment with the same timeslot, date, and patient.
     */
    private Doctor findDoctor(Profile profile, Date date, Timeslot timeslot) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getPatient().getProfile().equals(profile) && appointment.getDate().equals(date) && appointment.getTimeslot().equals(timeslot)) {
                if(appointment.getProvider() instanceof Doctor) return (Doctor) appointment.getProvider();
            }
        }
        return null;
    }

    /**
     * This method does the R command. Reschedule an existing appointment.
     * @param inputPart the input of the command line.
     * @return return a string representation of the result.
     */
    private String rCommand(String[] inputPart){
        if(inputPart.length<REQUIRED_INPUTS) return "Missing data tokens.";
        Date date = new Date(inputPart[1]);
        Timeslot timeslot = new Timeslot(inputPart[2]);
        Person patient = new Person(inputPart[3],inputPart[4],inputPart[5]);
        if(isValidAppointment(patient.getProfile(),date,timeslot)){
            return date +" " + timeslot + " " + patient + " does not exist.";
        }
        Timeslot newTimeslot = new Timeslot(inputPart[6]);
        if(newTimeslot.getMinute() == 0 && newTimeslot.getHour() ==0){
            return(inputPart[6] + " is not a valid time slot.");
        }
        //does they have the appointment at the newTimeslot?
        if(hasAppointmentAtTimeslot(date,newTimeslot,patient.getProfile())){
            return patient + " has an existing appointment at " + date + " " + newTimeslot;
        }
        //is the provider free at that timeslot?
        Doctor doc = findDoctor(patient.getProfile(),date,timeslot);
        if(isDoctorFree(date, newTimeslot, doc)){
            Appointment old = new Appointment(date,timeslot,patient,doc);
            appointments.remove(old);
            Appointment newApp = new Appointment(date, newTimeslot, patient,doc);
            appointments.add(newApp);
            return "Rescheduled to " + date + " " + newTimeslot + " " + doc;
        }
        return doc + " is not available at slot " + inputPart[6] +"." ;

    }

    /**
     * This method does the PA command. Print the list of appointments;
     * ordered by date/time/provider.
     */
    private void PA_Command() {
        if (!appointments.isEmpty()) {
            Sort.appointment(appointments, 'A');
            System.out.println("\n** List of appointments, ordered by date/time/provider.");
            for (int i = 0; i < appointments.size(); i++) {
                System.out.println(appointments.get(i).toString());
            }
            System.out.println("** end of list **");
        }else{
            System.out.println("Schedule calendar is empty.");
        }
    }

    /**
     * This method does the PP command. Print the list of appointments;
     * ordered by patient/date/time.
     */
    private void PP_Command() {
        if (!appointments.isEmpty()) {
            Sort.appointment(appointments, 'P');
            System.out.println("\n** List of appointments, ordered by patient/date/time.");
            for (int i = 0; i < appointments.size(); i++) {
                System.out.println(appointments.get(i).toString());
            }
            System.out.println("** end of list **");
        }else{
            System.out.println("Schedule calendar is empty.");
        }
    }

    /**
     * This method does the PL command. Print the list of appointments;
     * ordered by patient/date/time.
     */
    private void PL_Command() {
        if (!appointments.isEmpty()) {
            Sort.appointment(appointments, 'L');
            System.out.println("\n** List of appointments, ordered by county/date/time.");
            for (int i = 0; i < appointments.size(); i++) {
                System.out.println(appointments.get(i).toString());
            }
            System.out.println("** end of list **");
        }else{
            System.out.println("Schedule calendar is empty.");
        }
    }

    /**
     * This print out the bill of each patient.
     * @param patients the patients list.
     */
    private void printChargePerPatient(List<Patient> patients){
        int[] charge = new int[patients.size()];
        for(int i =0; i< patients.size(); i++){
            Patient current = patients.get(i);
            charge[i] = current.charge();
        }
        for (int i = 0;i<charge.length; i++){
            DecimalFormat format = new DecimalFormat("#,###.00");
            String formatCharge = format.format(charge[i]);
            System.out.println("(" + (i+1) +") " + patients.get(i).getProfile() +" [amount due: $" + formatCharge + "]");
        }
    }

    /**
     * This method does the PS command. Print the bill of the patients.
     */
    private void PS_Command(){
        if(appointments.isEmpty()) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        Sort.appointment(appointments,'P');
        List<Patient> patients = new List<>();
        for(int i =0; i< appointments.size(); i++){
            Appointment appointment = appointments.get(i);
            Patient patient = new Patient(appointment.getPatient().getProfile(), appointment);
            if(patients.isEmpty()){
                patients.add(patient);
            }else{
                if(patients.contains(patient)){
                    int index = patients.indexOf(patient);
                    patients.get(index).addVisit(appointment);
                }else{
                    patients.add(patient);
                }
            }
        }
        System.out.println(("** Billing statement ordered by patient **"));
        printChargePerPatient(patients);
        System.out.println("** end of list **");
        appointments = new List<>();
    }

    /**
     * This method does the PO command. Print the list of Office type appointments;
     * ordered by county/date/time.
     */
    private void PO_Command(){
       if (!appointments.isEmpty()) {
           Sort.appointment(appointments, 'O');
           System.out.println("\n** List of office appointments ordered by county/date/time.");
           for (int i = 0; i < appointments.size(); i++) {
               if(appointments.get(i) instanceof Imaging) break;
               System.out.println(appointments.get(i).toString());
           }
           System.out.println("** end of list **");
       }else{
           System.out.println("Schedule calendar is empty.");
       }
    }

    /**
     * This method does the PI command. Print the list of imaging appointments;
     * ordered by county/date/time.
     */
    private void PI_Command(){
        if (!appointments.isEmpty()) {
            Sort.appointment(appointments, 'I');
            System.out.println("\n** List of radiology appointments ordered by county/date/time.");
            for (int i = 0; i < appointments.size(); i++) {
                if(appointments.get(i) instanceof Imaging) {
                    System.out.println(appointments.get(i).toString());
                }
            }
            System.out.println("** end of list **");
        }else{
            System.out.println("Schedule calendar is empty.");
        }
    }

    private int[] calc(){
        Profile[] pro = new Profile[providers.size()];
        for(int i = 0; i< providers.size(); i++){
            pro[i] = providers.get(i).getProfile();
        }
        int[] money = new int[pro.length];
        for(int i = 0; i< pro.length; i++){
            Profile temp = pro[i];
            Provider temp1 = (Provider) providers.get(i);
            for(int j= 0; j< appointments.size(); j++){
                if(appointments.get(j).getProvider().getProfile().equals(temp)){
                    money[i] = money[i] +  temp1.rate();
                }
            }
        }
        return money;
    }
    /**
     * This method does the PC command. Print the list of expected credit amounts;
     * for providers for seeing patients, sorted by provider profile.
     */
    private void PC_Command(){
        if(appointments.isEmpty()){
            System.out.println("Schedule calendar is empty.");
            return;
        }
        int charge[] = calc();

        System.out.println("\n** Credit amount ordered by provider. **");
        for(int i = 0; i< providers.size(); i++){
            DecimalFormat format = new DecimalFormat("#,###.00");
            String formatCharge = format.format(charge[i]);
            System.out.println("(" + (i+1) +") " + providers.get(i).getProfile() +" [amount due: $" + formatCharge + "]");
        }
        System.out.println("** end of list **");
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
                System.out.println(cCommand(inputPart));
                return true;
            case "R":
                System.out.println(rCommand(inputPart));
                return true;
            case "PA":
                PA_Command();
                return true;
            case "PP":
                PP_Command();
                return true;
            case "PL":
                PL_Command();
                return true;
            case "PS":
                PS_Command();
                return true;
            case "PO":
                PO_Command();
                return true;
            case "PI":
                PI_Command();
                return true;
            case "PC":
                PC_Command();
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
            System.out.println("Providers loaded to the list.");
            fileRead.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public void displayProviderList(){
        provider(providers);
        for (int i = 0; i<providers.size(); i++){
            System.out.println(providers.get(i).toString());
        }
        System.out.println();
    }

    /**
     * This method creates a circular linked list in order by location and prints out the rotation list.
     */
    public void createRotation(){
        rotation = new CircularLinkedList();
        int size = technicians.size();
        for(int i = size-1; i >= 1; i--){
            Node node = new Node(technicians.get(i));
            rotation.insert(node);
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
        appointments = new List<>();
        loadProviderList();
        displayProviderList();
        System.out.println("Rotation list for the technicians.");
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
            //input command
            boolean terminate = command(input);
            if(!terminate){
                return;
            }
        }
    }

}
