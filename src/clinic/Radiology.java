package clinic;

public class Radiology {

    public enum Service{
        CATSCAN, ULTRASOUND, XRAY;
    }

    private Service service;

    public Radiology(String service) {
        if (service.equalsIgnoreCase("xray")) {
            this.service = Service.XRAY;
        } else if (service.equalsIgnoreCase("ultrasound")) {
            this.service = Service.ULTRASOUND;
        } else if (service.equalsIgnoreCase("catscan")) {
            this.service = Service.CATSCAN;
        }
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Radiology) {
            Radiology radiology = (Radiology) obj; // Cast to Radiology
            // Compare the service types
            return this.service == radiology.service; // Compare enum values
        }
        return false;
    }
}
