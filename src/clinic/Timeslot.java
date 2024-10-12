package clinic;

/**
 * This class defines the time slots of a day in the format HH:MM.
 * It is an enum class and have only 6 slots specified by the clinic.
 * @author Gordon Lin, modified 9/28/2024.
 */
public class Timeslot implements Comparable<Timeslot> {
    //public static final int NOON = 12;
    private int hour;
    private int minute;

    @Override
    public int compareTo(Timeslot o) {
        return 0;
    }

    /**
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
    /**
    public static Timeslot getTime(String time){
        if(time.equals("1")){
            return SLOT1;
        } else if(time.equals("2")){
            return SLOT2;
        } else if (time.equals("3")) {
            return SLOT3;
        } else if (time.equals("4")) {
            return SLOT4;
        } else if (time.equals("5")){
            return SLOT5;
        } else if (time.equals("6")){
            return SLOT6;
        }else{
            return null;
        }
    }

    /**
     * Get the hour of the timeslot.
     * @return return the hour of the timeslot.
     *
    public int getHour(){
        return hour;
    }

    /**
     * Get the minute of the timeslot.
     * @return return the minute of the timeslot.
     *
    public int getMinute(){
        return minute;
    }

    /**
     * Return a textual representation of the timeslot.
     * @return return a string containing the hour:minute.
     *
    @Override
    public String toString(){
        String time;
        int hour;
        if(this.hour>NOON){
            hour = this.hour -12;
            time = "PM";
        }else{
            hour = this.hour;
            time = "AM";
        }
        if(this.minute == 0){
            return(hour + ":00" + " " + time);
        }
        return(hour + ":" + this.minute + " " + time);
    }
*/
}
