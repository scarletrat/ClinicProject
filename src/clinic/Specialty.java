package clinic;

/**
 * This class defines the specialty offered and the charge per service.
 * It is an enum class and so far have only 3 specialty offered.
 * @author Gordon Lin, modified 9/28/2024
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    public static Specialty getSpecialty(String specialty){
        if(specialty.equalsIgnoreCase("FAMILY")){
            return FAMILY;
        } else if(specialty.equalsIgnoreCase("PEDIATRICIAN")){
            return PEDIATRICIAN;
        } else if (specialty.equalsIgnoreCase("ALLERGIST")) {
            return ALLERGIST;
        }else{
            return null;
        }
    }
    private final int charge;

    Specialty(int charge){
        this.charge = charge;
    }

    public int getCharge(){
        return charge;
    }

    /**
     * Return a textual representation of Specialty.
     * @return return the name.
     */
    @Override
    public String toString(){
        return name();
    }
}
