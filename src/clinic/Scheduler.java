package clinic;
import java.util.Scanner;


public class Scheduler {
    public void sCommand(String[] inputPart){

        Date appointmentDate = new Date(inputPart[1]);
        String timeSlot = inputPart[2];
        String firstName = inputPart[3];
        String lastName = inputPart[4];
        String dob = inputPart[5];
        String provider = inputPart[6];

    }
    public boolean command(String input) {
        String[] inputPart = input.split(",");
        String command = inputPart[0];
        switch (command) {
            case "S":
                //Create a schedule
                System.out.println("Create appointment");
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
            boolean terminate = command(input);
            if(!terminate){
                return;
            }
        }
    }
    }
