package clinic;
import java.util.Calendar;

/**
 * This class models the date of the real world.
 * It will account for leap year. It implements Java Interface Comparable.
 * And uses the java Calendar class to get today's date
 * @author Gordon Lin, modified Sept. 23, 2024
 */
public class Date implements Comparable<Date> {
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    private int year;
    private int month;
    private int day;

    /**
     * No-argument constructor/default constructor.
     * Set the date to the current year, month, and day.
     */
    public Date(){
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH) + 1;
        this.day = today.get(Calendar.DATE);
    }

    /**
     * Constructor with 3 parameters.
     * @param year the year of the date.
     * @param month the month of the date.
     * @param day the day of the date.
     */
    public Date(int month, int day, int year){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Compare to see if two dates are equal
     * @param obj the object to be compared to
     * @return return true if two date objects are equal; return false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Date){
            Date date = (Date) obj;
            return this.year == date.year
                    &&this.month == date.month
                    &&this.day == date.day;
        }
        return false;
    }

    /**
     * Returns a textual representation of the Date object.
     * @return a string containing the month/day/year.
     */
    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    /**
     * Compare two Date objects.
     * @param date the Date object being compared to.
     * @return return 1 if this date object is greater than "date" or after "date",
     * return -1 if smaller or before "date";
     * return 0 if they are equal.
     */
    @Override
    public int compareTo(Date date){
        if(this.year > date.year){
            return 1;
        } else if (this.year < date.year){
            return -1;
        }else{
            if(this.month > date.month){
                return 1;
            }else if(this.month < date.month){
                return -1;
            }else{
                if(this.day > date.day){
                    return 1;
                }else if(this.day < date.day){
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     * Get the year of the date.
     * @return return year of the date.
     */
    public int getYear(){
        return year;
    }

    /**
     * Get the month of the date.
     * @return retyrn the month of the date.
     */
    public int getMonth(){
        return month;
    }

    /**
     * Get the day of the date.
     * @return the day of the date.
     */
    public int getDay(){
        return day;
    }

    /**
     * Set the year of the date to the given input.
     * @param year the year to set the date to.
     */
    public void setYear(int year){
        this.year = year;
    }

    /**
     * Set the month of the date to the given input.
     * @param month the month to set the date to.
     */
    public void setMonth(int month){
        this.month = month;
    }

    /**
     * Set the day of the date to the given input.
     * @param day the day to set the date to.
     */
    public void setDay(int day){
        this.day = day;
    }

    /**
     * Testing if the date is a valid calendar date.
     * @return return true if a valid calendar date and after 1900;
     * return false if not a valid calendar date and before 1900.
     */
    public boolean isValid(){
        if(year < 1900){
            return false;
        }
        int whatMonth = isWhatMonth(month);
        if(whatMonth == -1){
            return false;
        }
        if(whatMonth == 2) return day <= 31 && day > 0;
        if(whatMonth == 1) return day <= 30 && day > 0;
        if(whatMonth == 0 &&isLeapYear(year)){
            return day <= 29 && day > 0;
        }else{
            return day <= 28 && day > 0;
        }
    }

    /**
     * Private helper method, to see how many days the month have.
     * @param month the month which you are checking
     * @return 2 if the month have 31 days;
     * return 1 if the month have 30 days
     * return 0 if the month is February
     * return -1 if invalid month.
     */
    private int isWhatMonth(int month){
        int[] month_31Days = {1,3,5,7,8,10,12};
        int[] month_30Days = {4,6,9,11};
        if(month == 2){
            return 0;
        }
        for (int month30Day : month_30Days) {
            if (month == month30Day) {
                return 1;
            }
        }
        for (int month31Day : month_31Days){
            if (month == month31Day){
                return 2;
            }
        }
        return -1;
    }

    /**
     * Private helper method, checking if the year is a leap year.
     * @param year the year which you are checking
     * @return true if it is a leap year;
     * return false if it is not a leap year.
     */
    private boolean isLeapYear(int year){
        if(year % QUADRENNIAL == 0){
            if(year % CENTENNIAL == 0){
                return year % QUATERCENTENNIAL == 0;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    /**
     * Testbed main() is a driver to test the code within this class, specifically,
     * test the public method
     * @param args command line arguments
     */
    public static void main(String[] args){
        /**
         * testDaysInFeb_NonLeap(); testDaysInFeb_Leap(); testMonth_OutOfRange();
         * testDay_OutOfRange();
         * 4 invalid: day,month out of range. Day exceed non leap and leap year in feb
         * 2 valid: First of janurary, Last day of december
         */
    }
}



