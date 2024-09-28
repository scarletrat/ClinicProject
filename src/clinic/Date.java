package clinic;
import java.util.Calendar;

/**
 * This class models the date of the real world.
 * It will account for leap year. It implements Java Comparable Interface.
 * And uses the Java Calendar class to get today's date
 * @author Gordon Lin, modified Sept. 27, 2024.
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
     * Set the date to the current month, day, and year.
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
     * Constructor with a string input format mm/dd/yy
     * @param date the date input mm/dd/yyyy
     */
    public Date(String date){
        String[] datePart = date.split("/");
        this.year = Integer.parseInt(datePart[2]);
        this.day = Integer.parseInt(datePart[1]);
        this.month = Integer.parseInt(datePart[0]);
    }

    /**
     * Compare to see if two dates are equal.
     * @param obj the object to be compared to.
     * @return return true if two date objects are equal; return false otherwise.
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
     * @return return 1 if this date object is greater than "date" or after "date";
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

    public String getDate(){
        // return mm/dd/yyyy format
        String month = Integer.toString(this.getMonth());
        String day = Integer.toString(this.getDay());
        String year = Integer.toString(this.getYear());
        return(month +"/" + day +"/" + year);
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
     * Checking if the current date is a weekday or not.
     * @return return true if it is a weekday;
     * return 0 otherwise.
     */
    public boolean isWeekDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY;

    }

    /**
     * Check if the date is in the past.
     * @return return true if the date is in the past;
     * return false otherwise
     */
    public boolean isPast(){
        Calendar today = Calendar.getInstance();
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH) + 1;
        int todayDay = today.get(Calendar.DATE);
        if(year <= todayYear)
            if(month <= todayMonth)
                return day < todayDay;
        return false;
    }

    /**
     * Checking if the date is today.
     * @return return true if it is today;
     * return false otherwise.
     */
    public boolean isToday(){
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;
        int day = today.get(Calendar.DATE);
        return this.year == year && this.month == month && this.day == day;
    }

    /**
     * Checking if the date is in the future.
     * @return return true if the date is in the future;
     * return false otherwise.
     */
    public boolean isFuture(){
        Calendar today = Calendar.getInstance();
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH) + 1;
        int todayDay = today.get(Calendar.DATE);
        if(year >= todayYear)
            if(month >= todayMonth)
                return day > todayDay;
        return false;
    }

    /**
     *Check if the date is within 6 months from today. Don't take date in the past.
     * @return return true if the date is within 6 months from today;
     * return false otherwise including date in the past.
     */
    public boolean within6MonthFromToday(){
        Calendar sixMonth = Calendar.getInstance();
        sixMonth.add(Calendar.MONTH,6);
        Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR,year);
        today.set(Calendar.MONTH,month-1);
        today.set(Calendar.DAY_OF_MONTH,day);
        if(isPast()){
            return false;
        }
        int sixMonthFromToday = today.compareTo(sixMonth);
        return sixMonthFromToday <= 0;
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
        testDaysInFeb_NonLeap();
        testDaysInFeb_Leap();
        test30DayMonth_OutOfRange();
        testMonth_OutOfRange();
        test31DayMonth_InRange();
        testValidFeb_Leap();
    }

    /** Test Case #1 */
    private static void testDaysInFeb_NonLeap(){
        Date date = new Date("2/29/2018");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #1: # of days in Feb. in a non-leap year is 28");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test Case #2 */
    private static void testDaysInFeb_Leap(){
        Date date = new Date("2/30/2020");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #2: # of days in Feb. in a leap year is 29");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test Case #3 */
    private static void test30DayMonth_OutOfRange(){
        Date date = new Date("4/31/2020");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #3: # of days in 30 Day Month is 1 to 30");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test Case #4 */
    private static void testMonth_OutOfRange(){
        Date date = new Date("0/20/2010");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #4: Range of months are from 1 to 12");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test Case #5 */
    private static void test31DayMonth_InRange(){
        Date date = new Date("12/31/2000");
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #5: # of days in 31 Day Month is 1 to 31");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Test Case #6 */
    private static void testValidFeb_Leap(){
        Date date = new Date("2/29/2020");
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #6: # of days in Feb. in a leap year is 29");
        testResult(date, expectedOutput, actualOutput);
    }

    /** Check if a given test case PASS or FAIL.
     *Print out FAIL if the output does not match
     * Print out PASS otherwise.
     */
    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput){
        System.out.println("Test input: " + date.toString());
        System.out.println("Expected output: " + expectedOutput);
        System.out.println("Actual output: " + actualOutput);
        if(expectedOutput != actualOutput){
            System.out.println(" (FAIL) ");
        }else{
            System.out.println(" (PASS) ");
        }
    }
}



