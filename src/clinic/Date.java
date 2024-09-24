package clinic;
import java.util.Calendar;

/**
 * This class models the date of the real world.
 * It will account for leap year. It implements Java Interface Comparable.
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
     * Testbed main() is a driver to test the code within this class, specifically,
     * test the public method
     * @param args command line arguments
     */
    public static void main(String[] args){
        Date one = new Date();
        Date two = new Date(9,23,2025);
        System.out.println(one.equals(two));
        System.out.println(one.compareTo(two));
        System.out.println(one);
    }
}



