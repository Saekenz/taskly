package at.ac.univie.se2.team0204.viewmodel.convert;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;

import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.Task;
import at.ac.univie.se2.team0204.viewmodel.TaskImportIterator;

public class JSONConverter implements IConverter {

    /**
     * Key required by the JSON file to work
     */
    private static final String JSON_ARRAY_KEY = "tasks";

    /**
     * Tag used by the logger to identify this class
     */
    private static final String TAG = "JSONConverter";

    /**
     * This object helps with converting strings from and to objects.
     */
    Gson gson = new Gson();

    /**
     * Converts a JSON String to a an iterator of Map<String, String> objects. These objects contain a task each.
     * <p>
     *     The application assumes the file has the following structure: It must an object with the key "tasks", which
     *     holds an array of tasks to be imported. Each task needs the fields "taskType", "title", "description", "status",
     *     "priority" and "deadline".
     * </p>
     * @param rawTask The raw string of all tasks that need importing.
     * @return An {@link TaskImportIterator} object holding all converted tasks.
     */
    @Override
    public TaskImportIterator convertToIterator(String rawTask) throws Exception {

        Log.i(TAG, "Converting JSON file to Iterator");
        // CODE SNIPPET INFLUENCED BY DOCUMENTATION
        // SRC https://github.com/google/gson/blob/master/UserGuide.md#maps-examples
        TypeToken<Map<String, ArrayList<Map<String, String>>>> map = new TypeToken<Map<String, ArrayList<Map<String, String>>>>(){};
        Map<String, ArrayList<Map<String, String>>> tasks = gson.fromJson(rawTask, map);
        // CODE END

        if(tasks.get(JSON_ARRAY_KEY) == null) {
            throw new Exception("JSON File does not have the right structure.");
        }

        return new TaskImportIterator(tasks.get(JSON_ARRAY_KEY));
    }

    /**
     * Converts a {@link Task} object into a JSON string.
     *
     * @param task A {@link Task} object that should be converted to a string.
     * @return A string representing the task in JSON.
     */
    @Override
    public String convertToString(Task task) {
        Log.i(TAG, "Converting object to JSON string");

        StringBuilder jsonTask = new StringBuilder("{\"tasks\": [");
        String convertedTask = gson.toJson(task);

        // Some small hack to insert the type
        convertedTask = convertedTask.substring(1);
        if(task instanceof Appointment) {
            jsonTask.append("{\"taskType\":\"Appointment\",");
        } else {
            jsonTask.append("{\"taskType\":\"ToDo\",");
        }

        // This is a mock for now, since resp 1 is not done yet.
        jsonTask.append("\"priority\":2,");
        jsonTask.append("\"status\":\"Before\",");

        jsonTask.append(convertedTask);
        jsonTask.append("]}");

        return  jsonTask.toString();
    }
}
