package clinic;

public class Radiology {

    public enum Service{
        CATSCAN, ULTRASOUND, XRAY;
    }

    private Service service;

    public Radiology(Service service){
        this.service = service;
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
