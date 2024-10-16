package clinic;

public class Person {
    protected Profile profile;


    public Person(){
        profile = new Profile();
    }

    public Person(Profile profile){
        this.profile = profile;
    }

    public Person(String fname, String lname, String dob){
        this.profile = new Profile(fname,lname,dob);
    }

    public Profile getProfile(){
        return profile;
    }

    public int compareTo(Person person) {
        return profile.compareTo(person.getProfile());
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Person){
            Person person = (Person) obj;
            return this.profile.equals(person.profile);
        }
        return false;
    }
}
