package at.ac.univie.se2.team0204.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.taskranking.TaskRankingDatabase;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.AppointmentIteratable;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.AppointmentIterator;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.AppointmentIteratorMode;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.NewestAppointmentIterator;

public class CalendarRepresentationVM extends AndroidViewModel implements AppointmentIteratable{

    private List<Appointment> appointmentList;
    private AppointmentIterator appointmentIterator;

    public CalendarRepresentationVM(@NonNull Application application) {
        super(application);
        TaskRankingDatabase database = TaskRankingDatabase.getRoomDatabase(application);


        appointmentList=null;
    }

    public void init(AppointmentIteratorMode mode, List<Appointment> list){
        this.appointmentList=list;

        switch (mode){
            case NEWEST_FIRST: {
                appointmentIterator = new NewestAppointmentIterator(appointmentList);
                break;
            }
        }


    }

    @Override
    public AppointmentIterator getAppointmentIterator() {
        return appointmentIterator;
    }
}
