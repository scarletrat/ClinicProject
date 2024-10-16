package clinic;

public class Technician extends Provider{
    private int ratePerVisit;

    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
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

    @Override
    public String toString(){return super.toString() + "[rate: $" + ratePerVisit + ".00]";
    }
}
