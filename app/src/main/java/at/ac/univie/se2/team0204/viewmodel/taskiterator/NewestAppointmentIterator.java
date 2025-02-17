package at.ac.univie.se2.team0204.viewmodel.taskiterator;

import java.util.Collections;
import java.util.List;

import at.ac.univie.se2.team0204.exceptions.AppointmentIteratorException;
import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.view.AppointmentCreationActivity;

public class NewestAppointmentIterator implements AppointmentIterator {

    List<Appointment> appointments;
    int index;

    public NewestAppointmentIterator(List<Appointment> appointmentList){

        appointments=appointmentList;
        index=-1;

        Collections.sort(appointments,(app1,app2)->{
            int result=compareDateToInt(app1.getDate(),app2.getDate());

            return result;
        });

    }

    @Override
    public Appointment nextAppointment() {
        index++;
        if(index+1>appointments.size())
            throw new AppointmentIteratorException("No Appointment left",String.format("There exist only %d appointments. Index now at %d",appointments.size(),index));

        return appointments.get(index);
    }

    @Override
    public Appointment currentAppointment() {

        if(appointments.isEmpty())
            throw new AppointmentIteratorException("NoAppointmentsExist","No appointment exists at the moment");
        return appointments.get(index);
    }

    @Override
    public boolean hasNext() {
        return index+1<appointments.size();
    }

    /**
     *
     * @param date1
     * @param date2
     * @return negative number if date1 is earlier, positive number if date2 is earlier, 0 if same date.
     */
    private int compareDateToInt(String date1,String date2){

        int year1=0, year2=0, month1=0, month2=0, day1=0, day2=0;

        String[] values1=date1.split("\\.");
        try {
            year1 = Integer.valueOf(values1[2]);
            month1 = Integer.valueOf(values1[1]);
            day1 = Integer.valueOf(values1[0]);
        }
        catch (Exception e){
            return 1;
        }

        String[] values2 = date2.split("\\.");

        try {

            year2 = Integer.valueOf(values2[2]);
            month2 = Integer.valueOf(values2[1]);
            day2 = Integer.valueOf(values2[0]);
        }
        catch (Exception e){
            return -1;
        }

        if(year1!=year2)
            return year1-year2;
        if(month1!=month2)
            return month1-month2;
        if(day1!=day2)
            return day1-day2;

        return 0;
    }
}
