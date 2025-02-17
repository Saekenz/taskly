package at.ac.univie.se2.team0204.viewmodel.taskfactory;


import android.util.Log;

import java.util.Map;
import java.util.Objects;

import at.ac.univie.se2.team0204.model.room.Todo;

public class TodoFactory implements TaskFactory {

    /**
     * Tag used by the logger to identify this class.
     */
    private static final String TAG = "TodoFactory";

    /**
     * Creates a {@link Todo}.
     * <p>
     *     If the raw {@link Todo} has an id, a new task is created using this id. Else the id is auto generated and returned.
     * </p>
     * @param rawTask A map containing the values of the newly created {@link Todo}.
     * @return The created {@link Todo}
     */
    @Override
    public Todo createTask(Map<String, String> rawTask) {

        Log.i(TAG, "Creating new Task");

        Todo newTask;
        // Null checking not required, as this is done in a previous step
        // If the id exists, make sure to create the task using it.
        if(rawTask.containsKey("taskId")) {
            newTask = new Todo(Integer.parseInt(Objects.requireNonNull(rawTask.get("taskId"))), rawTask.get("title"), rawTask.get("description"));
        } else {
            newTask = new Todo(rawTask.get("title"), rawTask.get("description"));
        }

        Log.i(TAG, "Task created");
        return newTask;
    }
}
