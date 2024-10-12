package clinic;

public class Doctor extends Provider{
    private Specialty specialty;
    private String npi;

    @Override
    public int rate() {
        return 0;
    }
}
