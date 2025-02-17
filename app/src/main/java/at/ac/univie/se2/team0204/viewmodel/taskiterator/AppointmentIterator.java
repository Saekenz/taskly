package at.ac.univie.se2.team0204.viewmodel.taskiterator;

import at.ac.univie.se2.team0204.model.room.Appointment;

public interface AppointmentIterator {


    public abstract Appointment nextAppointment();

    public abstract Appointment currentAppointment();

    public abstract boolean hasNext();

}
