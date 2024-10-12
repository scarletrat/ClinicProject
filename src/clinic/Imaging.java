package clinic;

public class Imaging extends Appointment{
    private Radiology room;

    @Override
    public boolean equals(Object obj){
        if (!super.equals(obj)) return false;
        if( obj instanceof  Imaging){
            Imaging imaging = (Imaging) obj;
            return this.room.equals(imaging.room);
        }
        return false;
    }
}
