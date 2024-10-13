package clinic;

public class Technician extends Provider{
    private int ratePerVisit;

    public Technician(Profile profile, Location location) {
        super(profile, location);
    }

    public void setRatePerVisit(int ratePerVisit){
        this.ratePerVisit = ratePerVisit;
    }

    @Override
    public int rate() {
        return ratePerVisit;
    }

    @Override
    public boolean equals(Object obj){
        if(!super.equals(obj)) return false;
        if(obj instanceof Technician){
            Technician technician = (Technician) obj;
            return this.ratePerVisit == technician.ratePerVisit;
        }
        return false;
    }
}
