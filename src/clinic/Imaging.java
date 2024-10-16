package clinic;

import util.Date;

public class Imaging extends Appointment{
    private Radiology room;
    public Imaging(Date date, Timeslot timeslot, Person profile, String room){
        super(date, timeslot, profile);
        this.room = new Radiology(room);
    }
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
