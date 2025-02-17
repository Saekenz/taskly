package at.ac.univie.se2.team0204.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.viewmodel.CalendarRepresentationVM;
import at.ac.univie.se2.team0204.viewmodel.ListRepresentationVM;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;
import at.ac.univie.se2.team0204.viewmodel.listview.ItemMoveCallback;
import at.ac.univie.se2.team0204.viewmodel.listview.TaskListAdapter;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.AppointmentIterator;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.AppointmentIteratorMode;

public class CalendarViewActivity extends AppCompatActivity {

    private TaskVM taskVM;
    private CalendarRepresentationVM calendarVM;
    private AppointmentIteratorMode mode=AppointmentIteratorMode.NEWEST_FIRST;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        //Set AppointmentIteratorMode
        //TODO

        this.linearLayout=findViewById(R.id.linearLayoutCalendar);

        this.taskVM = new ViewModelProvider(this).get(TaskVM.class);
        this.calendarVM=new ViewModelProvider(this).get(CalendarRepresentationVM.class);


        taskVM.getAppointmentLiveData().observe(this, notes -> {
            //called every time data changes
            calendarVM.init(mode,notes);
            AppointmentIterator iterator=calendarVM.getAppointmentIterator();

            TextView title=new TextView(this);
            title.setText("Ordered chronologically:");
            title.setTextSize(30);
            linearLayout.addView(title);

            while(iterator.hasNext()){
                Appointment appointment=iterator.nextAppointment();

                TextView view=new TextView(this);
                view.setText(String.format("%s [on %s]",appointment.getTitle(),appointment.getDate()));
                linearLayout.addView(view);
            }


        });
    }
}