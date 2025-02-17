package at.ac.univie.se2.team0204.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.Task;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;

public class TaskViewActivity extends AppCompatActivity {

    /**
     * Fills the TaskViewActivity with values.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        // Get information passed by previous activity.
        Bundle intentBundle = getIntent().getExtras();
        int taskId = intentBundle.getInt("taskId");
        String taskType = intentBundle.getString("taskType");

        // Get task information.
        TaskVM taskVM = new ViewModelProvider(this).get(TaskVM.class);
        Task task;
        if(taskType.equals("Appointment")) {
            task = taskVM.getAppointment(taskId);
        } else {
            task = taskVM.getTodo(taskId);
        }

        // Write data to the screen.
        TextView title = findViewById(R.id.titleTextView);
        title.setText(task.getTitle());

        TextView desc = findViewById(R.id.descriptionTextView);
        desc.setText(task.getDescription());

        if(taskType.equals("Appointment")) {
            Appointment castedTask = (Appointment)task;

            TextView date = findViewById(R.id.dateTextView);
            String dateText = "Deadline: " + castedTask.getDate();
            date.setText(dateText);

            TextView place = findViewById(R.id.placeTextView);
            String placeText = "Place: " + castedTask.getPlace();
            place.setText(placeText);
        }
    }
}