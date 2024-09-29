package clinic;

/**
 * This class defines the time slots of a day in the format HH:MM.
 * It is an enum class and have only 6 slots specified by the clinic.
 * @author Gordon Lin, modified 9/27/2024.
 */
public enum Timeslot {
    SLOT1 (9, 0),
    SLOT2 (10, 45),
    SLOT3 (11, 15),
    SLOT4 (13, 30),
    SLOT5 (15, 0),
    SLOT6 (16, 15);

    public static final int NOON = 12;
    private final int hour;
    private final int minute;

    private Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Get the timeslot given the input, 1-6
     * @param time the number 1 to 6
     * @return the timeslot that corresponds with each number;
     * return null if it's not a valid timeslot.
     */
    public static Timeslot getTime(String time){
        int timeSlot = Integer.parseInt(time);
        if(timeSlot == 1){
            return SLOT1;
        } else if(timeSlot == 2){
            return SLOT2;
        } else if (timeSlot == 3) {
            return SLOT3;
        } else if (timeSlot == 4) {
            return SLOT4;
        } else if (timeSlot == 5){
            return SLOT5;
        } else if (timeSlot == 6){
            return SLOT6;
        }else{
            return null;
        }
    }

    /**
     * Get the hour of the timeslot.
     * @return return the hour of the timeslot.
     */
    public int getHour(){
        return hour;
    }

    /**
     * Get the minute of the timeslot.
     * @return return the minute of the timeslot.
     */
    public int getMinute(){
        return minute;
    }

    /**
     * Return a textual representation of the timeslot.
     * @return return a string containing the hour:minute.
     */
    @Override
    public String toString(){
        String time;
        if(hour>NOON){
            time = "PM";
        }else{
            time = "AM";
        }
        return(this.hour + ":" + this.minute + " " + time);
    }

}
