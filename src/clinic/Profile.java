package clinic;

/**
 * This class models a patient's profile.
 * It will include their first and last name and date of birth.
 * It implements Java Comparable Interface.
 * @author Gordon Lin modified Sept. 27, 2024.
 */
public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    /**
     * A default/no-argument constructor
     * Set the profile first and last name to "default" and dob to today.
     */
    public Profile(){
        this.fname = "default";
        this.lname = "default";
        this.dob = new Date();
    }

    /**
     * Constructor with 3 parameters.
     * @param fname the first name of the profile.
     * @param lname the last name of the profile.
     * @param dob the date of birth of the profile.
     */
    public Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Constructor with 3 parameters.
     * @param fname the first name of the profile.
     * @param lname the last name of the profile.
     * @param dob the date of birth of the profile in String.
     */
    public Profile(String fname, String lname, String dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = new Date(dob);
    }

    /**
     * Compare to see if two profile are equal.
     * @param obj the object to be compared to.
     * @return return true if two profile objects are equal; return false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Profile){
            Profile profile = (Profile) obj;
            return this.fname.equals(profile.fname)
                    &&this.lname.equals(profile.lname)
                    &&this.dob.equals(profile.dob);
        }
        return false;
    }

    /**
     * A textual representation of the Profile object.
     * @return return a string containing first nam, last name, and date of birth.
     */
    @Override
    public String toString(){
        return(fname + " " + lname + " " + dob);
    }

    /**
     * Compare two Profile objects.
     * @param profile the object to be compared.
     * @return return 1 if either lname, fname, dob in this order of this profile is after input "profile" dob;
     * return -1 if either lname, fname, dob in this order of this profile is before input "profile" dob;
     * return 0 if equal.
     */
    @Override
    public int compareTo(Profile profile){
        if(this.lname.compareTo(profile.lname) != 0){
            if(this.lname.compareTo(profile.lname)<0)
                return -1;
            else{
                return 1;
            }
        }
        if(this.fname.compareTo(profile.fname) != 0){
            if(this.fname.compareTo(profile.fname)<0)
                return -1;
            else{
                return 1;
            }
        }
        if(this.dob.compareTo(profile.dob) != 0){
            return this.dob.compareTo(profile.dob);
        }
        return 0;
    }

    /**
     * Get the first name of the profile.
     * @return return the first name of the profile.
     */
    public String getFname(){ return fname;}

    /**
     * Get the last name of the profile.
     * @return return the last name of the profile.
     */
    public String getLname(){ return lname;}

    /**
     * Get the date of birth of the profile in String.
     * @return return the date of birth of the profile in string.
     */
    public String getDob_inString(){ return dob.getDate();}

    /**
     * Get the date of birth of the profile in Date object.
     * @return return the date of birth of the profile in Date object.
     */
    public Date getDob_inDate(){ return dob;}

    /**
     * Set the profile's first name to the given string.
     * @param fname the new first name of the profile.
     */
    public void setFname(String fname){ this.fname = fname;}

    /**
     * Set the profile's last name to the given string.
     * @param lname the new last name of the profile.
     */
    public void setLname(String lname){ this.lname = lname;}

    /**
     * Set the profile's date of birth to the given date Object.
     * @param dob the new date of birth of the profile.
     */
    public void setDob_inDate(Date dob){ this.dob = dob;}

    /**
     * Set the profile's date of birth to the given String.
     * @param dob the new date of birth of the profile in String.
     */
    public void setDob_inString(String dob){
        this.dob = new Date(dob);
    }

    /**
     * Testbed main() is a driver to test the code within this class, specifically,
     * test the public method.
     * @param args command line arguments.
     */
    public static void main(String[] args){
        testInput_SmallLastName();
        testInput_SmallFirstName();
        testInput_SmallDob();
        testInput_BigLastName();
        testInput_BigFirstName();
        testInput_BigDob();
        testInput_SameProfile();
    }

    /** Test Case #1 */
    private static void testInput_SmallLastName(){
        Profile profile = new Profile("Carl", "Brook", "2/20/2000");
        Profile compare = new Profile("Roy", "Brown", "2/20/2000");
        int expectedOutput = -1;
        int actualOutput = profile.compareTo(compare);
        System.out.println("**Test case #1: Last name of profile should be compared first");
        testResult(profile,compare,expectedOutput,actualOutput);
    }

    /** Test case #2 */
    private static void testInput_SmallFirstName(){
        Profile profile = new Profile("Carl", "Brook", "10/06/2004");
        Profile compare = new Profile("Clark", "Brook", "1/1/2000");
        int expectedOutput = -1;
        int actualOutput = profile.compareTo(compare);
        System.out.println("**Test case #2: First name of profile is compared before date of birth");
        testResult(profile,compare,expectedOutput,actualOutput);
    }

    /** Test case #3 */
    private static void testInput_SmallDob(){
        Profile profile = new Profile("Carl", "Brook", "10/06/2004");
        Profile compare = new Profile("Carl", "Brook", "1/1/2008");
        int expectedOutput = -1;
        int actualOutput = profile.compareTo(compare);
        System.out.println("**Test case #3: Date of birth of profile is compared");
        testResult(profile,compare,expectedOutput,actualOutput);
    }

    /** Test case #4 */
    private static void testInput_BigLastName(){
        Profile compare = new Profile("Carl", "Brook", "10/06/2004");
        Profile profile = new Profile("Clark", "Brook", "1/1/2000");
        int expectedOutput = 1;
        int actualOutput = profile.compareTo(compare);
        System.out.println("**Test case #4: First name of profile is compared before date of birth");
        testResult(profile,compare,expectedOutput,actualOutput);
    }

    /** Test case #5 */
    private static void testInput_BigFirstName(){
        Profile compare = new Profile("Carl", "Brook", "10/06/2004");
        Profile profile = new Profile("Clark", "Brook", "1/1/2000");
        int expectedOutput = 1;
        int actualOutput = profile.compareTo(compare);
        System.out.println("**Test case #5: First name of profile is compared before date of birth");
        testResult(profile,compare,expectedOutput,actualOutput);
    }

    /** Test case #6 */
    private static void testInput_BigDob(){
        Profile compare = new Profile("Carl", "Brook", "10/06/2004");
        Profile profile = new Profile("Carl", "Brook", "1/1/2008");
        int expectedOutput = 1;
        int actualOutput = profile.compareTo(compare);
        System.out.println("**Test case #6: Date of birth of profile is compared");
        testResult(profile,compare,expectedOutput,actualOutput);
    }

    /** Test case #7 */
    private static void testInput_SameProfile(){
        Profile profile = new Profile("Carl", "Brook", "9/8/2022");
        Profile compare = new Profile("Carl", "Brook", "9/8/2022");
        int expectedOutput = 0;
        int actualOutput = profile.compareTo(compare);
        System.out.println("**Test case #7: Same profile is being compared");
        testResult(profile,compare,expectedOutput,actualOutput);
    }

    /** Check if a given test case and compareTo method works PASS or FAIL.
     * Print out FAIL if the output does not match.
     * Print out PASS otherwise.
     * @param profile the date being tested.
     * @param actualOutput the actual output of the test/isValid method.
     * @param expectedOutput the expected output of the test.
     */
    private static void testResult(Profile profile,Profile compare, int expectedOutput, int actualOutput){
        System.out.println("Test input: " + profile.toString());
        System.out.println("Test to be compared to: " + compare.toString());
        System.out.println("Expected output: " + expectedOutput);
        System.out.println("Actual output: " + actualOutput);
        if(expectedOutput != actualOutput){
            System.out.println(" (FAIL) ");
        }else{
            System.out.println(" (PASS) ");
        }
    }
}
