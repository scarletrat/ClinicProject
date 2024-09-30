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

    private final int charge;

    private Specialty(int charge){
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
