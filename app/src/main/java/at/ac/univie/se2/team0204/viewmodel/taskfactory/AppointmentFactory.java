package at.ac.univie.se2.team0204.viewmodel.taskfactory;

import android.util.Log;

import java.util.Map;
import java.util.Objects;

import at.ac.univie.se2.team0204.model.room.Appointment;

public class AppointmentFactory implements TaskFactory {

    /**
     * Tag used by the logger to identify this class.
     */
    private static final String TAG = "AppointmentFactory";

    /**
     * Creates an {@link Appointment}.
     * <p>
     *     If the raw task has an id, a new {@link Appointment} is created using this id. Else the id is auto generated and returned.
     * </p>
     * @param rawTask A map containing the values of the newly created {@link Appointment}.
     * @return The created {@link Appointment}
     */
    @Override
    public Appointment createTask(Map<String, String> rawTask) {

        Log.i(TAG, "Creating appointment task");

        Appointment newTask;

        // If the imported task contains an id, make sure it is added to the task.
        if(rawTask.containsKey("taskId")) {
            newTask = new Appointment(Integer.parseInt(Objects.requireNonNull(rawTask.get("taskId"))),
                    rawTask.get("title"),
                    rawTask.get("description"),
                    "Somewhere",
                    rawTask.get("date"));
        } else {
            newTask = new Appointment(rawTask.get("title"), rawTask.get("description"), "Somewhere", rawTask.get("date"));
        }

        Log.i(TAG, "Task created");

        return newTask;
    }
}
