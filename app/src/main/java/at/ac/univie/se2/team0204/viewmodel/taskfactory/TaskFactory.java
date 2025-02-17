package at.ac.univie.se2.team0204.viewmodel.taskfactory;

import java.util.Map;

import at.ac.univie.se2.team0204.model.room.Task;

public interface TaskFactory {

    /**
     * Creates a new {@link Task} from the imported map.
     * @param rawTask The {@link Task} in the form of a map. Each key is an attribute and each value is the value of the attribute.
     * @return The newly created {@link Task}.
     */
    Task createTask(Map<String, String> rawTask);
}
